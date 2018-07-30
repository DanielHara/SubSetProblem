package Resources;

public class ContractPackage {

  private float rate;
  private int quantity;			//n�mero de contratos contados.

  ContractPackage(float rate) {
    this.rate = rate;
    quantity = 1;
  }

  public float getRate() {
    return rate;
  }

  public void inc() {
    quantity++;
  }

  public int getQuantity() {
    return quantity;
  }
}
