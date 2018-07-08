package supportemail.qualityunit.com.entity.linetype.impl;

import supportemail.qualityunit.com.entity.data.DatePeriod;
import supportemail.qualityunit.com.entity.data.QuestionType;
import supportemail.qualityunit.com.entity.data.ResponseType;
import supportemail.qualityunit.com.entity.data.Service;
import supportemail.qualityunit.com.entity.linetype.LineType;

public class D extends LineType {

    private DatePeriod datePeriod;

    public D(Service service, QuestionType questionType, ResponseType responseType, DatePeriod datePeriod) {
        super(service, questionType, responseType);
        this.datePeriod = datePeriod;
    }

    public D() {
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(DatePeriod datePeriod) {
        this.datePeriod = datePeriod;
    }
}
