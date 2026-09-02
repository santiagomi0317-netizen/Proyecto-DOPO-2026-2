/**
 * Representa la entidad visual y lógica de un símbolo dentro de una rueda.
 * Encapsula la figura gráfica proveniente del paquete shapes y su color CSS asociado.
 * 
 * @author David Laiton - Santiago Murillo
 * @version 1.0
 */
public class Symbol{
    private String color;
    private boolean isVisible;
    private Rectangle figure;
    private int xPosition;
    private int yPosition;
    
    /**
     * Crea un símbolo con una representación gráfica asociada a un color estándar CSS.
     * @param color Nombre del color en estándar CSS ("red", "blue", "yellow").
     * @param xPos Posición inicial en el eje X para el dibujo.
     * @param yPos Posición inicial en el eje Y para el dibujo.
     */
    public Symbol(String color){
        this.color = color;
        isVisible = false;
        xPosition = 0;
        yPosition = 0;
        //Creamos la figura el el canvas
        figure = new Rectangle();
        figure.changeColor(this.color);
        
    }

    /**
     * Obtiene el color CSS asignado al símbolo.
     * @return Nombre del color CSS.
     */
    public String getColor(){
        return null;
    }
    
    /**
     * Cambia la posición en pantalla de la representación gráfica del símbolo.
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     */
    public void setPosition(int x, int y){
        
    }
    
    /**
     * Muestra el elemento grafico del simbolo en el lienzo
     */
    public void makeVisible(){
        this.isVisible = true;
        this.figure.makeVisible();
        
    }
    
    /**
     * Oculta el elemento grafico del simbolo del lienzo
     */
    public void makeInvisible(){
        this.isVisible = false;
        this.figure.makeInvisible();
    }
}