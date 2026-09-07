import java.util.ArrayList;
import java.util.Random;
/**
 * Clase rueda la cual se encarga de adminsitrar comos e comprta la rueda en acciones como spin
 * 
 * @author (Santiago Murillo) 
 * @version (6 de septiembre)
 */
public class Wheel{
    private Symbol simboloActual;
    private boolean isVisible;
    private int posicionX;
    private RectangleBorder marcoCasilla;

    /**
     * Constructor de la rueda ya instaciada con un simbolo aleatorio
     */
    public Wheel(ArrayList<Symbol> symbols) {
        this.isVisible = true;
        this.posicionX = 0;
        this.marcoCasilla = new RectangleBorder(50, 50, 1, "black");
        this.marcoCasilla.moveVertical(35);
        if (symbols != null && !symbols.isEmpty()) {
            spin(symbols);    
        }
    }
    
    /**
     * Hace girar la rueda y selecciona un símbolo aleatorio de la lista.
     * @param symbols catálogo de símbolos
     */
    public void spin(ArrayList<Symbol> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            if (this.simboloActual != null && isVisible) {
                this.simboloActual.makeInvisible();
            }
            this.simboloActual = null;
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(symbols.size());
        setSymbol(symbols.get(randomIndex));
    }
    
    /**
     * Reposiciona dinámicamente la rueda y su figura gráfica a una nueva coordenada X.
     * Tomada de Gemini
     * @param nuevaPosicionX Coordenada horizontal absoluta en el Canvas.
     */
    public void moveA(int nuevaPosicionX) {
        int desplazamiento = nuevaPosicionX - this.posicionX;
        if (this.marcoCasilla != null) {
            this.marcoCasilla.moveHorizontal(desplazamiento);
        }
        if (this.simboloActual != null) {
            this.simboloActual.moveHorizontal(desplazamiento);
        }
        posicionX = nuevaPosicionX;
    }
    
    /**
     * Asigna un símbolo específico a la rueda.
     * @param symbol nuevo símbolo a mostrar
     */
    public void setSymbol(Symbol symbol) {
        if (this.simboloActual != null && isVisible) {
            this.simboloActual.makeInvisible();
        }
        if (symbol != null) {
            this.simboloActual = new Symbol(symbol.getColor(), symbol.getForma());
            if(this.posicionX!=0){
                this.simboloActual.moveHorizontal(this.posicionX);
            }
            this.simboloActual.moveVertical(35);
            if (isVisible) {
                this.simboloActual.makeVisible();
            }
        } else {
            this.simboloActual = null;
        
        }  
    }
    
    
    /**
     * Retorna el color del símbolo actual o null si no tiene.
     */
    public String getColor() {
        if (simboloActual != null) {
            return simboloActual.getColor();
        } else {
            return null;
        }
    }
    
    /**
     * Retorna el objeto Symbol que está asignado actualmente a la rueda.
     * 
     * @return el Symbol actual, o null si la rueda está vacía.
     */
    public Symbol getSimboloActual() {
        return this.simboloActual;
    }
    
    /**
     * Muestra la rueda visualmente.
     */
    public void makeVisible() {
        this.isVisible = true;
        if (simboloActual != null) {
            simboloActual.makeVisible();
        if (marcoCasilla != null) {
            marcoCasilla.makeVisible();
        }
        }
    }
    
    /**
     * Oculta la rueda visualmente.
     */
    public void makeInvisible() {
        this.isVisible = false;
        if (simboloActual != null) {
            simboloActual.makeInvisible();
        }
        if (simboloActual != null) {
            simboloActual.makeInvisible();
        }    
    }
}