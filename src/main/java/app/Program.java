package app;

import algorithm.ProblemSet;
import algorithm.SubSet;
import resources.ContractList;
import resources.InputReader;
import resources.OutputWriter;
import resources.ProblemSetFileReader;

public class Program {
  public static void main(String[] args) {
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    InputReader inputReader;
    ProblemSetFileReader problemSetFileReader;

    try {
      inputReader = new InputReader(inputFile);
      problemSetFileReader = new ProblemSetFileReader(inputReader);
    } catch (Exception e) {
      System.out.println("Error: " + e);
      return;
    }

    ProblemSet problemSet = problemSetFileReader.getProblemSet();

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
