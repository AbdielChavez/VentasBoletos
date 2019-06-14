package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteUpdateController implements Initializable {


    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private Button button1;
    @FXML private Button button2;

    private OperacionCliente op;


    private Cliente cliente = null;
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



    public void setCliente(Cliente cliente,VistaController controller){
        this.cliente = cliente;
        this.controller = controller;
        this.text1.setText(cliente.getClienteId()+"");
        this.text2.setText(cliente.getClienteNombre());
        this.text3.setText(cliente.getClienteApellidos());
        this.text4.setText(cliente.getClienteDireccion());

    }
    public void button1MousePressed(MouseEvent event){
        this.cliente.setClienteNombre(this.text2.getText());
        this.cliente.setClienteApellidos(this.text3.getText());
        this.cliente.setClienteDireccion(this.text4.getText());
        this.op.updateCliente(this.cliente);
        this.controller.cargarDatos();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();

    }


    public void button2MousePressed(MouseEvent event){

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();



    }




}
