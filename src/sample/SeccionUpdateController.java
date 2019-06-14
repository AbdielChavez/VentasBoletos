package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SeccionUpdateController implements Initializable {


    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private Button button1;
    @FXML private Button button2;
    private OperacionCliente op;


    private Seccion seccion = null;
    private VistaController controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initComponents();
    }

    public void initComponents(){
        this.text1.setDisable(true);
        this.op = new OperacionCliente();

        this.button1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button1MousePressed(event);
            }
        });
        this.button2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button2MousePressed(event);
            }
        });
    }

    public void setSeccion(Seccion seccion,VistaController controller){
        this.seccion = seccion;
        this.controller = controller;
        this.text1.setText(seccion.getSeccionId()+"");
        this.text2.setText(seccion.getSeccionDescripcion());
        this.text3.setText(seccion.getSeccionPrecio()+"");
    }

    public void button1MousePressed(MouseEvent event){

        this.seccion.setSeccionDescripcion(this.text2.getText());
        this.seccion.setSeccionPrecio(Float.parseFloat(this.text3.getText()));

        this.op.updateSeccion(this.seccion);
        this.controller.cargarDatos();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();


    }
    public void button2MousePressed(MouseEvent event){

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();


    }
}
