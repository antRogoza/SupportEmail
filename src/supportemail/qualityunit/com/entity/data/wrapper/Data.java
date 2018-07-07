package supportemail.qualityunit.com.entity.data.wrapper;

import supportemail.qualityunit.com.entity.linetype.impl.C;
import supportemail.qualityunit.com.entity.linetype.impl.D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private int linesCount;
    private Map<D, List<C>> queriesAndItsWaitingTimeLines;

    public Data(int linesCount, Map<D, List<C>> queriesAndItsWaitingTimeLines) {
        this.linesCount = linesCount;
        this.queriesAndItsWaitingTimeLines = queriesAndItsWaitingTimeLines;
    }

    public Data() {
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public Map<D, List<C>> getQueriesAndItsWaitingTimeLines() {
        return queriesAndItsWaitingTimeLines;
    }

    public void setQueriesAndItsWaitingTimeLines(Map<D, List<C>> queriesAndItsWaitingTimeLines) {
        this.queriesAndItsWaitingTimeLines = queriesAndItsWaitingTimeLines;
    }

    @Override
    public String toString() {
        for (Map.Entry entry : queriesAndItsWaitingTimeLines.entrySet()) {
//            List value = (List) entry.getValue();
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
        return "Data{" +
                "linesCount=" + linesCount +
                ", queriesAndItsWaitingTimeLines=" + queriesAndItsWaitingTimeLines +
                '}';
    }

}
