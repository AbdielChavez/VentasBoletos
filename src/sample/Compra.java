package sample;

import javafx.beans.property.*;

import java.util.Date;

public class Compra {

    private IntegerProperty compraId = new SimpleIntegerProperty();
    private IntegerProperty clienteId = new SimpleIntegerProperty();
    private IntegerProperty numBoleto = new SimpleIntegerProperty();
    private BooleanProperty pagado = new SimpleBooleanProperty();
    private Date fecha;


    public Compra(){}
    public Compra(int compraId,int clienteId,int numBoleto,boolean pagado,Date fecha){
        this.compraId.set(compraId);
        this.clienteId.set(clienteId);
        this.numBoleto.set(numBoleto);
        this.pagado.set(pagado);
        this.fecha = fecha;
    }

    public int getCompraId() {
        return compraId.get();
    }

    public IntegerProperty compraIdProperty() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId.set(compraId);
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


    public int getNumBoleto() {
        return numBoleto.get();
    }

    public IntegerProperty numBoletoProperty() {
        return numBoleto;
    }

    public void setNumBoleto(int numBoleto) {
        this.numBoleto.set(numBoleto);
    }

    public boolean isPagado() {
        return pagado.get();
    }

    public BooleanProperty pagadoProperty() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado.set(pagado);
    }

    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return "Compra{" +
                "compraId ="+this.compraId.get()+
                ", clienteId="+this.clienteId.get()+
                ", numBoleto="+this.numBoleto.get()+
                ", pagado="+this.pagado.get()+
                ", fecha="+this.fecha.toString()+"}";
    }
}
