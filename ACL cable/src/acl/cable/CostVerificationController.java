/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.CostVerification;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class CostVerificationController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private PieChart pieChartBreakDown;
    @FXML
    private PieChart pieChartCost;
    @FXML
    private DatePicker from;
    @FXML
    private DatePicker to;
    @FXML
    private Button btnFind;
    @FXML
    private ComboBox comboDiv;
    @FXML
    private ComboBox comboMachine;
    @FXML
    private TextField totalBreakDown;
    @FXML
    private TextField electricalBreakDown;
    @FXML
    private TextField mechanicalBreakDown;
    @FXML
    private TextField cost;
    @FXML
    private TextField electricalCost;
    @FXML
    private TextField mechanicalCost;
    @FXML
    private Label lblCost;
    private ArrayList<Factory> factorys;
    private CostVerification costVerification;
    private boolean comboSearchLock;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatePickerValidations.setDefaultFormat(to);
        DatePickerValidations.setDefaultFormat(from);
        


        
    }    

    void setData(CostVerification cost, ArrayList<Factory> factorys) {
        this.factorys=factorys;
        costVerification.setDivision(factorys.get(0));
        costVerification.setMachine(factorys.get(0).getMachines().get(0));
        setData(cost);
        comboDiv.getSelectionModel().select(0);
        comboMachine.getSelectionModel().select(0);
        comboSearchLock=false;
        comboDiv.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (!comboSearchLock) {
                    if (comboDiv.getValue() != null
                            && !comboDiv.getValue().toString().isEmpty()) {
                        int index = comboDiv.getSelectionModel().getSelectedIndex();
                        ArrayList<Machine> machines = factorys.get(index).getMachines();
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

    private void setData(CostVerification costVerification) {
        this.costVerification=costVerification;
        comboSearchLock = true;
        comboDiv.getItems().clear();
        for (Factory factory : factorys) {
            comboDiv.getItems().add(factory.getName());
        }
        ArrayList<Machine> machines = factorys.get(0).getMachines();
        comboMachine.getItems().clear();
        for (Machine machine : machines) {
            comboMachine.getItems().add(machine.getName());
        }
        
        comboSearchLock=false;
        from.setValue(DatePickerValidations.getLocalDateFromLongValue(costVerification.getFrom()));
        to.setValue(DatePickerValidations.getLocalDateFromLongValue(costVerification.getTo()));
        totalBreakDown.setText(DateValidation.getTimeFormated(costVerification.getTotalBreakDown()));
        electricalBreakDown.setText(DateValidation.getTimeFormated(costVerification.getElectricalBreakDown()));
        mechanicalBreakDown.setText(DateValidation.getTimeFormated(costVerification.getMechanicalBreakDown()));
        cost.setText(SimpleValidation.getSimpleDouble(costVerification.getTotalCost()));
        electricalCost.setText(SimpleValidation.getSimpleDouble(costVerification.getElectricalCost()));
        mechanicalCost.setText(SimpleValidation.getSimpleDouble(costVerification.getMechanicalCost()));
        lblCost.setText(costVerification.getMachine().getName()+" of "+((Factory)costVerification.getDivision()).getName());
        ObservableList<PieChart.Data> pieChartDataBreakDown = null;
        if (costVerification.getElectricalBreakDown()!=0 || costVerification.getMechanicalBreakDown()!=0) {
            pieChartDataBreakDown
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Electrical ", costVerification.getElectricalBreakDown()),
                            new PieChart.Data("Mechanical", costVerification.getMechanicalBreakDown())
                    );
        }else{
            pieChartDataBreakDown
                    = FXCollections.observableArrayList(
                            new PieChart.Data("No Break down ", costVerification.getElectricalBreakDown())
                    );
        }

        pieChartBreakDown.setTitle("Department Vs Breakdown");
        pieChartBreakDown.setData(pieChartDataBreakDown);
        ObservableList<PieChart.Data> pieChartDataCost=null;
        if (costVerification.getElectricalCost()!=0 || costVerification.getMechanicalCost()!=0) {
            pieChartDataCost
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Electrical ", costVerification.getElectricalCost()),
                            new PieChart.Data("Mechanical", costVerification.getMechanicalCost())
                    );
        }else{
            pieChartDataCost
                    = FXCollections.observableArrayList(
                            new PieChart.Data("No Break down ", costVerification.getElectricalCost())
                    );
        }

        pieChartBreakDown.setTitle("Department Vs Cost");
        pieChartBreakDown.setData(pieChartDataBreakDown);
    }
    @FXML
    private void btnFindClicked(MouseEvent evt){
        int selectedIndexMachine=comboMachine.getSelectionModel().getSelectedIndex();
        int selectedIndexFactory=comboDiv.getSelectionModel().getSelectedIndex();
        Factory f = null;
        Machine m=null;
        if (comboDiv.getValue() != null && !comboDiv.getValue().toString().isEmpty()) {
            f=factorys.get(selectedIndexFactory);
            if(comboMachine.getValue()!=null && !comboMachine.getValue().toString().isEmpty()){
                m = f.getMachines().get(selectedIndexMachine);
            }
        }
        if (f!=null & m!=null) {
            long from = DatePickerValidations.getLongDateFromString(this.from.toString());
            long to = DatePickerValidations.getLongDateFromString(this.to.toString());
            long today = DatePickerValidations.getLongDateFromString(DatePickerValidations.getDateStringFromLocalDate(LocalDate.now()));
            if (to > from && to <= today) {
                costVerification.setMachine(m);
                costVerification.setDivision(f);
                costVerification.setFrom(from);
                costVerification.setTo(to);
                try {
                    costVerification = (CostVerification) ClientConnector.getClientConnector().getReportController().getCostVerificationReport(costVerification);
                    setData(costVerification);
                } catch (NotBoundException ex) {
                    Logger.getLogger(CostVerificationController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CostVerificationController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(CostVerificationController.class.getName()).log(Level.SEVERE, null, ex);
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
                    this.to.setValue(LocalDate.now());
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You must properly select both Factory and Machine");
            alert.setContentText("Please note that:\n"
                    + "If you searched for a factory or machine make sure that one of machine or factory is selected");

            alert.showAndWait();
        }
    }
    
}
