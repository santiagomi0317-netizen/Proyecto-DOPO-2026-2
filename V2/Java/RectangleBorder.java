/**
 * Representa un marco o rectángulo compuesto únicamente por sus bordes.
 * Permite encerrar figuras sin tapar el fondo.
 * 
 * @author Santiago Murillo
 * @version 2026-2
 */
public class RectangleBorder {
    private Rectangle superior;
    private Rectangle inferior;
    private Rectangle izquierdo;
    private Rectangle derecho;
    private boolean isVisible;
    private int width;
    private int height;
    private int thickness;

    /**
     * Constructor para un rectángulo de solo borde.
     * 
     * @param width Ancho total del marco en píxeles.
     * @param height Alto total del marco en píxeles.
     * @param thickness Grosor de la línea del borde en píxeles.
     * @param color Color del borde (ej. "black", "red", "blue").
     */
    public RectangleBorder(int width, int height, int thickness, String color) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.isVisible = false;

        superior = new Rectangle();
        inferior = new Rectangle();
        izquierdo = new Rectangle();
        derecho = new Rectangle();
        
        ajustarDimensiones();
        changeColor(color);
    }

    /**
     * Ajusta las dimensiones y posiciones de las 4 líneas para formar el borde.
     */
    private void ajustarDimensiones() {
        // Lado superior e inferior
        superior.changeSize(thickness, width);
        inferior.changeSize(thickness, width);

        // Lado izquierdo y derecho
        izquierdo.changeSize(height, thickness);
        derecho.changeSize(height, thickness);

        // Posicionar lados opuestos
        inferior.moveVertical(height - thickness);
        derecho.moveHorizontal(width - thickness);
    }

    /**
     * Cambia el tamaño del marco de bordes.
     */
    public void changeSize(int newWidth, int newHeight) {
        boolean estabaVisible = isVisible;
        if (estabaVisible) makeInvisible();

        // Reposicionar inferior y derecho a su origen antes de cambiar tamaño
        inferior.moveVertical(-(height - thickness));
        derecho.moveHorizontal(-(width - thickness));

        this.width = newWidth;
        this.height = newHeight;

        ajustarDimensiones();

        if (estabaVisible) makeVisible();
    }

    /**
     * Cambia el color del borde.
     */
    public void changeColor(String color) {
        superior.changeColor(color);
        inferior.changeColor(color);
        izquierdo.changeColor(color);
        derecho.changeColor(color);
    }

    /**
     * Muestra el marco en la pantalla.
     */
    public void makeVisible() {
        isVisible = true;
        superior.makeVisible();
        inferior.makeVisible();
        izquierdo.makeVisible();
        derecho.makeVisible();
    }

    /**
     * Oculta el marco de la pantalla.
     */
    public void makeInvisible() {
        isVisible = false;
        superior.makeInvisible();
        inferior.makeInvisible();
        izquierdo.makeInvisible();
        derecho.makeInvisible();
    }

    /**
     * Desplaza el marco horizontalmente.
     */
    public void moveHorizontal(int distance) {
        superior.moveHorizontal(distance);
        inferior.moveHorizontal(distance);
        izquierdo.moveHorizontal(distance);
        derecho.moveHorizontal(distance);
    }

    /**
     * Desplaza el marco verticalmente.
     */
    public void moveVertical(int distance) {
        superior.moveVertical(distance);
        inferior.moveVertical(distance);
        izquierdo.moveVertical(distance);
        derecho.moveVertical(distance);
    }
}