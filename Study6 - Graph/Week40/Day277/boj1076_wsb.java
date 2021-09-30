import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static String[] resistanceColors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String resistanceFirstColor = br.readLine();
        String resistanceSecondColor = br.readLine();
        String resistanceMultiplierColor = br.readLine();

        System.out.println(getResistanceNum(resistanceFirstColor, resistanceSecondColor, resistanceMultiplierColor));
        br.close();
    }

    static long getResistanceNum(String firstColor, String secondColor, String multiplierColor) {
        int multiplicand = getMultiplicandResistanceVlaue(firstColor, secondColor);
        int multiplier = getMultiplierResistanceValue(multiplierColor);
        return (long) multiplicand * multiplier;
    }

    static int getMultiplicandResistanceVlaue(String firstColor, String secondColor) {
        int firstValue = getResistanceValue(firstColor);
        int secondValue = getResistanceValue(secondColor);

        String valueStr = new StringBuilder()
            .append(firstValue)
            .append(secondValue)
            .toString();
        return Integer.parseInt(valueStr);
    }

    static int getMultiplierResistanceValue(String color) {
        String valueStr = new StringBuilder("1")
            .append("0".repeat(getResistanceValue(color)))
            .toString();
        return Integer.parseInt(valueStr);
    }

    static int getResistanceValue(String color) {
        for (int i = 0; i < resistanceColors.length; i++) {
            if (resistanceColors[i].equals(color)) return i;
        }
        return -1;
    }
}