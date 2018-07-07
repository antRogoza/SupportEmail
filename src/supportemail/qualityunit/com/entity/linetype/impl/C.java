package supportemail.qualityunit.com.entity.linetype.impl;

import supportemail.qualityunit.com.entity.data.ResponseType;
import supportemail.qualityunit.com.entity.linetype.LineType;
import supportemail.qualityunit.com.entity.data.QuestionType;
import supportemail.qualityunit.com.entity.data.Service;

import java.util.Date;

public class C extends LineType {

    private Date date;
    private int waitingTime;

    public C(Service service, QuestionType questionType, ResponseType responseType) {
        super(service, questionType, responseType);
    }

    public C(Service service, QuestionType questionType, ResponseType responseType, Date date, int waitingTime) {
        super(service, questionType, responseType);
        this.date = date;
        this.waitingTime = waitingTime;
    }

    public C() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return super.toString() + "C{" +
                "date=" + date +
                ", waitingTime=" + waitingTime +
                '}';
    }
}
