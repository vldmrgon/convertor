package microbenchmark;

public class JmhRunner {

    public static void main(String[] args) {
        JmhConfiguration.runMicrobenchmark(CheckConvertor.class);
    }
}