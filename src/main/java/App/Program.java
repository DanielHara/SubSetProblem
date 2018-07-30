package App;

import Algorithm.AlgorithmMode;
import Algorithm.ProblemSet;
import Algorithm.SubSet;
import Resources.ContractList;
import Resources.InputReader;
import Resources.OutputWriter;

public class Program {
  public static void main(String[] args) {
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    InputReader input = null;

    try {
      input = new InputReader(inputFile);
    } catch (Exception e) {
      System.out.println("Error: " + e);
      return;
    }

    AlgorithmMode modo = AlgorithmMode.BEST_AVERAGE;

    int numberReadContracts;
    float rateReadContract;

    String modeAsString = input.nextToken();

    if (modeAsString.toUpperCase().contains("ABOVE_AVERAGE")) {
      modo = AlgorithmMode.ABOVE_AVERAGE;
    } else if (modeAsString.toUpperCase().contains("BELOW_AVERAGE")) {
      modo = AlgorithmMode.BELOW_AVERAGE;
    }

    int numberPossibleContracts = Integer.parseInt(input.nextToken());
    int numberToChoose = Integer.parseInt(input.nextToken());
    float desiredAverage = Float.parseFloat(input.nextToken());


    float[] v = new float[numberPossibleContracts];

    int i = 0;
    while (!input.endOfFile()) {
      numberReadContracts = Integer.parseInt(input.nextToken());
      rateReadContract = Float.parseFloat(input.nextToken());

      for (int j = 1; j <= numberReadContracts; j++) {
        v[i] = rateReadContract;
        i++;
      }
    }

    ProblemSet problemSet = new ProblemSet(modo, numberToChoose, desiredAverage, v);

    SubSet problemSetSolver = new SubSet();

    float[] solutionRates =  problemSetSolver.runAlgorithm(problemSet);

    ContractList solutionContractList = new ContractList(solutionRates);

    String solutionString = solutionContractList.getDescription();

    try {
      OutputWriter outputWriter = new OutputWriter(outputFile);
      outputWriter.writeString(solutionString);
      outputWriter.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }
}
