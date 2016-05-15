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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Isura Manchanayake
 */
public class EngineerAssignedController implements Initializable {

    
    @FXML
    private Label lblName;
    @FXML
    private BorderPane borderPane;
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public Label getLblName() {
        return lblName;
    }
    
    public BorderPane borderPane() {
        return borderPane;
    }
    
}
