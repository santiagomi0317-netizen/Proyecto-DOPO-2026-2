import java.util.ArrayList;
import java.util.List;
/**
 * Representa una rueda individual dentro de la maquina tragamonedas
 * Administra la colección secuencial de simbolos, su rotacion grafica y el simbolo activo
 * 
 * @author David Laiton - Santiago Murillo 
 * @version 1.0
 */
public class Wheel
{
    
    /**
     * Crea una rueda vacia sin simbolos asignados
     */
    public Wheel()
    {
        // initialise instance variables
        
    }

    /**
     * Agrega un nuevo simbolo a la secuencia de la rueda
     * @param  color Color CSS del simbolo a crear
     */
    public void addSymbol(String color){
        // put your code here
        
    }
    
    /**
     * Elimina la primera o todas las apariciones de un simbolo especifico en la rueda
     * @param color Color CSS del simbolo al remover
     * @return true si se eliminó, false en caso contrario
     */
    public boolean removeSymbol(String color){
        return false;
    }
    
    /**
     * Verifica si la rueda contiene un simbolo con el codigo indicado
     * @param color Color CSS a buscar
     * @return true si existe en la rueda
     */
    public boolean containSymbol(String color){
        return false;
    }
    
    /**
     * Gira la rueda para cambiar el simbolo que queda visible en la parte frontal
     */
    public void spin(){
        
    }
    
    /**
     * Fuerza la selección del simbolo visible en la rueda
     * @param color Color CSS del simbolo que debe quedar al fente
     */
    public void setTopSymbol(String color){
        
    }
    
    /**
     * Obtiene el color CSS del simbolo que esta actualmente visible en la rueda
     * @return Nombre del color del color visible
     */
    public String getVisibleSymbol(){
        return null;
    }
    
    /**
     * Retorna todos los colores de los simbolos contenidos en esta rueda en su orden actual
     * @return Arreglo de cadenas con los colores de la rueda
     */
    public String[] getSymbolArray(){
        return null;
    }
    
    /**
     * Retorna la lista de simbolos sin repetir presentes en esta rueda
     * @return Lista con los colores unicos de la rueda
     */
    public List<String> getAllSymbols(){
        return null;
    }
    
    /**
     * Muestra la representacion visual de la rueda y de su simbolo activo
     */
    public void makeVisible(){
        
    }
    
    /**
     * Oculta la representacion visual de la rueda
     */
    public void makeInvisible (){
        
    }
}