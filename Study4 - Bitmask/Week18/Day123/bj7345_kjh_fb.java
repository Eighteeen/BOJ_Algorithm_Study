// 문제 풀이 실패 : 모범답안
// 이 모범답안은 정직하게 문제가 원하는 바를 그대로 수행하는 코드로 보임
// 제가 틀린 이유는 수학적으로 머리가 안 돌아가서..

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

            String resultBitString = bitStringOfFxModHx(bitStingOfFxMultiplyGx(fx, gx), hx); // 곱하고 나머지 구함
            sb.append(resultBitString.length()); // 길이
            for (char bit : resultBitString.toCharArray()) {
                sb.append(" ").append(bit); // 각 비트
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    // 나머지
    static String bitStringOfFxModHx(String fx, String hx) {
        if (hx.equals("0")) return fx; // 이 줄은 지워도 잘 동작함. 0으로 나눈 나머지는 존재하지 않음
        if (hx.equals("1")) return "0"; // 정수를 1로 나누면 나눠떨어질 수 밖에 없음. 즉 나머지는 0

        int fxMaxDgree = fx.length() - 1, hxMaxDgree = hx.length() - 1;
        List<Integer> divisionDgreeList = digitListOfBitString(hx);

        boolean[] bitBoolArr = convertBitStringToBoolArr(fx);
        while (fxMaxDgree >= hxMaxDgree) {
            int quotientDgree = fxMaxDgree - hxMaxDgree;
            // 각 비트 몫과 곱하면 1은 0 0은 1로 반전되므로 나눈것과 마찬가지
            multiplyBitArr(bitBoolArr, divisionDgreeList, quotientDgree);
            fxMaxDgree = maxDigit(bitBoolArr);
        }

        // 모든 몫으로 나누고 나면 나머지가 남음
        return convertBitBoolArrToString(bitBoolArr);   
    }

    // 곱
    static String bitStingOfFxMultiplyGx(String fx, String gx) {
        List<Integer> fxDgreeList = digitListOfBitString(fx);
        List<Integer> gxDgreeList = digitListOfBitString(gx);

        boolean[] bitBoolArr = new boolean[fx.length() + gx.length() - 1];
        // 분배법칙처럼 한자리씩 곱함
        for (int fxDgree : fxDgreeList) {
            multiplyBitArr(bitBoolArr, gxDgreeList, fxDgree);
        }

        return convertBitBoolArrToString(bitBoolArr);
    }

    // 여러 자릿수와 딱 한자리수를 곱하는 함수
    // '각 자릿수 인덱스를 서로 더한' 인덱스를 반전시키면 곱한것
    static void multiplyBitArr(boolean[] bitArr, List<Integer> multiplyDgreeList, int multiplyDgree) {
        for (int dgree : multiplyDgreeList) {
            int calcDgree = dgree + multiplyDgree;
            bitArr[calcDgree] = !bitArr[calcDgree];
        }
    }

    // String을 받아 '1이 위치하는 인덱스'들을 담는 List를 반환 (맨 오른쪽을 인덱스 0으로 침)
    static List<Integer> digitListOfBitString(String bitString) {
        List<Integer> digitList = new ArrayList<>();
        int maxDigit = bitString.length() - 1;
        for (int i = 0; i <= maxDigit; i++) {
            if (bitString.charAt(i) == '1') digitList.add(maxDigit - i);
        }
        return digitList;
    }

    // 1이 있는 자리 중 제일 높은 자리를 반환
    static int maxDigit(boolean[] bitArr) {
        int maxDigit = bitArr.length - 1;
        for (; maxDigit > 0; maxDigit--) {
            if (bitArr[maxDigit]) break;
        }
        return maxDigit;
    }


    // String을 받아 boolean[]로 반환 (맨 오른쪽을 첫 원소로 침)
    static boolean[] convertBitStringToBoolArr(String bitString) {
        int maxDigit = bitString.length() - 1;
        boolean[] bitBoolArr = new boolean[maxDigit + 1];
        for (int i = 0; i <= maxDigit; i++) {
            if (bitString.charAt(i) == '1') bitBoolArr[maxDigit - i] = true;
        }
        return bitBoolArr;
    }

    // 위 함수의 역과정
    static String convertBitBoolArrToString(boolean[] bitArr) {
        bitArr = Arrays.copyOf(bitArr, maxDigit(bitArr) + 1);

        StringBuilder sb = new StringBuilder();
        for (boolean bit : bitArr) {
            sb.append(bit ? "1" : "0");
        }
        return sb.reverse().toString();
    }

    // 띄어쓰기로 떨어진 것 붙여주기
    static String convertStringArrToString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
