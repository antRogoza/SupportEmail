package supportemail.qualityunit.com;

import supportemail.qualityunit.com.context.ContextInitializer;
import supportemail.qualityunit.com.entity.data.QuestionType;
import supportemail.qualityunit.com.entity.data.wrapper.Data;
import supportemail.qualityunit.com.exception.InvalidFileData;
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
        supportemail.qualityunit.com.entity.data.Service service1 = new supportemail.qualityunit.com.entity.data.Service("1", new supportemail.qualityunit.com.entity.data.Service.Variant("1"));
        supportemail.qualityunit.com.entity.data.Service service2 = new supportemail.qualityunit.com.entity.data.Service("1", new supportemail.qualityunit.com.entity.data.Service.Variant("1"));
        QuestionType questionType1 = new QuestionType("1",new QuestionType.Category(null, new QuestionType.Category.SubCategory("1")));
        QuestionType questionType2 = new QuestionType("1",new QuestionType.Category("1", new QuestionType.Category.SubCategory("1")));

        System.out.println(service1.equals(service2));
        System.out.println(questionType1.equals(questionType2));
        ///////////////////////////////////////////////////////////////////////////////////
        ContextInitializer contextInitializer = new ContextInitializer();
        contextInitializer.init();
    }

    public void process() throws IOException, ParseException {
        String pathToFile = reader.readPathToFile();
        Data data = parser.parseFileData(pathToFile);
        Service service = new Service();
        List<String> averageWaitings = service.averageWaitingTimes(data.getQueriesAndItsWaitingTimeLines());
        for(String averageWaiting : averageWaitings){
            Writer.write(averageWaiting);
        }
    }

}
