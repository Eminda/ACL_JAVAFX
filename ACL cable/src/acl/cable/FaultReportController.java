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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class FaultReportController implements Initializable {

    /**
     * Initialises the controller class.
     */
    public static FaultReportController controller;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private FlowPane pnlData;
    @FXML
    private ScrollPane pnlScrlData;
    @FXML
    private AnchorPane pnlAnchrDataOut;
    @FXML
    private AnchorPane pnlAnchrDataIn;
    @FXML
    private VBox vboxData;
    @FXML
    private FlowPane pnlFlowData;
    @FXML
    Button btnFake;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pnlAnchrDataOut.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                pnlAnchrDataIn.setPrefWidth(pnlAnchrDataOut.getWidth());
                pnlScrlData.setPrefWidth(pnlAnchrDataOut.getWidth());
                vboxData.setPrefWidth(pnlAnchrDataOut.getWidth() - 10);
            }
        });

        pnlAnchrDataOut.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                pnlAnchrDataIn.setPrefHeight(pnlAnchrDataOut.getHeight());
                pnlScrlData.setPrefHeight(pnlAnchrDataOut.getHeight());
                vboxData.setPrefHeight(pnlAnchrDataOut.getHeight());
            }
        });

        pnlScrlData.setContent(vboxData);

        vboxData.setSpacing(10);
        //try {
/*            
         for (int i = 0; i < 20; i++) {
         VBox vbox = new VBox();
         vbox.setPrefHeight(30);
         vbox.getChildren().add(new Label("brief message"));

         TitledPane t = new TitledPane();
         t.setText("message");

         //VBox inside = new VBox();
         //inside.getChildren().add(new Label("message description"));
         //Label priority = new Label("Priority level: High");
         //priority.setStyle("-fx-text-fill:#FF0000");
         //inside.getChildren().add(priority);
         //t.setContent(inside);
         //t.setContent(FXMLLoader.load("FaultMaintenanceMessage.fxml"));
         VBox inside = new VBox();
         //try {
         /*Parent root = null;
         try {
         root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"));
         } catch (IOException ex) {
         Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
         }
         Stage stage = new Stage();
         stage.setTitle("My New Stage Title");
         stage.setScene(new Scene(root));*/
        /*FXMLLoader fxl = new FXMLLoader();
         root = (BorderPane) fxl.load(getClass().getClassLoader().getResource("FaultMaintenanceMessage.fxml"));
         FaultMaintenanceMessageController f = (FaultMaintenanceMessageController) fxl.getController();
         node = root.lookup("#mainPane");*/
        //   BorderPane bp = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("FaultMaintenanceMessage.fxml"));
        //inside.getChildren().add(bp);
        try {
            //} catch (IOException ex) {
            //Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
            //}
            //t.setContent(inside);
            /*      Accordion a = new Accordion();
             a.getPanes().addAll(t);
             vbox.getChildren().add(a);
             vboxData.getChildren().add(vbox);
            
             }*/
            btnNewClicked();
            //} catch (IOException ex) {
            //    Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
            //}
        } catch (IOException ex) {
            Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void pnlScrlZoom(ActionEvent event) {
        System.out.println("dsfsg");
        pnlScrlData.setPrefWidth(pnlAnchrDataOut.getPrefWidth());
        pnlData.setPrefWidth(pnlScrlData.getPrefWidth());
    }

    @FXML
    private void btnFakeClicked(MouseEvent event) {
        System.out.println("sdgxfg");
        System.out.println("dsfsg" + pnlAnchrDataOut.getPrefWidth());
        pnlScrlData.setPrefWidth(pnlAnchrDataOut.getPrefWidth());
        pnlData.setPrefWidth(pnlScrlData.getPrefWidth());
    }

    @FXML
    private void btnEngAssignedClicked() throws IOException {
        vboxData.getChildren().clear();
        vboxData.getChildren().add((AnchorPane) getNodeFromResource("FaultMaintenanceDescription_1.fxml"));
        //System.out.println(vboxData.getPrefWidth());
        vboxData.setPrefHeight(10000);
        vboxData.setSpacing(0);

        for (int i = 0; i < 5; i++) {
            Object[] a = getControllerFromResource("FaultMaintenanceMessage.fxml");
            AnchorPane ap = (AnchorPane) a[0];
            int priority = (int) (3 * Math.random());
            ap.setStyle("-fx-background-color: " + (new String[]{"#f67483", "#f6c374", "#6ff184"})[priority] + ";");
            FaultMaintenanceMessageController c = (FaultMaintenanceMessageController) a[1];
            c.getBtnView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (int i = 0; i < vboxData.getChildren().size(); i++) {
                        vboxData.getChildren().get(i).setManaged(false);
                        vboxData.getChildren().get(i).setVisible(false);
                    }

                    TitledPane tp1 = new TitledPane();
                    tp1.setText("Fault Message");
                    tp1.setContent(getNodeFromResource("FaultMaintenanceMachineCreate.fxml"));
                    vboxData.getChildren().add(tp1);

                    TitledPane tp2 = new TitledPane();
                    tp2.setText("Fault Description");
                    tp2.setContent(getNodeFromResource("FaultMaintenanceInvolment.fxml"));
                    vboxData.getChildren().add(tp2);

                    TitledPane tp3 = new TitledPane();
                    tp3.setText("Assign Engineer");
                    tp3.setContent(getNodeFromResource("EngineerAssignmentCreate.fxml"));
                    vboxData.getChildren().add(tp3);

                    TitledPane tp4 = new TitledPane();
                    tp4.setText("Assign Engineer");
                    tp4.setContent(getNodeFromResource("FaultMaintenanceCompletionInfo.fxml"));
                    vboxData.getChildren().add(tp4);

                }

            });
            vboxData.getChildren().add(ap);
        }
    }

    @FXML
    private void btnNewClicked() throws IOException {

        vboxData.getChildren().clear();
        vboxData.getChildren().add((AnchorPane) getNodeFromResource("FaultMaintenanceDescription_1.fxml"));
        //System.out.println(vboxData.getPrefWidth());
        vboxData.setPrefHeight(10000);
        vboxData.setSpacing(0);

        for (int i = 0; i < 5; i++) {
            Object[] a = getControllerFromResource("FaultMaintenanceMessage.fxml");
            AnchorPane ap = (AnchorPane) a[0];
            int priority = (int) (3 * Math.random());
            ap.setStyle("-fx-background-color: " + (new String[]{"#f67483", "#f6c374", "#6ff184"})[priority] + ";");
            FaultMaintenanceMessageController c = (FaultMaintenanceMessageController) a[1];
            c.getBtnView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (int i = 0; i < vboxData.getChildren().size(); i++) {
                        vboxData.getChildren().get(i).setManaged(false);
                        vboxData.getChildren().get(i).setVisible(false);
                    }

                    TitledPane tp1 = new TitledPane();
                    tp1.setText("Fault Message");
                    tp1.setContent(getNodeFromResource("FaultMaintenanceMachineCreate.fxml"));
                    vboxData.getChildren().add(tp1);

                    TitledPane tp2 = new TitledPane();
                    tp2.setText("Fault Description");
                    tp2.setContent(getNodeFromResource("FaultMaintenanceInvolment.fxml"));
                    vboxData.getChildren().add(tp2);

                    TitledPane tp3 = new TitledPane();
                    tp3.setText("Assign Engineer");
                    tp3.setContent(getNodeFromResource("EngineerAssignmentCreate.fxml"));
                    vboxData.getChildren().add(tp3);
                }

            });
            vboxData.getChildren().add(ap);
        }
    }

    @FXML
    private void btnBackClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MainWindow2.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("MainWindow2.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
        MainWindow2Controller.controller.addListeners();
    }

    @FXML
    private void btnCompletedClicked() throws IOException {
        vboxData.getChildren().clear();
        vboxData.getChildren().add((AnchorPane) getNodeFromResource("FaultMaintenanceDescription_1.fxml"));
        //System.out.println(vboxData.getPrefWidth());
        vboxData.setPrefHeight(10000);
        vboxData.setSpacing(0);

        for (int i = 0; i < 5; i++) {
            Object[] a = getControllerFromResource("FaultMaintenanceMessage.fxml");
            AnchorPane ap = (AnchorPane) a[0];
            int priority = (int) (3 * Math.random());
            ap.setStyle("-fx-background-color: " + (new String[]{"#f67483", "#f6c374", "#6ff184"})[priority] + ";");
            FaultMaintenanceMessageController c = (FaultMaintenanceMessageController) a[1];
            c.getBtnView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (int i = 0; i < vboxData.getChildren().size(); i++) {
                        vboxData.getChildren().get(i).setManaged(false);
                        vboxData.getChildren().get(i).setVisible(false);
                    }

                    TitledPane tp1 = new TitledPane();
                    tp1.setText("Fault Message");
                    tp1.setContent(getNodeFromResource("FaultMaintenanceMachineCreate.fxml"));
                    vboxData.getChildren().add(tp1);

                    TitledPane tp2 = new TitledPane();
                    tp2.setText("Fault Description");
                    tp2.setContent(getNodeFromResource("FaultMaintenanceInvolment.fxml"));
                    vboxData.getChildren().add(tp2);

                    TitledPane tp3 = new TitledPane();
                    tp3.setText("Assign Engineer");
                    tp3.setContent(getNodeFromResource("EngineerAssignmentCreate.fxml"));
                    vboxData.getChildren().add(tp3);

                    TitledPane tp4 = new TitledPane();
                    tp4.setText("Assign Engineer");
                    tp4.setContent(getNodeFromResource("FaultMaintenanceCompletionInfo.fxml"));
                    vboxData.getChildren().add(tp4);

                }

            });
            vboxData.getChildren().add(ap);
        }
    }

    public VBox getVBoxData() {
        return vboxData;
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

    private <T> Object[] getControllerFromResource(String name) {
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getClassLoader().getResource(name));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        Node n = null;
        T t = null;
        try {
            n = fx.load(getClass().getResource(name).openStream());
            t = fx.<T>getController();
        } catch (IOException ex) {
            Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] r = new Object[]{n, t};
        return r;
    }
}
