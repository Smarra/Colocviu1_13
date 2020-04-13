package com.example.colocviu1_13;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    private String instr;

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.arithmeticmean",
            "ro.pub.cs.systems.eim.practicaltest01.geometricmean"
    };

    public ProcessingThread(Context context, String instrr) {
        this.context = context;

        instr = instrr;
    }

    @Override
    public void run() {
        Log.d("PROCESSING_THREAD_TAG", "Thread has started! PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myPid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("ROCESSING_THREAD_TAG", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        String message = new Date(System.currentTimeMillis()) + " " + instr;
        Log.d("TAG", message);
        intent.putExtra("message", message);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}