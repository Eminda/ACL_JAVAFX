/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.MachineEffieciencyFaultDetail;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.Report.WorkingTimeContribution;
import acl.cable.modal.Report.WorkingTimeFault;
import acl.cable.modal.Report.WorkingTimePercentage;
import acl.cable.modal.comman.Worker;
import acl.cable.validation.DatePickerValidations;
import acl.cable.validation.DateValidation;
import acl.cable.validation.SimpleValidation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class LookUpWorkingTimeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    @FXML
    private TabPane tabPane;
    @FXML
    private DatePicker from;
    @FXML
    private DatePicker to;
    @FXML
    private Button btnFind;
    @FXML
    private TextField txtWorkerTime;
    @FXML
    private TextField txtDepartmentTime;
    @FXML
    private TextField txtDepartmentAssigns;
    @FXML
    private TextField txtWorkerAssigns;
    @FXML
    private ComboBox comboChoose;
    @FXML
    private ComboBox comboWorker;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn job;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn time;
    @FXML
    private TableColumn machine;
    @FXML
    private TableColumn factory;
    @FXML
    private Label name;
    @FXML
    private Label epfNo;
    @FXML 
    private Label workerDetail;
    private ArrayList<Worker> workers;
    private WorkingTime workingTime;
    private final ObservableList<WorkingTimeFault> data
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getStyleClass().add("floating");
        DatePickerValidations.setDefaultFormat(to);
        to.setValue(LocalDate.now());
        DatePickerValidations.setDefaultFormat(from);
        id.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("id"));
        job.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("job"));
        date.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("time"));
        factory.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("factory"));
        machine.setCellValueFactory(new PropertyValueFactory<WorkingTimeFault, String>("machine"));
        comboChoose.getItems().clear();
        comboChoose.getItems().add("All");
        comboChoose.getItems().add("Faults Only");
        comboChoose.getItems().add("Maintenance Only");
        comboChoose.getSelectionModel().select(1);

        comboWorker.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (comboWorker.getValue() != null && !comboWorker.getValue().toString().isEmpty()) {
                    String str = comboWorker.getValue().toString().replaceAll("[^A-Za-z0-9]", "");
                    comboWorker.setValue(str);
                }
            }
        }
        );
    }

    void setPane(WorkingTime workingTime, ArrayList<Worker> workers) {
        this.workers = workers;
        comboWorker.getItems().clear();
        for (Worker worker : workers) {
            String foormatted = String.format("%20s%s%s", worker.getPreferedName(), " : ", worker.getEpfId());
            comboWorker.getItems().add(foormatted);
        }
        comboWorker.getSelectionModel().select(0);
        workerDetail.setText("Details of "+workers.get(0).getName());
        setPane(workingTime);
        from.setValue(DatePickerValidations.getLocalDateFromLongValue(workingTime.getFrom()));
        to.setValue(DatePickerValidations.getLocalDateFromLongValue(workingTime.getTo()));
    }

    private void setPane(WorkingTime workingTime) {
        this.workingTime = workingTime;
        setPercentage();
        setContribution();

    }

    private void setPercentage() {
        WorkingTimePercentage percentage = workingTime.getPercentage();
        txtWorkerTime.setText(DateValidation.getTimeFormated(percentage.getWorkerTime()));
        txtWorkerAssigns.setText(DateValidation.getTimeFormated(percentage.getWorkerAssigns()));
        txtDepartmentAssigns.setText(DateValidation.getTimeFormated(percentage.getDepartmentAssings()));
        txtDepartmentTime.setText(DateValidation.getTimeFormated(percentage.getDepartmentTime()));
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Total Department Time", percentage.getDepartmentTime() - percentage.getWorkerTime()),
                        new PieChart.Data("His Time", percentage.getWorkerTime())
                );

        pieChart.setTitle("Worker Vs Department");
        pieChart.setData(pieChartData);
    }

    private void setContribution() {
        ArrayList<WorkingTimeFault> faults = workingTime.getContribution().getFaults();
        data.clear();
        for (WorkingTimeFault fault : faults) {
            data.add(fault);
        }
        Worker worker = workers.get(comboWorker.getSelectionModel().getSelectedIndex());
        name.setText(worker.getName());
        epfNo.setText(worker.getEpfId());
    }

    @FXML
    private void setTableAccordingToChoice(ActionEvent evt) {
       
            int selectedIndex = comboChoose.getSelectionModel().getSelectedIndex();
            if (selectedIndex != workingTime.getContribution().getSearchType()) {
                workingTime.getContribution().setSearchType(selectedIndex);
                try {
                    workingTime = (WorkingTime) ClientConnector.getClientConnector().getFactoryController().getReportController().getWorkingTimeContribution(workingTime);
                    this.workingTime = workingTime;
                    setContribution();
                } catch (NotBoundException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       
    }

    @FXML
    private void setReport(MouseEvent evt) {
        setReport();
    }

    

    private void setReport() {
        int selectedIndex = comboWorker.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Worker worker = workers.get(selectedIndex);
            long from = DatePickerValidations.getLongDateFromString(this.from.toString());
            long to = DatePickerValidations.getLongDateFromString(this.from.toString());
            if (worker.getEpfId() != workingTime.getWorker().getEpfId() || from != workingTime.getFrom() || to != workingTime.getTo()) {
                workingTime.setWorker(worker);
                workingTime.setFrom(from);
                workingTime.setTo(to);
                workingTime.getContribution().setSearchType(comboChoose.getSelectionModel().getSelectedIndex());
                try {
                    workingTime = (WorkingTime) ClientConnector.getClientConnector().getFactoryController().getReportController().getWorkingTimeReport(workingTime);
                    setPane(workingTime);
                    workerDetail.setText("Details of "+worker.getName());
                } catch (NotBoundException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(LookUpWorkingTimeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("You haven't selected a worker properly");
            alert.setContentText("Please make sure that:\n"
                    + "If you searched for a worker that worker is properly selected");

            alert.showAndWait();
        }
    }

}
