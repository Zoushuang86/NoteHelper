package com.ustc.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by DELL on 2018/3/4.
 */

public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
