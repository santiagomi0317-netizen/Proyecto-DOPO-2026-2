import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SlotMachineC2Test.
 *
 * @author  (Santiago Murillo)
 * @version (9 de septiembre)
 */
public class SlotMachineC2Test{
    private SlotMachine machine;
    
    
    /**
     * Constructor por defecto de la calse tests SlotMachineC2Test
     * Se crea la máquina y se pone en modo INVISIBLE para las pruebas de unidad
     */
    @BeforeEach
    public void setUp() {
        machine = new SlotMachine();
        machine.makeInvisible();
    }

    /**
     * Liberar recursos depues de cada prueba unitaria
     *
     */
    @AfterEach
    public void tearDown() {
        machine = null;
    }
    // =========================================================================
    // swap(wheel1 : int, wheel2 : int) : void
    // =========================================================================

    /**
     * ¿Qué debería hacer? Intercambiar exitosamente la posición de dos ruedas válidas dentro del rango.
     * ¿Qué no debería hacer? Fallar o marcar ok en false cuando los índices ingresados son correctos.
     */
    @Test
    public void shouldSwapTwoValidWheels() {
        machine.swap(1, 2);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Detectar que el índice de la segunda rueda supera el número de ruedas existentes y marcar ok en false.
     * ¿Qué no debería hacer? Intentar acceder a un índice fuera de límites ni lanzar IndexOutOfBoundsException.
     */
    @Test
    public void shouldNotSwapInvalidWheelIndex() {
        machine.swap(1, 4);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Identificar que se intentó intercambiar una rueda consigo misma y rechazar la operación (ok en false).
     * ¿Qué no debería hacer? Realizar un intercambio redundante o reportar la acción como exitosa.
     */
    @Test
    public void shouldNotSwapSameWheel() {
        machine.swap(1, 1);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Validar que los índices negativos no son válidos para las ruedas y asignar ok en false.
     * ¿Qué no debería hacer? Aceptar posiciones menores a 1 ni romper la ejecución con excepciones.
     */
    @Test
    public void shouldNotSwapWithNegativeIndex() {
        machine.swap(-1, 3);
        assertFalse(machine.ok());
    }

    // =========================================================================
    // lock(wheel : int) : void
    // =========================================================================

    /**
     * ¿Qué debería hacer? Fijar o bloquear exitosamente una rueda existente de la máquina (ok en true).
     * ¿Qué no debería hacer? Ignorar la orden de bloqueo ni permitir que la rueda siga girando libremente.
     */
    @Test
    public void shouldlockValidWheel() {
        machine.lock(1);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Rechazar el intento de bloquear una rueda con un índice negativo (ok en false).
     * ¿Qué no debería hacer? Modificar el estado interno de la máquina ante un parámetro inválido.
     */
    @Test
    public void shouldNotlockInvalidWheelMin() {
        machine.lock(-1);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Validar que el índice excede el total de ruedas y marcar la operación como no exitosa (ok en false).
     * ¿Qué no debería hacer? Intentar bloquear posiciones de rueda inexistentes.
     */
    @Test
    public void shouldNotlockInvalidWheelMor() {
        machine.lock(4);
        assertFalse(machine.ok());
    }

    // =========================================================================
    // unlock(wheel : int) : void
    // =========================================================================

    /**
     * ¿Qué debería hacer? Liberar o desbloquear una rueda válida previamente fijada (ok en true).
     * ¿Qué no debería hacer? Mantener la rueda bloqueada tras ejecutar el comando de desbloqueo.
     */
    @Test
    public void shouldUnlockValidWheel() {
        machine.unlock(1);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Detectar que el índice negativo no corresponde a ninguna rueda y asignar ok en false.
     * ¿Qué no debería hacer? Alterar el estado de bloqueo de las ruedas existentes.
     */
    @Test
    public void shouldNotUnlockInvalidWheelMin() {
        machine.unlock(-1);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Rechazar la solicitud de desbloqueo sobre una rueda que no existe por estar fuera de rango (ok en false).
     * ¿Qué no debería hacer? Permitir comandos de desbloqueo en posiciones inválidas.
     */
    @Test
    public void shouldNotUnlockInvalidWheelMor() {
        machine.unlock(4);
        assertFalse(machine.ok());
    }

    // =========================================================================
    // spin(int wheel, int steps) : void
    // =========================================================================

    /**
     * ¿Qué debería hacer? Rotar la rueda especificada el número exacto de pasos solicitados (ok en true).
     * ¿Qué no debería hacer? Fallar la rotación cuando la rueda está desbloqueada y la cantidad de pasos es válida.
     */
    @Test
    public void shouldSpinSteps() {
        machine.spin(1, 3);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Impedir el giro a pasos si la rueda indicada tiene un índice negativo (ok en false).
     * ¿Qué no debería hacer? Intentar procesar animaciones o movimientos en índices no existentes.
     */
    @Test
    public void shouldNotSpinStepsWheelMin() {
        machine.spin(-1, 3);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Rechazar la rotación por pasos si el número de rueda supera la cantidad de ruedas configuradas (ok en false).
     * ¿Qué no debería hacer? Intentar acceder a posiciones de rueda fuera de los límites de la lista.
     */
    @Test
    public void shouldNotSpinStepsWheelMax() {
        machine.spin(4, 3);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Bloquear la rotación por pasos si el catálogo global de símbolos de la máquina está vacío (ok en false).
     * ¿Qué no debería hacer? Girar la rueda sin tener símbolos disponibles para mostrar o causar un error por lista vacía.
     */
    @Test
    public void shouldNotSpinStepsWheelSymbolsEmpty() {
        machine.delSymbol("green");
        machine.delSymbol("blue");
        machine.delSymbol("red");
        machine.spin(1, 3);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Impedir que una rueda que se encuentra en estado bloqueado (locked) sea girada por pasos (ok en false).
     * ¿Qué no debería hacer? Mover o alterar la posición de una rueda mientras permanezca fijada.
     */
    @Test
    public void shouldNotSpinStepsWheelLocked() {
        machine.lock(1);
        machine.spin(1, 3);
        assertFalse(machine.ok());
    }

    // =========================================================================
    // spin(String[] symbolstoput) : void
    // =========================================================================

    /**
     * ¿Qué debería hacer? Aplicar la configuración de símbolos recibida en el arreglo sobre las ruedas correspondientes (ok en true).
     * ¿Qué no debería hacer? Rechazar un arreglo cuyos símbolos existan y coincida en tamaño con el número de ruedas.
     */
    @Test
    public void shouldSpinSetSymbols1() {
        String[] symbolstoput = {"red", "red", "red"};
        machine.spin(symbolstoput);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Establecer la combinación dada permitiendo diferentes colores válidos por rueda (ok en true).
     * ¿Qué no debería hacer? Fallar al recibir combinaciones con variedad de símbolos registrados.
     */
    @Test
    public void shouldSpinSetSymbols2() {
        String[] symbolstoput = {"red", "brown", "red"};
        machine.spin(symbolstoput);
        assertTrue(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Detectar que la referencia del arreglo pasado como parámetro es null y marcar ok en false.
     * ¿Qué no me debería hacer? Intentar iterar un arreglo nulo ni generar una excepción NullPointerException.
     */
    @Test
    public void shouldnotSpinWithArrayNull() {
        String[] symbolstoput = null;
        machine.spin(symbolstoput);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Rechazar el cambio de configuración si la lista de símbolos disponibles fue eliminada previamente (ok en false).
     * ¿Qué no debería hacer? Asignar nombres de colores a las ruedas cuando no existen en el catálogo general.
     */
    @Test
    public void shouldNotSpinWithSymbolsArray() {
        machine.delSymbol("green");
        machine.delSymbol("blue");
        machine.delSymbol("red");
        String[] symbolstoput = {"red", "red", "red"};
        machine.spin(symbolstoput);
        assertFalse(machine.ok());
    }

    /**
     * ¿Qué debería hacer? Validar que la cantidad de elementos del arreglo difiere del número total de ruedas y cancelar la operación (ok en false).
     * ¿Qué no debería hacer? Actualizar parcialmente solo una parte de las ruedas si el tamaño del arreglo es diferente.
     */
    @Test
    public void shouldNotSpinWithSymbolsLDifWheelsS() {
        String[] symbolstoput = {"red", "brown"};
        machine.spin(symbolstoput);
        assertFalse(machine.ok());
    }
}