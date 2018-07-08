package supportemail.qualityunit.com.util.validation;

import supportemail.qualityunit.com.entity.data.QuestionType;
import supportemail.qualityunit.com.exception.InvalidValidationDataInFile;
import supportemail.qualityunit.com.util.constant.Constants;

public class Validation {
    private static String SERVICE_REGEX = "(([1-9]|10)(\\.[1-3])?)|\\*";
    private static String QUESTION_TYPE_REGEX = "(([1-9]|10)(\\.([1-9]|1[0-9]|20)(\\.[1-5])?)?)|\\*";
    private static String RESPONSE_TYPE_REGEX = "P|N|\\*";

    public static String WAITING_TIME_REGEX = "\\d{1,6}";
    public static String LINE_SPLITER = " ";
    public static String DATE_SPLITER = "\\-";
    public static String DATE_REGEX = "dd.MM.yyyy";
    public static String NUMBER_SPLITER = "\\.";

    public static boolean validationService(String query) throws InvalidValidationDataInFile {
        if (query.matches(SERVICE_REGEX)) {
            return true;
        }
        throw new InvalidValidationDataInFile(Constants.INVALID_VALIDATION_DATA_IN_FILE);
    }

    public static boolean validationQuestionType(String query) throws InvalidValidationDataInFile {
        if (query.matches(QUESTION_TYPE_REGEX)) {
            return true;
        }
        throw new InvalidValidationDataInFile(Constants.INVALID_VALIDATION_DATA_IN_FILE);
    }

    public static boolean validationResponseType(String query) throws InvalidValidationDataInFile {
        if (query.matches(RESPONSE_TYPE_REGEX)) {
            return true;
        }
        throw new InvalidValidationDataInFile(Constants.INVALID_VALIDATION_DATA_IN_FILE);
    }
}
