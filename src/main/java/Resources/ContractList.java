package Resources;

import Resources.ContractPackage;

import java.util.ArrayList;

public class ContractList {
    private ArrayList<ContractPackage> contractList;
    final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;
    final String NO_SOLUTION_FOUND = "NO SOLUTION FOUND";

    public ContractList() {
        contractList = new ArrayList();
    }

    public void addContractUnit(float rate) {
        for(int i = 0; i < contractList.size(); i++) {
            if (isEqual(contractList.get(i).getRate(), rate)) {
                contractList.get(i).Inc();
                return;
            }
        }
        ContractPackage newContract = new ContractPackage(rate);
        contractList.add(newContract);
    }

    private boolean isEqual(float f1, float f2) {
        return (Math.abs(f1 - f2)  < FLOAT_COMPARISON_THRESHOLD);
    }

    public ContractList(float[] rates) {
        contractList = new ArrayList();

        this.addContractRates(rates);
    }

    public void addContractRates(float[] rates) {
        if(rates != null) {
            for (float rate : rates) {
                this.addContractUnit(rate);
            }
        }
    }

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

    public String getDescription() {
        String description = "";

        if (contractList.size() == 0) {
            return NO_SOLUTION_FOUND;
        }

        for (ContractPackage contract: contractList) {
            description =
                 description.concat(contract.getQuantity() + " contracts with rate " + contract.getRate() + "\r\n");
        }

        description = description.concat("Average: " + this.getAverageRate());

        return description;
    }
}
