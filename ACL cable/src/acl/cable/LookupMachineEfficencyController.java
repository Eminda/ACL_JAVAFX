/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.MachineEfficiency;
import acl.cable.modal.Report.MachineEfficiencyFaults;
import acl.cable.modal.Report.MachineEfficiencyPercentage;
import acl.cable.modal.Report.MachineEfficiencyTimeLine;
import acl.cable.modal.Report.MachineEffieciencyFaultDetail;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.Machine;
import acl.cable.validation.DatePickerValidations;
import acl.cable.validation.DateValidation;
import acl.cable.validation.SimpleValidation;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class LookupMachineEfficencyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    @FXML
    private BorderPane borderChartAndData;
    @FXML
    private TabPane tabPane;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private DatePicker dateFromPercentage;
    @FXML
    private DatePicker dateToPercentage;
    @FXML
    private DatePicker dateFromFault;
    @FXML
    private DatePicker dateToFault;
    @FXML
    private TextField txttotalTime;
    @FXML
    private TextField txtbreakDownTime;
    @FXML
    private TextField txtCost;
    @FXML
    private ComboBox comboDiv;
    @FXML
    private ComboBox comboMachine;
    @FXML
    private Button btnFindReport;
    @FXML
    private Button btnFindPercentage;
    @FXML
    private Button btnFindFaults;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn job;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn time;
    @FXML
    private TableColumn isElectrical;
    @FXML
    private TableColumn isMechanical;
    @FXML
    private TableColumn electricalWorkers;
    @FXML
    private TableColumn mechanicalWorkers;
    @FXML
    private TableColumn cost;
    @FXML
    private Label machineDetail;
    private boolean comboSearchLock = true;

    private MachineEfficiency machineEfficiency;
    private ArrayList<Factory> factories;
    private final ObservableList<MachineEffieciencyFaultDetail> data
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getStyleClass().add("floating");
        DatePickerValidations.setDefaultFormat(dateToFault);
        dateToFault.setValue(LocalDate.now());
        DatePickerValidations.setDefaultFormat(dateToPercentage);
        DatePickerValidations.setDefaultFormat(dateFromFault);
        DatePickerValidations.setDefaultFormat(dateFromPercentage);
        dateFromPercentage.setValue(LocalDate.now());

        id.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("id"));
        job.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("job"));
        date.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("time"));
        isElectrical.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("isElectrical"));
        isMechanical.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("isMechanical"));
        electricalWorkers.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("noOfElectricalWorkers"));
        mechanicalWorkers.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("noOfMechanicalWorkers"));
        cost.setCellValueFactory(new PropertyValueFactory<MachineEffieciencyFaultDetail, String>("cost"));

    }

    public void setReports(MachineEfficiency machineEfficiency, ArrayList<Factory> factorys) {
        this.factories = factorys;
        setReports(machineEfficiency);
        comboSearchLock = true;
        comboDiv.getItems().clear();
        for (Factory factory : factories) {
            comboDiv.getItems().add(factory.getName());
        }
        ArrayList<Machine> machines = factories.get(0).getMachines();
        comboMachine.getItems().clear();
        for (Machine machine : machines) {
            comboMachine.getItems().add(machine.getName());
        }

        comboSearchLock = false;
        comboDiv.getSelectionModel().select(0);
        comboMachine.getSelectionModel().select(0);
        machineDetail.setText(factorys.get(0).getMachines().get(0).getName() + " of " + factorys.get(0).getShortName());
        comboSearchLock = false;

        comboDiv.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (!comboSearchLock) {
                    if (comboDiv.getValue() != null
                            && !comboDiv.getValue().toString().isEmpty()) {
                        int index = comboDiv.getSelectionModel().getSelectedIndex();
                        ArrayList<Machine> machines = factories.get(index).getMachines();
                        comboMachine.getItems().clear();
                        for (Machine machine : machines) {
                            comboMachine.getItems().add(machine);
                        }
                    }
                }
            }

        });
        comboDiv.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (comboDiv.getValue() != null && !comboDiv.getValue().toString().isEmpty()) {
                    String str = comboDiv.getValue().toString().replaceAll("[^A-Za-z0-9]", "");
                    comboDiv.setValue(str);
                }
            }
        }
        );
        comboMachine.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (comboMachine.getValue() != null && !comboMachine.getValue().toString().isEmpty()) {
                    String str = comboMachine.getValue().toString().replaceAll("[^A-Za-z0-9]", "");
                    comboMachine.setValue(str);
                }
            }
        }
        );
    }

    private void setReports(MachineEfficiency machineEfficiency) {
        this.machineEfficiency = machineEfficiency;
        setPercentageReport();
        setTimeLine();
        setFaults();
    }

    private void setPercentageReport() {
        MachineEfficiencyPercentage percentage = machineEfficiency.getPercentage();
        dateFromPercentage.setValue(DatePickerValidations.getLocalDateFromLongValue(percentage.getFrom()));
        txttotalTime.setText(DateValidation.getTimeFormated(percentage.getTotalTime()));
        txtbreakDownTime.setText(DateValidation.getTimeFormated(percentage.getBreakDownTime()));
        txtCost.setText(SimpleValidation.getSimpleDouble(percentage.getCost()));
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Breakdown", percentage.getBreakDownTime()),
                        new PieChart.Data("No Breakdown", percentage.getTotalTime() - percentage.getBreakDownTime())
                );
        pieChart.setTitle("BreakDown Time Vs Total Time");
        pieChart.setData(pieChartData);
    }

    private void setTimeLine() {
        MachineEfficiencyTimeLine timeLine = machineEfficiency.getTimeLine();
        Map<String, Integer> xyData = timeLine.getXYData();
        XYChart.Series series = new XYChart.Series<>();
        lineChart.getData().add(series);
        if (xyData.size() == 0) {
            String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            series.getData().add(new XYChart.Data<>(monthNames[Calendar.getInstance().get(Calendar.MONTH)], 0));
        } else {
            Set<String> keySet = xyData.keySet();
            for (String key : keySet) {
                series.getData().add(new XYChart.Data<>(key, xyData.get(key)));
            }
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private void setFaults() {
        MachineEfficiencyFaults faults = machineEfficiency.getFaults();
        dateFromFault.setValue(DatePickerValidations.getLocalDateFromLongValue(faults.getFrom()));
        dateToFault.setValue(DatePickerValidations.getLocalDateFromLongValue(faults.getTo()));
        data.clear();
        ArrayList<MachineEffieciencyFaultDetail> faultDetails = faults.getFaults();
        for (MachineEffieciencyFaultDetail fault : faultDetails) {
            data.add(fault);
        }
    }

    @FXML
    private void btnFindReportClicked(MouseEvent evt) {
        int selectedFactory = comboDiv.getSelectionModel().getSelectedIndex();
        int selectedMachine = comboDiv.getSelectionModel().getSelectedIndex();
        if (selectedFactory != -1 && selectedMachine != -1) {
            setWholeReport(factories.get(selectedFactory), factories.get(selectedFactory).getMachines().get(selectedMachine));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You must properly select both Factory and Machine");
            alert.setContentText("Please note that:\n"
                    + "If you searched for a factory or machine make sure that one of machine or factory is selected");

            alert.showAndWait();
        }

    }

    private void setWholeReport(Factory f, Machine m) {
        machineEfficiency.setDivision(f);
        machineEfficiency.setMachine(m);
        try {
            machineEfficiency = (MachineEfficiency) ClientConnector.getClientConnector().getFactoryController().getReportController().getMachineEfficiencyFaults(machineEfficiency);
            setReports(machineEfficiency);
            machineDetail.setText(m.getName() + " of " + f.getShortName());
        } catch (NotBoundException ex) {
            Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnFindPercentageReportClicked(MouseEvent evt) {
        long from = DatePickerValidations.getLongDateFromString(dateFromPercentage.toString());
        long to = DatePickerValidations.getLongDateFromString(dateToPercentage.toString());
        int selectedFactory = comboDiv.getSelectionModel().getSelectedIndex();
        int selectedMachine = comboDiv.getSelectionModel().getSelectedIndex();
        Factory f = null;
        Machine m = null;
        if (selectedFactory != -1 && selectedMachine != -1) {
            f = factories.get(selectedFactory);
            m = f.getMachines().get(selectedMachine);
        }
        if ((f != null && m != null && ((Factory) machineEfficiency.getDivision()).getDIID() == f.getDIID() && machineEfficiency.getMachine().getId() == m.getId()) || f == null || m==null) {
            if (to > from & DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now())) >= to) {
                MachineEfficiencyPercentage percentage = machineEfficiency.getPercentage();
                percentage.setFrom(from);
                percentage.setTo(to);
                try {
                    this.machineEfficiency = (MachineEfficiency) ClientConnector.getClientConnector().getFactoryController().getReportController().getMachineEfficiencyPercentage(machineEfficiency);
                    setPercentageReport();
                } catch (NotBoundException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("There is a problem with from and to date selection");
                alert.setContentText("Please note that:\n"
                        + "From date must be less than To date\n"
                        + "and"
                        + "To date can not exceed today");

                alert.showAndWait();
                if (DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now())) < to) {
                    dateToPercentage.setValue(LocalDate.now());
                }
            }
        } else {
            if (to > from & DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now())) >= to) {
                machineEfficiency.getPercentage().setFrom(from);
                machineEfficiency.getPercentage().setTo(to);
                setWholeReport(f, m);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("There is a problem with from and to date selection");
                alert.setContentText("Please note that:\n"
                        + "From date must be less than To date\n"
                        + "and"
                        + "To date can not exceed today");

                alert.showAndWait();
            }
        }

    }

    @FXML
    private void btnFindFaultsClicked(MouseEvent evt) {
        long from = DatePickerValidations.getLongDateFromString(dateFromFault.toString());
        long to = DatePickerValidations.getLongDateFromString(dateToFault.toString());
        if (to > from & DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now())) >= to) {
            MachineEfficiencyFaults faults = machineEfficiency.getFaults();
            Machine m = null;
            Factory f = null;
            int selectedMachineIndex = comboMachine.getSelectionModel().getSelectedIndex();
            int selectedFactoryIndex = comboDiv.getSelectionModel().getSelectedIndex();
            if (selectedFactoryIndex != -1 && selectedMachineIndex != -1) {
                f = factories.get(selectedFactoryIndex);
                m = f.getMachines().get(selectedMachineIndex);
            }
            faults.setFrom(from);
            faults.setTo(to);
            if ((f != null && m != null && ((Factory) machineEfficiency.getDivision()).getDIID() == f.getDIID() && machineEfficiency.getMachine().getId() == m.getId()) || f==null || m==null) {
                try {
                    machineEfficiency = (MachineEfficiency) ClientConnector.getClientConnector().getFactoryController().getReportController().getMachineEfficiencyFaults(machineEfficiency);
                    setFaults();
                } catch (NotBoundException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(LookupMachineEfficencyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                setWholeReport(f, m);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There is a problem with from and to date selection");
            alert.setContentText("Please note that:\n"
                    + "From date must be less than To date\n"
                    + "and"
                    + "To date can not exceed today");

            alert.showAndWait();
            if (DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now())) < to) {
                dateToFault.setValue(LocalDate.now());
            }
        }
    }

}
