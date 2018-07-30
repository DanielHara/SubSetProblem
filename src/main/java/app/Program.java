package app;

import algorithm.ProblemSet;
import algorithm.SubSet;
import resources.ContractList;
import resources.InputReader;
import resources.OutputWriter;
import resources.ProblemSetFileReader;

public class Program {
  /**
   * Main program: reads input.txt and write the
   * chosen contracts on output.txt.
   */
  public static void main(String[] args) {
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    InputReader inputReader;
    ProblemSetFileReader problemSetFileReader;
    ProblemSet problemSet;

    try {
      inputReader = new InputReader(inputFile);
      problemSetFileReader = new ProblemSetFileReader(inputReader);
      problemSet = problemSetFileReader.getProblemSet();
    } catch (Exception e) {
      System.out.println("Error: " + e);
      return;
    }

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
