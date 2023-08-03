package org.example.app.convertor;

public interface IConvertor<FROM, TO> {
    TO convert(FROM t);
}

