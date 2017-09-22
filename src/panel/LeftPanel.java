package panel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class LeftPanel extends Panel {

    private JLabel imageLable,timerLabel;
    private TimerTask countTime;
    private Timer timer;
    long interval;

    public LeftPanel() {
        super(null);

        imageLable=new JLabel();
        imageLable.setLocation(50,50);
        imageLable.setSize(400,400);
        add(imageLable);

        timerLabel=new JLabel();
        timerLabel.setLocation(200,500);
        timerLabel.setSize(200,50);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        add(timerLabel);

        timer=new Timer();

        countTime=new TimerTask() {
            @Override
            public void run() {
                interval+=100;
                timerLabel.setText(interval/60000+":"+interval/1000%60+":"+interval%1000);
            }
        };
    }
    public void init(Image srcImage){
        imageLable.setIcon(new ImageIcon(srcImage));
        interval=0L;
        timerLabel.setText(interval/60000+":"+interval/1000%60+":"+interval%1000);
    }
    public void start(){
        timer.scheduleAtFixedRate(countTime,1,1);
    }
    public void stop(){
        timer.cancel();
    }
    public String getTime(){
        return interval/60000+":"+interval/1000%60+":"+interval%1000;
    }
}
