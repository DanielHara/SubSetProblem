package algorithm;

import java.util.Arrays;

public class ProblemSet {
  private AlgorithmMode mode;
  private int numberToChoose;
  private float desiredAverage;
  private float[] rates;

  /**
   * Returns object with problem data.
   * @param mode the obtained average must be >=, <= the desiredAverage, or indifferent.
   * @param numberToChoose how many contracts to choose from
   * @param desiredAverage average to be obtained as near as possible
   * @param rates contracts available
   */
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
