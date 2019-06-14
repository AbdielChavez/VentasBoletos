package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private TextField text5;

    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;

    @FXML private ComboBox comboBox;

    @FXML private Button boton1;
    @FXML private Button boton2;

    //declarar
    private Cliente cliente;
    private Seccion seccion;
    private Boleto boleto;
    private Compra compra;
    private OperacionCliente operacionCliente;



//hace que toda la pantalla tenga funcioanlidad en el scenebuilder

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initComponents();
    }



    public void initComponents(){
        //se crean los objetos que sabes que vas usar
        this.cliente = new Cliente();
        this.seccion = new Seccion();
        this.boleto = new Boleto();
        this.compra = new Compra();
        this.operacionCliente = new OperacionCliente();
        //fecha
        this.comboBox.getItems().clear();
        Date date = new Date();
        String fecha = new SimpleDateFormat("yyyy-mm-dd").format(date);
        this.label4.setText(fecha);
        //status de compra
        this.comboBox.getItems().add("Pagado");
        this.comboBox.getItems().add("No pagado");
        this.comboBox.setValue("No pagado");

        //el value property es un string, cuando cambia notificas a la compra que cambio el status
        //los add listener agregar el evento cuando el valor de un property a cambiado
        //se usa cuando los propertys son incomátibles

        this.comboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue.equals("Pagado")){
                    compra.setPagado(true);
                } else {
                    compra.setPagado(false);
                }
            }
        });

        this.boton1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Boton1MousePressed(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        this.boton2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Boton2MousePressed(event);
            }
        });



        //son del mismo tipo los enlazas con un bind, cuando cambia el bind cambia el property
        //enlazamos el atributo seccion id con el de la clase ya que van hacer lo mismo
        this.boleto.seccionIdProperty().bind(this.seccion.seccionIdProperty());

        //lo mismo con el de la compra
        this.compra.clienteIdProperty().bind(this.cliente.clienteIdProperty());
        this.compra.numBoletoProperty().bind(this.boleto.boletoIdProperty());


        //enlazamos los clientes con los campos text porque seran iguales

        this.cliente.clientNombreProperty().bind(this.text1.textProperty());
        this.cliente.clienteApellidosProperty().bind(this.text2.textProperty());
        this.cliente.clienteDireccionProperty().bind(this.text3.textProperty());

        //lo mismo con el da la seccion

        this.seccion.seccionDescripcionProperty().bind(this.text4.textProperty());

        /*en este caso como el precio es tipo float tenemos que notificar
        * parsear y notificar al property */

        this.text5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                /*cuando haya un cambio en el campo de text field* se le
                cambiara de inmediato al campo seccion como el textfield nos
                devuelve string hay que hacer un cast /
                 */
                seccion.setSeccionPrecio(Float.parseFloat(newValue));

            }
        });






        this.cliente.setClienteId(this.generarIds());
        this.seccion.setSeccionId(this.generarIds());
        this.boleto.setBoletoId(this.generarIds());
        this.compra.setCompraId(this.generarIds());



        this.label1.setText(this.cliente.getClienteId()+"");
        this.label2.setText(this.seccion.getSeccionId()+"");
        this.label3.setText(this.compra.getCompraId()+"");
        this.label5.setText(this.boleto.getBoletoId()+"");

    }




    public int generarIds(){
        int id = (int) (Math.random()*(10000-1000000+1)+1000000);
        return id;
    }


    public void Boton1MousePressed(MouseEvent evt) throws ParseException {
        this.compra.setFecha(new SimpleDateFormat("yyyy-mm-dd").parse(this.label4.getText()));
        this.operacionCliente.insertVenta(cliente,seccion,boleto,compra);
    }

    public void Boton2MousePressed(MouseEvent evt){
        FXMLLoader loader = new FXMLLoader();

        try {
            AnchorPane root = loader.load(this.getClass().getResourceAsStream("vista.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
