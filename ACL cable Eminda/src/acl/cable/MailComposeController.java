/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class MailComposeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("qqqqqqqq");
        scrollPane.setStyle("-fx-background: rgb(167,187,239);");
    }    
    
}
