import java.util.Timer;

public class Launcher {
        private static final Timer[] timers = new Timer[2];
        private static final MyTimerTask[] myTimerTasks = new MyTimerTask[2];

    public static void main(String[] args) {

        for (int i = 0; i < timers.length; i++) {
            final boolean isSchedulable = i != 0;
            timers[i] = new Timer();
            myTimerTasks[i] = new MyTimerTask(
                    timers[i], isSchedulable, String.format("Timer%d", i + 1));
            scheduleTimer(isSchedulable, i);
        }
    }

    private static void scheduleTimer(boolean isSchedulable, int index) {
        if (isSchedulable) {
            timers[index].schedule(myTimerTasks[index], 1000, 2000);
        } else {
            timers[index].schedule(myTimerTasks[index], 1000);
        }
    }
}
