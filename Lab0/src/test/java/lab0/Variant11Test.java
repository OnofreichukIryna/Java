package lab0;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lab0.Variant11;
import lab0.Variant11.POLES;

import static org.testng.Assert.assertEquals;

public class Variant11Test {

    public static double EPS = 0.0000001;

    @Test
    public void integerNumbersTest() {
        assertEquals(new Variant11().integerNumbersTask(123), new int[]{6, 6});
        assertEquals(new Variant11().integerNumbersTask(456), new int[]{15, 120});
    }

    @Test(dataProvider = "booleanProvider")
    public void booleanTest(int num1, int num2, boolean expected) {
        assertEquals(new Variant11().booleanTask(num1, num2), expected);
    }

    @DataProvider
    public Object[][] booleanProvider() {
        return new Object[][]{
                {2, 4, true},
                {1, 3, true},
                {0, 0, true},
                {5, 10, false}
        };
    }

    @Test(dataProvider = "ifProvider")
    public void ifTest(int num1, int num2, int[] expected) {
        assertEquals(new Variant11().ifTask(num1, num2), expected);
    }

    @DataProvider
    public Object[][] ifProvider() {
        return new Object[][]{
                {2, 3, new int[]{3, 3}},
                {0, 0, new int[]{0, 0}},
                {5, 5, new int[]{0, 0}},
                {-2, -3, new int[]{-2, -2}}
        };
    }

    @Test(dataProvider = "switchProvider")
    public void switchTest(int command1, int command2, POLES expected) {
        assertEquals(new Variant11().switchTask(command1, command2), expected);
    }

    @DataProvider
    public Object[][] switchProvider() {
        return new Object[][]{
                {1, 1, POLES.SOUTH},
                {2, 1, POLES.WEST},
                {1, -1, POLES.NORTH},
                {-1, 1, POLES.NORTH},
                {2, 2, POLES.NORTH},
                {-20, 3, POLES.NORTH}
        };
    }

    @Test(dataProvider = "forProvider")
    public void forTest(int n, double expected) {
        assertEquals(new Variant11().forTask(n), expected, EPS);
    }

    @DataProvider
    public Object[][] forProvider() {
        return new Object[][]{
                {5, 355},
                {3, 86}
        };
    }

    @Test(dataProvider = "whileProvider")
    public void whileTest(int input, int expectedK, int expectedSum) {
        assertEquals(new Variant11().whileTask(input), new int[]{expectedK, expectedSum});
    }

    @DataProvider
    public Object[][] whileProvider() {
        return new Object[][]{
                {10, 4, 10},
                {12, 5, 15},
                {25, 7, 28}
        };
    }

    @Test(dataProvider = "arrayProvider")
    public void arrayTest(double[] array, int k, double[] expected) {
        assertEquals(new Variant11().arrayTask(array, k), expected);
    }

    @DataProvider
    public Object[][] arrayProvider() {
        return new Object[][]{
                {new double[]{10, 2, 3}, 2, new double[]{10, 3}},
                {new double[]{10, 2, 13}, 3, new double[]{10}},
                {new double[]{4, 3, 5, -4, 9, 2}, 5, new double[]{4, 2}}
        };
    }

    @Test(dataProvider = "matrixProvider")
    public void twoDimensionArrayTest(int[][] input, int[][] expectedOutput) {
        assertEquals(new Variant11().twoDimensionArrayTask(input), expectedOutput);
    }

    @DataProvider
    public Object[][] matrixProvider() {
        return new Object[][]{
                {
                        new int[][]{
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9},
                                {10, 11, 12}
                        },
                        new int[][]{
                                {1, 2, 3},
                                {6, 5, 4},
                                {7, 8, 9},
                                {12, 11, 10}
                        }
                }
        };
    }
}
