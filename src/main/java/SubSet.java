import net.mintern.primitive.Primitive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SubSet
{
    final int T = 1000;

    final int MEDIAPARACIMA = 0;
    final int MEDIAPARABAIXO = 1;
    final int MELHORMEDIA = 2;

    final float INFINITY = -1;
    final float FLOAT_COMPARISON_THRESHOLD = 0.0001;

    // Colocar apenas um T opcional.
    SubSet() {
    }



    //Soma dos elementos do vetor u, que tem 10 posi��es v�lidas, excluindo a posi��o 0 (n�o � usada).
    //Poderia chamar u de POSIÇÕES do vetor v.
    public float Soma (int[] u, float[] v)
    {
        float sum = 0;
        for (int position: u) {
            sum = sum + v[position];
        }
        return sum;
    }

    public float Distancia (int[] u, int t, int Modo, float b, float[] v)
    {
        if (Modo == MELHORMEDIA)
            return Math.abs((Soma(u, v) - (float)t*b));
        else
        {
            if (Modo == MEDIAPARACIMA)
            {
                if ((Soma(u, v) - (float)t*b) < (-1)*FLOAT_COMPARISON_THRESHOLD)
                    return INFINITY;
                else return Math.abs((Soma(u, v) - (float)t*b));
            }
            else
            {
                if ((Soma(u, v) - (float)t*b) > FLOAT_COMPARISON_THRESHOLD)
                    return INFINITY;
                else return Math.abs((Soma(u, v) - (float)t*b));
            }
        }

    }

    public void RunAlgorithm ()
    {
        int Modo = problemSet.getMode();
        float media = problemSet.getDesiredAverage();
        int p = problemSet.getNumberToChoose();
        float[] v = problemSet.getRates();
        int n = v.length;

        float b = (float) media * p/T;

        if (Modo == MEDIAPARACIMA) {
            Primitive.sort(v, (d1, d2)->Double.compare(d2,d1));
        }
        else if (Modo == MEDIAPARABAIXO) {
            Arrays.sort(v);
        }

        int [][][] M = new int [n][T][p];

        for (int t = 0; t < T; t++)
            for (int i = 0; i < p; i++)
                M[p-1][t][i] = i;


        float Dist = INFINITY;
        int[] u;			//Vetor iterador
        int[] r;			//Vetor �timo

        for(int i = p; i < n; i++)
            for (int t = 0; t < T; t++) {
                if (v[i] > t * b)
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                else
                {
                    Dist = Distancia(M[i-1][t], t, Modo, b, v);
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                    r = Arrays.copyOf(M[i-1][t], M[i-1][t].length);

                    for (int k = 0; k < p; k++)
                    {
                        u = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                        u = Replace(u[k], i, u);
                        if (Dist == INFINITY || (Dist > Distancia(u, t, Modo, b, v) && Distancia(u, t, Modo, b, v) > 0))
                        {
                            r = Arrays.copyOf(u, u.length);
                            M[i][t] = Arrays.copyOf(u, u.length);
                            Dist = Distancia(r, t, Modo, b, v);
                        }
                    }
                }
            }
    }


    public float


    /*
    public void ShowSolution (String filename)
    {

        float Soma = 0;

        int i, j;

        for (i = 1; i <= p; i++)
            Soma = Soma + v[M[n][T][i]];

        float media = (float) Soma/p;

        for (j = 1; j <=p; j++)
            for (i = 0; i < L.size(); i++)
                if (L.get(i).Compare((float)v[M[n][T][j]]))
                    L.get(i).Inc();

        try
        {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (i = 0; i < L.size(); i++)
                bw.write(L.get(i).GetNumber() + " contratos de " + L.get(i).getTaxa() + "\r\n");
            bw.write("M�dia = " + media);
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("ERRO de I/O:" + e);
        }
    }   */

    //Esta fun��o varre o vetor v, e substitui os n�meros r de v pelo n�mero s
    //, compondo um novo vetor, que � retornado.
    public int[] Replace (int r, int s, int[] v)
    {
        int[] u = new int[p];

        int i;
        for (i = 0; i < p; i++) {
            if (v[i] != r)
                u[i] = v[i];
            else u[i] = s;
        }
        return u;
    }
}
