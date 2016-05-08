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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Isura Manchanayake
 */
public class FaultMaintenanceCompletionInfoController implements Initializable {

    /**
     * Initialises the controller class.
     */
    
    @FXML
    TextField txtDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public TextField getTxt() {
        return txtDate;
    }
    
}
