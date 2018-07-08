package supportemail.qualityunit.com;

import supportemail.qualityunit.com.context.ContextInitializer;
import supportemail.qualityunit.com.entity.data.wrapper.Data;
import supportemail.qualityunit.com.service.Service;
import supportemail.qualityunit.com.util.parser.Parser;
import supportemail.qualityunit.com.util.reader.Reader;
import supportemail.qualityunit.com.util.writer.Writer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class Main {

    private Parser parser;
    private Reader reader;

    public Main(Parser parser, Reader reader) {
        this.parser = parser;
        this.reader = reader;
    }

    public static void main(String[] args) throws IOException, ParseException {

        ContextInitializer contextInitializer = new ContextInitializer();
        contextInitializer.init();
    }

    public void process() throws IOException, ParseException {
        String pathToFile = reader.readPathToFile();
        Data data = parser.parseFileData(pathToFile);
        Service service = new Service();
        List<String> averageWaitings = service.averageWaitingTimes(data.getQueriesAndItsWaitingTimeLines());
        for (String averageWaiting : averageWaitings) {
            Writer.write(averageWaiting);
        }

    }

}
