import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔해요 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        int testCaseNum = 0;
        while (!(input = br.readLine()).equals("0")) {
            String[] pizzaTableInfo = input.split(" ");
            int r = Integer.parseInt(pizzaTableInfo[0]);
            int w = Integer.parseInt(pizzaTableInfo[1]);
            int l = Integer.parseInt(pizzaTableInfo[2]);

            sb.append(String.format("Pizza %d ", ++testCaseNum))
                .append(isRectangleInsideCircle(r, w, l) ? "fits" : "does not fit")
                .append(" on the table.\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isRectangleInsideCircle(int radius, int rectangleWidth, int rectangleHeight) {
        int diameter = radius * 2;
        double diagonal = Math.sqrt(Math.pow(rectangleWidth, 2) + Math.pow(rectangleHeight, 2));
        if (diameter >= diagonal) return true;
        return false;
    }
}