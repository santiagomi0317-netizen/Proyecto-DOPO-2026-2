import java.util.ArrayList;

/**
 * Clase principal de la maquina traga monedas
 * Reutiliza el paquete "shapes" (a través de Wheel) para el dibujo.
 * 
 * @author (Santiago Murillo) 
 * Coordina un conjunto de ruedas (Wheel) y simbolos (Symbol)
 * @version (1 de septiembre)
 */
public class SlotMachine {
    private ArrayList<Wheel> wheels;
    private ArrayList<Symbol> symbols;
    private boolean isVisible;
    private boolean ok;

    /**
     * Crea una maquina con 3 wheels
     */
    public SlotMachine() {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<Symbol>();
        isVisible = false;
        ok = true;
        addWheel(1);
        addWheel(2);
        addWheel(3);  
    }
    
    /**
     * Agrega una rueda nueva en la posición indicada
     * @param pos posición donde se insertará la rueda (1-based)
     */
    public void addWheel(int pos) {
        int index;
        if (pos < 1) {
            index = 0;
        } else if (pos > wheels.size()) {
            index = wheels.size();
        } else {
            index = pos - 1;
        }
        
        Wheel nueva = new Wheel();
        wheels.add(index, nueva);
        ok = true;
    }
    
    /**
     * Elimina la rueda en la posición dada (1-based)
     * @param pos posición de la rueda a eliminar
     */
    public void delWheel(int pos) {
        if (wheels.isEmpty() || pos < 1 || pos > wheels.size()) {
            ok = false;
            return;
        }
        
        wheels.remove(pos - 1);
        ok = true;
    }
        
    /**
     * Añade un símbolo al catálogo de la máquina
     * @param color identificador único del símbolo
     * @param forma figura geométrica ("Triangle", "Circle", "Rectangle")
     */
    public void addSymbol(String color, String forma) {
        if (findSymbol(color) != null) {
            ok = false; 
            return;
        }
        
        symbols.add(new Symbol(color, forma));
        ok = true;
    }
        
    /**
     * Elimina un símbolo de la máquina por su color
     * @param color del símbolo a quitar
     */
    public void delSymbol(String color) {
        Symbol found = findSymbol(color);
        
        if (found == null) {
            ok = false;
            return;
        }
        
        symbols.remove(found);
        
        // Si una rueda tenía este símbolo, la hace girar para cambiarlo
        for (Wheel wheel : wheels) {
            if (wheel.getSimboloActual() == found) {
                wheel.spin(symbols);
            }
        }
        ok = true;
    }
    
    /**
     * Busca un símbolo en la lista por su color.
     * @param color identificador del símbolo
     * @return el Symbol encontrado o null si no existe
     */
    private Symbol findSymbol(String color) {
        for (Symbol s : symbols) {
            if (s.getColor().equalsIgnoreCase(color)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * Establece el símbolo seleccionado en la rueda indicada
     * @param wheelPos posición de la rueda (1-based)
     * @param color id del símbolo
     */
    public void placeSymbol(int wheelPos, String color) {
        Symbol symbolToPlace = findSymbol(color);
        
        if (symbolToPlace == null || wheels.isEmpty()) {
            ok = false;
            return;
        }
        
        int index;
        if (wheelPos < 1) {
            index = 0;
        } else if (wheelPos > wheels.size()) {
            index = wheels.size() - 1;
        } else {
            index = wheelPos - 1;
        }
        
        Wheel targetWheel = wheels.get(index);
        targetWheel.setSymbol(symbolToPlace); 
        ok = true;
    }
        
    /**
     * Rueda una rueda específica
     * @param wheelPos posición de la rueda (1-based)
     */
    public void spin(int wheelPos) {
        Wheel wheel = wheelAt(wheelPos);
        if (wheel == null || symbols.isEmpty()) {
            ok = false;
            return;
        }
        wheel.spin(symbols);
        ok = true;
    }
    
    /**
     * Obtiene la rueda en la posición especificada (1-based)
     * @param wheelPos posición de la rueda 
     * @return el objeto Wheel si la posición es válida; null en caso contrario.
     */
    private Wheel wheelAt(int wheelPos) {
        if (wheelPos < 1 || wheelPos > wheels.size()) {
            return null;
        }
        return wheels.get(wheelPos - 1);
    }
    
    /**
     * Rueda todas las ruedas de la máquina
     */
    public void spin() {
        if (wheels.isEmpty() || symbols.isEmpty()) {
            ok = false;
            return;
        }
        for (Wheel wheel : wheels) {
            wheel.spin(symbols);
        }
        ok = true;
    }
    
    /**
     * Retorna los símbolos del catálogo
     * @return arreglo con los colores disponibles
     */
    public String[] symbols() {
        if (symbols.isEmpty()) {
            ok = true;
            return new String[0];
        }
        
        String[] result = new String[symbols.size()];
        for (int i = 0; i < symbols.size(); i++) {
            result[i] = symbols.get(i).getColor();
        }
        ok = true;
        return result;
    }
    
    /**
     * Retorna los colores de los símbolos visibles actualmente
     * @return arreglo con los colores de cada rueda
     */
    public String[] configuration() {
        if (wheels.isEmpty()) {
            ok = true;
            return new String[0];
        }
        
        String[] result = new String[wheels.size()];
        for (int i = 0; i < wheels.size(); i++) {
            result[i] = wheels.get(i).getColorActual();
        }
        ok = true;
        return result;
    }
    
    /**
     * Retorna el número de distintos colores visibles
     * @return cantidad de colores únicos en las ruedas
     */
    public int distinctSymbols() {
        ArrayList<String> distinct = new ArrayList<>();
        for (String color : configuration()) {
            if (color != null && !distinct.contains(color)) {
                distinct.add(color);
            }
        }
        ok = true;
        return distinct.size();
    }
    
    /**
     * Evalúa si todas las ruedas muestran el mismo símbolo
     * @return true si es jackpot; false en caso contrario
     */
    public boolean isJackpot() {
        if (wheels.isEmpty() || distinctSymbols() != 1) {
            ok = true;
            return false;
        }
        ok = true;
        return true;
    }
    
    /**
     * Oculta la máquina en pantalla
     */
    public void makeInvisible() {
        isVisible = false;
        ok = true;
    }
    
    /**
     * Hace visible la máquina en pantalla
     */
    public void makeVisible() {
        isVisible = true;
        ok = true;
    }  
    
    /**
     * Termina la simulación limpiando las listas
     */
    public void exit() {
        wheels.clear();
        symbols.clear();
        ok = true;
    }
    
    /**
     * Indica el estado de la última acción realizada
     * @return true si la última operación fue exitosa
     */
    public boolean ok() {
        return ok;
    }
}