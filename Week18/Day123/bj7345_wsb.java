import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] fxInfo = br.readLine().split(" ");
            String fx = convertStringArrToString(Arrays.copyOfRange(fxInfo, 1, fxInfo.length));
            String[] gxInfo = br.readLine().split(" ");
            String gx = convertStringArrToString(Arrays.copyOfRange(gxInfo, 1, gxInfo.length));
            String[] hxInfo = br.readLine().split(" ");
            String hx = convertStringArrToString(Arrays.copyOfRange(hxInfo, 1, hxInfo.length));

            String resultBitString = bitStringOfFxMofHx(bitStingOfFxMultiplyGx(fx, gx), hx);
            sb.append(resultBitString.length());
            for (char bit : resultBitString.toCharArray()) {
                sb.append(" ").append(bit);
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static String bitStringOfFxMofHx(String fx, String hx) {
        if (hx.equals("0")) return fx;
        if (hx.equals("1")) return "0";

        int fxMaxDgree = fx.length() - 1, hxMaxDgree = hx.length()- 1;
        int remainderBit = convertBitStringToInt(fx);
        List<Integer> divisionDgreeList = digitListOfBitString(hx);

        while (fxMaxDgree >= hxMaxDgree) {
            int quotientDgree = fxMaxDgree - hxMaxDgree;

            for (int divisionDgree : divisionDgreeList) {
                int calcDgree = divisionDgree + quotientDgree;
                remainderBit ^= (1 << calcDgree);
            }

            fxMaxDgree = Integer.toBinaryString(remainderBit).length() - 1;
        }
        System.out.println(remainderBit);

        return Integer.toBinaryString(remainderBit);
    }

    static String bitStingOfFxMultiplyGx(String fx, String gx) {
        List<Integer> fxDgreeList = digitListOfBitString(fx);
        List<Integer> gxDgreeList = digitListOfBitString(gx);

        int multiplyBit = 0;
        for (int fxDgree : fxDgreeList) {
            for (int gxDgree : gxDgreeList) {
                int calcDgree = fxDgree + gxDgree;
                multiplyBit ^= (1 << calcDgree);
            }
        }
        
        return Integer.toBinaryString(multiplyBit);
    }
    
    static List<Integer> digitListOfBitString(String bitString) {
        List<Integer> digitList = new ArrayList<>();
        int digit = bitString.length();
        for (char bit : bitString.toCharArray()) {
            --digit;
            if (bit == '0') continue;
            digitList.add(digit);
        }
        return digitList;
    }

    static int convertBitStringToInt(String bitString) {
        int bitMask = 0;
        int digit = bitString.length();
        for (char bit : bitString.toCharArray()) {
            --digit;
            if (bit == '0') continue;
            bitMask |= (1 << digit);
        }
        return bitMask;
    }

    static String convertStringArrToString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}