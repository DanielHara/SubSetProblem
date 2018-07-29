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
    public void testAlgorithmAverageAbove() {
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

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }

    @Test
    public void testAlgorithmAverageBelow() {
        int numberToChoose = 2;
        float[] rates = {9, 10, 10};
        float desiredAverage = 9.9f;

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.MEDIAPARABAIXO,
                numberToChoose,
                desiredAverage,
                rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.RunAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }

    @Test
    public void testAlgorithmBestAverage() {
        int numberToChoose = 2;
        float[] rates = {9, 9, 10, 10, 20};
        float desiredAverage = 9.5f;

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.MELHORMEDIA,
                numberToChoose,
                desiredAverage,
                rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.RunAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }
}
