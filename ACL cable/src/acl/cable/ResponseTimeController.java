/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.Connector.ClientConnector;
import acl.cable.modal.Report.DepartmentResponseTime;
import acl.cable.modal.Report.ResponseTime;
import acl.cable.modal.comman.ElectricalDepartment;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class ResponseTimeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChartElectrical;
    @FXML
    private PieChart pieChartMechanical;
    @FXML
    private Label responseTimeElectrical;
    @FXML
    private Label responseTimeMechanical;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setPane() {
        try {
            ResponseTime responseTime = (ResponseTime) ClientConnector.getClientConnector().getFactoryController().getReportController().getResponseTime();
            ObservableList<PieChart.Data> pieChartData = null;
            DepartmentResponseTime electrical=responseTime.getElectricalDepartmentResponseTime();
            if (electrical.getAverageResponseTime()!=0) {
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("<10 min", responseTime.getElectricalDepartmentResponseTime().getResponseLessThan10()),
                                new PieChart.Data("<20 min", responseTime.getElectricalDepartmentResponseTime().getResponseLessThan20()),
                                new PieChart.Data("other", responseTime.getElectricalDepartmentResponseTime().getOtherTime())
                        );
            }else{
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("", 100)
                               
                        );
            }

            pieChartElectrical.setData(pieChartData);
            ObservableList<PieChart.Data> pieChartData1 = null;
            if (responseTime.getMechanicalDepartmentResponseTime().getAverageResponseTime()!=0) {
                pieChartData1
                        = FXCollections.observableArrayList(
                                new PieChart.Data("<10 min", responseTime.getMechanicalDepartmentResponseTime().getResponseLessThan10()),
                                new PieChart.Data("<20 min", responseTime.getMechanicalDepartmentResponseTime().getResponseLessThan20()),
                                new PieChart.Data("other", responseTime.getMechanicalDepartmentResponseTime().getOtherTime())
                        );
            }else{
                 pieChartData1
                        = FXCollections.observableArrayList(
                                new PieChart.Data("", 100)
                               
                        );
            }
            pieChartMechanical.setData(pieChartData1);
            responseTimeElectrical.setText("Average Response Time : \t"+responseTime.getElectricalDepartmentResponseTime().getAverageResponseTime());
            responseTimeMechanical.setText("Average Response Time : \t"+responseTime.getMechanicalDepartmentResponseTime().getAverageResponseTime());
        } catch (NotBoundException ex) {
            Logger.getLogger(ResponseTimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ResponseTimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ResponseTimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
