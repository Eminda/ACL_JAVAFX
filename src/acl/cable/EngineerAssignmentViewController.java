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
import javafx.scene.layout.VBox;

/**
 *
 * @author Isura Manchanayake
 */
public class EngineerAssignmentViewController implements Initializable {

    @FXML
    private VBox vBoxInside;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public VBox getVBoxInside() {
        return vBoxInside;
    }

}
