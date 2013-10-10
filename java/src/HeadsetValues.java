import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 10/9/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeadsetValues {
    ArrayList<Integer> attention;
    ArrayList<Integer> meditation;

    int maxValues;

    HeadsetValues(int _maxValues) {
        attention = new ArrayList<Integer>();
        meditation = new ArrayList<Integer>();

        maxValues = _maxValues;
    }

    public void addAttention(int value) {
        synchronized(this) {
            attention.add(value);
            if (attention.size() > maxValues) {
                attention.remove(0);
            }
        }
    }

    public void addMeditation(int value) {
        synchronized(this) {
            meditation.add(value);
            if (meditation.size() > maxValues) {
                meditation.remove(0);
            }
        }
    }

    public synchronized int getAvgAttention() {
        int numValues = attention.size();
        int avg = 0;

        if (numValues>0) {
            for (int i = 0; i < attention.size(); i++) {
                avg += attention.get(i);
            }
            avg /= attention.size();
        }

        return avg;
    }

    public synchronized int getAvgMeditation() {
        int numValues = meditation.size();
        int avg = 0;

        if (numValues>0) {
            for (int i = 0; i < meditation.size(); i++) {
                avg += meditation.get(i);
            }
            avg /= meditation.size();
        }

        return avg;
    }

    public void clearLast() {
        synchronized(this) {
            if (meditation.size() > 0) {
                meditation.remove(meditation.size()-1);
            }
            if (attention.size() > 0) {
                attention.remove(attention.size()-1);
            }
        }
    }
}
