package metro.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class SwipeRecord {
    private int recordId;
    private int cardNo;
    private double fareDeducted;
    private int start;       // stationId (FK)
    private int end;         // stationId (FK)
    private Timestamp startTime;
    private Timestamp endTime;
    private Date date;

    public SwipeRecord() {}

    public SwipeRecord(int recordId, int cardNo, double fareDeducted,
                       int start, int end, Timestamp startTime,
                       Timestamp endTime, Date date) {
        this.recordId = recordId;
        this.cardNo = cardNo;
        this.fareDeducted = fareDeducted;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    // convenient ctor for inserts (no recordId)
    public SwipeRecord(int cardNo, double fareDeducted, int start, int end,
                       Timestamp startTime, Timestamp endTime, Date date) {
        this.cardNo = cardNo;
        this.fareDeducted = fareDeducted;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getCardNo() { return cardNo; }
    public void setCardNo(int cardNo) { this.cardNo = cardNo; }

    public double getFareDeducted() { return fareDeducted; }
    public void setFareDeducted(double fareDeducted) { this.fareDeducted = fareDeducted; }

    public int getStart() { return start; }
    public void setStart(int start) { this.start = start; }

    public int getEnd() { return end; }
    public void setEnd(int end) { this.end = end; }

    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }

    public Timestamp getEndTime() { return endTime; }
    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}