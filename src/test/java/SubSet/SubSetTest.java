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

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.ABOVE_AVERAGE,
                                               numberToChoose,
                                               desiredAverage,
                                               rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.runAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }

    @Test
    public void testAlgorithmAverageBelow() {
        int numberToChoose = 2;
        float[] rates = {9, 10, 10};
        float desiredAverage = 9.9f;

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.BELOW_AVERAGE,
                numberToChoose,
                desiredAverage,
                rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.runAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }

    @Test
    public void testAlgorithmBestAverage() {
        int numberToChoose = 2;
        float[] rates = {9, 9, 10, 10, 20};
        float desiredAverage = 9.5f;

        ProblemSet problemSet = new ProblemSet(AlgorithmMode.BEST_AVERAGE,
                                               numberToChoose,
                                               desiredAverage,
                                               rates);

        SubSet subSet = new SubSet();

        float[] solution = subSet.runAlgorithm(problemSet);
        Arrays.sort(solution);

        Assert.assertArrayEquals(new float[]{9, 10}, solution, 0.1f);
    }

    final float ERROR_THRESHOLD = 0.01f;

    public float averageArray(float[] v) {
        if (v.length == 0)
            return -1;

        float sum = 0;
        for (float el: v) {
            sum = sum + el;
        }
        return sum / v.length;
    }

    @Test
    public void testBestAverageRealCase() {
        int numberToChoose = 20;
        float[] rates = new float[600];
        float desiredAverage = 9.33f;

        for(int i = 0; i < 100; i++) {
            rates[i] = 9.23f;
        }

        for(int i = 100; i < 300; i++) {
            rates[i] = 9.32f;
        }

        for(int i = 300; i < 600; i++) {
            rates[i] = 9.49f;
        }

        ProblemSet problemSetBestAverage = new ProblemSet(AlgorithmMode.BEST_AVERAGE,
                                                          numberToChoose,
                                                          desiredAverage,
                                                          rates);

        ProblemSet problemSetBelowAverage = new ProblemSet(AlgorithmMode.BELOW_AVERAGE,
                numberToChoose,
                desiredAverage,
                rates);

        ProblemSet problemSetAboveAverage = new ProblemSet(AlgorithmMode.ABOVE_AVERAGE,
                numberToChoose,
                desiredAverage,
                rates);


        SubSet subSet = new SubSet();
        float[] solutionBestAverage = subSet.runAlgorithm(problemSetBestAverage);
        float[] solutionBelowAverage = subSet.runAlgorithm(problemSetBelowAverage);
        float[] solutionAboveAverage = subSet.runAlgorithm(problemSetAboveAverage);

        Assert.assertEquals(desiredAverage, averageArray(solutionBestAverage), ERROR_THRESHOLD);
        Assert.assertEquals(desiredAverage, averageArray(solutionBelowAverage), ERROR_THRESHOLD);
        Assert.assertEquals(desiredAverage, averageArray(solutionAboveAverage), ERROR_THRESHOLD);

        Assert.assertTrue(averageArray(solutionBelowAverage) <= desiredAverage);
        Assert.assertTrue(averageArray(solutionAboveAverage) >= desiredAverage);
    }
}
