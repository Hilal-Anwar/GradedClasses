package org.graded;


import javafx.scene.text.Text;

import java.util.Objects;

public final class TimeTable {
    private final String time;
    private final String grade;
    private final String roomNo;
    private final String subject;

    public TimeTable(String time, String grade, String roomNo, String subject) {
        this.time = time;
        this.grade = grade;
        this.roomNo = roomNo;
        this.subject = subject;
    }

    public Double[] getDataList() {
        return new Double[]{new Text(time).getLayoutBounds().getWidth(),
                new Text(grade).getLayoutBounds().getWidth(),
                new Text(roomNo).getLayoutBounds().getWidth(),
                new Text(subject).getLayoutBounds().getWidth()};
    }

    public String time() {
        return time;
    }

    public String grade() {
        return grade;
    }

    public String roomNo() {
        return roomNo;
    }

    public String subject() {
        return subject;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TimeTable) obj;
        return Objects.equals(this.time, that.time) &&
                Objects.equals(this.grade, that.grade) &&
                Objects.equals(this.roomNo, that.roomNo) &&
                Objects.equals(this.subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, grade, roomNo, subject);
    }

    @Override
    public String toString() {
        return "TimeTable[" +
                "time=" + time + ", " +
                "grade=" + grade + ", " +
                "roomNo=" + roomNo + ", " +
                "subject=" + subject + ']';
    }

}
