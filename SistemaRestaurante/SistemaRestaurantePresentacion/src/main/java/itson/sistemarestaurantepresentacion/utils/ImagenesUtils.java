
package itson.sistemarestaurantepresentacion.utils;

import java.awt.Image;
import javax.swing.ImageIcon;


public class ImagenesUtils {
    
    
    public static ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int nuevoAncho, int nuevoAlto){
        
        Image imagen = imagenOriginal.getImage();
        
        Image imagenRedimensionada = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        
        ImageIcon iconoImagenRedimensionado = new ImageIcon(imagenRedimensionada);
        
        return iconoImagenRedimensionado;
    }
}
