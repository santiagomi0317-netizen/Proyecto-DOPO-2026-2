import java.util.ArrayList;

/**
 * Clase principal de la maquina traga monedas
 * Reutiliza el paquete "shapes" (a través de Wheel) para el dibujo.
 * 
 * @author (Santiago Murillo) 
 * Coordina un conjunto de ruedas (Wheel), simbolos (Symbol) y la interfaz grafica
 * @version (6 de septiembre)
 * Ciclo 1
 */
public class SlotMachine {
    private ArrayList<Wheel> wheels;
    private ArrayList<Symbol> symbols;
    private boolean isVisible;
    private boolean ok;
    private RectangleBorder generalBorder;

    /**
     * Crea una maquina con 3 wheels
     * @param ninguno
     * @return void
     * invariante: siempre cuando creamos una maquina , esta es visible, viene con tres ruedas,
     * con un borde que abarca las ruedas, una lista de tres ruedas y simbolos.
     * Ciclo 1
     */
    public SlotMachine() {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<Symbol>();
        isVisible = true;
    
        generalBorder = new RectangleBorder(200, 80, 2, "black");
        generalBorder.moveHorizontal(20);
        generalBorder.moveVertical(20);
        
        addSymbol("red", "Circle");
        addSymbol("blue", "Rectangle");
        addSymbol("green", "Triangle");
        
        for (int i=1; i<4 ;i++){
            addWheel(i);
        } 

        if (isJackpot()) {
        showMessageTemporal("¡GANASTE!", 3000);
        ok = true;
        }
    }
    
    /**
     * Agrega una rueda nueva en la posición indicada
     * @param pos (int) posición donde se insertará la rueda (1-based)
     * @return void
     * Invariante, cuando agrego uan rueda en una posicion negativa,
     * la indexo al principio y si es superior al tamaño la pongo en la ultima. 
     * Ciclo 1
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
        
        Wheel nueva = new Wheel(symbols);
        wheels.add(index, nueva);
        
        reorderVisualWheels();
        
        if (isVisible) nueva.makeVisible();
        ok = true;
    }
    
    /**
     * Elimina la rueda en la posición dada (1-pos)
     * @param pos (int) posición de la rueda a eliminar
     * @return void
     * Invariante, siempre tiene que haber 3 ruedas funcionales en la maquina tragamonedas, 
     * si se intenta eliminar una para quedar con dos no se puede realizar la accion
     * Ciclo 1
     */
    public void delWheel(int pos) {
        if (wheels.size()<=3) {
            ok = false;
            return;
        }
        if (pos < 1 || pos > wheels.size()) {
            ok = false;
            return;
        }
        
        Wheel wheelToRemove = wheels.remove(pos - 1);
        wheelToRemove.makeInvisible();
        
        reorderVisualWheels();
        ok = true;
    }
      
    /**
     * Añade un símbolo al catálogo de la máquina
     * @param color (String) identificador único del símbolo, basado en el estandas css
     * @param forma (String) figura geométrica ("Triangle", "Circle", "Rectangle")
     * @return void
     * 
     * invariante, se agrega un simbolo si su color no se encuentra en la lista Symbols
     * Ciclo 1
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
     * @param color (String) del símbolo a quitar
     * @return void
     * Invariante, se elimina un symbolo el cual su id(color) existe en la lista symbols
     * Ciclo 1
     */
    public void delSymbol(String color) {
        Symbol found = findSymbol(color);
        
        if (found == null) {
            ok = false;
            return;
        }
        
        // Si una rueda tenía este símbolo, la hace girar para cambiarlo
        for (Wheel wheel : wheels) {
            while (wheel.getActualSymbol() == found) {
                wheel.spin(symbols);
            }
        }
        symbols.remove(found);
        ok = true;
    }
    
    /**
     * Establece el símbolo seleccionado en la rueda indicada
     * @param wheelPos(int) posición de la rueda (1-based)
     * @param color(String) id del símbolo
     * Invariante, mi  wheelPos en un negativa,
     * la indexo al principio y si es superior al tamaño la pongo en la ultima. 
     * Por otro lado cuando el simbolo no se encuentra en my lista symbols, 
     * no se puede realizar la accion
     * Ciclo 1
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
        if (isJackpot()) {
            showMessageTemporal("¡GANASTE!", 3000);
        }
        ok = true;       
    }
        
    /**
     * Rueda una rueda específica
     * @param wheelPos (int) posición de la rueda (1-based)
     * Sobrecarga de spin donde recibe un parametro int
     * Invariante, si la rueda no existe o no hay simbolos que colocar no se realiza
     * la accion
     * Ciclo 1
    */
    public void spin(int wheelPos) {
        Wheel wheel = wheelAt(wheelPos);
        if (wheel == null || symbols.isEmpty()) {
            ok = false;
            return;
        }
        wheel.spin(symbols);
        if (isJackpot()) {
            showMessageTemporal("¡GANASTE!", 3000);
        }
        ok = true;
    }
    
    /**
     * Rueda todas las ruedas de la máquina
     * @param nada
     * @return nada
     * Sobrecarga de sping donde no reibe nada
     * Invriante, si no tengo ningun elemento en la lista de Symbols, no puedo girar
     * Ciclo 1
     */
    public void spin() {
        if (symbols.isEmpty()) {
            ok = false;
            return;
        }
        for (Wheel wheel : wheels) {
            wheel.spin(symbols);
        }
        if (isJackpot()) {
            showMessageTemporal("¡GANASTE!", 3000);
        }
        ok = true;
    }
    
    /**
     * Retorna los símbolos del catálogo
     * @return arreglo (String[]) con los colores disponibles
     * Ciclo 1
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
     * Ciclo 1
     */
    public String[] configuration() {
        if (wheels.isEmpty()) {
            ok = true;
            return new String[0];
        }
        
        String[] result = new String[wheels.size()];
        for (int i = 0; i < wheels.size(); i++) {
            result[i] = wheels.get(i).getColor();
        }
        ok = true;
        return result;
    }
    
    /**
     * Retorna el número de distintos colores visibles
     * @return cantidad de colores únicos en las ruedas (int)
     * Ciclo 1
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
     * @return boolean
     * Invariante, true si es jackpot; false en caso contrario
     * Ciclo 1
     */
    public boolean isJackpot() {
        if (distinctSymbols() != 1) {
            ok = true;
            return false;
        }
        ok = true;
        return true;
    }
    
    /**
     * Oculta la máquina en pantalla
     * Ciclo 1
     */
    public void makeInvisible() {
        isVisible = false;
        for (Wheel wheel : wheels) {
            wheel.makeInvisible();
        }
        if (generalBorder != null) {
            generalBorder.makeInvisible();
            ok = true;
        }
    }

    /**
     * Hace visible la máquina en pantalla
     * Ciclo 1
     */
    public void makeVisible() {
        isVisible = true;
        if (generalBorder != null) {
            generalBorder.makeVisible();
        }
        for (Wheel wheel : wheels) {
            wheel.makeVisible();
        }
        ok = true;
    }  
    
    /**
     * Termina la simulación limpiando las listas
     * Ciclo 1
     */
    public void exit() {
        makeInvisible();
        ok = true;
    }
    
    /**
     * Indica el estado de la última acción realizada
     * @return bolean
     * Ciclo 1
     */
    public boolean ok() {
        return ok;
    }
    
    /**
     * Agrega tantas ruedas al final de mi maquina
     * @return void
     * Invariante, siempre voy a crear tantas ruedas como me indique el usuario
     * Ciclo 1
     */
    public void addMultipleWheels(int num) {
        for (int i=0; i<num; i++){
            addWheel(wheels.size()+1);
        }
    }
    
    /**
     * Quita tantas ruedas al final de mi maquina
     * @return void
     * Invariante, siempre voy a eliminar tantas ruedas como me indique el usuario, 
     * sin embargo con el requisito de que me queden tres
     * Ciclo 1
     */
    public void delMultipleWheels(int num) {
        for (int i=0; i<num; i++){
            delWheel(wheels.size());
        }
    }
    
    /**
     * Obtiene la rueda en la posición especificada (1-based)
     * @param wheelPos posición de la rueda 
     * @return el objeto Wheel si la posición es válida; null en caso contrario.
     * Invariante, al encontrar la rueda dentro de mi arreglo de Wheels, devuelvo la
     * posicion -1 para que lo entienda la maquina
     * Ciclo 1
     */
    public Wheel wheelAt(int pos) {
        if (pos < 1 || pos > wheels.size()) {
            return null;
        }
        return wheels.get(pos - 1);
    }
    
    /**
     * Busca un símbolo en la lista por su color.
     * @param color (String) identificador del símbolo
     * @return el Symbol (Symbol) encontrado o null si no existe
     * Ciclo 1
     */
    public Symbol findSymbol(String color) {
        for (Symbol s : symbols) {
            if (s.getColor().equalsIgnoreCase(color)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * Llama a el canvas para mostrar un mensaje de la siguiente manera
     * @param (String) mensaje a mostrar
     * @param (int) milisegundos en pantalla
     * @return el Symbol (Symbol) encontrado o null si no existe
     * Tomado de Gemini
     * Ciclo 1
     */
    private void showMessageTemporal(String mensaje, int milisegundos) {
    // Muestra el mensaje debajo de la máquina en (X=20, Y=120) durante 3 segundos (3000 ms)
    Canvas.getCanvas().showFloatingMessage(mensaje, 300, 300, "blue", 200, milisegundos);
    }   
    
    /**
     * Reorganiza las posiciones X de cada rueda para mantener una separación consistente.
     * Tomada de Gemini
     * Ciclo 1
     */
    private void reorderVisualWheels() {
        int margenInicialX = 35;
        int espaciadoX = 60;

        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).moveA(margenInicialX + (i * espaciadoX));
        }
        
        if (generalBorder != null) {
            int anchoCalculado = (wheels.size() * espaciadoX) + 20; 
            generalBorder.changeSize(anchoCalculado, 80);
            
            if (isVisible) {
                generalBorder.makeVisible();
            }
        }
    }
}