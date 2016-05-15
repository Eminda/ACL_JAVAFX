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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeCreateEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane borderPaneEmployee;
    @FXML private ComboBox cmb;
    private EmployeeDetailController employeeDetailController;
    int val=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeDetail.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        try {

            BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeDetail.fxml"));

            borderPaneEmployee.setCenter(pane);
            this.employeeDetailController=EmployeeDetailController.controller;
            selectEng();
            initCmb();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeCreateEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    @FXML
    public void setEmployeePanelAccordingToComboChoose() throws IOException {
        val = cmb.getSelectionModel().getSelectedIndex();
        System.out.println(val);
        if (val==0) this.selectEng();
        else if (val==1)this.selectOIC();
        else this.selectWkr();
        
    }
    
    private void selectEng() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeEngineerDetail.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeEngineerDetail.fxml").openStream());
        fx.<EmployeeEngineerDetailController>getController().setButtonPane(false, true,true);
        employeeDetailController.setEmplyeeDetailPanel(pane);}
    private void selectOIC() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeOICdetail.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeOICdetail.fxml").openStream());
        fx.<EmployeeOICdetailController>getController().setButtonPane(false, true,true);
        employeeDetailController.setEmplyeeDetailPanel(pane);}
     private void selectWkr() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeWorkerDetail.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeWorkerDetail.fxml").openStream());
        fx.<EmployeeWorkerDetailController>getController().setButtonPane(false, true,true);
        employeeDetailController.setEmplyeeDetailPanel(pane);}
     
     private void initCmb(){
         this.cmb.getItems().addAll(
                 "Engineer",
                 "OIC",
                 "Worker"
         );
     }
}
