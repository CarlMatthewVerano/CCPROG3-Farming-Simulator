/*
 * Names: Dela Cruz, Frances Julianne      12143642
 *        Verano, Carl Matthew             12116203
 */

/**
 * Imported this class to know if there's an I/O exception
 * that may have occurred. 
 */
import java.io.IOException;

/**
 * Driver class where the gui, model, and controller is run.
 */
public class Driver {
    public static void main(String[] args) throws IOException {
        MyFarmGUI gui = new MyFarmGUI();

        MyFarmModel model = new MyFarmModel();

        MyFarmController myFarmController = new MyFarmController(model, gui);
    }
}