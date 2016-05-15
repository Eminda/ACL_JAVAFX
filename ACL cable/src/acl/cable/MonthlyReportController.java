/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.MonthlyReport;
import acl.cable.modal.Report.MonthlyReportDivision;
import acl.cable.modal.Report.Report;
import acl.cable.modal.comman.Machine;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class MonthlyReportController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TabPane tabPane;
    @FXML
    private ComboBox comboYear;
    @FXML
    private ComboBox comboMonth;
    private String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int oldestYear;
    private int oldestMonth;
    private MonthlyReport monthlyReport;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getStyleClass().add("floating");
    }
    public void setData(MonthlyReport monthlyReport){
        int currentYear=Calendar.getInstance().get(Calendar.YEAR);
        oldestYear=monthlyReport.getYear();
        oldestMonth=monthlyReport.getMonth();
        for (int i = monthlyReport.getYear(); i <= currentYear; i++) {
            comboYear.getItems().add(i);
        }
        comboYear.getSelectionModel().selectLast();
        setMonth();
        comboYear.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                
                    if (comboYear.getValue() != null
                            && !comboYear.getValue().toString().isEmpty()) {
                        setMonth();
                    }
                
            }

        });
    }
    private void setMonth(){
        int year=(int) comboYear.getSelectionModel().getSelectedItem();
        if(year==oldestYear){
            comboMonth.getItems().clear();
            int endMonth=12;
            if(oldestYear==Calendar.getInstance().get(Calendar.YEAR)){
                endMonth=oldestMonth;
            }
            for (int i = oldestMonth-1; i < 12; i++) {
                comboMonth.getItems().add(monthNames[i]);
            }
        }else if(year==Calendar.getInstance().get(Calendar.YEAR)){
            for (int i = 0; i <=Calendar.getInstance().get(Calendar.MONTH); i++) {
                comboMonth.getItems().add(monthNames[i]);
            }
        }
    }
    private BorderPane getTableForFactory() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("MonthlyReportMachineSummary.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane= (BorderPane)fx.load(getClass().getResource("MonthlyReportMachineSummary.fxml"));
        //pane.setPrefHeight(scrollPaneACF.getHeight());
        return pane;
    }
    @FXML
    private void viewButtonClicked(MouseEvent e){
        int selectedYear=(int) comboYear.getSelectionModel().getSelectedItem();
        String selectedMonth=(String) comboMonth.getSelectionModel().getSelectedItem();
        int month=0;
        for (int i = 0; i < monthNames.length; i++) {
            String monthName = monthNames[i];
            if(monthName.equals(selectedMonth)){
                month=i+1;
                break;
            }
        }
        MonthlyReport monthlyReport=new MonthlyReport();
        monthlyReport.setYear(selectedYear);
        monthlyReport.setMonth(month);
        try {
            this.monthlyReport=(MonthlyReport) ClientConnector.getClientConnector().getReportController().getMonthlyReport(monthlyReport);
            if(monthlyReport.getDivisions().size()>0){
                setDivisions();
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void setDivisions() throws IOException {
        ArrayList<MonthlyReportDivision> divisions=monthlyReport.getDivisions();
        tabPane.getTabs().clear();
        for (MonthlyReportDivision division : divisions) {
            FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("MonthlyReportMachineSummary.fxml"));
            fx.setBuilderFactory(new JavaFXBuilderFactory());
            BorderPane pane=(BorderPane)fx.load(getClass().getResource("MonthlyReportMachineSummary.fxml").openStream());
            MonthlyReportMachineSummaryController controller=fx.<MonthlyReportMachineSummaryController>getController();
            controller.setData(division);
            Tab tab=new Tab(division.getFactory().getShortName(), pane);
            tabPane.getTabs().add(tab);
        }
    }

}
