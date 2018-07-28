import java.util.ArrayList;

public class Program {

    public static void main (String[] args)
    {
        final int MEDIAPARACIMA = 0;
        final int MEDIAPARABAIXO = 1;
        final int MELHORMEDIA = 2;

        InputReader input = null;

        try
        {
            input = new InputReader("input.txt");
        }
        catch (Exception e)
        {
            System.out.println("Erro: " + e);
        }

        String ModeAsString;
        int Modo = MELHORMEDIA;

        int N_ContratosPossiveis;
        int N_Escolher;
        float MediaDesejada;

        int N_ContratosLidos;
        float Taxa_Contrato_Lido;

        ModeAsString = input.NextToken();

        if (ModeAsString.toUpperCase().contains("MEDIAPARACIMA"))
            Modo = MEDIAPARACIMA;
        else
        {
            if (ModeAsString.toUpperCase().contains("MEDIAPARABAIXO"))
                Modo = MEDIAPARABAIXO;
        }

        N_ContratosPossiveis = Integer.parseInt(input.NextToken());
        N_Escolher = Integer.parseInt(input.NextToken());
        MediaDesejada = Float.parseFloat(input.NextToken());


        float[] v = new float[N_ContratosPossiveis + 1];

        ArrayList<PacoteContratos> Valores_Taxas = new ArrayList<PacoteContratos>();

        int i, j;
        i = 1;
        while(!input.EndOfFile())
        {
            N_ContratosLidos = Integer.parseInt(input.NextToken());
            Taxa_Contrato_Lido = Float.parseFloat(input.NextToken());

            PacoteContratos Novo = new PacoteContratos (Taxa_Contrato_Lido);
            Valores_Taxas.add(Novo);

            for (j = 1; j <= N_ContratosLidos; j++)
            {
                v[i] = Taxa_Contrato_Lido;
                i++;
            }
        }

        SubSet Algorithm = new SubSet(N_ContratosPossiveis, (float) MediaDesejada, N_Escolher, v, Valores_Taxas, Modo);


        Algorithm.RunAlgorithm();
        Algorithm.ShowSolution("output.txt");
    }

}
