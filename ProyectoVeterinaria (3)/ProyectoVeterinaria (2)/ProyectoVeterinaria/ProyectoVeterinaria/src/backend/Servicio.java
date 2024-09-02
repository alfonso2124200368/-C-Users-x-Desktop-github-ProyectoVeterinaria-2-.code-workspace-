package backend;
import backend.Conexion;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Servicio {
    private int idServicio;
    private String nombreServicio, descripcion;
    private double precio;

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String registrar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "insert into servicio values(?,?,?,?)";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idServicio);
                ps.setString(2, nombreServicio);
                ps.setString(3, descripcion);
                ps.setInt(4, idServicio);
                int fila = ps.executeUpdate();
                mensaje = fila + " fila registrada";
                conexion.desconectar();
            }catch(SQLException ex){
                mensaje = "Error al registrar "+ ex.getMessage();
            }
            
        }else{
            mensaje ="No se pudo conectar";
            
        }
        return mensaje;
    }
    public String modificar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "update servicio set nombreServicio=?, descripcion=?, precio=? where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idServicio);
                ps.setString(2, nombreServicio);
                ps.setString(3, descripcion);
                ps.setInt(4, idServicio);
                int fila = ps.executeUpdate();
                mensaje = fila + " fila modificada";
                conexion.desconectar();
            }catch(SQLException ex){
                mensaje = "Error al modificar "+ ex.getMessage();
            }
            
        }else{
            mensaje ="No se pudo conectar";
            
        }
        return mensaje;
    }
    public String eliminar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "Delete from servicio where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idServicio);
                
                int fila = ps.executeUpdate();
                mensaje = fila + " fila eliminada";
                conexion.desconectar();
            }catch(SQLException ex){
                mensaje = "Error al eliminar "+ ex.getMessage();
            }
            
        }else{
            mensaje ="No se pudo conectar";
            
        }
        return mensaje;
    }
    public String consultar (){
        String mensaje="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){
        String instruccionSQL ="select + from servicio where codigo =?";
        try{
            PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
            ps.setInt(1, idServicio);
            ResultSet fila = ps.executeQuery();
            if(fila.next()){
                nombreServicio = fila.getString("nombre servicio");
                descripcion = fila.getString("descripcion");
                precio = fila.getInt("precio");
            }
        }catch(SQLException ex){
            mensaje = "Error al consultar";
        }
    }else{
            mensaje= "No se pudo consultar";
        }
        return mensaje;
    }
}

