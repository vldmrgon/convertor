package org.example.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.example.app.factory.ConvertorFactory;
import org.example.app.convertor.IConvertor;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ConvertorFactory convertorFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        IConvertor<Integer, Integer> convertor1 = convertorFactory.getConvertor(Integer.class, Integer.class);
        Integer transform1 = convertor1.convert(1);

        IConvertor<String, Integer> convertor2 = convertorFactory.getConvertor(String.class, Integer.class);
        Integer transform2 = convertor2.convert("123");

        IConvertor<String, String> convertor3 = convertorFactory.getConvertor(String.class, String.class);
        String transform3 = convertor3.convert("My string");
    }
}