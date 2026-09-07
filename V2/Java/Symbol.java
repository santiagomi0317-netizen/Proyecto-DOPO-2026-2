/**
 * Write a description of class Symbol here.
 * 
 * @author (Santiago Murillo) 
 * @version (9 de septiembre)
 */
public class Symbol{
    private String color;
    private String forma;
    private boolean isVisible;
    private Circle circulo;
    private Rectangle rectangulo;
    private Triangle triangulo;
    
    /**
     * Constructor para instanciar un nuevo Symbol.
     * 
     * @param color identificador del color (ej. "red", "blue", "yellow")
     * @param forma tipo de figura geométrica ("Triangle", "Circle", "Rectangle")
     */
    public Symbol(String color, String forma) {
        this.color = normalizarColorCSS(color);
        this.forma = (forma != null) ? forma : "Circle";
        this.isVisible = false;
        instanciarFigura();
    }
    
    /**
     * Normaliza la cadena para asegurar compatibilidad con nombres de colores estándar CSS.
     * Convierte la cadena a minúsculas y elimina espacios extras.
     * Tomado de gemini
     */
    private String normalizarColorCSS(String colorCSS) {
        if (colorCSS == null) {
            return "";
        }
        return colorCSS.trim().toLowerCase();
    }
    
    /**
     * Crea la figura correspondiente según el valor de 'forma' y le aplica el color.
     */
    private void instanciarFigura() {
        if (forma.equalsIgnoreCase("Circle")) {
            circulo = new Circle();
            circulo.changeColor(color);
            
            circulo.moveHorizontal(10); 
            circulo.moveVertical(10);
        } else if (forma.equalsIgnoreCase("Rectangle")) {
            rectangulo = new Rectangle();
            rectangulo.changeColor(color);
            
            rectangulo.moveHorizontal(10);
            rectangulo.moveVertical(10);
        } else if (forma.equalsIgnoreCase("Triangle")) {
            triangulo = new Triangle();
            triangulo.changeColor(color);
            
            triangulo.moveHorizontal(25);
            triangulo.moveVertical(10);
        }
    }
    
    /**
     * Obtiene el color identificador del símbolo.
     * 
     * @return el nombre o código del color
     */
    public String getColor() {
        return this.color;
    }
    
    /**
     * Obtiene el tipo de forma geométrica del símbolo.
     * 
     * @return el nombre de la figura geométrica
     */
    public String getForma() {
        return this.forma;
    }
    
    /**
     * Oculta la figura del símbolo de la pantalla.
     */
    public void makeInvisible() {
        this.isVisible = false;
        if (circulo != null) circulo.makeInvisible();
        if (rectangulo != null) rectangulo.makeInvisible();
        if (triangulo != null) triangulo.makeInvisible();
    }
    
    /**
     * Consulta si el símbolo está visible.
     * 
     * @return true si es visible, false en caso contrario
     */
    public void makeVisible() {
        this.isVisible = true;
        if (circulo != null) circulo.makeVisible();
        if (rectangulo != null) rectangulo.makeVisible();
        if (triangulo != null) triangulo.makeVisible();
    }
    
    /**
     * Desplaza horizontalmente la figura visual para alinearla en su columna.
     * 
     * @param distance Cantidad de píxeles a desplazar.
     */
    public void moveHorizontal(int distance) {
        if (circulo != null) circulo.moveHorizontal(distance);
        if (rectangulo != null) rectangulo.moveHorizontal(distance);
        if (triangulo != null) triangulo.moveHorizontal(distance);
    }

    /**
     * Desplaza verticalmente la figura visual en la pantalla.
     * 
     * @param distance cantidad de píxeles a mover en el eje Y
     */
    public void moveVertical(int distance) {
        if (circulo != null) circulo.moveVertical(distance);
        if (rectangulo != null) rectangulo.moveVertical(distance);
        if (triangulo != null) triangulo.moveVertical(distance);
    }  
}