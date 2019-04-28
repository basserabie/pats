/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

/**
 *
 * @author YishaiBasserabie
 */
public class fetchLessonData {
    ConnectDB db = new ConnectDB();
    private int lessonID, studentID, venueID, lessonDuration;
    private String lessonDate, lessonTime, day;

    public fetchLessonData(int lessonID, int studentID, int venueID, int lessonDuration, String lessonDate, String lessonTime, String day) {
        this.lessonID = lessonID;
        this.studentID = studentID;
        this.venueID = venueID;
        this.lessonDuration = lessonDuration;
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.day = day;
    }

    public ConnectDB getDb() {
        return db;
    }

    public void setDb(ConnectDB db) {
        this.db = db;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public int getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(int lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    
}