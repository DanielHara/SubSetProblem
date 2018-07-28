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

    float[] v;	//Vetor dos n�meros representando as taxas dos fundos
    int n;      //Tamanho do vetor (excluindo a posi��o 0). n = n�mero de fundos dos quais podemos escolher.
    int p;
    float media;

    int [][][] M; //Matriz de vetores, de dimens�es (n- 10) x T, onde T � o n�mero de floats poss�veis (lembrar que a posi��o 0 � ignorada)
    //M[i][T] � o vetor de 10 elementos representando a melhor solu��o com 10 dos primeiros i elementos
    //cuja soma melhor aproxima T.
    //b � o PASSO
    float b; 		  //T*b � o n�mero a ser melhor aproximado pela soma de v[k], com k pertencente ao vetor M[i][T]
    int T;        //T tamanho m�ximo

    ArrayList<PacoteContratos> L;

    int Modo;

    final int MEDIAPARACIMA = 0;
    final int MEDIAPARABAIXO = 1;
    final int MELHORMEDIA = 2;

    // SubSet(int _n, float media, int _p, float[] _v, ArrayList<PacoteContratos> _L, int _Modo)
    SubSet(ProblemSet problemSet)
    {
        Modo = problemSet.getMode();
        media = problemSet.getDesiredAverage();
        p = problemSet.getNumberToChoose();
        v = problemSet.getRates();
        n = v.length;

        T = 1000;

        b = (float) media * p/T;

        if (Modo == MEDIAPARACIMA) {
            Arrays.sort(v); //TEM QUE INVERTER A ORDEM!
        }
        else
        {
            if (Modo == MEDIAPARABAIXO) {
                Arrays.sort(v);
            }
        }

        M = new int [n][T][p];

        int i, t;   //Iteradores
        for (t = 0; t < T; t++)
            for (i = 0; i < p; i++)
                M[p][t][i] = i;
    }



    //Soma dos elementos do vetor u, que tem 10 posi��es v�lidas, excluindo a posi��o 0 (n�o � usada).
    public float Soma (int[] u)
    {
        float Soma = 0;
        int k;
        for (k = 0; k < p; k++)
            Soma = Soma + v[u[k]];
        return Soma;
    }

    public float Distancia (int[] u, int t, int Modo)
    {
        if (Modo == MELHORMEDIA)
            return (float)Math.abs((float)(Soma(u) - (float)t*b));
        else
        {
            if (Modo == MEDIAPARACIMA)
            {
                if ((float)(Soma(u) - (float)t*b) < -0.001)
                    return -1;
                else return (float)Math.abs((float)(Soma(u) - (float)t*b));
            }
            else
            {
                if ((float)(Soma(u) - (float)t*b) > 0.001)
                    return -1;
                else return (float)Math.abs((float)(Soma(u) - (float)t*b));
            }
        }

    }

    public void RunAlgorithm ()
    {
        int i, t, k;
        float Dist = -1;
        int[] u;			//Vetor iterador
        int[] r;			//Vetor �timo

        for(i = p; i < n; i++)
            for (t = 0; t < T; t++)
            {
                if (v[i] > t * b)
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                else
                {
                    Dist = Distancia(M[i-1][t], t, Modo);
                    M[i][t] = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                    r = Arrays.copyOf(M[i-1][t], M[i-1][t].length);

                    for (k = 0; k < p; k++)
                    {
                        u = Arrays.copyOf(M[i-1][t], M[i-1][t].length);
                        u = Replace(u[k], i, u);
                        if (Dist < 0 || (Dist > Distancia(u, t, Modo) && Distancia(u, t, Modo) > 0))
                        {
                            r = Arrays.copyOf(u, u.length);
                            M[i][t] = Arrays.copyOf(u, u.length);
                            Dist = Distancia(r, t, Modo);
                        }
                    }
                }
            }
    }


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

    public boolean isEqual(float f1, float f2)
    {
        return (Math.abs(f1 - f2) < 0.001);
    }

    //Esta fun��o varre o vetor v, e substitui os n�meros r de v pelo n�mero s
    //, compondo um novo vetor, que � retornado.
    public int[] Replace (int r, int s, int[] v)
    {
        int[] u = new int[p];

        int i;
        for (i = 0; i < p; i++)
        {
            if (v[i] != r)
                u[i] = v[i];
            else u[i] = s;
        }
        return u;
    }

    public float[] CopyVectorFloat (float[] v)
    {
        float[] u = new float [v.length];

        int i;
        for (i = 0; i < v.length; i++)
            u[i] = v[i];

        return u;
    }
}
