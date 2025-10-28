/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.controller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;
import lodgeman.lalitman.view.AppInfo;

/**
 *
 * @author svastrad
 */
public class PopUpCallBack {
    
    
    public void showPopUp()
    {
         Circle C = new Circle(200, 200, 130, Color.AQUAMARINE);
     Stage s = AppInfo.getInstance().getmPrimaryStage();
     final Popup popup = new Popup(); popup.setX(400); popup.setY(300);
     popup.getContent().addAll(C);
        Text t = new Text("Hello World");
         popup.getContent().addAll(t);
          Button b = new Button("Hello");
          popup.getContent().addAll(b);
        
                
    popup.show(s); 
    
    
    }
            
}
