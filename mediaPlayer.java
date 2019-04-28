/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class mediaPlayer {
    public void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("find sound").getAbsoluteFile()); //creates a new audioinputstream of the Roar wav file
        Clip clip = AudioSystem.getClip(); //instantiates a clip to hold the sample file 
        clip.open(audioInputStream); //sets the clip as the opened Roar.wav file
        clip.start(); //plays the clip audio
    } catch(Exception ex) {
        JOptionPane.showMessageDialog(null, "Error - the audio could not be played \nThe program can still be used \nPlease call IT Department for assisstance",null, JOptionPane.ERROR); //notifies the user that the clip couldn't be played
    }
}
}
