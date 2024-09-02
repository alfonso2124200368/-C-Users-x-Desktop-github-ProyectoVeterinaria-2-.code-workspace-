
//    private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
//    private static final String USER = "root";
//    private static final String PASSWORD = "jarale";

    package backend;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AlumnosUTJCCD
 */
public class Conexion {
    String usuario = "root";
    String password = "jarale";
    String url = "jdbc:mysql://localhost:3306/veterinaria";
    Connection conex;

    public Connection conectar() {
        // 1.
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            // Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // 2.
            conex = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            // Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erorr de conexion" + ex.getMessage());
        }
        return conex;
    }
    public void desconectar() {
        try {
            conex.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al desconectar");
        }
    }
}
