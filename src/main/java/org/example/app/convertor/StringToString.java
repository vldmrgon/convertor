package org.example.app.convertor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StringToString implements IConvertor<String, String> {
    @Override
    public String convert(String s) {
        log.info("Work method transform, String to String");
        return s;
    }
}
