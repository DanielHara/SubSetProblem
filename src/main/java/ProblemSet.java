import java.util.Arrays;

public class ProblemSet {
    private int mode;
    private int numberToChoose;
    private float desiredAverage;
    private float[] rates;

    public ProblemSet(int mode, int numberToChoose, float desiredAverage, float[] rates) {
        this.mode = mode;
        this.numberToChoose = numberToChoose;
        this.desiredAverage = desiredAverage;
        this.rates = Arrays.copyOf(rates, rates.length);
    }

    public int getMode() {
        return this.mode;
    }

    public int getNumberToChoose() {
        return this.numberToChoose;
    }

    public float getDesiredAverage() {
        return this.desiredAverage;
    }

    public float[] getRates() {
        return Arrays.copyOf(this.rates, this.rates.length);
    }
}
