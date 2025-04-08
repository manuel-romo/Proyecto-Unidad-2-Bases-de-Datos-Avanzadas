
package itson.sistemarestaurantepresentacion.utils;

import itson.sistemarestaurantepresentacion.excepciones.ImagenNoEncontradaException;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;


public class ImagenesUtils {
    
    
    public static ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int nuevoAncho, int nuevoAlto){
        
        Image imagen = imagenOriginal.getImage();
        
        Image imagenRedimensionada = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        
        ImageIcon iconoImagenRedimensionado = new ImageIcon(imagenRedimensionada);
        
        return iconoImagenRedimensionado;
    }
    
    public static ImageIcon obtenerImagen(String direccionImagen) throws ImagenNoEncontradaException{

        URL imagen = ImagenesUtils.class.getResource(direccionImagen);
        
        ImageIcon iconoImagen;
        
        if(imagen == null){
            
            File file = new File(direccionImagen);
            if (file.exists()) {
                // Crear un ImageIcon desde el archivo
                iconoImagen = new ImageIcon(file.getAbsolutePath());
                
            } else {
                throw new ImagenNoEncontradaException("No se encontró ninguna"
                        + " imagen en la dirección especificada.");
            }
            
        } else{
            iconoImagen = new ImageIcon(ImagenesUtils.class.getResource(direccionImagen));
        }
        
        return iconoImagen;
    }
}
