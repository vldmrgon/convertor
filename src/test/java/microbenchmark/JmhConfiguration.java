package microbenchmark;

import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.Runner;

import lombok.experimental.UtilityClass;
import lombok.SneakyThrows;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class JmhConfiguration {

    @SneakyThrows
    public void runMicrobenchmark(List<Class<?>> classes) {
        new Runner(new OptionsBuilder().include(getBenchmarkNames(classes)).build()).run();
    }

    public void runMicrobenchmark(Class<?> clazz) {
        runMicrobenchmark(Collections.singletonList(clazz));
    }

    private String getBenchmarkNames(List<Class<?>> benchmarkClasses) {
        return benchmarkClasses.stream().map(Class::getSimpleName).collect(Collectors.joining("|"));
    }
}