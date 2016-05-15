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
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeDetailController implements Initializable {
    public static EmployeeDetailController controller;    
    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane borderPaneEmployee;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller=this;
    }    

    void setEmplyeeDetailPanel(Node pane) {
        borderPaneEmployee.setCenter(pane);
    }
    
}
