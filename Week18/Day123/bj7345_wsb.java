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

            String resultBitString = bitStringOfFxModHx(bitStingOfFxMultiplyGx(fx, gx), hx);
            sb.append(resultBitString.length());
            for (char bit : resultBitString.toCharArray()) {
                sb.append(" ").append(bit);
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static String bitStringOfFxModHx(String fx, String hx) {
        if (hx.equals("0")) return fx;
        if (hx.equals("1")) return "0";

        int fxMaxDgree = fx.length() - 1, hxMaxDgree = hx.length()- 1;
        List<Integer> divisionDgreeList = digitListOfBitString(hx);

        boolean[] bitBoolArr = convertBitStringToBoolArr(fx);
        while (fxMaxDgree >= hxMaxDgree) {
            int quotientDgree = fxMaxDgree - hxMaxDgree;
            multiplyBitArr(bitBoolArr, divisionDgreeList, quotientDgree);
            fxMaxDgree = maxDigit(bitBoolArr);
        }

        return convertBitBoolArrToString(bitBoolArr);
    }

    static String bitStingOfFxMultiplyGx(String fx, String gx) {
        List<Integer> fxDgreeList = digitListOfBitString(fx);
        List<Integer> gxDgreeList = digitListOfBitString(gx);

        boolean[] bitBoolArr = new boolean[fx.length() + gx.length() - 1];
        for (int fxDgree : fxDgreeList) {
            multiplyBitArr(bitBoolArr, gxDgreeList, fxDgree);
        }

        return convertBitBoolArrToString(bitBoolArr);
    }

    static void multiplyBitArr(boolean[] bitArr, List<Integer> multiplyDgreeList, int multiplyDgree) {
        for (int dgree : multiplyDgreeList) {
            int calcDgree = dgree + multiplyDgree;
            bitArr[calcDgree] = !bitArr[calcDgree];
        }
    }

    static List<Integer> digitListOfBitString(String bitString) {
        List<Integer> digitList = new ArrayList<>();
        int maxDigit = bitString.length() - 1;
        for (int i = 0; i <= maxDigit; i++) {
            if (bitString.charAt(i) == '1') digitList.add(maxDigit - i);
        }
        return digitList;
    }

    static int maxDigit(boolean[] bitArr) {
        int maxDigit = bitArr.length - 1;
        for (; maxDigit > 0; maxDigit--) {
            if (bitArr[maxDigit]) break;
        }
        return maxDigit;
    }

    static boolean[] convertBitStringToBoolArr(String bitString) {
        int maxDigit = bitString.length() - 1;
        boolean[] bitBoolArr = new boolean[maxDigit + 1];
        for (int i = 0; i <= maxDigit; i++) {
            if (bitString.charAt(i) == '1') bitBoolArr[maxDigit - i] = true;
        }
        return bitBoolArr;
    }

    static String convertBitBoolArrToString(boolean[] bitArr) {
        bitArr = Arrays.copyOf(bitArr, maxDigit(bitArr) + 1);

        StringBuilder sb = new StringBuilder();
        for (boolean bit : bitArr) {
            sb.append(bit ? "1" : "0");
        }
        return sb.reverse().toString();
    }

    static String convertStringArrToString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}