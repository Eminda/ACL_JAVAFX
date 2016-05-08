/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeWorkerDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnResign;
    @FXML
    private Button btnCreate;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setButtonPane(boolean removeAll,boolean isCreating,boolean removeResign){
        btnResign.setVisible(false);
        if(removeAll){
            btnCreate.setVisible(false);
        }else{
            if(isCreating){
                btnCreate.setText("Create");
            }else{
                btnCreate.setText("Edit");
                if (!removeResign) {
                    btnResign.setVisible(true);
                }
            }
        }
    }
    
}
