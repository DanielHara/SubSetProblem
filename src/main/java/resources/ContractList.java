package resources;

import java.util.ArrayList;

/**
 * List of ContractPackages, each with a different rate.
 */
public class ContractList {
  private ArrayList<ContractPackage> contractList;
  static final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;
  static final String NO_SOLUTION_FOUND = "NO SOLUTION FOUND";

  /**
   * Constructs with empty list.
   */
  public ContractList() {
    contractList = new ArrayList();
  }

  private void addContractUnit(float rate) {
    for (int i = 0; i < contractList.size(); i++) {
      if (isEqual(contractList.get(i).getRate(), rate)) {
        contractList.get(i).inc();
        return;
      }
    }
    ContractPackage newContract = new ContractPackage(rate);
    contractList.add(newContract);
  }

  private boolean isEqual(float f1, float f2) {
    return (Math.abs(f1 - f2)  < FLOAT_COMPARISON_THRESHOLD);
  }

  /**
   * Contructs with the given rates.
   * @param rates to be added to object.
   */
  public ContractList(float[] rates) {
    contractList = new ArrayList();

    this.addContractRates(rates);
  }

  /**
   * Adds contracts with the rates passed.
   * @param rates to be added
   */
  public void addContractRates(float[] rates) {
    if (rates != null) {
      for (float rate : rates) {
        this.addContractUnit(rate);
      }
    }
  }

  /**
   * Average rate.
   * @return average rate of the contracts (simple average)
   */
  public float getAverageRate() {
    int totalNumberOfContracts = 0;
    float rateAccumulator = 0;
    for (ContractPackage contract: contractList) {
      totalNumberOfContracts = totalNumberOfContracts + contract.getQuantity();
      rateAccumulator = rateAccumulator + contract.getRate() * contract.getQuantity();
    }

    float averageRate = rateAccumulator / totalNumberOfContracts;

    return averageRate;
  }

  /**
   * String description.
   * @return description of the contracts contained in the object
   */
  public String getDescription() {
    String description = "";

    if (contractList.size() == 0) {
      return NO_SOLUTION_FOUND;
    }

    for (ContractPackage contract: contractList) {
      description =
          description.concat(contract.getQuantity()
                             + " contracts with rate " + contract.getRate() + "\r\n");
    }

    description = description.concat("Average: " + this.getAverageRate());

    return description;
  }
}
