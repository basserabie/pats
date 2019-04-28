/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class populateComboBoxes {
    
    //methid to populate grade combo boxes
    public String [] populateGrades() {
        String grades [] = {"10", "11", "12"};
        return grades;
    }
    
    //populates schools combo boxes
    public String [] populateSchools() {
        schoolsArray sa = new schoolsArray();
        String [] schools = new String [sa.getSchoolsDataArray().size()];
        for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
            schools[i] = sa.getSchoolsDataArray().get(i).getSchoolName();
        }
        return schools;
    }
    
    
    public String [] populateVenues() {
        venueArray va = new venueArray();
        String venues [] = new String [va.getVenuesArray().size()];
        for (int i = 0; i < va.getVenuesArray().size(); i++) {
            venues[i] = va.getVenuesArray().get(i).getVenue();
        }
        return venues;
    }
    
    //populates a corrected combo box of students according to grade
    public String [] correctStudentsAccordingToGrade(String gradeSelected) {
        studentsArray sa = new studentsArray();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                count1++;
            }
        }
        String students [] = new String[count1];
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
             String fname = "";
             String lname = "";
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                 fname = sa.getStudentArray().get(i).getfName();
                 lname = sa.getStudentArray().get(i).getlName();
                 //adds item of fname and lname
                 students[count2]  = fname + " " + lname;
                 count2++;
             }
         }
        return students;
    }
    
    public String [] populateMinuteComboBoxAccordingToHour(String hourSelected) {
        String minutes [] = new String [4];
        int min = 15;
        minutes[0] = hourSelected + ":" + "00";
        for (int i = 0; i < 3; i++) {
            minutes[i+1] = hourSelected + ":" + ""+min;
            min += 15;
        }
        return minutes;
    }
    
    public String [] populateBookingAmoountComboBox() {
        String amounts [] = {"three months", "six months", "year"};
        return amounts;
    }
    
    //populates duration spinner for lesson booking
    public String [] populateDurationSpinner() {
        String hours [] = {"1", "2", "3", "4", "5"};
        return hours;
    } 
    
    public DefaultTableModel schools() {
         schoolsArray sa = new schoolsArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"school", "principal", "principal emial"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();
             String email = sa.getSchoolsDataArray().get(i).getPEmail();
             model.addRow(new Object[] {school, principal, email});
         }
        return model;
     }
    
    public DefaultTableModel Lessons() {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student ID","student name", "venue", "date", "start-time", "end-time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String startTime = la.getLessonDataArray().get(i).getLessonTime();
            String endTime = la.GetEndTimeForSpecificStudent(la.getLessonDataArray().get(i).getLessonDuration(), la.getLessonDataArray().get(i).getLessonTime(), i);
            String day = la.getLessonDataArray().get(i).getDay();
            model.addRow(new Object[] {StudentID, StudentName, venue, date, startTime, endTime, day});
        }
        return model;
     }
    
    public DefaultTableModel Students() {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student ID","student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherName(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            String upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
            String upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            
            model.addRow(new Object[] {StudentID, StudentName, grade, school, motherName, upcomingLessonDate, 
                upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
        }
        return model;
     }
    
    
}
