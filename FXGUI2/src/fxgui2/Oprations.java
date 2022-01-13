
package fxgui2;

//port fxassignment.show_tbl;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author yididya
 */
public class Oprations extends Application {

    private Connection con = null;
    private PreparedStatement prstmt=null;
    private ResultSet rset = null;
    Statement stmnt= null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        TableView table = new TableView();
        
        
        
        TextField txt0= new TextField();
        TextField txt1 = new TextField();
        TextField txt2 = new TextField();
        TextField txt3 = new TextField();
        TextField txt4 = new TextField();
        TextField txt5 = new TextField();


        Button insertbtn = new Button();
        Button updatebtn = new Button();
        Button showbtn = new Button();
        Button distnictbtn =new Button();
        Button selectbtn = new Button();

       
        insertbtn.setText("INSERT");
        updatebtn.setText("UPDATE");
        showbtn.setText("SHOW");
        distnictbtn.setText("distnict");
        selectbtn.setText("select");
        
        
        

        Label lbl0 = new Label("SID :");
        Label lbl1 = new Label("STUD_ID :");
        Label lbl2 = new Label("FIRST_NAME :");
        Label lbl3 = new Label("LAST_NAME :");
        Label lbl4 = new Label("SECTION :");
        Label lbl5 = new Label("DEPARTMENT :");
        DbConnection dbcon = new DbConnection();

        insertbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DbConnection dbcon = new DbConnection();
                String sql = "Insert into DEPT_TB1 (SID, STUDID, FIRSTNAME, LASTNAME,SECTION, DEPARTMENT ) Values (?,?,?,?,?,?)";
                
                
                String lbl0 = txt0.getText();
                String lbl1 = txt1.getText();
                String lbl2 = txt2.getText();
                String lbl3 = txt3.getText();
                String lbl4 = txt4.getText();
                String lbl5 = txt5.getText();
                try {
                    Connection con = dbcon.connMethod();
                 
                    prstmt = con.prepareStatement(sql);
                    prstmt.setString(1, lbl0);
                    prstmt.setString(2, lbl1);
                    prstmt.setString(3, lbl2);
                    prstmt.setString(4, lbl3);
                    prstmt.setString(5, lbl4);
                    prstmt.setString(6, lbl5);

                    int i = prstmt.executeUpdate();
                    if (i == 1) {
                        System.out.println("Data Inserted");
                    }

                } catch (SQLException ex) {

                    Logger.getLogger(Oprations.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Oprations.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        prstmt.close();
                    } catch (SQLException ex) {

                    }
                }

            }
            
        });
        
        
        
        
        updatebtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DbConnection db = new DbConnection();
                Connection con = null;

                try {

                    con = db.connMethod();
                    String lbl2 = txt2.getText();
                    String txx = "JD";
                    String sql = "UPDATE DEPT_TB1 SET FIRSTNAME='" + txx + "' WHERE FIRSTNAME='" + lbl2 + "'";

                    PreparedStatement statement = con.prepareStatement(sql);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "The Data Is Updated!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        
        
        
        showbtn.setOnAction(new EventHandler<ActionEvent>() {
 private ObservableList<ObservableList> data;
 
            @Override
            public void handle(ActionEvent event) 
            {
             
                DbConnection DBCON;
        Connection c;
        ResultSet rset;
        data = FXCollections.observableArrayList();
        try {

            table.setStyle("-fx-background-color:yellow; -fx-font-color:blue ");
            DBCON = new DbConnection();
            c = DBCON.connMethod();
                String SQL = "SELECT * from dept_tb1";
                rset = c.createStatement().executeQuery(SQL);
                for (int i = 0; i < rset.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rset.getMetaData().getColumnName(i + 1));
                
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                table.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }


            while (rset.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                row.add(rset.getString(i));
                }
                System.out.println("Row[1]added " + row);
                data.add(row);

            }


            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
            }
        });
        
        
        
        
         distnictbtn.setOnAction(new EventHandler<ActionEvent>() {
 private ObservableList<ObservableList> data;

            @Override
            public void handle(ActionEvent event) 
            {
             
                DbConnection DBCON;
        Connection c;
        ResultSet rset;
        data = FXCollections.observableArrayList();
        try {

            table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
            DBCON = new DbConnection();
            c = DBCON.connMethod();
                String SQL = "SELECT DISTINCT SECTION from dept_tb1 ";
                rset = c.createStatement().executeQuery(SQL);
                for (int i = 0; i < rset.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rset.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                table.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }


            while (rset.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                row.add(rset.getString(i));
                }
                System.out.println("Row[1]added " + row);
                data.add(row);

            }


            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
            }
        });
        
         
         
         
       
         selectbtn.setOnAction(new EventHandler<ActionEvent>() {
 private ObservableList<ObservableList> data;

            @Override
            public void handle(ActionEvent event) 
            {
             
                DbConnection DBCON;
        Connection c;
        ResultSet rs;
        data = FXCollections.observableArrayList();
        try {

            table.setStyle("-fx-background-color:white; -fx-font-color:black");
            DBCON = new DbConnection();
            c = DBCON.connMethod();
                String SQL = "SELECT department from dept_tb1 where FIRSTNAME ='Elias' and SECTION ='SecA'and DEPARTMENT='CS' ";
                rs = c.createStatement().executeQuery(SQL);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                table.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }


            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
                }
                System.out.println("Row[1]added " + row);
                data.add(row);

            }


            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
            }
        });
        
        GridPane root = new GridPane();
      
        root.addRow(0, lbl0, txt0);
        root.addRow(1, lbl1, txt1);
        root.addRow(2, lbl2, txt2);
        root.addRow(3, lbl3, txt3);
        root.addRow(4, lbl4, txt4);
        root.addRow(5, lbl5, txt5);
        root.addRow(6, insertbtn, updatebtn, showbtn,distnictbtn,selectbtn);
        root.addColumn(3, table);
       
        root.setHgap(15);
        root.setVgap(15);

        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("Data_table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
