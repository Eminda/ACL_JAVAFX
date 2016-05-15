/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MailInBoxController implements Initializable {
    
    
    @FXML
    AnchorPane pane;
    private MailHomeController home;
    public void iniComponents(Double width, MailHomeController home){
       pane.setPrefWidth(width);
       this.home = home;
       
    }
    public void mousePressed(){
        pane.setStyle("");
        pane.setStyle("-fx-background-color:#ff9933;-fx-border-color:#000000");
    }
    
    public void mouseRleased(){
        pane.setStyle("-fx-background-color:#a7bbef;-fx-border-color:#000000");
         
    }
    
    public void clcked() throws IOException{
        mousePressed();
        home.viewMail();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
