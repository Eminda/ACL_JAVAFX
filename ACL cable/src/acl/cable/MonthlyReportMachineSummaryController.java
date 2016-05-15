/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.modal.Report.MonthlyReportDivision;
import acl.cable.modal.Report.MonthlyReportRecord;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class MonthlyReportMachineSummaryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn no;
    @FXML
    private TableColumn machine;
    @FXML
    private TableColumn totalTime;
    @FXML
    private TableColumn totalBreakDownTime;
    @FXML
    private TableColumn electricalBreakDown;
    @FXML
    private TableColumn mechanicalBreakDown;
    @FXML
    private TableColumn electricalBreakDownPercentage;
    @FXML
    private TableColumn mechanicalBreakDownPercentage;
    private final ObservableList<MonthlyReportRecord> data
            = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        no.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("no"));
        machine.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("machine"));
        totalTime.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("totalTime"));
        totalBreakDownTime.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("totalBreakDownTime"));
        electricalBreakDown.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("electricalBreakdownTime"));
        mechanicalBreakDown.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("mechanicalBreakdownTime"));
        electricalBreakDownPercentage.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("electricalBreakDownPrecentage"));
        mechanicalBreakDownPercentage.setCellValueFactory(new PropertyValueFactory<MonthlyReportRecord, String>("mechanicalBreakDownPrecentage"));
        
    }    

    void setData(MonthlyReportDivision division) {
        ArrayList<MonthlyReportRecord> records=division.getAllRecords();
        data.clear();
        for (int i = 0; i < records.size(); i++) {
            MonthlyReportRecord report = records.get(i);
            report.setNo(i+1);
            data.add(report);
        }
    }
    
}
