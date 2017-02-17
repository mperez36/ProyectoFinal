import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConsultaMain {
    
    public static void main(String[] args) throws SQLException {
        Producto item = new Producto(500,"Pantalon azul",700);
        BaseDatos bd = new BaseDatos();
        try {
//            bd.guardarProducto(BaseDatos.obtener(), item);
//            item = bd.recuperarPorId(BaseDatos.obtener(), 128);
//            System.out.println("descripcion: " + item.getDescripcion());
//            System.out.println("precio: " + item.getPrecio());
//            bd.eliminarPorId(BaseDatos.obtener(), 123);
//        
            List<Producto> lista = bd.recuperarTodos(BaseDatos.obtener());
            
            for(int i=0; i<lista.size(); i++){
                System.out.println("\nID: " + lista.get(i).getId());
                System.out.println("Descripcion: " + lista.get(i).getDescripcion());
                System.out.println("Precio: " + lista.get(i).getPrecio());
           }
            BaseDatos.cerrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConsultaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
