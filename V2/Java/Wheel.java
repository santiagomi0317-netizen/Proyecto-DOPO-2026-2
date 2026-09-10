import java.util.ArrayList;
import java.util.Random;
/**
 * Clase rueda la cual se encarga de adminsitrar comos e comprta la rueda en acciones como spin
 * 
 * @author (Santiago Murillo) 
 * @version (6 de septiembre)
 */
public class Wheel{
    private Symbol ActualSymbol;
    private boolean isVisible;
    private int positionX;
    private RectangleBorder border; 
    private boolean lock;

    /**
     * Constructor de la rueda ya instaciada con un simbolo aleatorio
     */
    public Wheel(ArrayList<Symbol> symbols) {
        this.isVisible = true;
        this.positionX = 0;
        this.border = new RectangleBorder(50, 50, 1, "black");
        this.border.moveVertical(35);
        this.lock= false;
        if (symbols != null && !symbols.isEmpty()) {
            spin(symbols);
        
        }
    }
    
    /**
     * Hace girar la rueda y selecciona un símbolo aleatorio de la lista.
     * @param symbols catálogo de símbolos
     * Sobrecarga de Spin que recibe un ArrayList de simbolos
     */
    public void spin(ArrayList<Symbol> symbols) {
        if (lock)return;

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
     */
    public void setSymbol(Symbol symbol) {
        if (lock) return;
        if (this.ActualSymbol != null) {
            this.ActualSymbol.makeInvisible();
        }
        if (symbol != null) {
            this.ActualSymbol = new Symbol(symbol.getColor(), symbol.getForm());
            if(this.positionX!=0){
                this.ActualSymbol.moveHorizontal(this.positionX);
            }
            this.ActualSymbol.moveVertical(35);
            if (this.isVisible) {
                this.ActualSymbol.makeVisible();
            }
        } else {
            this.ActualSymbol = null;
        }  
    }
    
    /**
     * Retorna el color del símbolo actual o null si no tiene.
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
     */
    public Symbol getActualSymbol() {
        return this.ActualSymbol;
    }
    
    /**
     * Muestra la rueda visualmente.
     */
    public void makeVisible() {
        this.isVisible = true;
        if (ActualSymbol != null) {
            ActualSymbol.makeVisible();
        if (border != null) {
            border.makeVisible();
        }
        }
    }
    
    /**
     * Oculta la rueda visualmente.
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
    
    /**
     * Bloquea la rueda para evitar que cambie de símbolo en los giros.
     * Invariante: Siempre cuando se bloquea, esta se le ve el marco rojo
     */
    public void lock() {
        this.lock = true;
        if (this.border != null) {
        this.border.changeColor("red");
        }
    }

    /**
     * Desbloquea la rueda permitiendo que vuelva a girar.
     * Invariante: Siempre cuando se bloquea, esta se le ve el marco negro
    */
    public void unlock() {
        this.lock = false;
        if (this.border != null) {
        this.border.changeColor("black");
        }
    }

    /**
     * @return boolean, true si la rueda esta bloqueada o false si esta desbloqueada
    */
    public boolean isLocked() {
        return this.lock;
    }
    
    /**
     * Metodo que cambia simbolo por symbolo hasta llegar a el ultimo indicado por steps,
     * Incluye la interfaz grafica
     * @param symbols (ArrayList<Symbol>) lista de simbolos disponibles
     * @param steps (int) cantidad de saltos en symbols
     * Invariante: Siempre que tengamos una lista de symbolos no nula, se van a mostrar uno por uno 
     * en la interfaz grafica como se va recorriendo symbols hasta llegar al simbolo final.
     */
    public void stepSpin(ArrayList<Symbol> symbols, int steps) {
        if (this.lock || symbols == null || symbols.isEmpty() || steps == 0) {
            return;
        }
        int direction;
        if (steps >= 0){
            direction = 1;
        } else {
            direction = -1;
        }
        int index=findCurrentSymbolIndex(symbols);;
        for (int i = 0; i < Math.abs(steps); i++) {
            index +=direction;
            if (index >= symbols.size()) {
                index = 0; 
            } else if (index < 0) {
                index = symbols.size() - 1;
            }
            setSymbol(symbols.get(index));
            wait(300);
        }
    }   
    
    /**
    * Método auxiliar para pausar la ejecución unos milisegundos sin bloquear BlueJ.
    * @param miliseconds (int)
    * Invariante: Siempre se para la ejecucion del programa tantos milisegundos como me indique el usuario
    */
    private void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (Exception a) {   
        }
    }
    
    /**
     * Método para encontrar la posición del símbolo actual en la lista symbols.
     * @param symbols (ArrayList<Symbol>) lista de simbolos disponibles}
     * Invariante: Siempre que el simbolo exista, se obtiene su pocicion en la lista symbols
     */
    public int findCurrentSymbolIndex(ArrayList<Symbol> symbols) {
        if (this.ActualSymbol == null) return 0;
        for (int i = 0; i < symbols.size(); i++) {
            Symbol s = symbols.get(i);
            if (s.getColor().equalsIgnoreCase(this.ActualSymbol.getColor()) &&
                s.getForm().equalsIgnoreCase(this.ActualSymbol.getForm())) {
                return i;
            }
        }
        return 0;
    }
}