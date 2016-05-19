/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.Dialog;
import UeserController.EmployeeTable;
import UeserController.ResignedEmployee;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EmployeeResignedViewController implements Initializable {

    @FXML ComboBox employeeType;
    @FXML TextField fName;
    @FXML TextField fNic;
    @FXML TextField fEpfno;
    @FXML Button bCreate;
    @FXML Button bRjoin;
    public DBController dbc;
    public ObservableList ob ;
    @FXML private TableView<ResignedEmployee> table;
    @FXML private TableColumn<ResignedEmployee,String> name;
    @FXML private TableColumn<ResignedEmployee,String> prefName;
    @FXML private TableColumn<ResignedEmployee,String> nic;
    @FXML private TableColumn<ResignedEmployee,String> epfno;
    @FXML private TableColumn<ResignedEmployee,String> resigneddate;
    EmployeeViewController ctrl;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() ) {
            System.out.println(table.getSelectionModel().getSelectedItem().getName());
            bRjoin.setDisable(false);
           
            
        }
    }
});
        
        
        initComponent();
        this.bRjoin.setDisable(true);
        employeeType.getSelectionModel().select(0);
        try {
            bttnFliter();
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeResignedViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(EmployeeResignedViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void setView(EmployeeViewController ctrl){
        this.ctrl = ctrl;
    }
    
    public void init(){
        System.out.println("init is called");
        name.setCellValueFactory(new PropertyValueFactory<ResignedEmployee,String>("name"));
        prefName.setCellValueFactory(new PropertyValueFactory<ResignedEmployee,String>("prefName"));
        nic.setCellValueFactory(new PropertyValueFactory<ResignedEmployee,String>("nic"));
        epfno.setCellValueFactory(new PropertyValueFactory<ResignedEmployee,String>("epfno"));
        resigneddate.setCellValueFactory(new PropertyValueFactory<ResignedEmployee,String>("resigneddate"));
        table.setItems(ob);
        }
    
     public void setObservable(ArrayList<ResignedEmployee> user){
        ob = FXCollections.observableArrayList();
        for (ResignedEmployee user1 : user) {
            ob.add(user1);
            System.out.println("user is added "+user1.getResigneddate());
            }}
     
     public void makeList() throws RemoteException, NotBoundException{
        System.out.println("make list is called");
        dbc = new DBController();
        setObservable(dbc.getretiredEmployee(employeeType.getSelectionModel().getSelectedIndex(),fName.getText(),fNic.getText(),fEpfno.getText()));
        init();    
    }
      public void initComponent(){
        employeeType.getItems().addAll(
                "All",
                "Engineer",
                "OIC",
                "Worker"
        );}
    public void bttnFliter() throws RemoteException, NotBoundException{
        ;
        makeList();
        System.out.println(employeeType.getSelectionModel().getSelectedIndex());
    }
    @FXML public void buttonClickedRejoin() throws IOException{
        makeRejoin(table.getSelectionModel().getSelectedItem().getEpfno());
    }
    public void makeRejoin(String epf) throws RemoteException, IOException{
        dbc.rejoinEmployee(epf);
        ctrl.showResignedTable();
        Dialog.showInfo("Success", "Rejoin Success");
    }
}
