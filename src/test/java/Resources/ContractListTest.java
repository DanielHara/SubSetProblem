package Resources;

import org.junit.Assert;
import org.junit.Test;

public class ContractListTest {

    final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

    @Test
    public void testConstructor() {
        float[] rates = {9, 8, 9, 8};
        ContractList contractList = new ContractList(rates);

        Assert.assertEquals(8.5, contractList.getAverageRate(), FLOAT_COMPARISON_THRESHOLD);
    }
}

