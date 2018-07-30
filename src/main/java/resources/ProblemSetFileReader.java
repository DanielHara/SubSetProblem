package resources;

import algorithm.AlgorithmMode;
import algorithm.ProblemSet;
import exceptions.IllegalInputFileException;

public class ProblemSetFileReader {
  static final String ILLEGAL_MODE = "Illegal mode has been passed";


  InputReader inputReader;

  public ProblemSetFileReader(InputReader inputReader) {
    this.inputReader = inputReader;
  }

  public ProblemSet getProblemSet () throws Exception {
    int numberReadContracts;
    float rateReadContract;
    AlgorithmMode modo;

    String modeAsString = inputReader.nextToken();

    switch (modeAsString.toUpperCase()) {
      case "ABOVE_AVERAGE":
        modo = AlgorithmMode.ABOVE_AVERAGE;
        break;
      case "BELOW_AVERAGE":
        modo = AlgorithmMode.BELOW_AVERAGE;
        break;
      case "BEST_AVERAGE":
        modo = AlgorithmMode.BEST_AVERAGE;
        break;
      default:
        throw new IllegalInputFileException(ILLEGAL_MODE);
    }

    int numberPossibleContracts = Integer.parseInt(inputReader.nextToken());
    int numberToChoose = Integer.parseInt(inputReader.nextToken());
    float desiredAverage = Float.parseFloat(inputReader.nextToken());

    float[] v = new float[numberPossibleContracts];

    int i = 0;
    while (!inputReader.endOfFile()) {
      numberReadContracts = Integer.parseInt(inputReader.nextToken());
      rateReadContract = Float.parseFloat(inputReader.nextToken());

      for (int j = 1; j <= numberReadContracts; j++) {
        v[i] = rateReadContract;
        i++;
      }
    }

    return new ProblemSet(modo, numberToChoose, desiredAverage, v);
  }
}
