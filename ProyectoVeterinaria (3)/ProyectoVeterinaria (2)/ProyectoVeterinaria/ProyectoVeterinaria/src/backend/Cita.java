package backend;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Cita {
    private int idCita;
    private String status, cliente, mascota, fechaCita, fechaAsignada, fechaFinalizacion;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getFechaAsignada() {
        return fechaAsignada;
    }

    public void setFechaAsignada(String fechaAsignada) {
        this.fechaAsignada = fechaAsignada;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
    public String registrar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "insert into cita values(?,?,?,?,?,?,?)";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCita);
                ps.setString(2, fechaCita);
                ps.setString(3, fechaAsignada);
                ps.setString(4, status);
                ps.setString(5, fechaFinalizacion);
                ps.setString(6, cliente);
                ps.setString(7, mascota);
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
            String instruccionSQL = "update cita set fechaCita=?, fechaAsignada=?, status=?, fechaFinalizacion=?, cliente=?, mascota=? where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCita);
                ps.setString(2, fechaCita);
                ps.setString(3, fechaAsignada);
                ps.setString(4, status);
                ps.setString(5, fechaFinalizacion);
                ps.setString(6, cliente);
                ps.setString(7, mascota);
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
            String instruccionSQL = "Delete from cita where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCita);
                
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
        String instruccionSQL ="select + from cita where codigo =?";
        try{
            PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
            ps.setInt(1, idCita);
            ResultSet fila = ps.executeQuery();
            if(fila.next()){
                fechaCita = fila.getString("fecha cita");
                fechaAsignada = fila.getString("fecha asignada");
                status = fila.getString("status");
                fechaFinalizacion = fila.getString("fecha finalizacion");
                cliente = fila.getString("cliente");
                mascota = fila.getString("mascota");
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


