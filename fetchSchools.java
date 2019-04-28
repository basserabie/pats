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
public class fetchSchools {
    private int schoolID;
    private String schoolName, PFName, PLName, PEmail;

    public fetchSchools(int schoolID, String schoolName, String PFName, String PLName, String PEmail) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.PFName = PFName;
        this.PLName = PLName;
        this.PEmail = PEmail;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPFName() {
        return PFName;
    }

    public void setPFName(String PFName) {
        this.PFName = PFName;
    }

    public String getPLName() {
        return PLName;
    }

    public void setPLName(String PLName) {
        this.PLName = PLName;
    }

    public String getPEmail() {
        return PEmail;
    }

    public void setPEmail(String PEmail) {
        this.PEmail = PEmail;
    }
    
    
}
