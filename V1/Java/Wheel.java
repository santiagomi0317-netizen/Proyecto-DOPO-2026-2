import java.util.ArrayList;
import java.util.Random;
/**
 * Clase rueda la cual se encarga de adminsitrar comos e comprta la rueda en acciones como spin
 * 
 * @author (Santiago Murillo) 
 * @version (6 de septiembre)
 * Ciclo 1
 */
public class Wheel{
    private Symbol ActualSymbol;
    private boolean isVisible;
    private int positionX;
    private RectangleBorder border; 

    /**
     * Constructor de la rueda ya instaciada con un simbolo aleatorio
     * Ciclo 1
     */
    public Wheel(ArrayList<Symbol> symbols) {
        this.isVisible = true;
        this.positionX = 0;
        this.border = new RectangleBorder(50, 50, 1, "black");
        this.border.moveVertical(35);
        if (symbols != null && !symbols.isEmpty()) {
            spin(symbols);    
        }
    }
    
    /**
     * Hace girar la rueda y selecciona un símbolo aleatorio de la lista.
     * @param symbols catálogo de símbolos
     * Sobrecarga de Spin que recibe un ArrayList de simbolos
     * Ciclo 1
     */
    public void spin(ArrayList<Symbol> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            if (this.ActualSymbol != null && isVisible) {
                this.ActualSymbol.makeInvisible();
            }
            this.ActualSymbol = null;
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(symbols.size());
        setSymbol(symbols.get(randomIndex));
    }
    
    /**
     * Reposiciona dinámicamente la rueda y su figura gráfica a una nueva coordenada X.
     * Tomada de Gemini
     * @param nuevaPositionX Coordenada horizontal absoluta en el Canvas.
     * Ciclo 1
     */
    public void moveA(int nuevaPosicionX) {
        int desplazamiento = nuevaPosicionX - this.positionX;
        if (this.border != null) {
            this.border.moveHorizontal(desplazamiento);
        }
        if (this.ActualSymbol != null) {
            this.ActualSymbol.moveHorizontal(desplazamiento);
        }
        positionX = nuevaPosicionX;
    }
    
    /**
     * Asigna un símbolo específico a la rueda.
     * @param symbol nuevo símbolo a mostrar
     * Ciclo 1
     */
    public void setSymbol(Symbol symbol) {
        if (this.ActualSymbol != null && isVisible) {
            this.ActualSymbol.makeInvisible();
        }
        if (symbol != null) {
            this.ActualSymbol = new Symbol(symbol.getColor(), symbol.getForm());
            if(this.positionX!=0){
                this.ActualSymbol.moveHorizontal(this.positionX);
            }
            this.ActualSymbol.moveVertical(35);
            if (isVisible) {
                this.ActualSymbol.makeVisible();
            }
        } else {
            this.ActualSymbol = null;
        }  
    }
    
    
    /**
     * Retorna el color del símbolo actual o null si no tiene.
     * Ciclo 1
     */
    public String getColor() {
        if (ActualSymbol != null) {
            return ActualSymbol.getColor();
        } else {
            return null;
        }
    }
    
    /**
     * Retorna el objeto Symbol que está asignado actualmente a la rueda.
     * 
     * @return el Symbol actual, o null si la rueda está vacía.
     * Ciclo 1
     */
    public Symbol getActualSymbol() {
        return this.ActualSymbol;
    }
    
    /**
     * Muestra la rueda visualmente.
     * Ciclo 1
     */
    public void makeVisible() {
        this.isVisible = true;
        if (ActualSymbol != null) {
            ActualSymbol.makeVisible();
        }
        if (border != null) {
            border.makeVisible();
        }
    }
    
    /**
     * Oculta la rueda visualmente.
     * Ciclo 1
     */
    public void makeInvisible() {
        this.isVisible = false;
        if (ActualSymbol != null) {
            ActualSymbol.makeInvisible();
        }
        if (border != null) {
            border.makeInvisible();
        }
    }   
}