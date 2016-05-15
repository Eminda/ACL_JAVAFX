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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class ReportViewController implements Initializable {
    
    @FXML
    private VBox vboxData;

    @FXML
    private Button btnBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }   

    public ReportViewController() {
        
    }
    
    @FXML
    private void btnMachineEfficiencyClicked(MouseEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("LookupMachineEfficency.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("LookupMachineEfficency.fxml").openStream());
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }
    @FXML
    private void btnCostVerificationClicked(MouseEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("CostVerification.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("CostVerification.fxml").openStream());
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }
    @FXML
    private void btnWorkingTimeClicked(MouseEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("LookUpWorkingTime.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("LookUpWorkingTime.fxml").openStream());
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }
    @FXML
    private void btnResponseTimeClicked(MouseEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("Response Time.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("Response Time.fxml").openStream());
        ResponseTimeController controller = fx.getController();
        System.out.println(controller);
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }
    @FXML
    private void btnFaultPercentageClicked(MouseEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("MonthlyReport.fxml"));
        
        fx.setBuilderFactory(new JavaFXBuilderFactory());

        BorderPane pane=(BorderPane)fx.load(getClass().getResource("MonthlyReport.fxml").openStream());
        
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
        

        
    }

    @FXML
    private void btnBackButtonClicked(MouseEvent e){
        MainWindowController.controller.setMainWindowControl2();
    }
}
