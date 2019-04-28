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
public class keysArray {
    ConnectDB  db = new ConnectDB();
    private ArrayList<fetchKeys> keyArray = new ArrayList<>();

    public keysArray() {
        ResultSet r = db.getResults("SELECT * FROM lessonKeys");
        try {
            while(r.next()) {
                fetchKeys fks = new fetchKeys(r.getInt("lessonID"), r.getString("lessonKey"));
                keyArray.add(fks);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

    public ArrayList<fetchKeys> getKeyArray() {
        return keyArray;
    }

    public void setKeyArray(ArrayList<fetchKeys> keyArray) {
        this.keyArray = keyArray;
    }
    
    public String getKeyFromLessonID(int id) {
        String key = "";
        for (int i = 0; i < this.keyArray.size(); i++) {
            if (this.keyArray.get(i).getLessonID() == id) {
                key = this.keyArray.get(i).getLessonKey();
            }
        }
        return key;
    }
    
    public String getStartTimeFromKey(String key) {
        lessonDataArray la = new lessonDataArray();
        String startTime = "";
        System.out.println(la.getLessonDataArray().size() + "    lesson array");
        System.out.println(this.keyArray.size() + "    key array");
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {
                startTime = la.getStartTimeFromIndex(i);
            }
        }
        System.out.println(startTime);
        return startTime;
    }
    
}
