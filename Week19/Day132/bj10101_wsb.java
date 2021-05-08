import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] angleArr = new int[3];
        for (int i = 0; i < 3; i++) {
            angleArr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(typeOfTriangle(angleArr));
        br.close();
    }

    static String typeOfTriangle(int[] angleArr) {
        int standardAngle = angleArr[0];
        int sumAngle = 0, sameStandardAngles = 0;
        for (int angle : angleArr) {
            sumAngle += angle;
            if (standardAngle == angle) sameStandardAngles++;
        }

        if (sumAngle != 180) return "Error";
        if (sameStandardAngles == 3) return "Equilateral";
        if (sameStandardAngles == 2 || angleArr[1] == angleArr[2]) return "Isosceles";
        return "Scalene";
    }
}