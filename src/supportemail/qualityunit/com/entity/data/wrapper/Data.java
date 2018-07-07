package supportemail.qualityunit.com.entity.data.wrapper;

import supportemail.qualityunit.com.entity.linetype.impl.C;
import supportemail.qualityunit.com.entity.linetype.impl.D;

import java.util.List;

public class Data {

    private int linesCount;
    private List<C> C;
    private List<D> D;

    public Data(int linesCount, List<C> c, List<D> d) {
        this.linesCount = linesCount;
        C = c;
        D = d;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public List<C> getC() {
        return C;
    }

    public void setC(List<C> c) {
        C = c;
    }

    public List<D> getD() {
        return D;
    }

    public void setD(List<D> d) {
        D = d;
    }

    @Override
    public String toString() {
        return "Data{" +
                "linesCount=" + linesCount +
                ", C=" + C +
                ", D=" + D +
                '}';
    }

}
