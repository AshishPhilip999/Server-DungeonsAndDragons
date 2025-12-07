package Generic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickHandler {
    private final int tickRate;              // ticks per second (e.g., 20)
    private final long tickIntervalNanos;    // time per tick in nanoseconds
    private boolean running = false;

    private final List<Runnable> tickCallbacks = new CopyOnWriteArrayList<>();

    public TickHandler(int tickRate) {
        this.tickRate = tickRate;
        this.tickIntervalNanos = 1_000_000_000L / tickRate;
    }

    public void start() {
        if (running) return;
        running = true;

        Thread tickThread = new Thread(() -> {
            long lastTick = System.nanoTime();

            while (running) {
                long now = System.nanoTime();
                long diff = now - lastTick;

                if (diff >= tickIntervalNanos) {
                    lastTick = now;
                    runTick();
                } else {
                    long sleepNanos = tickIntervalNanos - diff;
                    try {
                        Thread.sleep(sleepNanos / 1_000_000L, (int)(sleepNanos % 1_000_000L));
                    } catch (InterruptedException ignored) {
                        System.out.println(ignored);
                    }
                }
            }
        });

        tickThread.setName("Tick-Thread");
        tickThread.start();
    }

    public void stop() {
        running = false;
    }

    public void onTick(Runnable callback) {
        tickCallbacks.add(callback);
    }

    private void runTick() {
        for (Runnable r : tickCallbacks) {
            r.run();
        }
    }
}
