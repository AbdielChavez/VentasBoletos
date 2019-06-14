package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class VistaController implements Initializable {
//la table view recibe un valor de objetos, si  quieres clientes
     @FXML private TableView tableCliente;
     @FXML private TableView tableCompras;
     @FXML private TableView tableSeccion;
     @FXML private Button boton1;
     @FXML private Button boton2;

     private OperacionCliente op;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initComponents();
    }


    public void initComponents(){
        this.op = new OperacionCliente();

        this.tableCliente.getColumns().clear();
        this.tableSeccion.getColumns().clear();
        this.tableCompras.getColumns().clear();


        //columnas tabla clientes
        TableColumn colClienteId = new TableColumn<>("No. cliente");
        TableColumn colClienteNombre = new TableColumn<>("Nombre");
        TableColumn colClienteApellidos = new TableColumn<>("Apellidos");
        TableColumn colClienteDireccion = new TableColumn<>("Direccion");


        this.tableCliente.getColumns().add(colClienteId);
        this.tableCliente.getColumns().add(colClienteNombre);
        this.tableCliente.getColumns().add(colClienteApellidos);
        this.tableCliente.getColumns().add(colClienteDireccion);

        //especificacion de la propiedad a buscar
        colClienteId.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("clienteNombre"));
        colClienteApellidos.setCellValueFactory(new PropertyValueFactory<>("clienteApellidos"));
        colClienteDireccion.setCellValueFactory(new PropertyValueFactory<>("clienteDireccion"));

        //tabla seccion

        TableColumn colSeccionId = new TableColumn<>("Seccion ID");
        TableColumn colSeccionDescripcion = new TableColumn<>("Descripcion");
        TableColumn colSeccionprecio = new TableColumn<>("Precio");

        this.tableSeccion.getColumns().addAll(colSeccionId,colSeccionDescripcion,colSeccionprecio);

        colSeccionId.setCellValueFactory(new PropertyValueFactory<>("seccionId"));
        colSeccionDescripcion.setCellValueFactory(new PropertyValueFactory<>("seccionDescripcion"));
        colSeccionprecio.setCellValueFactory(new PropertyValueFactory<>("seccionPrecio"));


        //tavla compras

        TableColumn colCompraId = new TableColumn<>("Compra Id");
        TableColumn colCompraClienteId = new TableColumn<>("ClienteID");
        TableColumn colNumBoleto = new TableColumn<>("BoletoID");
        TableColumn colPagado = new TableColumn<>("Pagado");
        TableColumn colFecha = new TableColumn<>("Fecha");

        this.tableCompras.getColumns().addAll(colCompraId,colCompraClienteId,colNumBoleto,colPagado,colFecha);


        colCompraId.setCellValueFactory(new PropertyValueFactory<>("compraId"));
        colCompraClienteId.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colNumBoleto.setCellValueFactory(new PropertyValueFactory<>("numBoleto"));
        colPagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));



        this.cargarDatos();

        this.boton1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Boton1MousePressed(event);
            }
        });

        this.boton2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Boton2MousePressed(event);
            }
        });


    }



    public void Boton1MousePressed(MouseEvent event){



       int clienteRow = this.tableCliente.getSelectionModel().getSelectedIndex();
       int seccionRow = this.tableSeccion.getSelectionModel().getSelectedIndex();
       int compraRow = this.tableCompras.getSelectionModel().getSelectedIndex();
       if(clienteRow != -1 && seccionRow == -1 && compraRow == -1){

           //System.out.println(this.tableCliente.getItems().get(clienteRow));

           Cliente cliente = (Cliente) this.tableCliente.getItems().get(clienteRow);

           FXMLLoader fxmlLoader = new FXMLLoader();
           try {
               AnchorPane root = fxmlLoader.load(this.getClass().getResourceAsStream("clienteUpdate.fxml"));

               ClienteUpdateController cuc = (ClienteUpdateController)fxmlLoader.getController();
               cuc.setCliente(cliente,this);
               Stage stage = new Stage();
               stage.setScene(new Scene(root));
               stage.show();

           } catch (IOException e) {
               e.printStackTrace();
           }


       }
       if(seccionRow != -1 && clienteRow == -1 && compraRow == -1){

           FXMLLoader fxmlLoader = new FXMLLoader();
           Seccion seccion = (Seccion)this.tableSeccion.getItems().get(seccionRow);
           try {
               AnchorPane root = fxmlLoader.load(this.getClass().getResourceAsStream("seccionUpdate.fxml"));
               SeccionUpdateController suc = (SeccionUpdateController)fxmlLoader.getController();
               suc.setSeccion(seccion,this);
               Stage stage = new Stage();
               stage.setScene(new Scene(root));
               stage.show();


           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       if(compraRow != -1 && seccionRow == -1 && clienteRow == -1){


           Compra compra = (Compra) this.tableCompras.getItems().get(compraRow);
           FXMLLoader fxmlLoader = new FXMLLoader();
           try {
               AnchorPane root = fxmlLoader.load(this.getClass().getResourceAsStream("compraUpdate.fxml"));
               CompraUpdateController cuc = (CompraUpdateController)fxmlLoader.getController();
               cuc.setCompra(compra,this);
               Stage stage = new Stage();
               stage.setScene(new Scene(root));
               stage.show();


           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        this.tableCliente.getSelectionModel().clearSelection();
        this.tableSeccion.getSelectionModel().clearSelection();
        this.tableCompras.getSelectionModel().clearSelection();



    }

    //validadiones de borrado

    public void Boton2MousePressed(MouseEvent event){
        int clienteRow = this.tableCliente.getSelectionModel().getSelectedIndex();
        int seccionRow = this.tableSeccion.getSelectionModel().getSelectedIndex();
        int compraRow = this.tableCompras.getSelectionModel().getSelectedIndex();

        //validacion de la sección
        if(seccionRow != -1 && clienteRow == -1 && compraRow == -1){
            Seccion seccion = (Seccion) this.tableSeccion.getItems().get(seccionRow);
            if(this.op.comprarSeccion(seccion.getSeccionId())){
                //si existe registro el alert de que no se puede eliminar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en la eliminación");
                alert.setContentText("La Seccion esta ligada a un boleto");
                alert.show();
            } else {
                this.op.deleteSeccion(seccion);
            }
        }

        //validadción del cliente
        if(clienteRow != -1 && seccionRow == -1 && compraRow == -1){
            Cliente cliente = (Cliente)this.tableCliente.getItems().get(clienteRow);

            if(this.op.comprarCliente(cliente.getClienteId())){
                //si es existe el cliente muestra el alert de que no se puede eliminar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en la eliminación");
                alert.setContentText("El cliente esta asociado a una compra");
                alert.show();
            } else {
                this.op.deleteCliente(cliente);
            }
        }

        //validacion de la compra
        if(compraRow != -1 && clienteRow == -1 && seccionRow == -1){
            Compra compra = (Compra)this.tableCompras.getItems().get(compraRow);


            //System.out.println(!this.op.comprobarCompra(compra.getCompraId()));
            if(this.op.comprobarCompra(compra.getCompraId())){//verifico si el estado de la compra es es pagado
                //si es pagado muestro el alert de que no se puede eliminar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en la eliminación");
                alert.setContentText("La compra ya se realizo");
                alert.show();
            } else {
                //elimino si el resultado es falso
                this.op.deleteCompra(compra);
            }

        }


        this.cargarDatos();
        this.tableCliente.getSelectionModel().clearSelection();
        this.tableSeccion.getSelectionModel().clearSelection();
        this.tableCompras.getSelectionModel().clearSelection();
    } //borrado


    public void cargarDatos(){

        this.tableCliente.getItems().clear();
        this.tableSeccion.getItems().clear();
        this.tableCompras.getItems().clear();

        try{

            ResultSet rs = this.op.getClientes();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setClienteId(rs.getInt("clienteID"));
                cliente.setClienteNombre(rs.getString("nombre"));
                cliente.setClienteApellidos(rs.getString("apellidos"));
                cliente.setClienteDireccion(rs.getString("direccion"));

                this.tableCliente.getItems().add(cliente);
            }

            rs = this.op.getSeccion();
            while(rs.next()){
                Seccion seccion = new Seccion();
                seccion.setSeccionId(rs.getInt("seccionId"));
                seccion.setSeccionDescripcion(rs.getString("descripcion"));
                seccion.setSeccionPrecio(rs.getFloat("precio"));

                this.tableSeccion.getItems().add(seccion);
            }

            rs = this.op.getCompra();
            while (rs.next()){
                Compra compra = new Compra();
                compra.setCompraId(rs.getInt("compraId"));
                compra.setClienteId(rs.getInt("clienteId"));
                compra.setNumBoleto(rs.getInt("numBoleto"));
                compra.setFecha(rs.getDate("fechaHora"));
                compra.setPagado(rs.getBoolean("pagado"));
                this.tableCompras.getItems().add(compra);
            }



        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
