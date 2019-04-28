/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import com.mindfusion.common.DateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.time.Clock;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.UUID;
import static org.apache.commons.lang.CharSetUtils.count;

/**
 *
 * @author YishaiBasserabie
 */
public class lessonDataArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchLessonData> lessonDataArray = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private String lessonKey;

    public lessonDataArray() {
        ResultSet r = db.getResults("SELECT * FROM lessonData");
        try {
            while(r.next()) {
                fetchLessonData fld = new fetchLessonData(r.getInt("LessonID"), r.getInt("StudentID"),
                        r.getInt("venueID"), r.getInt("lessonDuration"), r.getString("lessonDate"), r.getString("lessonTime"), r.getString("lessonDay"));
                lessonDataArray.add(fld);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
        //calls method to sort array list
        this.sortArray();
    }

    public ArrayList<fetchLessonData> getLessonDataArray() {
        return lessonDataArray;
    }

    public void setLessonDataArray(ArrayList<fetchLessonData> lessonDataArray) {
        this.lessonDataArray = lessonDataArray;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getLessonKey() {
        return lessonKey;
    }

    public void setLessonKey(String lessonKey) {
        this.lessonKey = lessonKey;
    }
    
    public int getIndexFromID(int id) {
        int index = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                index = i;
            }
        }
        return index;
    }
    
    public  int getStudentIDFromIndex(int index) {
        int student  = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (i == index) {
                student = this.lessonDataArray.get(i).getStudentID();
            }
        }
        return student;
    }
    
    public String getStartTimeFromIndex(int index) {
        String time  = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (i == index) {
                time = this.lessonDataArray.get(i).getLessonTime();
            }
        }
        return time;
    }
    
    public boolean compareTimesFromKeys(String key1, String key2) {
        keysArray ka = new keysArray();
        boolean before = false;
        
        int startTime1 = (Integer.parseInt(ka.getStartTimeFromKey(key1).substring(0, 2))*60)+Integer.parseInt(ka.getStartTimeFromKey(key1).substring(3, 5));
        int startTime2 = (Integer.parseInt(ka.getStartTimeFromKey(key2).substring(0, 2))*60)+Integer.parseInt(ka.getStartTimeFromKey(key2).substring(3, 5)); 
                
        if (startTime1 < startTime2) {
            before = true;
        }
        return before;
    }
    
    public String getFrequencyFromKey(String lessonKey) {
        keysArray ka = new keysArray();
        String frequency = "once-off";
        int countFreq = 0;
        ArrayList<String> refDates = new ArrayList<>();
        
        int day1, month1, day2, month2;
        
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (i > 0) {
                if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(lessonKey) && !this.lessonDataArray.get(i).getLessonDate().equals(this.lessonDataArray.get(i-1).getLessonDate())) {
                    refDates.add(this.lessonDataArray.get(i).getLessonDate());
                    countFreq++;
                }
            }
            
            if (countFreq > 1) {
                day1 = Integer.parseInt(refDates.get(0).substring(5, 7));
                day2 = Integer.parseInt(refDates.get(1).substring(5, 7));
                month1 = Integer.parseInt(refDates.get(0).substring(8, 10));
                month2 = Integer.parseInt(refDates.get(1).substring(8, 10));
                
                if (this.isMonthArpart(refDates.get(0), refDates.get(1))) {
                    frequency = "monthly";
                }
                if (this.isWeekApart(refDates.get(0), refDates.get(1))) {
                    frequency = "weekly";
                }
            }
        }
        return frequency;
    }
    
    public boolean isLeapYear(String date) {
        boolean leap = false;
        int year = Integer.parseInt(date.substring(0, 4));
        //checks if leap year
        if (year%100 == 0 && year%400 ==0) {
                leap = true;
            } else {
                if (year%4 == 0 && year%100 != 0) {
                    leap = true;
                }
            }
        return leap;
    }
    
    public int checkDaysInMonth(String date) {
        int days = 0;
        int month = Integer.parseInt(date.substring(8, 10));
        
        if (this.isLeapYear(date)) {
            switch (month) {
                case 1:
                    days = 31;
                    break;
                case 2:
                    days = 28;
                    break;
                case 3:
                    days = 31;
                    break;
                case 4:
                    days = 30;
                    break;
                case 5:
                    days = 31;
                    break;
                case 6:
                    days = 30;
                    break;
                case 7:
                    days = 31;
                    break;
                case 8: 
                    days = 31;
                    break;
                case 9:
                    days = 30;
                    break;
                case 10:
                    days = 31;
                    break;
                case 11:
                    days = 30;
                    break;
                case 12:
                    days = 31;
                    break;
            }
        } else {
            switch (month) {
                case 1:
                    days = 31;
                    break;
                case 2:
                    days = 29;
                    break;
                case 3:
                    days = 31;
                    break;
                case 4:
                    days = 30;
                    break;
                case 5:
                    days = 31;
                    break;
                case 6:
                    days = 30;
                    break;
                case 7:
                    days = 31;
                    break;
                case 8: 
                    days = 31;
                    break;
                case 9:
                    days = 30;
                    break;
                case 10:
                    days = 31;
                    break;
                case 11:
                    days = 30;
                    break;
                case 12:
                    days = 31;
                    break;
            }
        }
        return days;
    }
    
    public boolean isWeekApart(String date1, String date2) {
        boolean weekly = false;
        
        int day1 = Integer.parseInt(date1.substring(5, 7));
        int day2 = Integer.parseInt(date2.substring(5, 7));
        int month1 = Integer.parseInt(date1.substring(8, 10));
        int month2 = Integer.parseInt(date2.substring(8, 10));
        //checks if the days are in the same month
        if (month2 - month1 != 0) {
            //gest days in each of the months in question
            int daysInMonth1 = this.checkDaysInMonth(date1);
            if ((day2 + daysInMonth1 - day1) == 7) {
                weekly = true;
            }
        } else {
            if (day2 - day1 == 7) {
                weekly = true;
            }
        }
        return weekly;
    }
    
    public boolean isMonthArpart(String date1, String date2) {
        boolean monthly = false;
        
        int day1 = Integer.parseInt(date1.substring(5, 7));
        int day2 = Integer.parseInt(date2.substring(5, 7));
        int month1 = Integer.parseInt(date1.substring(8, 10));
        int month2 = Integer.parseInt(date2.substring(8, 10));
        
        if (month2 - month1 == 1 || month2 - month1 == -11) {
                    if (day2 == day1) {
                        monthly = true;
                    }
                }
        return monthly;
    }
    
    public void AddStudentsAdded(String name) {
        this.names.add(name);
            for (int i = 0; i < this.names.size(); i++) {
            }
    }
    public void removeStudentAdded(String name) {
        this.names.remove(name);
    }
    
    public String getArrayOfStudentsAdded(int index) {
        String namesArray [] = this.names.toArray(new String[this.names.size()]);
        return namesArray[index];
    }
    
    public String [] getArrayOfStudentsAddedNoIndex() {
        String namesArray [] = this.names.toArray(new String[this.names.size()]);
        return namesArray;
    }
    
    public void addToNamesList(String name) {
        if (!names.contains(name)) {
            this.AddStudentsAdded(name);
        }
    }
    
    //method sorts the array list
    public void sortArray() {
        //sorts by dates and times
         Collections.sort(lessonDataArray, new Comparator<fetchLessonData>() {
             public int compare(fetchLessonData l1, fetchLessonData l2) {
                 int comp = 0;
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
                 try {
                     Date date1 = sdf.parse(l1.getLessonDate() + " " + l1.getLessonTime());
                     Date date2 = sdf.parse(l2.getLessonDate() + " " + l2.getLessonTime());
                     comp =  date1.compareTo(date2);
                 } catch (ParseException ex) {
                     Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  return comp;
             }
         });
    }
    
    public String formatDate(String date) {
        String finalDate = "";
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
        Calendar cal = Calendar.getInstance(); // adds instance to cal
        //gets substrings of ivne full date
        String day = date.substring(8, 10);
        String month = date.substring(4, 7);
        String year = date.substring(25);
        
        //checks and formats month
        int finalMonth = 0;
        switch (month) {
            case "Jan": 
                finalMonth = 1;
                break;
            case "Feb":
                finalMonth = 2;
                break; 
            case "Mar":
                finalMonth = 3;
                break; 
            case "Apr":
                finalMonth = 4;
                break; 
            case "May":
                finalMonth = 5;
                break; 
            case "Jun":
                finalMonth = 6;
                break; 
            case "Jul":
                finalMonth = 7;
                break; 
            case "Aug":
                finalMonth = 8;
                break; 
            case "Sep":
                finalMonth = 9;
                break; 
            case "Oct":
                finalMonth = 10;
                break; 
            case "Nov":
                finalMonth = 11;
                break; 
            case "Dec":
                finalMonth = 12;
                break; 
        }
        
        if (finalMonth < 10) {
            finalDate = year + "/" + day + "/" + "0" + ""+finalMonth;
        } else {
            finalDate = year + "/" + day + "/" + "" + ""+finalMonth;
        }
        
        try {
            cal.setTime(sdf.parse(finalDate)); 
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sdf.format(cal.getTime());
    }
    
    public String formatTime(String date) {
        String time = date.substring(11, 16);
        return time;
    }
    
    public String formatDay(String day) {
        String finalDay = "";
        
        //gets substring of day from given full date
        String subbedDay = day.substring(0, 3);
         switch (subbedDay) {
             case "Sun":
                 finalDay = "sunday";
                 break;
            case "Mon":
                 finalDay = "monday";
                 break;
            case "Tue":
                 finalDay = "tuesday";
                 break;
            case "Wed":
                 finalDay = "wednesday";
                 break;
            case "Thu":
                 finalDay = "thursday";
                 break;
            case "Fri":
                 finalDay = "friday";
                 break;
            case "Sat":
                 finalDay = "saturday";
                 break;
         }
         return finalDay;
    }
    
    public void addLesson(String venue, String date, String time, String day, int size, String name, int frequency, int duration, String lessonKeyToAdd) {
        ConnectDB db = new ConnectDB();
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        keysArray ka = new keysArray();
        venueArray va = new venueArray();
        int countDisplayMessages = 0;
        int countLessonIDForKeys = 1;
        
        if (this.checkIfInPast(date, time) == false) {
            if(!this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd).contains("Venue")) {
                
        int id = sa.studentIDFromName(name);
        
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
        Calendar cal = Calendar.getInstance(); // adds instance to cal
        try {
            cal.setTime(sdf.parse(date)); 
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        String insert;
        String insertKey;
        
        if (frequency == 0) {
            insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES ('" + id + "', '" 
                    + va.venueIDFromVenue(venue) + "', '" + sdf.format(cal.getTime()) + "', '" + time + "', '" + duration + "', '" + day + "')";
            insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
            try {
                db.UpdateDatabase(insert);
            } catch (SQLException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                db.UpdateDatabase(insertKey);
            } catch (SQLException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (frequency == 1) {
                for (int i = 0; i < 52; i++) {
                    insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES ('" + id + "', '" 
                    + va.venueIDFromVenue(venue) + "', '" + sdf.format(cal.getTime()) + "', '" + time + "', '" + duration + "', '" + day + "')";
                    insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                    try {
                        db.UpdateDatabase(insert);
                    } catch (SQLException ex) {
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        db.UpdateDatabase(insertKey);
                    } catch (SQLException ex) {
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //adds a week to the date
                    cal.add(cal.DATE, 7);
                }
            } else {
                if (frequency == 2) {
                    for (int i = 0; i < 12; i++) {
                        insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES ('" + id + "', '" 
                        + va.venueIDFromVenue(venue) + "', '" + sdf.format(cal.getTime()) + "', '" + time + "', '" + duration + "', '" + day + "')";
                        insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                        try {
                            db.UpdateDatabase(insert);
                        } catch (SQLException ex) {
                            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            db.UpdateDatabase(insertKey);
                        } catch (SQLException ex) {
                            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //adds a week to the date
                        cal.add(cal.MONTH, 1);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "the lesson(s) have been added!");
        } else {
            if (countDisplayMessages == 0) {
                JOptionPane.showMessageDialog(null, this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd));
            }
            countDisplayMessages++;
        }
        } else {
            JOptionPane.showMessageDialog(null, "please do not try to book a lesson in the past ;)");
        }
        
    }
    
    public int getDurationFromTimeAndDate(String time, String date) {
        int duration = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (this.lessonDataArray.get(i).getLessonTime().equals(time) && this.lessonDataArray.get(i).getLessonDate().equals(date)) {
                duration = this.lessonDataArray.get(i).getLessonDuration();
            }
        }
        return duration;
    }
    
    //checks if double booked
    public String checkIfDoublebooking(String date, String time, int duration, int size, String lessonKeyToCheck) {
        ConnectDB db = new ConnectDB();
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        venueArray va = new venueArray();
        //sorts array of lessons
        this.sortArray();
        //gets end time of attemped time
        String endTimeAttempted = this.getEndTime(time, duration);
        
        String tempstudents = "\nStudent(s): ";
        String tempvenue = "";
        String tempReturn = "";
        String tempTime = "";
        boolean ok = true;
        boolean oneStudent = size == 1;
        
        int minsAttemptedTime = (Integer.parseInt(time.substring(0, 2))*60)+Integer.parseInt(time.substring(3, 5));
        int minsAttemptedEndTime = (Integer.parseInt(endTimeAttempted.substring(0, 2))*60)+Integer.parseInt(endTimeAttempted.substring(3, 5));
        int minsDuration = duration*60;
            
            for (int i = 0; i < this.lessonDataArray.size(); i++) {
            //gets reference end time TODO: problem is that the size of the lessonArray is not updating
            String endTimeReference = this.getEndTime(this.getLessonDataArray().get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            
            int minsReferenceTime = (Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2))*60)+Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(3, 5));
            int minsReferenceEndTime = (Integer.parseInt(endTimeReference.substring(0, 2))*60)+Integer.parseInt(endTimeReference.substring(3, 5));
            
            //checks the name of reference against array of added students
            boolean isInArray = Arrays.stream(this.getArrayOfStudentsAddedNoIndex()).anyMatch(sa.studentNameFromID(this.lessonDataArray.get(i).getStudentID())::equals);
            //checks if an attempted lesson to be booked has the same lesson key as the reference lesson
            boolean keyIsEqual = this.getLessonKey(i).equals(lessonKeyToCheck);
            //checks if lesson attmepted to be booked will overlap with an already booked lesson
            boolean checkDate = this.lessonDataArray.get(i).getLessonDate().equals(date);
            boolean check1 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime > 0 && !(minsAttemptedEndTime <= minsReferenceTime);
             boolean check2 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
              boolean check3 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
               boolean check4 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime < 0;
                boolean check5 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
                 boolean check6 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime < 0 && !(minsReferenceTime-minsAttemptedEndTime <= 0);
                  boolean check7 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
                
            if (oneStudent) {
                if (keyIsEqual == false && checkDate == true && (check1 == true) ||
                    keyIsEqual == false && checkDate == true && (check2 == true) ||
                    keyIsEqual == false && checkDate == true && (check3 == true) ||
                    keyIsEqual == false && checkDate == true && (check4 == true) ||
                    keyIsEqual == false && checkDate == true && (check5 == true) ||
                    keyIsEqual == false && checkDate == true && (check6 == true) ||
                    keyIsEqual == false && checkDate == true && (check7 == true)) {
                    ok = false;
                } else {
                    ok = true;
                }
            } else {
                if (!isInArray && keyIsEqual == false && checkDate == true && (check1 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check2 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check3 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check4 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check5 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check6 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check7 == true)) {
                    ok = false;
                } else {
                    ok = true;
                }
            }
                //TODO: fix display message
            if (ok == false) {
                
                //gets name of double booked venue
                String prevStudentName = "tempStudName";
                for (int k = 0; k < va.getVenuesArray().size(); k++) {
                    if (va.getVenuesArray().get(k).getVenueID() == this.lessonDataArray.get(i).getVenueID()) {
                        tempvenue = "\nVenue: " + va.getVenuesArray().get(k).getVenue();
                    }
                    if (!tempTime.contains(this.lessonDataArray.get(i).getLessonTime())) {
                        tempTime += this.lessonDataArray.get(i).getLessonTime() + " - " + endTimeReference + ", ";
                    }
                } 
                for (int s = 0; s < sa.getStudentArray().size(); s++) {
                    if (sa.getStudentArray().get(s).getStudentID() == this.lessonDataArray.get(i).getStudentID() && !tempstudents.contains(prevStudentName)) {
                    tempstudents += sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName() + "\n                  ";
                    prevStudentName = sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName();
                }
            }
            }
        
        }      
    tempReturn = "You have already booked a lesson at this time and date:\n\nDate: " + date + "\nTime: " + tempTime + tempvenue + tempstudents;
    return tempReturn;
    } 
    
    public String getLessonKey(int lessonID) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String key = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            for (int k = 0; k < ka.getKeyArray().size(); k++) {
                if (this.lessonDataArray.get(i).getLessonID() == ka.getKeyArray().get(k).getLessonID()) {
                    key = ka.getKeyArray().get(k).getLessonKey();
                }
            }
        }
        return key;
    }
    
    public String generateLessonKey() {
        String generateString;
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    
    public String getEndTime(String time, int duration) {
        String endTime = "";
        int endHour = 0;
        String mins = time.substring(3, 5);
        int startHour = Integer.parseInt(time.substring(0, 2));
        endHour = startHour + duration;
        if (endHour < 10) {
            endTime = "0"+endHour + ":" + mins;
        } else {
            endTime = endHour + ":" + mins;
        }
        return endTime;
    }

    public String GetEndTimeForSpecificStudent(int duration, String startTime, int i) { 
        String endTime = "";
        int endHour = 0;
        String mins = startTime.substring(3, 5);
        int startHour = Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2));
        endHour = startHour + duration;
        if (endHour < 10) {
            endTime = "0"+endHour + ":" + mins;
        } else {
            endTime = endHour + ":" + mins;
        }
        return endTime;
    }
    
    
    public boolean checkIfInPast(String refDate, String refTime) {
        String delete = "";
        boolean inPast = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        Date todayDate = new Date();
        try {
            Date formattedTodayDate = sdf.parse(this.formatDate(todayDate.toString()) + " " + this.formatTime(todayDate.toString()));
            Date date1 = sdf.parse(refDate + " " + refTime);
            if (date1.compareTo(formattedTodayDate) < 0) {
               inPast = true;          
            }
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inPast;
    }
    
    public void DeletePastLessonsAndLessonKeys() {
        ConnectDB db = new ConnectDB();
        String deleteLessons = "";
        String deleteKeys = "";
        
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (this.checkIfInPast(this.lessonDataArray.get(i).getLessonDate(), this.lessonDataArray.get(i).getLessonTime()) == true) {
                deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();
                deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + this.lessonDataArray.get(i).getLessonID();
                try {
                    db.UpdateDatabase(deleteLessons);
                    db.UpdateDatabase(deleteKeys);
                } catch (SQLException ex) {
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String upcomingDate(int studentID) {
        String message = "";
        boolean booked = false;
        //checks if in fact booked a lesson
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (this.lessonDataArray.get(i).getStudentID() == studentID) {
                booked = true;
            }
        }
        //cheks if count id true i.e. a lesson has been booked by that student
        if (booked == true) {
            this.sortArray();
            String date = "";
        
            for (int i = 0; i < this.lessonDataArray.size(); i++) {
                if (this.lessonDataArray.get(i).getStudentID() == studentID) {
                    date = this.lessonDataArray.get(i).getLessonDate();
                    break;
                }
            }
            return date;
        } else {
            return "N/A";
        }
        }
    
    public String upcomingTime(int studentID, String upcomingDate) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            String time = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID()) {
                time = this.lessonDataArray.get(i).getLessonTime();
            }
        }
        return time;
        } else {
            return "N/A";
        }
        }
    
    public String upcomingVenue(int studentID, String upcomingDate, String upcomingTime) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            venueArray va = new venueArray();
        String venue = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID() 
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {
                venue = va.venueNameFromID(this.lessonDataArray.get(i).getVenueID());
            }
        }
        return venue;
        } else {
            return "N/A";
        }
    }
    
    public String upcomingDay(int studentID, String upcomingDate, String upcomingTime) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            String day = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID() 
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {
                day = this.lessonDataArray.get(i).getDay();
            }
        }
        return day;
        } else {
            return "N/A";
        }
    }
    
    public String getLessonStartTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                time = this.lessonDataArray.get(i).getLessonTime();
            }
        }
        return time;
    }
    
    public String getTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                time = this.lessonDataArray.get(i).getLessonTime() + "-" + this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            }
        }
        return time;
    }
}


