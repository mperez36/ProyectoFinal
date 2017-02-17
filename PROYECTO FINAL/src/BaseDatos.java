
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BaseDatos {
    
     private static Connection cnx = null;
    private String tabla = "catalogo";
    private String bd = "proyecto";
    
    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/catalogo", "root", "");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }
    
    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }
    
    public void guardarProducto(Connection conexion, Producto item) throws SQLException{
      try{
         PreparedStatement consulta = null;
         if(item.getId() > 0){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(id, descripcion, precio) VALUES(?, ?, ?)");
            consulta.setInt(1, item.getId());
            consulta.setString(2, item.getDescripcion());
            consulta.setFloat(3, item.getPrecio());
         }
         /*
         else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET titulo = ?, descripcion = ?, nivel_de_prioridad = ? WHERE id_tarea = ?");
            consulta.setString(1, tarea.getTitulo());
            consulta.setString(2, tarea.getDescripcion());
            consulta.setInt(3, tarea.getNivel_de_prioridad());
            consulta.setInt(4, tarea.getId_tarea());
         } */
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }

   public Producto recuperarPorId(Connection conexion, int id) throws SQLException {
      Producto p = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT descripcion, precio FROM " + this.tabla + " WHERE id = ?" );
         consulta.setInt(1, id);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            p = new Producto(id, resultado.getString("descripcion"), resultado.getFloat("precio"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return p;
   }
   
   public void eliminarPorId(Connection conexion,  int id) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id = ?");
         consulta.setInt(1, id);
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }

   public List<Producto> recuperarTodos(Connection conexion) throws SQLException{
      List<Producto> lista = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT id, descripcion, precio FROM " + this.tabla + " ORDER BY id");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            lista.add(new Producto(resultado.getInt("id"), resultado.getString("descripcion"), resultado.getFloat("precio")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return lista;
   }

}
