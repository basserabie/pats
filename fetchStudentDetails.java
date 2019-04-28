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
public class fetchStudentDetails {
    private int StudentID, schoolID, motherID;
    private String fName, lName, grade;

    public fetchStudentDetails(int StudentID, int schoolID, int motherID, String fName, String lName, String grade) {
        this.StudentID = StudentID;
        this.schoolID = schoolID;
        this.motherID = motherID;
        this.fName = fName;
        this.lName = lName;
        this.grade = grade;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getMotherID() {
        return motherID;
    }

    public void setMotherID(int motherID) {
        this.motherID = motherID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
}
