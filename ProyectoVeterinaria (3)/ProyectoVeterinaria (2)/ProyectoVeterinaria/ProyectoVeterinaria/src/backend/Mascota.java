package backend;
import backend.Conexion;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Mascota {
    private int idMascota;
    private String nombre, especie, raza, cliente;
    private int peso;

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
        public String registrar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "insert into mascota values(?,?,?,?,?,?)";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idMascota);
                ps.setString(2, nombre);
                ps.setString(3, especie);
                ps.setString(4, raza);
                ps.setInt(5, peso);
                ps.setString(6, cliente);
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
            String instruccionSQL = "update mascota set nombre=?, especie=?, raza=?, peso=?, dueño=? where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idMascota);
                ps.setString(2, nombre);
                ps.setString(3, especie);
                ps.setString(4, raza);
                ps.setInt(5, peso);
                ps.setString(6, cliente);
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
            String instruccionSQL = "Delete from mascota where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idMascota);
                
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
        String instruccionSQL ="select + from mascota where codigo =?";
        try{
            PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
            ps.setInt(1, idMascota);
            ResultSet fila = ps.executeQuery();
            if(fila.next()){
                nombre = fila.getString("nombre");
                especie = fila.getString("especie");
                raza = fila.getString("raza");
                peso = fila.getInt("peso");
                cliente = fila.getString("cliente");
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


