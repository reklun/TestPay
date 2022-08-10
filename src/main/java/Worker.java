import processor.MainProcessor;
import java.io.IOException;

public class Worker {

    public static void main(String[] args) throws IOException {
        MainProcessor main = new MainProcessor();
        main.process();
    }

}
