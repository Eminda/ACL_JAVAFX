/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.EmployeeTable;
import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.MechanicalDepartment;
import acl.cable.modal.comman.OIC;
import acl.cable.modal.comman.Worker;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EmployeeCurrentController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    private int val1=1;
    private int val2;
    public DBController dbc;
    public ObservableList ob ;
    @FXML private TableView<EmployeeTable> table;
    @FXML private TableColumn<EmployeeTable,String> name;
    @FXML private TableColumn<EmployeeTable,String> prefName;
    @FXML private TableColumn<EmployeeTable,String> nic;
    @FXML private TableColumn<EmployeeTable,String> epfno;
    @FXML private TableColumn<EmployeeTable,String> photo;
    
    @FXML ComboBox selectType;
    @FXML ComboBox selectDivision;
    @FXML Label Division;
    public EmployeeViewController view;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            System.out.println(table.getSelectionModel().getSelectedItem().getName());
            try {
                view.enableEdit(makeEmployee(table.getSelectionModel().getSelectedItem()));
            } catch (IOException ex) {
                Logger.getLogger(EmployeeCurrentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
});
    }
    public void init(){
        System.out.println("init is called");
        name.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("name"));
        prefName.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("prefName"));
        nic.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("nic"));
        epfno.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("epfno"));
        photo.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("photo"));
        table.setItems(ob);
    
    }
    
    public void initComponent(EmployeeViewController view){
        selectType.getItems().addAll(
                "All",
                "Engineer",
                "OIC",
                "Worker"
        );
        noDivision();
        this.view = view;
        
    }
    public void setObservable(ArrayList<EmployeeTable> user){
        ob = FXCollections.observableArrayList();
        for (EmployeeTable user1 : user) {
            ob.add(user1);
            System.out.println("user is added "+user1.getName());
        }
    }
    
    public void makeList() throws RemoteException, NotBoundException{
        System.out.println("make list is called");
        dbc = new DBController();
        setObservable(dbc.getEmployee(val1));
        init();    
    }
    
    public void makeFilteredList(){
        setObservable(dbc.getfliteredEmployee(val2));
        init();
    }
    private void noDivision(){
    this.Division.setVisible(false);
    this.selectDivision.setVisible(false);
    }
    private void Division(){
    this.Division.setVisible(true);
    this.selectDivision.setVisible(true);
    }
       
    private void initDepartment(){
        this.Division.setText("Select Department");
        selectDivision.getItems().clear();
        selectDivision.getItems().addAll(
                "All",
                "Electrical",
                "Mechanical"
        );
        Division();
        }
    private void initDivision(){
        this.Division.setText("Select Division");
        selectDivision.getItems().clear();
        selectDivision.getItems().setAll(
                "All",
                "Divi1",
                "Divi2"
        
        );
        Division();
    }
    @FXML
    public void slectType() throws RemoteException, NotBoundException{
        int val= selectType.getSelectionModel().getSelectedIndex();
        if (val==1) {
            initDepartment();
            val1=2;
            makeList();
            }
        else if(val==2) {initDivision();val1=3;makeList();}
        else if (val==3) {initDepartment();val1=4;makeList();}
        else {noDivision();val1=1;makeList();}
    }
    @FXML
    public void selectDivision() throws RemoteException, NotBoundException{
        int val= selectDivision.getSelectionModel().getSelectedIndex();
        if (val>-1) {val2=val;makeFilteredList();}
        
    }
    
  public Employee makeEmployee(EmployeeTable temp){
      Employee emp;
     if (temp.getPos().equals("Engineer")){emp = createEngineer(temp);}
     else if (temp.getPos().equals("OIC")){emp = createOIC(temp);}
     else emp = createWorker(temp);
     emp.setNIC(temp.getNic());
     emp.setPreferedName(temp.getPrefName());
     return emp;
  }
  private Engineer createEngineer(EmployeeTable emp){
      Engineer eng = new Engineer("",emp.getEpfno(),emp.getName(),null);
      Department dep;
      if("1".equals(emp.getDid())) dep = new ElectricalDepartment();
      else  dep = new MechanicalDepartment();
      dep.setId(Integer.parseInt(emp.getDid()));
      eng.setDepartment(dep);
      return eng;
  }
   private Worker createWorker(EmployeeTable emp){
      Worker eng = new Worker("",emp.getEpfno(),emp.getName(),null);
      Department dep;
      if("1".equals(emp.getDid())) dep = new ElectricalDepartment();
      else  dep = new MechanicalDepartment();
      dep.setId(Integer.parseInt(emp.getDid()));
      eng.setDepartment(dep);
      return eng;
  }
   
   private OIC createOIC(EmployeeTable emp){
      OIC eng = new OIC("",emp.getEpfno(),emp.getName(),null);
       Factory dep = new Factory();
      dep.setID(Integer.parseInt(emp.getDid()));
      eng.setFactor(dep);
      return eng;
  }
    
}
