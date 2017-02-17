
import javax.swing.JOptionPane;


public class Interaccion {
    
    
    public static String leerString (String msg){
       return(JOptionPane.showInputDialog(msg));
        
    }
    
    public static void desplegar(String msg, String t){
         JOptionPane.showMessageDialog(null,msg,t,
                 JOptionPane.PLAIN_MESSAGE);
        
    }
    
    public static int leerEntero (String msg){
        String cadena = JOptionPane.showInputDialog(msg);
        int entero = Integer.parseInt(cadena);
        return entero;
        
    }
    
    public static float leerFlotante (String msg){
        String cadena = JOptionPane.showInputDialog(msg);
        return Float.parseFloat(cadena);
        
        
        
    }
}
    
   
