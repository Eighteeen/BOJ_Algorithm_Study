import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while (!(input = br.readLine()).equals("0 0 0")) {
            String[] triangleInfo = input.split(" ");
            int side1 = Integer.parseInt(triangleInfo[0]);
            int side2 = Integer.parseInt(triangleInfo[1]);
            int side3 = Integer.parseInt(triangleInfo[2]);

            int maxSide = Math.max(side1 , Math.max(side2, side3));
            int sumSide = side1 + side2 + side3;
            if (sumSide - maxSide <= maxSide) {
                sb.append("Invalid").append("\n");
                continue;
            }

            boolean isSameSide1And2 = side1 == side2, isSameSide2And3 = side2 == side3;
            if (isSameSide1And2 && isSameSide2And3) sb.append("Equilateral").append("\n");
            else if (isSameSide1And2 || isSameSide2And3 || side1 == side3) sb.append("Isosceles").append("\n");
            else sb.append("Scalene").append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}