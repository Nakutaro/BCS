/**
 * Created by n.melnikov on 25.09.2017.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        InputData input = new InputData();
        Timer timers = new Timer();
        timers.timer();

        try {
            input.inputData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

