import processing.core.*;
import processing.*;

public class TProgressBar extends PShape {
    private int x;
    private int y;
    private int maxWidth;
    private int heightProgress;
    private float value;
    private PFont font;
    private int backgroundColor;
    private int progressColor;
    private int sizeFont;
    PApplet parent;

    public TProgressBar(PApplet parent, int maxWidth, int heightProgress, int x, int y) {
        this.maxWidth = maxWidth;
        this.heightProgress = heightProgress;
        this.x = x;
        this.y = y;
        this.value = x;
        this.backgroundColor = parent.color(182, 205, 250, 120);
        this.progressColor = parent.color(255);
        this.sizeFont = 30;
        this.font = parent.loadFont("TektonPro-BoldCond-48.vlw");
        parent.textFont(this.font);
        parent.textSize(this.sizeFont);
        parent.smooth();
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setHeightProgress(int heightProgress) {
        this.heightProgress = heightProgress;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }


    public void setFontSize(int fontSize) {
        this.sizeFont = sizeFont;
    }

    public int percentage() {
        return (int) ((this.value * 100) / this.maxWidth);
    }


    public void setValue(float value) {
        if (value <= this.maxWidth) {
            this.value = value;
        } else {
            //Todo println("valor es mayor que el limite(maxWidth) de la barra de progreso.");
        }
    }

    public void draw() {
        parent.pushStyle();
        parent.textSize(this.sizeFont);
        parent.strokeCap(parent.SQUARE);
        parent.stroke(this.backgroundColor);
        parent.strokeWeight(heightProgress);
        parent.line(this.x, this.y, this.x + this.maxWidth, this.y);
        parent.stroke(this.progressColor);
        parent.strokeWeight(heightProgress);
        parent.line(this.x, this.y, this.x + this.value, this.y);
        parent.textAlign(parent.CENTER);
        parent.text("ricardo " + percentage() + " %", this.x + this.maxWidth / 2, this.y - 30);
        parent.popStyle();
    }
}