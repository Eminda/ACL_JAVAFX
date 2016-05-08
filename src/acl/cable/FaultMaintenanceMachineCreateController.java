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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Isura Manchanayake
 */
public class FaultMaintenanceMachineCreateController implements Initializable {
    @FXML
    private ToggleGroup radioButtonGroup;
    @FXML
    private TextArea txtDescription;
    public static FaultMaintenanceMachineCreateController controller;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
    }
    
    public TextArea getTxtDesription() {
        return txtDescription;
    }
    
}
