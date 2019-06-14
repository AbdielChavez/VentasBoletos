package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private IntegerProperty clienteId = new SimpleIntegerProperty();
    private StringProperty clienteNombre = new SimpleStringProperty();
    private StringProperty clienteApellidos = new SimpleStringProperty();
    private StringProperty clienteDireccion = new SimpleStringProperty();


    public Cliente(){ }

    public Cliente(int clienteID, String clienteNombre,String clienteApellidos,String clienteDireccion){
        this.clienteId.set(clienteID);
        this.clienteNombre.set(clienteNombre);
        this.clienteApellidos.set(clienteApellidos);
        this.clienteDireccion.set(clienteDireccion);
    }


    public int getClienteId() {
        return clienteId.get();
    }

    public IntegerProperty clienteIdProperty() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }


    public String getClienteNombre() {
        return clienteNombre.get();
    }

    public StringProperty clientNombreProperty(){
        return clienteNombre;
    }

    public String getClienteApellidos() {
        return clienteApellidos.get();
    }

    public StringProperty clienteApellidosProperty() {
        return clienteApellidos;
    }

    public void setClienteApellidos(String clienteApellidos) {
        this.clienteApellidos.set(clienteApellidos);
    }

    public String getClienteDireccion() {
        return clienteDireccion.get();
    }
    public void setClienteDireccion(String clienteDireccion){ this.clienteDireccion.set(clienteDireccion);}

    public StringProperty clienteDireccionProperty() {
        return clienteDireccion;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre.set(clienteNombre);
    }

    @Override
    public String toString(){
        return "Cliente{"+
                "cliente id="+this.clienteId.get()+
                ", cliente nombre="+this.clienteNombre.get()+
                ", cliente apellidos="+this.clienteApellidos.get()+
                ", cliente direccion="+this.clienteDireccion.get()+"}";
    }
}
