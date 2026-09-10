/**
 * Representa un marco o rectángulo compuesto únicamente por sus bordes.
 * Permite encerrar figuras sin tapar el fondo.
 * Ciclo 1
 * 
 * @author Santiago Murillo
 * @version 2026-2
 */
public class RectangleBorder {
    private Rectangle up;
    private Rectangle down;
    private Rectangle left;
    private Rectangle right;
    private boolean isVisible;
    private int width;
    private int height;
    private int thickness;

    /**
     * Constructor para un rectángulo de solo bordes.
     * 
     * @param width Ancho total del marco.
     * @param height Alto total del marco.
     * @param thickness Grosor de la línea del borde.
     * @param color Color del borde (ej. "black", "red", "blue").
     * Ciclo 1
     */
    public RectangleBorder(int width, int height, int thickness, String color) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.isVisible = false;

        up = new Rectangle();
        down = new Rectangle();
        left = new Rectangle();
        right = new Rectangle();
        
        adjustRectangle();
        changeColor(color);
    }

    /**
     * Ajusta las dimensiones y posiciones de las 4 líneas para formar el borde.
     * Ciclo 1
     */
    private void adjustRectangle() {
        // Lado up e down
        up.changeSize(thickness, width);
        down.changeSize(thickness, width);

        // Lado left y right
        left.changeSize(height, thickness);
        right.changeSize(height, thickness);

        // Posicionar lados opuestos
        down.moveVertical(height - thickness);
        right.moveHorizontal(width - thickness);
    }

    /**
     * Cambia el tamaño del marco de bordes.
     * Ciclo 1
     */
    public void changeSize(int newWidth, int newHeight) {
        boolean estabaVisible = isVisible;
        if (estabaVisible) makeInvisible();

        // Reposicionar down y right a su origen antes de cambiar tamaño
        down.moveVertical(-(height - thickness));
        right.moveHorizontal(-(width - thickness));

        this.width = newWidth;
        this.height = newHeight;

        adjustRectangle();

        if (estabaVisible) makeVisible();
    }

    /**
     * Cambia el color del borde.
     * Ciclo 1
     */
    public void changeColor(String color) {
        up.changeColor(color);
        down.changeColor(color);
        left.changeColor(color);
        right.changeColor(color);
    }

    /**
     * Muestra el marco en la pantalla.
     * Ciclo 1
     */
    public void makeVisible() {
        isVisible = true;
        up.makeVisible();
        down.makeVisible();
        left.makeVisible();
        right.makeVisible();
    }

    /**
     * Oculta el marco de la pantalla.
     * Ciclo 1
     */
    public void makeInvisible() {
        isVisible = false;
        up.makeInvisible();
        down.makeInvisible();
        left.makeInvisible();
        right.makeInvisible();
    }

    /**
     * Desplaza el marco horizontalmente.
     * Ciclo 1
     */
    public void moveHorizontal(int distance) {
        
        up.moveHorizontal(distance);
        down.moveHorizontal(distance);
        left.moveHorizontal(distance);
        right.moveHorizontal(distance);
    }

    /**
     * Desplaza el marco verticalmente.
     * Ciclo 1
     */
    public void moveVertical(int distance) {
        up.moveVertical(distance);
        down.moveVertical(distance);
        left.moveVertical(distance);
        right.moveVertical(distance);
    }
}