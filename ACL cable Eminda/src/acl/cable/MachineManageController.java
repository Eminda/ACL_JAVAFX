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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class MachineManageController implements Initializable {

    
    @FXML
    private VBox vboxData;
    @FXML
    private BorderPane border;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void btnCreateClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MachineManageCreate.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane = (BorderPane) fx.load(getClass().getResource("MachineManageCreate.fxml").openStream());
        pane.setPrefSize(border.getWidth(), border.getHeight());

      border.setCenter(pane);
    }
    
    @FXML
    private void btnviwe() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("MachineManageView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("MachineManageView.fxml"));
        pane.setPrefSize(border.getWidth(), border.getHeight());
        border.setCenter(pane);
        
        
    }
    @FXML
    private void btnBackButtonClicked(){
        MainWindowController.controller.setMainWindowControl2();
    }
    
    
}
