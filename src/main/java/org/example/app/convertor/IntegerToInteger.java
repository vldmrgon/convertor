package org.example.app.convertor;

import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class IntegerToInteger implements IConvertor<Integer, Integer> {
    @Override
    public Integer convert(Integer t) {
        log.info("Work method transform, Integer to Integer");
        return t;
    }
}
