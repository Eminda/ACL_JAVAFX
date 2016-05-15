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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getStyleClass().add("floating");
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Breakdown", 30),
                        new PieChart.Data("No Breakdown", 70)
                );
        XYChart .Series series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 2));
        series.getData().add(new XYChart.Data<>("March", 23));
        series.getData().add(new XYChart.Data("April", 2));
        series.getData().add(new XYChart.Data<>("May", 23));
        series.getData().add(new XYChart.Data("June", 2));
        series.getData().add(new XYChart.Data<>("July", 23));
        series.getData().add(new XYChart.Data("Augest", 2));
        series.getData().add(new XYChart.Data<>("September", 23));
        series.getData().add(new XYChart.Data("November", 2));
        lineChart.getData().add(series);
        series.setName("Number of Faults");
        pieChart.setTitle("Weekly Record");
        pieChart.setData(pieChartData);
        //pieChart.setPrefWidth(borderChartAndData.getWidth()*0.7);
    }

}
