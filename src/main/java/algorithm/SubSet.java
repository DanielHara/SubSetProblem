package algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;
import net.mintern.primitive.Primitive;

public class SubSet {
  static final int DEFAULT_NUMBER_OF_PARTITIONS = 1000;
  int numberPartitions;

  static final float INFINITY = -1;
  static final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

  public SubSet(int numberPartitions) {
    this.numberPartitions = numberPartitions;
  }

  public SubSet() {
    this.numberPartitions = DEFAULT_NUMBER_OF_PARTITIONS;
  }

  private float sumOfPositions(int[] u, float[] v) {
    float sum = 0;
    for (int position: u) {
      sum = sum + v[position];
    }
    return sum;
  }

  private float distance(int[] u, int t, AlgorithmMode modo, float b, float[] v) {
    if (modo == AlgorithmMode.BEST_AVERAGE) {
      return Math.abs((sumOfPositions(u, v) - (float) t * b));
    } else if (modo == AlgorithmMode.ABOVE_AVERAGE) {
      if ((sumOfPositions(u, v) - (float) t * b) < (-1) * FLOAT_COMPARISON_THRESHOLD) {
        return INFINITY;
      } else {
        return Math.abs((sumOfPositions(u, v) - (float) t * b));
      }
    } else {
      if ((sumOfPositions(u, v) - (float) t * b) > FLOAT_COMPARISON_THRESHOLD) {
        return INFINITY;
      } else {
        return Math.abs((sumOfPositions(u, v) - (float) t * b));
      }
    }
  }

  public float[] runAlgorithm(ProblemSet problemSet) {
    AlgorithmMode modo = problemSet.getMode();
    float media = problemSet.getDesiredAverage();
    int p = problemSet.getNumberToChoose();
    float[] v = problemSet.getRates();
    int n = v.length;

    float b = media * p / numberPartitions;

    if (modo == AlgorithmMode.ABOVE_AVERAGE) {
      Primitive.sort(v, (d1, d2) -> Double.compare(d2, d1));
    } else if (modo == AlgorithmMode.BELOW_AVERAGE) {
      Arrays.sort(v);
    }

    int [][][] m = new int [n][numberPartitions + 1][p];

    for (int t = 0; t <= numberPartitions; t++) {
      for (int i = 0; i < p; i++) {
        m[p - 1][t][i] = i;
      }
    }

    float dist;
    int[] u;
    int[] r;

    for (int i = p; i < n; i++) {
      for (int t = 0; t <= numberPartitions; t++) {
        if (v[i] > t * b) {
          m[i][t] = Arrays.copyOf(m[i - 1][t], m[i - 1][t].length);
        } else {
          dist = distance(m[i - 1][t], t, modo, b, v);
          m[i][t] = Arrays.copyOf(m[i - 1][t], m[i - 1][t].length);

          for (int k = 0; k < p; k++) {
            u = Arrays.copyOf(m[i - 1][t], m[i - 1][t].length);
            u = replace(u[k], i, u);
            if (dist == INFINITY || (dist > distance(u, t, modo, b, v)
                && distance(u, t, modo, b, v) >= 0)) {
              r = Arrays.copyOf(u, u.length);
              m[i][t] = Arrays.copyOf(u, u.length);
              dist = distance(r, t, modo, b, v);
            }
          }
        }
      }
    }

    return mapPositionsToValues(m[n - 1][numberPartitions], v);
  }


  private float[] mapPositionsToValues(int[] u, float[] v) {
    float[] mappedValues = new float[u.length];

    for (int i = 0; i < u.length; i++) {
      mappedValues[i] = v[u[i]];
    }
    return mappedValues;
  }

  private int[] replace(int r, int s, int[] v) {
    int[] u = IntStream.range(0, v.length)
                       .map(i -> (v[i] != r) ? v[i] : s)
                       .toArray();
    return u;
  }
}
