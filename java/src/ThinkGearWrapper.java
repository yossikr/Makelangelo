/**
 * Created with IntelliJ IDEA.
 * Date: 10/5/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */

import processing.core.PApplet;
import processing.core.PFont;
import neurosky.ThinkGearSocket;

public class ThinkGearWrapper extends PApplet {



    public ThinkGearSocket neuroSocket;
    public int attention=10;
    public int meditation=10;
    public int attention_treshold=90;
    public int meditation_treshold=90;
    public PFont font;
    HeadsetValues eegValues;


    public void setup() {
        size(700,700);

        ThinkGearSocket neuroSocket = new ThinkGearSocket(this);
        eegValues = new HeadsetValues(10);

        try {
            neuroSocket.start();
        }
        catch (Exception e) {
            println("Is ThinkGear running??");
        }
        smooth();
        //noFill();
        font = createFont("Verdana",12);
        textFont(font);
    }

    public void draw() {
        //background(0,0,0,50);
        fill(0, 0,0, 255);
        noStroke();
        rect(0,0,120,80);


        fill(0, 0,0, 10);
        noStroke();
        rect(0,0,width,height);
        fill(0, 116, 168);
        stroke(0, 116, 168);
        text("Attention: "+attention, 10, 30);
        noFill();
        ellipse(width/2,height/2,attention*3,attention*3);


        fill(209, 24, 117, 100);
        noFill();
        text("Meditation: "+meditation, 10, 50);
        stroke(209, 24, 117, 100);
        noFill();
        ellipse(width/2,height/2,meditation*3,meditation*3);
    }

    public void poorSignalEvent(int sig) {

        if (sig > 10) {
            eegValues.clearLast();
        }
    }

    public void attentionEvent(int attentionLevel) {
        println("Attention Level: " + attentionLevel);
        attention = attentionLevel;
        eegValues.addAttention(attentionLevel);
    }


    public void meditationEvent(int meditationLevel) {
        println("Meditation Level: " + meditationLevel);
        meditation = meditationLevel;
        eegValues.addMeditation(meditationLevel);
    }

    public void blinkEvent(int blinkStrength) {

        println("blinkStrength: " + blinkStrength);
    }

    public void eegEvent(int delta, int theta, int low_alpha, int high_alpha, int low_beta, int high_beta, int low_gamma, int mid_gamma) {
        println("delta Level: " + delta);
        println("theta Level: " + theta);
        println("low_alpha Level: " + low_alpha);
        println("high_alpha Level: " + high_alpha);
        println("low_beta Level: " + low_beta);
        println("high_beta Level: " + high_beta);
        println("low_gamma Level: " + low_gamma);
        println("mid_gamma Level: " + mid_gamma);
    }

    void rawEvent(int[] raw) {
        //println("rawEvent Level: " + raw);
    }

    public void stop() {
        neuroSocket.stop();
        super.stop();
    }

    public int getAvgAttention() {
        return eegValues.getAvgAttention();
    }


    public int getAvgMeditation()  {
        return eegValues.getAvgMeditation();
    }

    public boolean isAttentive()  {
        int attention;
        attention = getAvgAttention();
        if (attention >= attention_treshold )  {
            return true;
        }

        return false;
    }

    public boolean isRelaxed()  {
        int meditation;
        meditation = getAvgMeditation();
        if (meditation >= meditation_treshold )  {
            return true;
        }

        return false;
    }

}
