package microbenchmark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.example.app.factory.ObjectConvertor;
import org.example.app.convertor.IConvertor;

import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, time = 1)
@Fork(value = 2, jvmArgs = {"-Xms256m", "-Xmx256m"})
public class CheckConvertor {

    private final String[] PACKAGES_TO_SCAN = {"org.example.app"};
    private ObjectConvertor objectConvertor;

    @Param({"100"})
    private int value;

    @Setup
    public void initSpringContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PACKAGES_TO_SCAN);
        objectConvertor = context.getBean(ObjectConvertor.class);
    }

    @Benchmark
    public void checkConvertorIntegerToInteger(Blackhole blackhole) {
        IConvertor<Integer, Integer> convertor = objectConvertor.getConvertor(Integer.class, Integer.class);
        Integer convert = convertor.convert(value);
        blackhole.consume(value);
    }

    @Benchmark
    public void checkConvertorStringToInteger(Blackhole blackhole) {
        IConvertor<String, Integer> convertor = objectConvertor.getConvertor(String.class, Integer.class);
        Integer convert = convertor.convert(String.valueOf(value));
        blackhole.consume(value);
    }

    @Benchmark
    public void checkConvertorStringToString(Blackhole blackhole) {
        IConvertor<String, String> convertor = objectConvertor.getConvertor(String.class, String.class);
        convertor.convert(String.valueOf(value));
        blackhole.consume(value);
    }
}