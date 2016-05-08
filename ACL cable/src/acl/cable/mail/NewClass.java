/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.mail;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JPanel;

/**
 *
 * @author Eminda
 */
public class NewClass implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public JPanel getPanel() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("Test.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("Test.fxml"));
        JFXPanel panel=new JFXPanel();
        panel.setScene(pane.getScene());
        JPanel jp=new JPanel(new BorderLayout());
        jp.add(panel);
        return jp;
    }
    
}
