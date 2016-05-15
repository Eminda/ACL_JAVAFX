/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MailHomeController implements Initializable {
    @FXML
    BorderPane border;
    
    public void composeBtnClck() throws IOException{
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MailCompose.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane = (BorderPane) fx.load(getClass().getResource("MailCompose.fxml"));
        pane.setPrefSize(border.getWidth(), border.getHeight());
        border.getChildren().clear();
        border.setCenter(pane);
    }
    public void inboxBtnClck() throws IOException{
        border.getChildren().clear();
        FXMLLoader filter = new FXMLLoader(getClass().getClassLoader().getResource("MailFilter.fxml"));
        filter.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane filterPane = (BorderPane) filter.load(getClass().getResource("MailFilter.fxml").openStream());
        filterPane.setPrefSize(border.getWidth(), border.getHeight());
        ScrollPane sp = new ScrollPane();
        VBox vb = new VBox();
        vb.getChildren().clear();
        for (int i =0;i<10;++i){
            FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MailInBox.fxml"));
            fx.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("MailInBox.fxml").openStream());
            MailInBoxController cntrl = fx.getController();
            cntrl.iniComponents(border.getWidth(),this);
            vb.getChildren().add(pane);
            sp.setPrefSize(border.getWidth(), border.getHeight());
            sp.setContent(vb);
            filterPane.setCenter(sp);
            border.setCenter(filterPane);
        }
    }
    
    public void sentboxBtnClck() throws IOException{
       border.getChildren().clear();
       FXMLLoader filter = new FXMLLoader(getClass().getClassLoader().getResource("MailFilter.fxml"));
        filter.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane filterPane = (BorderPane) filter.load(getClass().getResource("MailFilter.fxml").openStream());
        filterPane.setPrefSize(border.getWidth(), border.getHeight());
        ScrollPane sp = new ScrollPane();
        VBox vb = new VBox();
        vb.getChildren().clear();
        for (int i =0;i<10;++i){
            FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MailSentBox.fxml"));
            fx.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("MailSentBox.fxml").openStream());
            MailSentBoxController cntrl = fx.getController();
            cntrl.iniComponents(border.getWidth(),this);
            vb.getChildren().add(pane);
            sp.setPrefSize(border.getWidth(), border.getHeight());
            sp.setContent(vb);
            filterPane.setCenter(sp);
            border.setCenter(filterPane);
        }}
        
    
    public void viewMail() throws IOException{
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MailView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane mail = (BorderPane)fx.load(getClass().getResource("MailView.fxml").openStream());
        mail.setPrefSize(border.getWidth(), border.getHeight());
        border.getChildren().clear();
        border.setCenter(mail);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
    private void btnBackButtonClicked(MouseEvent e){
        MainWindowController.controller.setMainWindowControl2();
    }
   
    
}
