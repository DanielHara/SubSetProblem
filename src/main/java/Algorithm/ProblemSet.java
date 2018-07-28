package Algorithm;

import java.util.Arrays;

public class ProblemSet {
    private AlgorithmMode mode;
    private int numberToChoose;
    private float desiredAverage;
    private float[] rates;

    public ProblemSet(AlgorithmMode mode, int numberToChoose, float desiredAverage, float[] rates) {
        this.mode = mode;
        this.numberToChoose = numberToChoose;
        this.desiredAverage = desiredAverage;
        this.rates = Arrays.copyOf(rates, rates.length);
    }

    public AlgorithmMode getMode() {
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
