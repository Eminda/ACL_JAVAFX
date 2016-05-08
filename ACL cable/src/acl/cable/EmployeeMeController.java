/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeMeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane borderPaneEmployee;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setEmployeePanel();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeMeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void setEmployeePanel() throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeEngineerDetail.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeEngineerDetail.fxml").openStream());
        fx.<EmployeeEngineerDetailController>getController().setButtonPane(false, false,true);
        borderPaneEmployee.setCenter(pane);
    }
    
}
