package cho.mobile.realslot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView slot1, slot2, slot3;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private  boolean isStarted;
    private TextView msg;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.slot1 = (ImageView) findViewById(R.id.img1);
        this.slot2 = (ImageView) findViewById(R.id.img2);
        this.slot3 = (ImageView) findViewById(R.id.img3);
        this.msg = (TextView) findViewById(R.id.msg);
        findViewById(R.id.btn).setOnClickListener(this);

        createWheel1();
        createWheel2();
        createWheel3();
    }

    private void createWheel1() {
        wheel1 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot1.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(0,200));
    }

    private void createWheel2() {
        wheel2 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot2.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(200,400));
    }

    private void createWheel3() {
        wheel3 = new Wheel(new Wheel.WheelListener() {
            @Override
            public void newImage(final int img) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slot3.setImageResource(img);
                    }
                });
            }
        }, 200, randomLong(400,600));
    }

    @Override
    public void onClick(View view) {

        if (this.wheel1.isAlive())
            wheel1.stopWheel();
        else if (this.wheel2.isAlive()){
            wheel2.stopWheel();
        } else if (this.wheel3.isAlive()) {
            wheel3.stopWheel();
            if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                msg.setText("JACKPOT!");
            } else {
                msg.setText("Try again!");
            }
        }else {
            this.createWheel1();
            this.createWheel2();
            this.createWheel3();

            wheel1.start();
            wheel2.start();
            wheel3.start();

            isStarted = true;
        }
    }
}

