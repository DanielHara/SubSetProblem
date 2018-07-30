package resources;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;


public class ContractListTest {
  static final float FLOAT_COMPARISON_THRESHOLD = (float)0.0001;

  @Test
  public void testGetAverageList() {
    float[] rates = {9, 8, 9, 8};
    ContractList contractList = new ContractList(rates);

    Assert.assertEquals(8.5, contractList.getAverageRate(), FLOAT_COMPARISON_THRESHOLD);
  }

  @Test
  public void testGetDescription() {
    float[] rates = {9, 8, 9, 8};
    ContractList contractList = new ContractList(rates);

    String actualDescription = contractList.getDescription();

    String firstDescription = "2 contracts with rate 9.0";
    String secondDescription = "2 contracts with rate 8.0";
    String averageDescription = "Average: 8.5";

    Assert.assertThat(actualDescription, containsString(firstDescription));
    Assert.assertThat(actualDescription, containsString(secondDescription));
    Assert.assertThat(actualDescription, containsString(averageDescription));
  }
}
