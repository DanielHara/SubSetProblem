import java.util.ArrayList;

public class ContractList {
    ArrayList<PacoteContratos> contractList;
    final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

    public ContractList() {
        contractList = new ArrayList();
    }

    public void addContractUnit(float rate) {
        for(int i = 0; i < contractList.size(); i++) {
            if (isEqual(contractList.get(i).getTaxa(), rate)) {
                contractList.get(i).Inc();
                return;
            }
        }
        PacoteContratos newContract = new PacoteContratos(rate);
        contractList.add(newContract);
    }

    private boolean isEqual(float f1, float f2) {
        return (Math.abs(f1 - f2)  < FLOAT_COMPARISON_THRESHOLD);
    }

    public ContractList(float[] rates) {
        contractList = new ArrayList();
        for (float rate: rates) {
            this.addContractUnit(rate);
        }
    }

    public float getAverageRate() {
        int totalNumberOfContracts = 0;
        float rateAccumulator = 0;
        for (PacoteContratos contract: contractList) {
            totalNumberOfContracts = totalNumberOfContracts + contract.getQuantity();
            rateAccumulator = rateAccumulator + contract.getTaxa() * contract.getQuantity();
        }

        float averageRate = rateAccumulator / totalNumberOfContracts;

        return averageRate;
    }

    public String getDescription() {
        String description = "";

        for (PacoteContratos contract: contractList) {
            description =
                 description.concat(contract.getQuantity() + " contracts with rate " + contract.getTaxa() + "\r\n");
        }

        description = description.concat("Average: " + this.getAverageRate());

        return description;
    }
}
