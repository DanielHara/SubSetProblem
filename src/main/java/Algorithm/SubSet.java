package Algorithm;

import net.mintern.primitive.Primitive;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SubSet
{
    int T_DEFAULT = 1000;
    int T;

    final float INFINITY = -1;
    final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

    public SubSet(int T) {
        this.T = T;
    }

    public SubSet() {
        this.T = T_DEFAULT;
    }

    public float sumOfPositions (int[] u, float[] v)
    {
        float sum = 0;
        for (int position: u) {
            sum = sum + v[position];
        }
        return sum;
    }

    public float Distancia (int[] u, int t, AlgorithmMode Modo, float b, float[] v)
    {
        if (Modo == AlgorithmMode.BEST_AVERAGE)
            return Math.abs((sumOfPositions(u, v) - (float)t*b));
        else
        {
            if (Modo == AlgorithmMode.ABOVE_AVERAGE)
            {
                if ((sumOfPositions(u, v) - (float)t*b) < (-1)*FLOAT_COMPARISON_THRESHOLD)
                    return INFINITY;
                else return Math.abs((sumOfPositions(u, v) - (float)t*b));
            }
            else
            {
                if ((sumOfPositions(u, v) - (float)t*b) > FLOAT_COMPARISON_THRESHOLD)
                    return INFINITY;
                else return Math.abs((sumOfPositions(u, v) - (float)t*b));
            }
        }

    }

    public float[] RunAlgorithm (ProblemSet problemSet)
    {
        AlgorithmMode Modo = problemSet.getMode();
        float media = problemSet.getDesiredAverage();
        int p = problemSet.getNumberToChoose();
        float[] v = problemSet.getRates();
        int n = v.length;

        float b = media * p/T;

        if (Modo == AlgorithmMode.ABOVE_AVERAGE) {
            Primitive.sort(v, (d1, d2)->Double.compare(d2,d1));
        }
        else if (Modo == AlgorithmMode.BELOW_AVERAGE) {
            Arrays.sort(v);
        }

        int [][][] M = new int [n][T+1][p];

        for (int t = 0; t <= T; t++)
            for (int i = 0; i < p; i++)
                M[p-1][t][i] = i;


        float Dist;
        int[] u;
        int[] r;

        for(int i = p; i < n; i++)
            for (int t = 0; t <= T; t++) {
                if (v[i] > t * b)
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                else
                {
                    Dist = Distancia(M[i-1][t], t, Modo, b, v);
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);

                    for (int k = 0; k < p; k++)
                    {
                        u = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                        u = Replace(u[k], i, u);
                        if (Dist == INFINITY || (Dist > Distancia(u, t, Modo, b, v) && Distancia(u, t, Modo, b, v) >= 0))
                        {
                            r = Arrays.copyOf(u, u.length);
                            M[i][t] = Arrays.copyOf(u, u.length);
                            Dist = Distancia(r, t, Modo, b, v);
                        }
                    }
                }
            }

        return mapPositionsToValues(M[n-1][T], v);
    }


    private float[] mapPositionsToValues(int[] u, float[] v) {
        float[] mappedValues = new float[u.length];

        for(int i = 0; i < u.length; i++) {
            mappedValues[i] = v[u[i]];
        }
        return mappedValues;
    }

    public int[] Replace (int r, int s, int[] v)
    {
        int[] u = IntStream.range(0, v.length)
                           .map(i -> (v[i] != r)? v[i] : s)
                           .toArray();
        return u;
    }
}
