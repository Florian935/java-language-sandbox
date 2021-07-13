import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private final Timer timer;
    private final boolean isSchedulable;
    private final String name;

    public MyTimerTask(Timer timer, boolean isSchedulable, String name) {
        this.timer = timer;
        this.isSchedulable = isSchedulable;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: en cours\n", name);
        System.out.printf("%s: fini\n", name);
        if (!isSchedulable) {
            cancel();
            timer.cancel();
        }
    }
}
