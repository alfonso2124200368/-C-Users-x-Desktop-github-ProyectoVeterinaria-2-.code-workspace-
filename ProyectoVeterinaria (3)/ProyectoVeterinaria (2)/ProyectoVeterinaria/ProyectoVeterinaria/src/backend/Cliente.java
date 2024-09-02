package backend;
import java.sql.*;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Cliente {
    private int idCliente;
    private String nombre, telefono, correo, direccion, mascota;
    private int edad;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String registrar (){
        String mensaje ="";
        Conexion conexion = new Conexion();
        if(conexion.conectar()!=null){ //si se pudo conectar por que no esta vacio
            String instruccionSQL = "insert into cliente values(?,?,?,?,?,?,?)";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCliente);
                ps.setString(2, nombre);
                ps.setInt(3, edad);
                ps.setString(4, direccion);
                ps.setString(5, mascota);
                ps.setString(6, correo);
                ps.setString(7, telefono);
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
            String instruccionSQL = "update cliente set nombre=?, edad=?, direccion=?, mascota=?, correo=?, telefono=? where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCliente);
                ps.setString(2, nombre);
                ps.setInt(3, edad);
                ps.setString(4, direccion);
                ps.setString(5, mascota);
                ps.setString(6, correo);
                ps.setString(7, telefono);
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
            String instruccionSQL = "Delete from cliente where codigo =?";
            try{
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ps.setInt(1, idCliente);
                
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
        String instruccionSQL ="select + from cliente where codigo =?";
        try{
            PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
            ps.setInt(1, idCliente);
            ResultSet fila = ps.executeQuery();
            if(fila.next()){
                nombre = fila.getString("nombre");
                edad = fila.getInt("edad");
                direccion = fila.getString("dirección");
                mascota = fila.getString("mascota");
                correo = fila.getString("correo");
                telefono = fila.getString("télefono");
            }
        }catch(SQLException ex){
            mensaje = "Error al consultar";
        }
    }else{
            mensaje= "No se pudo consultar";
        }
        return mensaje;
    }
    public void loguearse (){ 
    }
    public String llenarTabla (DefaultTableModel modeloTabla){
        String mensaje = "";
        Conexion conexion = new Conexion();
        modeloTabla.addColumn("ID Ciente");
        modeloTabla.addColumn("Nombre Completo");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Mascota");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Télefono");
        if(conexion.conectar() != null) {
            String instruccionSQL = "select *from cliente";
            try {
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                ResultSet tabla = ps.executeQuery();
                while(tabla.next()){
                    Vector fila = new Vector();
                    // Llenar fila
                    fila.addElement(tabla.getString(idCliente));
                    fila.addElement(tabla.getString(nombre));
                    fila.addElement(tabla.getString(edad));
                    fila.addElement(tabla.getString(direccion));
                    fila.addElement(tabla.getDouble(mascota));
                    fila.addElement(tabla.getString(correo));
                    fila.addElement(tabla.getString(telefono));
                    // Agregar la fila al modeloTabla
                    modeloTabla.addRow(fila);
                }
            }catch(SQLException ex) {
                mensaje = "Error al llenar la tabla";
            }
        }
        return mensaje;
        
    }
    public String busquedaxNombre (DefaultTableModel modeloTabla){
        String mensaje = "";
        Conexion conexion = new Conexion();
        modeloTabla.addColumn("ID Cliente");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Mascota");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Télefono");
        if(conexion.conectar() != null) {
            String instruccionSQL = "select * from cliente where nombre like '"+nombre +"%'";
            try {
                PreparedStatement ps = conexion.conex.prepareStatement(instruccionSQL);
                // ps.setString(1, nombre);
                ResultSet tabla = ps.executeQuery();
                // llenar el modelo de la tabla
                while(tabla.next()){
                    Vector fila = new Vector();
                    // Llenar fila
                    fila.addElement(tabla.getString(idCliente));
                    fila.addElement(tabla.getString(nombre));
                    fila.addElement(tabla.getString(edad));
                    fila.addElement(tabla.getString(direccion));
                    fila.addElement(tabla.getDouble(mascota));
                    fila.addElement(tabla.getString(correo));
                    fila.addElement(tabla.getString(telefono));
                    // Agregar la fila al modeloTabla
                    modeloTabla.addRow(fila);
                }
            }catch(SQLException ex) {
                mensaje = "Error al llenar la tabla";
            }
        }
        return mensaje;
    }
}
