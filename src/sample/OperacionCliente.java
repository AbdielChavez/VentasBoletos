package sample;

import java.sql.*;

public class OperacionCliente{
    private Connection conexion = null;


    public OperacionCliente() {
        DBManager dbManager = new DBManager();
        this.conexion = dbManager.getConexion();
    }



    public void insertVenta(Cliente cliente,Seccion seccion,Boleto boleto,Compra compra){
        try{

            String sql = "INSERT INTO cliente (clienteID,nombre,apellidos,direccion)" +
                    "VALUES (?,?,?,?)";

            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,cliente.getClienteId());
            ps.setString(2,cliente.getClienteNombre());
            ps.setString(3,cliente.getClienteApellidos());
            ps.setString(4,cliente.getClienteDireccion());
            ps.execute();


            sql = "INSERT INTO seccion (seccionId,descripcion,precio)" +
                    "VALUES (?,?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getSeccionId());
            ps.setString(2,seccion.getSeccionDescripcion());
            ps.setFloat(3,seccion.getSeccionPrecio());
            ps.execute();

            sql = "INSERT INTO boleto (numBoleto,seccionId)" +
                    "VALUES (?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,boleto.getBoletoId());
            ps.setInt(2,boleto.getSeccionId());
            ps.execute();


            sql = "INSERT INTO compra (compraId,clienteId,numBoleto,fechaHora,pagado)" +
                    "VALUES (?,?,?,?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,compra.getCompraId());
            ps.setInt(2,compra.getClienteId());
            ps.setInt(3,compra.getNumBoleto());
            ps.setDate(4, new Date(compra.getFecha().getTime()));
            ps.setBoolean(5,compra.isPagado());
            ps.execute();

            System.out.println("Se realizo la inserción con exito");

        }catch (SQLException ex){
            ex.printStackTrace();

        }



    }


    //operaciones con el cliente

    public void deleteCliente(Cliente cliente){

        try{

            String sql = "DELETE FROM cliente WHERE clienteID = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,cliente.getClienteId());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente){
        try{

            String sql = "UPDATE cliente set nombre=?,apellidos=?,direccion=?" +
                    " WHERE clienteID = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setString(1,cliente.getClienteNombre());
            ps.setString(2,cliente.getClienteApellidos());
            ps.setString(3,cliente.getClienteDireccion());
            ps.setInt(4,cliente.getClienteId());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){

        }

    }



    public ResultSet getClientes(){
        try {

            String sql = "SELECT * FROM cliente;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }



    }




    public void deleteSeccion(Seccion seccion){
        try{


            String sql ="DELETE FROM boleto WHERE seccionId = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getSeccionId());
            ps.execute();

            sql = "DELETE FROM seccion WHERE seccionId = ?";
            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getSeccionId());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public void updateSeccion(Seccion seccion){
        try{

            String sql = "UPDATE seccion set descripcion=?,precio=?" +
                    " WHERE seccionId = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setString(1,seccion.getSeccionDescripcion());
            ps.setFloat(2,seccion.getSeccionPrecio());
            ps.setInt(3,seccion.getSeccionId());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet getSeccion(){
        try {

            String sql = "SELECT * FROM seccion;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }

    //operacion con la compra
    public void deleteCompra(Compra compra){
        try{

            String sql = "DELETE FROM compra WHERE compraId = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,compra.getCompraId());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Boolean comprarSeccion(int seccionId){
        try{

            String sql = "SELECT * from boleto WHERE seccionId = ?";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);
            stmt.setInt(1,seccionId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }catch (SQLException ex){

            ex.printStackTrace();
            return false;
        }
    }


    public Boolean comprarCliente(int clienteId){
        try{

            String sql = "SELECT * from compra WHERE clienteId = ?";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);
            stmt.setInt(1,clienteId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }

    }





    public void updateCompra(Compra compra){
        try{

            String sql = "UPDATE compra SET pagado=?" +
                    " WHERE compraId = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setBoolean(1,compra.isPagado());
            ps.setInt(2,compra.getCompraId());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Boolean comprobarCompra(int compraId){
        try{

            String sql = "SELECT * FROM compra WHERE compraId = ? AND pagado = 1";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);
            stmt.setInt(1,compraId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public ResultSet getCompra(){
        try {

            String sql = "SELECT * FROM compra;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }



}

//ante borrar debe validar si se estan usando y si se estan usando no debe permitir borrar