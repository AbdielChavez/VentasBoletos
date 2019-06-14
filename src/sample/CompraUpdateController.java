package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CompraUpdateController implements Initializable {

    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private ComboBox comboBox;
    @FXML private Button button1;
    @FXML private Button button2;
    private OperacionCliente op;

    private VistaController controller;
    private Compra compra;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initComponents();
    }
    public void initComponents(){

        this.op = new OperacionCliente();
        this.comboBox.getItems().clear();
        this.comboBox.getItems().add("Pagado");
        this.comboBox.getItems().add("No pagado");
        this.comboBox.setValue("No pagado");
        this.text1.setDisable(true);
        this.text2.setDisable(true);
        this.text3.setDisable(true);
        this.text4.setDisable(true);

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

    public void setCompra(Compra compra,VistaController controller){
        this.compra = compra;
        this.controller = controller;
        this.text1.setText(compra.getCompraId()+"");
        this.text2.setText(compra.getClienteId()+"");
        this.text3.setText(compra.getNumBoleto()+"");
        this.text4.setText(compra.getFecha().toString());

        boolean bool = this.compra.isPagado();
        if(bool){
            this.comboBox.setValue("Pagado");
        } else {
            this.comboBox.setValue("No pagado");
        }

    }

    public void button1MousePressed(MouseEvent event){

        String bool = this.comboBox.getValue().toString();
        if(bool.equals("Pagado")){
            this.compra.setPagado(true);
        } else {
            this.compra.setPagado(false);
        }
        this.op.updateCompra(this.compra);
        this.controller.cargarDatos();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();

    }
    public void button2MousePressed(MouseEvent event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

}
