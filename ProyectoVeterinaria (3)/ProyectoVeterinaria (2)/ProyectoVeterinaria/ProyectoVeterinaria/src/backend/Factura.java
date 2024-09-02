package backend;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Factura {
    private int idFactura;
    private String cliente, mascota, fecha_cita, servicio;
    private int precio;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String registrar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "insert into factura values(?,?,?,?,?,?)";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idFactura);
                ps.setString(2, cliente);
                ps.setString(3, mascota);
                ps.setString(4, fecha_cita);
                ps.setString(5, servicio);
                ps.setInt(6, precio);
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
            String instruccionSQL = "update factura set cliente=?, mascota=?, fechaCita=?, servicio=?, precio=? where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idFactura);
                ps.setString(2, cliente);
                ps.setString(3, mascota);
                ps.setString(4, fecha_cita);
                ps.setString(5, servicio);
                ps.setInt(6, precio);
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
            String instruccionSQL = "Delete from factura where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idFactura);
                
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
        String instruccionSQL ="select + from factura where codigo =?";
        try{
            PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
            ps.setInt(1, idFactura);
            ResultSet fila = ps.executeQuery();
            if(fila.next()){
                cliente = fila.getString("cliente");
                mascota = fila.getString("mascota");
                fecha_cita = fila.getString("fecha cita");
                servicio = fila.getString("servicio");
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
