package supportemail.qualityunit.com.util.parser;

import supportemail.qualityunit.com.entity.data.*;
import supportemail.qualityunit.com.entity.data.wrapper.Data;
import supportemail.qualityunit.com.entity.linetype.LineType;
import supportemail.qualityunit.com.entity.linetype.impl.C;
import supportemail.qualityunit.com.entity.linetype.impl.D;
import supportemail.qualityunit.com.util.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

    private Reader reader;

    public Parser(Reader reader) {
        this.reader = reader;
    }

    public Data parseFileData(String pathToFile) throws IOException, ParseException {
        Data data = new Data();
        int linesCount = -1;
        linesCount = reader.readLinesCount(pathToFile);
        data.setLinesCount(linesCount);
        Map<D, List<C>> queriesAndItsWaitingTimeLines = new LinkedHashMap<>();
        List<C> waitingTimeLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            LineType lineType;
            while ((line = reader.readLine()) != null) {
                if (line.matches("\\d{1,6}")) {
                    continue;
                }
                String[] lineContains = line.split(" ");
                    Service service = parseServiceAndVariation(lineContains[1]);
                    QuestionType questionType = parseQuestionTypeAndCategoryAndSubcategory(lineContains[2]);
                    ResponseType responseType = parseResponseType(lineContains[3]);
                    if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.C) {
                        C c = new C();
                        c.setService(service);
                        c.setQuestionType(questionType);
                        c.setResponseType(responseType);
                        c.setDate(parseDate(lineContains[4]));
                        c.setWaitingTime(parseWaitingTime(lineContains[5]));
                        waitingTimeLines.add(c);
                    } else if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.D) {
                        D d = new D();
                        d.setService(service);
                        d.setQuestionType(questionType);
                        d.setResponseType(responseType);
                        d.setDatePeriod(parseDatePeriod(lineContains[4]));
                        queriesAndItsWaitingTimeLines.put(d, new ArrayList(waitingTimeLines));
                    }
//                }
            }
        }
        data.setQueriesAndItsWaitingTimeLines(queriesAndItsWaitingTimeLines);
        return data;
    }

    private DatePeriod parseDatePeriod(String datePeriodString) throws ParseException {
        String[] datePeriods = datePeriodString.split("\\-");
        String dateFrom;
        String dateTo;
        DatePeriod datePeriod;
        if (datePeriods.length == 1) {
            dateFrom = datePeriods[0];
            datePeriod = new DatePeriod(parseDate(dateFrom), null);
            return datePeriod;
        } else {
            dateFrom = datePeriods[0];
            dateTo = datePeriods[1];
        }
        return new DatePeriod(parseDate(dateFrom), parseDate(dateTo));
    }

    private int parseWaitingTime(String waitingTime) {
        return Integer.parseInt(waitingTime);
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }

    private ResponseType parseResponseType(String responseType) {
        return ResponseType.valueOf(responseType);
    }

    private Service parseServiceAndVariation(String lineContain) {
//        System.out.println(lineContain);
        String[] serviceContains = lineContain.split("\\.");
        Service service;
        String serviceId = serviceContains[0];
        if (serviceHasVariation(serviceContains)) {
            service = new Service(serviceId, createVariation(serviceContains[1]));
        } else {
            service = new Service(serviceId, null);
        }
        return service;
    }

    private QuestionType parseQuestionTypeAndCategoryAndSubcategory(String lineContain) {
        String[] questionTypeContains = lineContain.split("\\.");
        QuestionType questionType;
        String questionTypeId = parseQuestionTypeId(questionTypeContains[0]);
        if (categoryHasSubCategory(questionTypeContains)) {
            questionType = new QuestionType(questionTypeId, createCategory(questionTypeContains[1], questionTypeContains[2]));
        } else if (questionTypeHasCategory(questionTypeContains)) {
            questionType = new QuestionType(questionTypeId, createCategory(questionTypeContains[1]));
        } else {
            questionType = new QuestionType(questionTypeId);
        }
        return questionType;
    }

    private QuestionType.Category createCategory(String categoryId) {
        return new QuestionType.Category(categoryId, null);
    }

    private boolean questionTypeHasCategory(String[] questionTypeContains) {
        return questionTypeContains.length == 2;
    }

    private QuestionType.Category.SubCategory createSubCategory(String subCategoryId) {
        return new QuestionType.Category.SubCategory(subCategoryId);
    }

    private QuestionType.Category createCategory(String categoryId, String subCategoryId) {
        return new QuestionType.Category(categoryId, createSubCategory(subCategoryId));
    }

    private String parseQuestionTypeId(String questionTypeContain) {
        return questionTypeContain;
    }

    private boolean categoryHasSubCategory(String[] questionTypeContains) {
        return questionTypeContains.length > 2;
    }

    private boolean serviceHasVariation(String[] serviceContains) {
        return serviceContains.length == 2;
    }

    private Service.Variant createVariation(String variationId) {
        return new Service.Variant(variationId);
    }

    private int parseServiceId(String serviceId) {
        return Integer.parseInt(serviceId);
    }

}
