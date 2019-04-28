/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static net.ucanaccess.converters.Functions.date;

/**
 *
 * @author YishaiBasserabie
 */
public class CalendarHandler {
    
    public void JCalendarActionPerformed(JCalendar cal) {
        cal.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            CalendarHandler ch = new CalendarHandler();
            JOptionPane.showMessageDialog(null, ch.getLessonDataOnThatDate(ch.getFormattedDateFromJCalendar(cal.getDate().toString())));
        }
        });
    }
    
    public String getFormattedDateFromJCalendar(String UDate) {
        lessonDataArray la = new lessonDataArray();
        String formattedDate = "";
        
        String UYear = UDate.substring(25);
        String UMonth = UDate.substring(4, 7);
        String UDay = UDate.substring(8, 10);
        
        //checks and formats month
        String finalMonth = "";
        switch (UMonth) {
            case "Jan": 
                finalMonth = "01";
                break;
            case "Feb":
                finalMonth = "02";
                break; 
            case "Mar":
                finalMonth = "03";
                break; 
            case "Apr":
                finalMonth = "04";
                break; 
            case "May":
                finalMonth = "05";
                break; 
            case "Jun":
                finalMonth = "06";
                break; 
            case "Jul":
                finalMonth = "07";
                break; 
            case "Aug":
                finalMonth = "08";
                break; 
            case "Sep":
                finalMonth = "09";
                break; 
            case "Oct":
                finalMonth = "10";
                break; 
            case "Nov":
                finalMonth = "11";
                break; 
            case "Dec":
                finalMonth = "12";
                break; 
        }
        formattedDate = UYear + "/" + UDay + "/" + finalMonth;
        return formattedDate;
    }
    
    
    public String getLessonDataOnThatDate(String date) {
        lessonDataArray la = new lessonDataArray();
        String lessonsOnDayData = "Youre lessons on this day are:\n\n";
        String lessonIntro = "";
        
        if (this.NumberOfLessonsOnDay(date) > 0) {
            String time = "";
            String venue = "";
            String students = "";
            //populates lessonsOnDayData with the data for all of the lessons on the selected date
            for (int i = 0; i < this.NumberOfLessonsOnDay(date); i++) {
                String key = this.getKeyFromPositionInDayAndDate(i, date);
                if (la.getFrequencyFromKey(key).equals("once-off")) {
                    lessonIntro = "This is a once-off lesson:\n";
                    time = "Time: " + this.timeFromLessonKey(key) + "\n";
                    venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                    students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(key, date, this.StartTimeFromLessonKey(key)));
                    lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                } else {
                    if (la.getFrequencyFromKey(key).equals("weekly")) {
                        lessonIntro = "This is a weekly lesson:\n";
                        time = "Time: " + this.timeFromLessonKey(key) + "\n";
                        venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                        students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(key, date, this.StartTimeFromLessonKey(key)));
                        lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                    } else {
                        if (la.getFrequencyFromKey(key).equals("monthly")) {
                            lessonIntro = "This is a monthly lesson:\n";
                            time = "Time: " + this.timeFromLessonKey(key) + "\n";
                            venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                            students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(key, date, this.StartTimeFromLessonKey(key)));
                            lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                        }
                    }
                }
            }
        } else {
            lessonsOnDayData = "you have no lessons on this day!";
        }
       return  lessonsOnDayData;
    }
    
    public String studentsStringFromArray(String [] array) {
        String students = "";
        for (int i = 0; i < array.length; i++) {
            students += array[i] + "\n                  ";
        }
        return students;
    }
    
    public String getKeyFromPositionInDayAndDate(int index, String date) {
        String key = "";
        for (int i = 0; i < this.keysOnDay(date).length; i++) {
            if (i == index) {
                key = this.keysOnDay(date)[i];
            }
        }
        return key;
    }
    
    public String [] keysOnDay(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        boolean keyAlreadyIn;
        ArrayList<String> keys = new ArrayList<>();
        String keysArray [] = new String[18];
        Stream<String> streamKeys;
        int count1 = 0;
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date)) {
                //streams array of keys to check if key already is in array
                streamKeys = Arrays.stream(keysArray);
                keyAlreadyIn = streamKeys.anyMatch(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID())::equals);
                if (keyAlreadyIn == false) {
                    keys.add(count1, ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                    keysArray[count1] = keys.get(count1);
                    count1++;
                }
            }
        }   
        return keysArray;
    }
    
    public String StartTimeFromLessonKey(String key) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String time = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (ka.getKeyArray().get(i).getLessonKey().equals(key)) {
                time = la.getLessonStartTimeFromLessonID(ka.getKeyArray().get(i).getLessonID());  
            }
        }
        return time;
    }
    
    public String timeFromLessonKey(String key) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String time = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (ka.getKeyArray().get(i).getLessonKey().equals(key)) {
                time = la.getTimeFromLessonID(ka.getKeyArray().get(i).getLessonID());  
            }
        }
        return time;
    }
    
    public String venueFromLessonKey(String key) {
        keysArray ka = new keysArray();
        venueArray va = new venueArray();
        lessonDataArray la = new lessonDataArray();
        String venue = "";
        for (int i = 0; i < ka.getKeyArray().size(); i++) {
            if (ka.getKeyArray().get(i).getLessonKey().equals(key)) {
                int lessonID = ka.getKeyArray().get(i).getLessonID();
                int index = la.getIndexFromID(lessonID);
                venue = va.venueNameFromID(la.getLessonDataArray().get(index).getVenueID());
            }
        }
        return venue;
    }
    
    public int NumberOfLessonsOnDay(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> keys = new ArrayList<>();
        boolean keyAlreadyIn = false;
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            System.out.println("i: " + i);
            System.out.println("lessonID: " + la.getLessonDataArray().get(i).getLessonID() + "  lessonDate: " + la.getLessonDataArray().get(i).getLessonDate());
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date)) {
                //checks if lesson already added to key list by checking it against the lessonKey
                System.out.println("date for attempted lesson: " + la.getLessonDataArray().get(i).getLessonDate());
                System.out.println("key for attempted lesson: " + ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                for (int k = 0; k < keys.size(); k++) {
                    if (keys.get(k).equals(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()))) {
                        keyAlreadyIn = true;
                        System.out.println("key already in: " + keyAlreadyIn);
                        System.out.println("");
                    }
                }
                if (keyAlreadyIn == false) {
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                }
                keyAlreadyIn = false;
            }
        }
        System.out.println("number of lessons: " + keys.size());
        return keys.size();
    }
    
    public String [] studentsFromLessonKey(String key, String date, String time) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        studentsArray sa = new studentsArray();
        boolean studentAlreadyIn = false;
        ArrayList<String> students = new ArrayList<>();
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(time)) {
                students.add(sa.studentNameFromID(la.getLessonDataArray().get(i).getStudentID()));
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);
        return studentsArray;
    }
    
}
