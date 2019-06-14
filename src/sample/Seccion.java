package sample;

import javafx.beans.property.*;

public class Seccion {
    private IntegerProperty seccionId = new SimpleIntegerProperty();
    private StringProperty seccionDescripcion = new SimpleStringProperty();
    private FloatProperty seccionPrecio = new SimpleFloatProperty();


    public Seccion(){}

    public Seccion(int seccionId,String seccionDescripcion,Float seccionPrecio){
        this.seccionId.set(seccionId);
        this.seccionDescripcion.set(seccionDescripcion);
        this.seccionPrecio.set(seccionPrecio);
    }


    public int getSeccionId() {
        return seccionId.get();
    }

    public IntegerProperty seccionIdProperty() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId.set(seccionId);
    }

    public String getSeccionDescripcion() {
        return seccionDescripcion.get();
    }

    public StringProperty seccionDescripcionProperty() {
        return seccionDescripcion;
    }

    public void setSeccionDescripcion(String seccionDescripcion) {
        this.seccionDescripcion.set(seccionDescripcion);
    }

    public float getSeccionPrecio() {
        return seccionPrecio.get();
    }

    public FloatProperty seccionPrecioProperty() {
        return seccionPrecio;
    }

    public void setSeccionPrecio(float seccionPrecio) {
        this.seccionPrecio.set(seccionPrecio);
    }

    @Override
    public String toString(){
        return "Seccion{"+
                "seccion Id ="+this.seccionId.get()+
                ", seccion descripcion="+this.seccionDescripcion.get()+
                ", seccion precio="+this.seccionPrecio.get()+"}";
    }
}
