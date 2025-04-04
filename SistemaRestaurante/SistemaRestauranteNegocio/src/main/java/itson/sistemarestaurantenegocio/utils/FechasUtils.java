
package itson.sistemarestaurantenegocio.utils;

import java.util.Calendar;


public class FechasUtils {
    
    public static Calendar obtenerFechaActual(){
        
        Calendar fechaHoraActual = Calendar.getInstance();
        
        return fechaHoraActual;
    }
    
    public static String obtenerCadenaFechaActual(Calendar fechaHoraActual){
        String anioActual = String.valueOf(fechaHoraActual.get(Calendar.YEAR));
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", 
            "Agosto","Septiembre", "Octubre", "Noviembre", "Diciembre"};
        
        String mesActual = meses[fechaHoraActual.get(Calendar.MONTH)];
        
        String diaActual = String.valueOf(fechaHoraActual.get(Calendar.DAY_OF_MONTH));
        
        String fechaHoraActualCadena = anioActual+mesActual+diaActual;
        
        return fechaHoraActualCadena;
    }
}
