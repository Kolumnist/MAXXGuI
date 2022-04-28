import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Savegame extends JFrame {

    // Textfelder für
    JTextField filename = new JTextField();   // Dateinamen
    JTextField dir = new JTextField();        // Directory
    JTextField exits = new JTextField();      // Existenz-Abfrage
    JTextField isdir = new JTextField();      // Directory-Abfrage

    // Knöpfe für Öffnen und Speichern
    JButton open = new JButton("Öffnen");
    JButton save = new JButton("Speichern");

    // Start-Directory
    JFileChooser c = new JFileChooser(new File("C://MAXXGuI_Game"));

    // Konstruktor
    public Savegame() {
        setTitle("Save Game");               // Fenster Titel
        Container cp = getContentPane();     // Fenster-Container
        open.addActionListener(new OpenL()); // AL registrieren
        cp.add(open, BorderLayout.NORTH);    //    und einfügen
        save.addActionListener(new SaveL());
        cp.add(save, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        exits.setEditable(false);
        isdir.setEditable(false);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1, 2, 2));   // Gridlayout für Textfelder-Panel
        p.add(filename);                                             // Feld filename hinzufügen
        p.add(dir);                                                  // Feld dir hinzufügen
        p.add(exits);                                                // Feld exits hinzufügen
        p.add(isdir);                                                // Feld isdir hinzufügen
        cp.add(p, BorderLayout.CENTER);
        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  // Sel.-Modus
        setVisible(true);
        setSize(500,500);
    }


    class OpenL implements ActionListener {                                         // AL. für Öfnnen Knopf

        public void actionPerformed(ActionEvent e) {
            int rVal = c.showOpenDialog(Savegame.this);                      // Öffne-Dialog öffnen
            if (rVal == JFileChooser.APPROVE_OPTION) {                             // falls bestätigt:
                filename.setText("Filename: " + c.getSelectedFile().getName());
                dir.setText("Akt.Directory: " + c.getCurrentDirectory());
                exits.setText("Existiert? " + c.getSelectedFile().exists());
                isdir.setText("Ist Directory? " + c.getSelectedFile().isDirectory());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {                              // falls abgebrochen:
                filename.setText("Es wurde Abbrechen gedrückt!");
                dir.setText("");
                exits.setText("");                                                 // Texte löschen
                isdir.setText("");
            }
        }
    }

    class SaveL implements ActionListener {                                        // AL für Speichern-Knopf

        public void actionPerformed(ActionEvent e) {
            int rVal = c.showSaveDialog(Savegame.this);                     // Speichern-Dialog öffnen
            if (rVal == JFileChooser.APPROVE_OPTION) {                            // falls bestätigt:
                filename.setText("Filename: " + c.getSelectedFile().getName());
                dir.setText("Akt. Dictory: " + c.getCurrentDirectory());
                exits.setText("Existiert? " + c.getSelectedFile().exists());
                isdir.setText("Ist Directory? " + c.getSelectedFile().isDirectory());
            }
            if(rVal == JFileChooser.CANCEL_OPTION) {                              // falls abgebrochen:
                filename.setText("Es wurde Abbrechen gedrückt!");
                dir.setText("");
                exits.setText("");
                isdir.setText("");
            }
        }
    }
}
