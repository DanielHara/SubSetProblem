package Algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;
import net.mintern.primitive.Primitive;

public class SubSet {
  static final int T_DEFAULT = 1000;
  int T;

  static final float INFINITY = -1;
  static final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

  public SubSet(int T) {
    this.T = T;
  }

  public SubSet() {
    this.T = T_DEFAULT;
  }

  public float sumOfPositions(int[] u, float[] v) {
    float sum = 0;
    for (int position: u) {
      sum = sum + v[position];
    }
    return sum;
  }

  public float Distancia(int[] u, int t, AlgorithmMode modo, float b, float[] v) {
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

    float b = media * p / T;

    if (modo == AlgorithmMode.ABOVE_AVERAGE) {
      Primitive.sort(v, (d1, d2) -> Double.compare(d2, d1));
    } else if (modo == AlgorithmMode.BELOW_AVERAGE) {
      Arrays.sort(v);
    }

    int [][][] M = new int [n][T + 1][p];

    for (int t = 0; t <= T; t++) {
      for (int i = 0; i < p; i++) {
        M[p - 1][t][i] = i;
      }
    }

    float dist;
    int[] u;
    int[] r;

    for (int i = p; i < n; i++) {
      for (int t = 0; t <= T; t++) {
        if (v[i] > t * b) {
          M[i][t] = Arrays.copyOf(M[i - 1][t], M[i - 1][t].length);
        } else {
          dist = Distancia(M[i - 1][t], t, modo, b, v);
          M[i][t] = Arrays.copyOf(M[i - 1][t], M[i - 1][t].length);

          for (int k = 0; k < p; k++) {
            u = Arrays.copyOf(M[i - 1][t], M[i - 1][t].length);
            u = replace(u[k], i, u);
            if (dist == INFINITY || (dist > Distancia(u, t, modo, b, v)
                && Distancia(u, t, modo, b, v) >= 0)) {
              r = Arrays.copyOf(u, u.length);
              M[i][t] = Arrays.copyOf(u, u.length);
              dist = Distancia(r, t, modo, b, v);
            }
          }
        }
      }
    }

    return mapPositionsToValues(M[n - 1][T], v);
  }


  private float[] mapPositionsToValues(int[] u, float[] v) {
    float[] mappedValues = new float[u.length];

    for (int i = 0; i < u.length; i++) {
      mappedValues[i] = v[u[i]];
    }
    return mappedValues;
  }

  public int[] replace(int r, int s, int[] v) {
    int[] u = IntStream.range(0, v.length)
                       .map(i -> (v[i] != r) ? v[i] : s)
                       .toArray();
    return u;
  }
}
