package Resources;

public class ContractPackage {

    private float rate;
    private int N;			//n�mero de contratos contados.

    ContractPackage (float rate)
    {
        this.rate = rate;
        N = 1;
    }

    public float getRate ()
    {
        return rate;
    }

    public void Inc ()
    {
        N++;
    }

    public int getQuantity ()
    {
        return N;
    }
}
