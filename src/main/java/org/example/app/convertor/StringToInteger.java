package org.example.app.convertor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StringToInteger implements IConvertor<String, Integer> {
    @Override
    public Integer convert(String s) {
        log.info("Work method transform, String to Integer");
        return Integer.parseInt(s);
    }
}
