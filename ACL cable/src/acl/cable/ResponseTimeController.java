/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("<10 min", 20),
                        new PieChart.Data("<20 min", 30),
                        new PieChart.Data("other", 60)
                );
        
        pieChartElectrical.setData(pieChartData);
        ObservableList<PieChart.Data> pieChartData1
                = FXCollections.observableArrayList(
                        new PieChart.Data("<10 min", 40),
                        new PieChart.Data("<20 min", 60),
                        new PieChart.Data("other", 20)
                );
        pieChartMechanical.setData(pieChartData1);
    }    
    
}
