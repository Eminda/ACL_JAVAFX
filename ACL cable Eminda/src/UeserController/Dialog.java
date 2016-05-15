/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Dialog {
    public static void show(String title , String massege){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        
        Label label = new Label();
        label.setText(massege);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox();
        layout.getChildren().setAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        //window.showAndWait();
        
    }
    
    public static void showError(String massege){
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("ERROR");
alert.setHeaderText(massege);
alert.setContentText(null);

alert.showAndWait();
    }
}