package supportemail.qualityunit.com.entity.linetype;

import supportemail.qualityunit.com.entity.data.ResponseType;
import supportemail.qualityunit.com.entity.data.QuestionType;
import supportemail.qualityunit.com.entity.data.Service;

public abstract class LineType {

    private Service service;
    private QuestionType questionType;
    private ResponseType responseType;

    public LineType(Service service, QuestionType questionType, ResponseType responseType) {
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
    }

}
