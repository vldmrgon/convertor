package org.example.app.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

import org.example.app.convertor.IConvertor;

import java.lang.reflect.ParameterizedType;

import java.util.Optional;
import java.util.List;
import java.util.Map;

@Component
public class ObjectConvertor implements ConvertorFactory {

    private final Map<String, IConvertor<?, ?>> cache = new ConcurrentHashMap<>();

    @Autowired
    private List<IConvertor<?, ?>> convertors;

    @Override
    @SuppressWarnings("unchecked")
    public <FROM, TO> IConvertor<FROM, TO> getConvertor(Class<FROM> fromClass, Class<TO> toClass) {

        String key = getKey(fromClass, toClass);

        if (cache.containsKey(key)) {
            return (IConvertor<FROM, TO>) cache.get(key);
        }

        Optional<? extends IConvertor<?, ?>> iConvertor = Optional.of(convertors.stream()
                .parallel()
                .filter(transformer -> isMatchingClass(transformer, 0, fromClass) && isMatchingClass(transformer, 1, toClass))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Convertor %sTo%s doesn't exist", fromClass.getSimpleName(), toClass.getSimpleName()))
                )
        );

        IConvertor<FROM, TO> convertor = iConvertor.map(transformer -> (IConvertor<FROM, TO>) transformer).get();

        cache.putIfAbsent(key, convertor);

        return convertor;
    }

    private boolean isMatchingClass(IConvertor<?, ?> transformer, int index, Class<?> clazz) {
        ParameterizedType type = (ParameterizedType) transformer.getClass().getGenericInterfaces()[0];
        Class<?> converterClass = (Class<?>) type.getActualTypeArguments()[index];
        return converterClass.equals(clazz);
    }

    private <FROM, TO> String getKey(Class<FROM> fromClass, Class<TO> toClass) {
        return fromClass.getName() + ":" + toClass.getName();
    }
}
