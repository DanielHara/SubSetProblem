package App;

import Algorithm.AlgorithmMode;
import Algorithm.ProblemSet;
import Algorithm.SubSet;
import Resources.ContractList;
import Resources.InputReader;
import Resources.OutputWriter;

public class Program {

    public static void main (String[] args)
    {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        InputReader input = null;

        try
        {
            input = new InputReader(inputFile);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
            return;
        }

        AlgorithmMode Modo = AlgorithmMode.BEST_AVERAGE;

        int numberPossibleContracts;
        int numberToChoose;
        float desiredAverage;

        int numberReadContracts;
        float rateReadContract;

        String ModeAsString = input.NextToken();

        if (ModeAsString.toUpperCase().contains("ABOVE_AVERAGE"))
            Modo = AlgorithmMode.ABOVE_AVERAGE;
        else if (ModeAsString.toUpperCase().contains("BELOW_AVERAGE"))
            Modo = AlgorithmMode.BELOW_AVERAGE;

        numberPossibleContracts = Integer.parseInt(input.NextToken());
        numberToChoose = Integer.parseInt(input.NextToken());
        desiredAverage = Float.parseFloat(input.NextToken());


        float[] v = new float[numberPossibleContracts];

        int i, j;
        i = 0;
        while(!input.EndOfFile())
        {
            numberReadContracts = Integer.parseInt(input.NextToken());
            rateReadContract = Float.parseFloat(input.NextToken());

            for (j = 1; j <= numberReadContracts; j++)
            {
                v[i] = rateReadContract;
                i++;
            }
        }

        ProblemSet problemSet = new ProblemSet(Modo, numberToChoose, desiredAverage, v);

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
