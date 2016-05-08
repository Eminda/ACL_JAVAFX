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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Isura Manchanayake
 */
public class FaultMaintenanceMessageController implements Initializable {

    @FXML
    AnchorPane mainPane;
    @FXML
    Button btnView;
    FaultReportController parentController;
    static int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*btnView.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("dfsdfsdf");
                    }
                }
        );*/
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    @FXML
    public void btnViewClicked() {
        //System.out.println(parentController.getVBoxData());
        //parentController.getVBoxData().getChildren().add(getNodeFromResource("FaultMaintenanceDescription_1.fxml"));
    }

    public Button getBtnView() {
        return btnView;
    }

    public void setParentController(FaultReportController controller) {
        this.parentController = controller;
    }

    public void addNewEventHandlerBtnView(ButtonViewActionListener eventHandler) {
        eventHandler.setButton(btnView);
        btnView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
    
    public void addNewEventHandlerBtnView() {
        ButtonViewActionListener eventHandler = new ButtonViewActionListener();
        eventHandler.setButton(btnView);
        btnView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
    
    private <T> T getNodeFromResource(String name) {
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getClassLoader().getResource(name));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        T t = null;
        try {
            t = (T) fx.load(getClass().getResource(name).openStream());
        } catch (IOException ex) {
            Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
