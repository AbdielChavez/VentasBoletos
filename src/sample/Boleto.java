package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Boleto {
    private IntegerProperty boletoId = new SimpleIntegerProperty();
    private IntegerProperty seccionId = new SimpleIntegerProperty();

    //constructor
    public Boleto(){}

    
    public Boleto(int boletoId,int seccionId){
        this.boletoId.set(boletoId);
        this.seccionId.set(seccionId);
    }


    public int getBoletoId() {
        return boletoId.get();
    }

    public IntegerProperty boletoIdProperty() {
        return boletoId;
    }

    public void setBoletoId(int boletoId) {
        this.boletoId.set(boletoId);
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

    @Override
    public String toString(){
        return "Boleto{"+
                "boletoId="+this.boletoId.get()+
                ", seccionId="+this.seccionId.get()+"}";
    }
}
