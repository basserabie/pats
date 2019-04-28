/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class studentsArray {
//    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchStudentDetails> studentArray = new ArrayList<>();

    public studentsArray() {
        ConnectDB db = new ConnectDB();
        ResultSet r = db.getResults("SELECT * FROM sDetTable");
        try {
            while(r.next()) {
                fetchStudentDetails fsd = new fetchStudentDetails(r.getInt("StudID"), r.getInt("schoolD"), 
                        r.getInt("motherID"), r.getString("fName"), r.getString("lName"), r.getString("grade"));
                studentArray.add(fsd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "here");
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

    public ArrayList<fetchStudentDetails> getStudentArray() {
        return studentArray;
    }

    public void setStudentArray(ArrayList<fetchStudentDetails> studentArray) {
        this.studentArray = studentArray;
    }
    
    public int studentIDFromName(String name) {
        String fname = "";
        String lname = "";
        int id = 0;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                fname = name.substring(0, i);
                lname = name.substring(i+1);
            }
        }
        for (int i = 0; i < studentArray.size(); i++) {
            if (fname.equals(studentArray.get(i).getfName()) && lname.equals(studentArray.get(i).getlName())) {
                id = studentArray.get(i).getStudentID();
            }
        }
        return id;
    }
    
    public String studentNameFromID(int id) {
        String name = "";
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (id == this.studentArray.get(i).getStudentID()) {
                name = this.studentArray.get(i).getfName() + " " + this.studentArray.get(i).getlName();
            }
        }
        return name;
    }
    
    public void addStudent(String fname, String lname, String grade, String school, String mfname, String mlname, String memail, String mcell) {
        ConnectDB db = new ConnectDB();
        mothersArray ma = new mothersArray();
        schoolsArray sa = new schoolsArray();
        dashboard d = new dashboard();
        
        //push
        try {
            //get motherID
            String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '" 
                + mlname + "', '" + memail + "', '" + mcell + "')";
            db.UpdateDatabase(pushMother);//pushes mother
            String motherID = ""+ma.getMotherID(mcell);
            //get SchoolID
            String schoolID = ""+sa.getSchoolID(school);
            
            //push student
            String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolD, motherID) VALUES ('" + fname 
                    + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + motherID+ "')";
            System.out.println(pushStudent);
            db.UpdateDatabase(pushStudent);//pushes student
        } catch (SQLException ex) {
            Logger.getLogger(addStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
