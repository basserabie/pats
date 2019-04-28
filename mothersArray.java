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
public class mothersArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    public ArrayList<fetchMothers> mothersArray = new ArrayList<>();

    public mothersArray() {
        ConnectDB  db = new ConnectDB();
        ResultSet r = db.getResults("SELECT * FROM mothers");
        try {
            while(r.next()) {
                fetchMothers fm = new fetchMothers(r.getInt("MotherID"), r.getString("motherfName"), 
                        r.getString("motherLName"), r.getString("motherEmail"), r.getString("motherCell"));
                mothersArray.add(fm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
        
    }
    
    public int getMotherID(String cell) {
        int temp = this.mothersArray.get(mothersArray.size()-1).getMotherID()+1;
        return temp;
    }
    
    public String getMotherName(int id) {
        studentsArray sa = new studentsArray();
        String name = "";
        
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (sa.getStudentArray().get(id-1).getMotherID() == this.mothersArray.get(i).getMotherID()) {
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();
            }
        }
        return name;
    } 
    
    
}
