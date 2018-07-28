import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Program {

    public static void main (String[] args)
    {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

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


        float[] v = new float[N_ContratosPossiveis];

        int i, j;
        i = 0;
        while(!input.EndOfFile())
        {
            N_ContratosLidos = Integer.parseInt(input.NextToken());
            Taxa_Contrato_Lido = Float.parseFloat(input.NextToken());

            for (j = 1; j <= N_ContratosLidos; j++)
            {
                v[i] = Taxa_Contrato_Lido;
                i++;
            }
        }

        ProblemSet problemSet = new ProblemSet(Modo, N_Escolher, MediaDesejada, v);

        SubSet problemSetSolver = new SubSet();

        float[] solutionRates =  problemSetSolver.RunAlgorithm(problemSet);

        ContractList solutionContractList = new ContractList(solutionRates);

        String solutionString = solutionContractList.getDescription();

        try
        {
            OutputWriter outputWriter = new OutputWriter(outputFile);
            outputWriter.writeString(solutionString);
            outputWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }
}
