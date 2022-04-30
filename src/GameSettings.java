/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 30.04.2022
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class GameSettings extends JFrame implements Serializable {

    // create a serial number for each new saved gameboard
    // protects against inconsisteny which can be arises because program and data can be changed separately
    // (but a serial number don't protect for 100%)
    private static final long serialVersionUID = 1;
    // create a String
    String dateiname;
    // create a gameboard
    GameBoard gameboard;

    // Start-Directory
    JFileChooser c = new JFileChooser();


    /*
    constructor of the class
     */
    public GameSettings() {
        
        // function changes the current directory for the current process.
        c.setCurrentDirectory(new File(".\\saveFiles"));
        // FileNameExtensionFilter = The extension for a file is the portion of the file name after the last "."
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.lcm", "lcm");
        // set the extension
        c.setFileFilter(filter);
    }

    /*
    This method opens a saved datafile
     */
    public void loadGame(GameBoard o) {

        // open-Dialog
        int rVal = c.showOpenDialog(GameSettings.this);

        // (APPROVE_OPTION = the user accepts file selection)
        if (rVal == JFileChooser.APPROVE_OPTION) {
            dateiname = c.getSelectedFile().getAbsolutePath();
            try {
                // FileInputStream obtains the input bytes from the called file
                FileInputStream fileIn = new FileInputStream(dateiname);

                // create an instance of the class java.io.ObjectInputStream who deserializes the called Object
                // deserializes the Object into human language
                ObjectInputStream stream = new ObjectInputStream(fileIn);

                // The readObject method is responsible for reading and restoring the state of the object for its particular class using data written to the stream by the corresponding writeObject method
                o = (GameBoard) stream.readObject();

                // starts a new game with the saved Object
                new GameBoard(o);

                //  close() = used to close the stream and release the resources that were busy in the stream (if there are any)
                stream.close();

                System.out.println("\n---Game loaded ---\n");
            } catch (Exception e) {
                System.out.println("Serialization Error! Can't load data.\n" + e.getClass() + " : " + e.getMessage() + "\n");
            }
        }
    }

    /*
    This method save the Object class in a datafile
     */
    public void saveGame(GameBoard o) throws IOException {

        // open-Dialog
        int rVal = c.showOpenDialog(GameSettings.this);

        // (APPROVE_OPTION = the user accepts file selection)
        if (rVal == JFileChooser.APPROVE_OPTION) {
            dateiname = c.getSelectedFile().getName();
            try {
                // FileOutputStream writes the Object (data) into a file
                FileOutputStream fileOut = new FileOutputStream(c.getSelectedFile().getAbsolutePath() + ".lcm");

                // create an instance of the class java.io.ObjectOutputStream who writes the Object into the *data stream*
                // -> serializes the Object into bytes (we need to change the language so the system will understand)
                ObjectOutputStream stream = new ObjectOutputStream(fileOut);

                // (SAVE_DIALOG = the user save file selection)
                c.setDialogType(JFileChooser.SAVE_DIALOG);

                // The writeObject method is responsible for writing the state of the object for its particular class so that the corresponding readObject method can restore it
                stream.writeObject(o);

                // flush the stream -> it means to clear the stream of any element that may be or maybe not inside the stream
                stream.flush();

                //  close() = used to close the stream and release the resources that were busy in the stream (if there are any)
                stream.close();

                System.out.println("Game saved!");
            } catch (Exception e) {
                System.out.println("Serialization Error! Can't save data.\n" + e.getClass() + " : " + e.getMessage() + "\n");
            }
        }
    }
}