package lab0;

public class Variant11 {
    public enum POLES{
        NORTH, EAST, SOUTH, WEST
    }

    /**
     *
     * @param k is square side
     * @return perimeter
     */
    public int inputOutputTask(int k) {
        return 0;
    }

    /**
     *
     * @param k is a three-digit number
     * @return sum and product in array
     */
    public int[] integerNumbersTask(int k) {
        int firstDigit = k / 100;
        int secondDigit = (k / 10) % 10;
        int thirdDigit = k % 10;

        int sum = firstDigit + secondDigit + thirdDigit;
        int product = firstDigit * secondDigit * thirdDigit;

        return new int[]{sum, product};
    }

    /**
     *
     * @param number1, number2 integer numbers
     * @return true, if numbers have the same parity
     */
    public boolean booleanTask(int number1, int number2) {
        return (number1 % 2 == number2 % 2);
    }


    /**
     *
     * @param number1, number2 integer numbers
     * @return changed number1, number2
     */
    public int[] ifTask(int number1, int number2) {
        if (number1 != number2) {
            int max = Math.max(number1, number2);
            number1 = max;
            number2 = max;
        } else {
            number1 = 0;
            number2 = 0;
        }
        return new int[]{number1, number2};
    }


    /**
     *
     * @param command1, command2
     * @return pole after numbers commands
     */
    public POLES switchTask(int command1, int command2) {
        assert(command1 >= -1 && command2 >= -1 && command1 <= 2 && command2 <= 2 && command1 != 0 && command2 != 0): "Argument should be -1 or 1 or 2";
        int arrow = 1;
        if(command1 == -1) command1 = 3;
        if(command2 == -1) command2 = 3;

        arrow += command1;
        arrow += command2;

        if(arrow > 4) arrow = arrow % 4;

        return switch (arrow) {
            case 1 -> POLES.NORTH;
            case 2 -> POLES.EAST;
            case 3 -> POLES.SOUTH;
            case 4 -> POLES.WEST;
            default -> POLES.NORTH;
        };
    }


    /**
     *
     * @param n is integer number
     * @return sum
     */
    public double forTask(int n) {
        double sum = n * n;
        for (int i = 1; i <= n; i++) {
            sum += (n + i) * (n + i);
        }
        return sum;
    }


    public int[] whileTask(int n) {
        assert (n > 1): "Argument should be more than 1";
        int sum = 0;
        int k = 1;
        while (sum < n){
            k++;
            sum = 0;
            for(int i = 1; i <= k; i++){
                sum += i;
            }
        }
        return new int[]{k, sum};
    }

    public double[] arrayTask(double[] array, int k) {
        assert (k >= 1 && k <= array.length): "Argumet should be in range from 1 to array length";
        double[] newArray = new double[1 + array.length % k];
        int j = 0;
        for (int i = 0; i < array.length; i += k){
            newArray[j] = array[i];
            j++;
        }
        return newArray;
    }

    /**
     *
     * @param array
     * @return transformed array where row with indexes k1 and k2 was changed
     */
    public int[][]  twoDimensionArrayTask(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = array[i][j];
                }
            } else {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = array[i][cols - 1 - j];
                }
            }
        }
        return result;
    }

    public static void main(String... strings) {
        System.out.println("Start of zero lab");
        System.out.println("Done!!!");
    }
}
