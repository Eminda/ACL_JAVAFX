package acl.cable;

import com.sun.javafx.application.PlatformImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewClass{
    private static JPanel jp;
    public static void start(zx z,JPanel t) {
        jp=t;
       
        PlatformImpl.startup(
                new Runnable() {
                    public void run() {
                     TableView table = new TableView();
                     
                    Scene scene = new Scene(new Group());

                    final Label label = new Label("Address Book");

                    label.setFont (

                    new Font("Arial", 20));

                    table.setEditable (
                    true);

                        TableColumn firstNameCol = new TableColumn("First Name");
                    TableColumn lastNameCol = new TableColumn("Last Name");
                    TableColumn emailCol = new TableColumn("Email");
                    TableColumn e=new TableColumn("Inner one");
                    TableColumn w=new TableColumn("Inner two");
                    emailCol.getColumns().addAll(e,w);

                    table.getColumns ()
                    .addAll(firstNameCol, lastNameCol, emailCol);

                        final VBox vbox = new VBox();

                    vbox.setSpacing (

                    5);
                    vbox.setPadding (

                    new Insets(10, 0, 0, 10));
                    vbox.getChildren ()

                    .addAll(label, table);

                     (
                    (Group) scene.getRoot()).getChildren().addAll(vbox);

                        JFXPanel p = new JFXPanel();

                    p.setScene (scene);
                    jp.add("North",new JLabel("sfff"));
                    jp.add (p);
                    jp.revalidate();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    z.setVisible(true);
                }
    }

);

    }

    
}
