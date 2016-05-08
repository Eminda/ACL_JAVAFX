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
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Isura Manchanayake
 */
public class FaultMaintenanceMessageController implements Initializable {
    @FXML
    AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setId("mainPane");
    }
    
    public AnchorPane getMainPane() {
        return mainPane;
    }
}
