
package fxgui2;

import com.sun.jdi.connect.spi.Connection;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FXGUI2 extends Application {
    
       Alert a;
    private Parent hbox;
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("click");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DbConnection dbcon = new DbConnection();
                try {
                    java.sql.Connection con = dbcon.connMethod();
                    if (con != null) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("you have successfully connected");
                    a.showAndWait();
                    } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("you have not successfully connected");
                    a.showAndWait();
                    }
                    
                  
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(FXGUI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                System.out.println("Hello World!");
            }
            
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  public static void main(String[] args){
      launch(args);
  }



}