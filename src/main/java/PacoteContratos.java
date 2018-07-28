public class PacoteContratos {

    private float taxa;
    private int N;			//n�mero de contratos contados.

    PacoteContratos (float taxa)
    {
        this.taxa = taxa;
        N = 1;
    }

    public float getTaxa ()
    {
        return taxa;
    }

    public void Inc ()
    {
        N++;
    }

    public int getQuantity ()
    {
        return N;
    }

    //Retorna true se taxa = t, e false, caso contr�rio.
    public boolean Compare (float t)
    {
        return (Math.abs((float)((float)taxa - (float)t)) < 0.001);
    }
}
