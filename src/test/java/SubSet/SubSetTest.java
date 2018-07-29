package SubSet;

import Algorithm.AlgorithmMode;
import Algorithm.ProblemSet;
import Algorithm.SubSet;
import Resources.ContractList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SubSetTest {

    @Test
    public void testAlgorithm() {
        int numberToChoose = 2;
        float[] rates = {9, 9, 10};
        float desiredAverage = 9.1f;

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.MEDIAPARACIMA,
                                               numberToChoose,
                                               desiredAverage,
                                               rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.RunAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(solution, new float[]{9, 10}, 0.1f);
    }
}
