package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DBManager implements AutoCloseable {

    private Connection conexion = null;

    public DBManager(){
        try{
            //correccion del horario
            String url = "jdbc:mysql://localhost:3306/bdboletaje?serverTimezone="+TimeZone.getDefault().getID();
            this.conexion = DriverManager.getConnection(url,"root","");

        }catch (SQLException ex){
            this.conexion = null;
            ex.printStackTrace();
        }
    }

    public Connection getConexion(){
        return this.conexion;
    }




    @Override
    public void close() throws Exception {
        if (this.conexion != null){
            this.conexion.close();
        }
        this.conexion = null;
    }
}
