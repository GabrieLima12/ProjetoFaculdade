package com.grupoaps.principal;

import java.time.Duration;

public class StopWatch {

    // private int startTime = 0;
    // private int stopTime = 0;
    private long totalElapsedTime = 0;
    private long totalMillis = 0;
    private long totalSecs = 0;
    private long totalMins = 0;

    private long lapElapsedTime = 0;
    private long lapMillis = 0;
    private long lapSecs = 0;
    private long lapMins = 0;

    private boolean running = false;
    private int lap = 0;


    Duration duration;

    long updateStart;
    long updateEnd;

    StopWatch()
    {

    }

    public void start() {
        if (running)
            return;

        updateStart = System.currentTimeMillis();
        lap += 1;
        running = true;
    }

    public void update()
    {
        if (running)
        {
            updateEnd = System.currentTimeMillis();
            long delta = updateEnd - updateStart;
            totalElapsedTime += delta;
            lapElapsedTime += delta;
            updateStart = updateEnd;

            totalMins = (totalElapsedTime / 60000) % 60;
            totalSecs = (totalElapsedTime / 1000) % 60;
            totalMillis = totalElapsedTime % 1000;

            lapMins = (lapElapsedTime / 60000) % 60;
            lapSecs = (lapElapsedTime / 1000) % 60;
            lapMillis = lapElapsedTime % 1000;
        }
    }

    public void lap()
    {

        lapElapsedTime = 0;
        lapMillis = 0;
        lapSecs = 0;
        lapMins = 0;

        lap += 1;

    }


    public String getFormattedTotalTime()
    {
        String sMillis = String.format("%03d", totalMillis);
        String sSeconds = String.format("%02d", totalSecs);
        String sMinutes = String.format("%02d", totalMins);

        return new String(sMinutes + ":" + sSeconds + "." + sMillis);
    }

    public String getFormattedLapTime()
    {
        String sMillis = String.format("%03d", lapMillis);
        String sSeconds = String.format("%02d", lapSecs);
        String sMinutes = String.format("%02d", lapMins);

        return new String(sMinutes + ":" + sSeconds + "." + sMillis);
    }

    public boolean isRunning()
    {
        return running;
    }

    public int getLap()
    {
        return lap;
    }

    public long getMillis()
    {
        return totalMillis;
    }

    public long getSeconds()
    {
        return totalSecs;
    }

    public long getMinutes()
    {
        return totalMins;
    }

    //elaspsed time in milliseconds
    public long getElapsedTime() {
        return totalElapsedTime;
    }
}

