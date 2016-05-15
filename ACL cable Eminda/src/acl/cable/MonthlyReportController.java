/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ScrollPane;
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
    private BorderPane borderPaneACF;
    @FXML
    private BorderPane borderPaneCCF;
    @FXML
    private BorderPane borderPaneFCF;
    @FXML
    private BorderPane borderPaneDNYF;
    @FXML
    private TabPane tabPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
        tabPane.getStyleClass().add("floating");
    }
    public void setData(){
        try {
            borderPaneACF.setCenter(getTableForFactory());
            borderPaneCCF.setCenter(getTableForFactory());
            borderPaneFCF.setCenter(getTableForFactory());
            borderPaneDNYF.setCenter(getTableForFactory());
        } catch (IOException ex) {
            Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }

}
