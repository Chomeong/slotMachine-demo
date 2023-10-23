package cho.mobile.realslot;

public class Wheel extends Thread{

    interface WheelListener {
        void newImage(int img);
    }

    private static int[] imgs = {R.drawable.slot_1, R.drawable.slot_2, R.drawable.slot_3,
            R.drawable.slot_4, R.drawable.slot_5, R.drawable.slot_6, R.drawable.slot_7,
            R.drawable.slot_8, R.drawable.slot_9};
    public int currentIndex;
    private WheelListener wheelListener;
    private long frameDuration;
    private long startIn;
    private boolean isStarted;

    public Wheel(WheelListener wheelListener, long frameDuration, long startIn) {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }
    public void nextImg() {
        currentIndex++;

        if (currentIndex == imgs.length) {
            currentIndex =0;
        }
    }

    public void run() {
        try {
            Thread.sleep(startIn);
        } catch (InterruptedException e) {}

        while(isStarted) {
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {}

            nextImg();

            if (wheelListener != null) {
                wheelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopWheel() {
        isStarted = false;
    }
}
