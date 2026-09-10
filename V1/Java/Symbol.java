/**
 * Write a description of class Symbol here.
 * 
 * @author (Santiago Murillo) 
 * @version (9 de septiembre)
 * Ciclo 1
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
     * Ciclo 1
     */
    public Symbol(String color, String forma) {
        this.color = ColorCSS(color);
        if (forma != null) {
            this.forma = forma;
        } else {
            this.forma = "Circle";
        }
        this.isVisible = false;
        createFigure(color, forma);
    }
    
    /**
     * Obtiene el color identificador del símbolo.
     * 
     * @return el nombre o código del color
     * Ciclo 1
     */
    public String getColor() {
        return this.color;
    }
    
    /**
     * Obtiene el tipo de forma geométrica del símbolo.
     * 
     * @return el nombre de la figura geométrica
     * Ciclo 1
     */
    public String getForm() {
        return this.forma;
    }
    
    /**
     * Oculta la figura del símbolo de la pantalla.
     * Ciclo 1
     */
    public void makeInvisible() {
        this.isVisible = false;
        if (circulo != null) circulo.makeInvisible();
        if (rectangulo != null) rectangulo.makeInvisible();
        if (triangulo != null) triangulo.makeInvisible();
    }
    
    /**
     * Muestra la figura del símbolo de la pantalla.
     * 
     * @return true si es visible, false en caso contrario
     * Ciclo 1
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
     * Ciclo 1
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
     * Ciclo 1
     */
    public void moveVertical(int distance) {
        if (circulo != null) circulo.moveVertical(distance);
        if (rectangulo != null) rectangulo.moveVertical(distance);
        if (triangulo != null) triangulo.moveVertical(distance);
    }  
    
    /**
     * Normaliza la cadena para asegurar compatibilidad con nombres de colores estándar CSS.
     * Convierte la cadena a minúsculas y elimina espacios extras.
     * Tomado de gemini
     * Ciclo 1
     */
    private String ColorCSS(String colorCSS) {
        if (colorCSS == null) {
            return "";
        }
        return colorCSS.trim().toLowerCase();
    }
    
    /**
     * Crea la figura correspondiente según el valor de 'forma' y le aplica el color.
     * Ciclo 1
     */
    private void createFigure(String color, String forma) {
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
}