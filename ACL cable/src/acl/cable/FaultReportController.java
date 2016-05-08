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
    Button btnFake;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlAnchrDataOut.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                pnlAnchrDataIn.setPrefWidth(pnlAnchrDataOut.getWidth());
                pnlScrlData.setPrefWidth(pnlAnchrDataOut.getWidth());
                vboxData.setPrefWidth(pnlAnchrDataOut.getWidth());
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
            /*VBox inside = new VBox();
             try {
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
            /*    BorderPane bp = (BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("FaultMaintenanceMessage.fxml"));
             inside.getChildren().add(bp);
                
             } catch (IOException ex) {
             Logger.getLogger(FaultReportController.class.getName()).log(Level.SEVERE, null, ex);
             }
             t.setContent(inside);*/
            Accordion a = new Accordion();
            a.getPanes().addAll(t);
            vbox.getChildren().add(a);
            vboxData.getChildren().add(vbox);

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

        /*FXMLLoader fx1 = new FXMLLoader();
         URL url1 = getClass().getClassLoader().getResource("EngineerAssignmentView.fxml");
         fx1.setLocation(url1);
         fx1.setBuilderFactory(new JavaFXBuilderFactory());
         TitledPane tp = (TitledPane) fx1.load(url1.openStream());
        
         /*URL url2 = getClass().getClassLoader().getResource("EngineerAssigned.fxml");
         FXMLLoader fx2 = new FXMLLoader();
         fx2.setLocation(url2);
         fx2.setBuilderFactory(new JavaFXBuilderFactory());
         BorderPane bp = (BorderPane) fx2.load(url2.openStream());
        
         tp.setContent(bp);*/
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(getClass().getClassLoader().getResource("EngineerAssignmentView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        TitledPane tp = (TitledPane) fx.load(getClass().getResource("EngineerAssignmentView.fxml").openStream());

        FXMLLoader fx2 = new FXMLLoader(getClass().getClassLoader().getResource("EngineerAssigned.fxml"));
        fx2.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane bp = (BorderPane) fx2.load(getClass().getResource("EngineerAssigned.fxml").openStream());
        
        EngineerAssignmentViewController controllerView = fx.<EngineerAssignmentViewController>getController();
        EngineerAssignedController controller = fx2.<EngineerAssignedController>getController();
        System.out.println("sfsffff" + controller);
        controller.getLblName().setText("kamal");
        controllerView.getVBoxInside().getChildren().add(bp);

//        EngineerAssignmentViewController controller = fx2.getController();
//        Parent root = fx2.load();
//        System.out.println("dfsdfsdf"+controller);
//        System.out.println("WWWWWWWWWWWW"+controller.getVBoxInside());
//        controller.getVBoxInside().getChildren().add(bp);

        vboxData.getChildren().add(tp);
    }

}
