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
import javafx.scene.control.TabPane;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getStyleClass().add("floating");
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Total Department Time", 60),
                        new PieChart.Data("His Time", 40)
                );
        
        pieChart.setTitle("Weekly Record");
        pieChart.setData(pieChartData);
    }    
    
}
