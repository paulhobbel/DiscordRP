package me.paulhobbel.discordrp.api.rpc;

public class DiscordRPCHandler implements Runnable {

    private static Thread handlerThread;

    public static void start() {
        if (handlerThread != null) return;

        handlerThread = new Thread(new DiscordRPCHandler(), "DiscordRPC-Handler");
        handlerThread.start();
    }

    public static void stop() {
        if (handlerThread == null) return;

        handlerThread.interrupt();
        handlerThread = null;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            DiscordRPC.RunCallbacks();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                DiscordRPC.Shutdown();
                break;
            }
        }
    }
}
