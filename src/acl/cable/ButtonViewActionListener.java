/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Isura Manchanayake
 */
public class ButtonViewActionListener implements EventHandler<MouseEvent> {

    Button button;
    
    public ButtonViewActionListener() {
        
    }
    
    public void setButton(Button button) {
        this.button = button;
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }
    
    public ButtonViewActionListener(Button b) {
        button = b;
        //button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("sssssdfsdf");
    }

}
