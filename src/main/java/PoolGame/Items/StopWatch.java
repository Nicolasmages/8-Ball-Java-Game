package PoolGame.Items;

import PoolGame.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
/**
 * the stopwatch in the game
 */
public class StopWatch {
    private int minutes;
    private int seconds;
    private boolean isWin;

    private int min_sec;

    private String time;

    /**
     * constructor of stopwatch
     */
    public StopWatch() {
        minutes = 0;
        seconds = 0;
        this.min_sec = 0;
        time = new String();
        isWin = false;

    }

    /**
     * Make a hard copy of StopWatch
     * @param stopWatch the stop watch want to be copied
     */
    public StopWatch(StopWatch stopWatch) {
        this.minutes = stopWatch.getMinutes();
        this.seconds = stopWatch.getSeconds();
        this.min_sec = stopWatch.getMin_sec();
        this.isWin = stopWatch.isWin();
    }

    /**
     * Tick as the game run and increment the time
     */
    public void tick() {
        if (!isWin) {
            min_sec += 1;
            if (min_sec == (1/App.getFRAMETIME())) {
                seconds += 1;
                min_sec = 0;
            }
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
        }
       String m = minutes >= 10 ? String.valueOf(minutes) : "0" + String.valueOf(minutes);
       String s = seconds >= 10 ? String.valueOf(seconds) : "0" + String.valueOf(seconds);
       time = String.format( m + ":" + s);
   }

    /**
     *
     * @return the string of time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return the minutes of the game
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     *
     * @return the game of the second
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     *
     * @return the ratio of the second to the fame
     */
    public int getMin_sec() {
        return min_sec;
    }

    /**
     *
     * @return if the game is won or not
     */
    public boolean isWin() {
        return isWin;
    }

    /**
     * Make the watch stop
     */
    public void won() {
        this.isWin = true;
    }
}
