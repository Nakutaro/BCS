/**
 * Created by n.melnikov on 25.09.2017.
 */
public class Timer {
    WorkWithFile workWithFile = new WorkWithFile();
    private int time = 60000;
    public void timer()
    {
        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(time);
                    System.out.print("//////");
                    workWithFile.currentBalance();
                    System.out.print("//////");
                    System.out.println(WorkWithFile.readFile(Const.pathToCurrentBalance));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
