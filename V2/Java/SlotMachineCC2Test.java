import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SlotMachineCC2Test.
 *
 * @author  (Santiago Murillo)
 * @version (9 de septiembre)
 */
public class SlotMachineCC2Test{
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
    // PRUEBAS COMPARTIDAS EN EL WIKI (Aporte de Santiago Murillo - Init: MuR)
    // =========================================================================
    
    /**
     * Prueba 1:
     * ¿Qué debería hacer? Permitir intercambiar (swap) dos ruedas aun si una o ambas están bloqueadas (lock),
     * manteniendo el estado de bloqueo en sus nuevas posiciones.
     * ¿Qué no debería hacer? Cancelar el intercambio o perder el estado de bloqueo tras la reubicación.
     */
    @Test
    public void accordingMurShouldSwapLockedWheelsAndMaintainLockState() {
        machine.lock(1);
        assertTrue(machine.ok());

        machine.swap(1, 2);
        assertTrue(machine.ok());

        machine.spin(2, 3);
        assertFalse(machine.ok()); 

        machine.spin(1, 3);
        assertTrue(machine.ok());
    }
    
    /**
     * Prueba 2:
     * ¿Qué debería hacer? Validar el comportamiento de spin(String[]) sobre una máquina que tiene
     * algunas ruedas bloqueadas y otras libres.
     * ¿Qué no debería hacer? Modificar el símbolo de las ruedas que están fijadas.
     */
    @Test
    public void accordingMurShouldSpinUnlockedWheelsOnlyWhenArrayIsApplied() {
        String[] symbolstoput = {"red", "red", "red"};
        machine.spin(symbolstoput);
        assertTrue(machine.ok());

        machine.lock(2);
        assertTrue(machine.ok());

        String[] newConfig = {"blue", "blue", "blue"};
        machine.spin(newConfig);
        assertTrue(machine.ok());

        assertFalse(machine.isJackpot());
    }
}