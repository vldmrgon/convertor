package org.example.app.factory;

import org.example.app.convertor.IConvertor;

public interface ConvertorFactory {

    <FROM, TO> IConvertor<FROM, TO> getConvertor(Class<FROM> from, Class<TO> to);
}
