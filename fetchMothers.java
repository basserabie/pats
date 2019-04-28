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
public class fetchMothers {
    private int motherID;
    private String motherFName, motherLName, motherEmail, motherCell;

    public fetchMothers(int motherID, String motherFName, String motherLName, String motherEmail, String motherCell) {
        this.motherID = motherID;
        this.motherFName = motherFName;
        this.motherLName = motherLName;
        this.motherEmail = motherEmail;
        this.motherCell = motherCell;
    }

    public int getMotherID() {
        return motherID;
    }

    public void setMotherID(int motherID) {
        this.motherID = motherID;
    }

    public String getMotherFName() {
        return motherFName;
    }

    public void setMotherFName(String motherFName) {
        this.motherFName = motherFName;
    }

    public String getMotherLName() {
        return motherLName;
    }

    public void setMotherLName(String motherLName) {
        this.motherLName = motherLName;
    }

    public String getMotherEmail() {
        return motherEmail;
    }

    public void setMotherEmail(String motherEmail) {
        this.motherEmail = motherEmail;
    }

    public String getMotherCell() {
        return motherCell;
    }

    public void setMotherCell(String motherCell) {
        this.motherCell = motherCell;
    }
    
    
}
