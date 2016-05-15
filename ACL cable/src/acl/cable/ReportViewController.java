/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.CostVerification;
import acl.cable.modal.Report.MachineEfficiency;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.Worker;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
    private static ArrayList<Factory> factorys;
    private static ArrayList<Worker> workers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            factorys = ClientConnector.getClientConnector().getFactoryController().getReportController().getAllFactorys();
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public ReportViewController() {
        
    }
    
    @FXML
    private void btnMachineEfficiencyClicked(MouseEvent event) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("LookupMachineEfficency.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("LookupMachineEfficency.fxml").openStream());
        LookupMachineEfficencyController controller = fx.<LookupMachineEfficencyController>getController();
        try {
            MachineEfficiency machineEfficiency = new MachineEfficiency();
            machineEfficiency.setDivision(factorys.get(0));
            machineEfficiency.setMachine(factorys.get(0).getMachines().get(0));
            machineEfficiency = (MachineEfficiency) ClientConnector.getClientConnector().getFactoryController().getReportController().getMachineEfficiencyReport(machineEfficiency);
            controller.setReports(machineEfficiency, factorys);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }

    @FXML
    private void btnCostVerificationClicked(MouseEvent event) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("CostVerification.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("CostVerification.fxml").openStream());
        CostVerificationController controller=fx.<CostVerificationController>getController();
        CostVerification cost=new CostVerification();
        cost.setDivision(factorys.get(0));
        cost.setMachine(factorys.get(0).getMachines().get(0));
        try {
            cost=(CostVerification) ClientConnector.getClientConnector().getFactoryController().getReportController().getCostVerificationReport(cost);
            controller.setData(cost,factorys);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }

    @FXML
    private void btnWorkingTimeClicked(MouseEvent event) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("LookUpWorkingTime.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("LookUpWorkingTime.fxml").openStream());
        LookUpWorkingTimeController controller = fx.<LookUpWorkingTimeController>getController();
        try {
            if (workers == null) {
                workers = ClientConnector.getClientConnector().getFactoryController().getReportController().getAllWorkers(((Engineer) MainWindowController.loggedUser).getDepartment());
            }
            WorkingTime workingTime = new WorkingTime();
            workingTime.setWorker(workers.get(0));
            workingTime=(WorkingTime) ClientConnector.getClientConnector().getFactoryController().getReportController().getWorkingTimeReport(workingTime);
            controller.setPane(workingTime, workers);
        } catch (NotBoundException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ReportViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }

    @FXML
    private void btnResponseTimeClicked(MouseEvent event) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("Response Time.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("Response Time.fxml").openStream());
        ResponseTimeController controller = fx.<ResponseTimeController>getController();
        controller.setPane();
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
    }

    @FXML
    private void btnFaultPercentageClicked(MouseEvent event) throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MonthlyReport.fxml"));
        
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        
        BorderPane pane = (BorderPane) fx.load(getClass().getResource("MonthlyReport.fxml").openStream());
        
        pane.setPrefWidth(vboxData.getWidth());
        pane.setPrefHeight(vboxData.getHeight());
        vboxData.getChildren().clear();
        vboxData.getChildren().add(pane);
        
    }
    
    @FXML
    private void btnBackButtonClicked(MouseEvent e) {
        MainWindowController.controller.setMainWindowControl2();
    }
}
