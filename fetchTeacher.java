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
public class fetchTeacher {
    ConnectDB db = new ConnectDB();
    loginSignup ls = new loginSignup();
    dataValidation vd = new dataValidation();
    private String fname, lname, cell, email, password;
    private String signUpProblems = "";
    boolean signedUp = false;

    //constructor fecthes teacherData from teacherTable
    public fetchTeacher() {
        //creates resultSet fetching all data from teacherTable
        String sql = "SELECT * FROM teacherTable";
        ResultSet rs = db.getResults(sql);
        try {
            while (rs.next()) {
                //fetches first name from teacerTable
                String fnametest = rs.getString("fName");
                this.fname = fnametest;
                //fetches last name from teacerTable
                String lnametest = rs.getString("lName");
                this.lname = lnametest;
                //fetches cell from teacerTable
                String celltest = rs.getString("cell");
                this.cell = celltest;
                //fetches email from teacerTable
                String emailtest = rs.getString("email");
                this.email = emailtest;
                //fetches password from teacerTable
                String passwordtest = rs.getString("password");
                this.password = passwordtest;
                //fetches first name from teacerTable
                boolean signedUptest = rs.getBoolean("signedUp");
                this.signedUp = signedUptest;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem at fetching teacherTable data");
        }
        
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSignedUp() {
        return signedUp;
    }

    public void SetSignedUp() {
        this.signedUp = signedUp;
    }
    
    //todo: fix this
    public boolean validateSignUp(String fname, String lname, String email, 
            String cell, String password, String confirmPassword) {
        boolean ok = true;
        
        if (!vd.checkName(fname, "f")) { 
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkName(lname, "l")) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkEmail(email)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkCell(cell)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkPassword(password, confirmPassword)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        }
        if (!this.signUpProblems.equals("")) {
            JOptionPane.showMessageDialog(null, this.signUpProblems);
        }
        return ok;
    }

    @Override
    public String toString() {
        return "fetchTeacher{" + "db=" + db + ", fname=" + fname + ", lname=" + lname + ", cell=" + cell + ", email=" + email + ", password=" + password + ", signedUp=" + signedUp + '}';
    }

   
    
}
