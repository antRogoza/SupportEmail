package supportemail.qualityunit.com.util.parser;

import supportemail.qualityunit.com.entity.data.*;
import supportemail.qualityunit.com.entity.data.wrapper.Data;
import supportemail.qualityunit.com.entity.linetype.LineType;
import supportemail.qualityunit.com.entity.linetype.impl.C;
import supportemail.qualityunit.com.entity.linetype.impl.D;
import supportemail.qualityunit.com.exception.InvalidValidationDataInFile;
import supportemail.qualityunit.com.util.constant.Constants;
import supportemail.qualityunit.com.util.reader.Reader;
import supportemail.qualityunit.com.util.validation.Validation;
import supportemail.qualityunit.com.util.writer.Writer;

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
                if (line.matches(Validation.WAITING_TIME_REGEX)) {
                    continue;
                }
                String[] lineContains = line.split(Validation.LINE_SPLITER);
                if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.C) {
                    C c = new C();
                    c.setService(checkValidationService(lineContains[1]));
                    c.setQuestionType(checkValidationQuestionType(lineContains[2]));
                    c.setResponseType(checkValidationResponseType(lineContains[3]));
                    c.setDate(parseDate(lineContains[4]));
                    c.setWaitingTime(parseWaitingTime(lineContains[5]));
                    waitingTimeLines.add(c);
                } else if (LineTypeEnum.valueOf(lineContains[0]) == LineTypeEnum.D) {
                    D d = new D();
                    d.setService(checkValidationService(lineContains[1]));
                    d.setQuestionType(checkValidationQuestionType(lineContains[2]));
                    d.setResponseType(checkValidationResponseType(lineContains[3]));
                    d.setDatePeriod(parseDatePeriod(lineContains[4]));
                    queriesAndItsWaitingTimeLines.put(d, new ArrayList(waitingTimeLines));
                }
            }
        }
        data.setQueriesAndItsWaitingTimeLines(queriesAndItsWaitingTimeLines);
        return data;
    }

    private Service checkValidationService(String serviceQuery){
        Service service = null;
        try {
            if(Validation.validationService(serviceQuery)) {
                service = parseServiceAndVariation(serviceQuery);
            }
        } catch (InvalidValidationDataInFile ex) {
            Writer.writeEx(ex.getMessage());
        }
        return service;
    }

    private QuestionType checkValidationQuestionType(String questionTypeQuery){
        QuestionType questionType = null;
        try {
            if(Validation.validationQuestionType(questionTypeQuery)) {
                questionType = parseQuestionTypeAndCategoryAndSubcategory(questionTypeQuery);
            }
        } catch (InvalidValidationDataInFile ex) {
            Writer.writeEx(ex.getMessage());
        }
        return questionType;
    }

    private ResponseType checkValidationResponseType(String responseTypeQuery){
        ResponseType responseType = null;
        try {
            if(Validation.validationResponseType(responseTypeQuery)) {
                responseType = parseResponseType(responseTypeQuery);
            }
        } catch (InvalidValidationDataInFile ex) {
            Writer.writeEx(ex.getMessage());
        }
        return responseType;
    }

    private DatePeriod parseDatePeriod(String datePeriodString) throws ParseException {
        String[] datePeriods = datePeriodString.split(Validation.DATE_SPLITER);
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
        return new SimpleDateFormat(Validation.DATE_REGEX).parse(date);
    }

    private ResponseType parseResponseType(String responseType) {
        return ResponseType.valueOf(responseType);
    }

    private Service parseServiceAndVariation(String lineContain) {
        String[] serviceContains = lineContain.split(Validation.NUMBER_SPLITER);
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
        String[] questionTypeContains = lineContain.split(Validation.NUMBER_SPLITER);
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
        return questionTypeContains.length == Constants.NUMBER_OF_ELEMENTS_IN_SIMPLE_QUERY;
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
        return questionTypeContains.length > Constants.NUMBER_OF_ELEMENTS_IN_SIMPLE_QUERY;
    }

    private boolean serviceHasVariation(String[] serviceContains) {
        return serviceContains.length == Constants.NUMBER_OF_ELEMENTS_IN_SIMPLE_QUERY;
    }

    private Service.Variant createVariation(String variationId) {
        return new Service.Variant(variationId);
    }


}
