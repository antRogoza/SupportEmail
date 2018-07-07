package supportemail.qualityunit.com.context;

import supportemail.qualityunit.com.Main;
import supportemail.qualityunit.com.util.parser.Parser;
import supportemail.qualityunit.com.util.reader.Reader;
import supportemail.qualityunit.com.util.writer.Writer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ContextInitializer {

    public void init() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Writer writer = new Writer();
        Reader reader = new Reader(scanner, writer);
        Parser parser = new Parser(reader);

        Main main = new Main(parser, reader);
        main.process();


    }

}
