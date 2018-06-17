package com.ctrip.ibu.flight.internalws.models.flight;

/**
 * 航段中转间隔
 * Create by kyxie on 2018/2/3 17:07
 */
public class ColumnLayoverDetail {

    private Integer preColumnSegmentNo;

    private Integer preColumnSequenceNo;

    private Integer curColumnSegmentNo;

    private Integer curColumnSequenceNo;

    private Integer hourLayover;

    private Integer minuteLayover;

    public Integer getPreColumnSegmentNo() {
        return preColumnSegmentNo;
    }

    public void setPreColumnSegmentNo(Integer preColumnSegmentNo) {
        this.preColumnSegmentNo = preColumnSegmentNo;
    }

    public Integer getPreColumnSequenceNo() {
        return preColumnSequenceNo;
    }

    public void setPreColumnSequenceNo(Integer preColumnSequenceNo) {
        this.preColumnSequenceNo = preColumnSequenceNo;
    }

    public Integer getCurColumnSegmentNo() {
        return curColumnSegmentNo;
    }

    public void setCurColumnSegmentNo(Integer curColumnSegmentNo) {
        this.curColumnSegmentNo = curColumnSegmentNo;
    }

    public Integer getCurColumnSequenceNo() {
        return curColumnSequenceNo;
    }

    public void setCurColumnSequenceNo(Integer curColumnSequenceNo) {
        this.curColumnSequenceNo = curColumnSequenceNo;
    }

    public Integer getHourLayover() {
        return hourLayover;
    }

    public void setHourLayover(Integer hourLayover) {
        this.hourLayover = hourLayover;
    }

    public Integer getMinuteLayover() {
        return minuteLayover;
    }

    public void setMinuteLayover(Integer minuteLayover) {
        this.minuteLayover = minuteLayover;
    }
}
