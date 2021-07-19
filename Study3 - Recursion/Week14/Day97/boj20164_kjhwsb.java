import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int minOddResult = 1000000, maxOddResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String numStr = br.readLine();
        divideAdd(numStr, 0);

        sb.append(minOddResult).append(" ").append(maxOddResult);
        System.out.println(sb);
        br.close();
    }

    static void divideAdd(String numStr, int cntOdd) {
        cntOdd += getCntOdd(numStr);
        int len = numStr.length();

        if (len == 1) {
            updateMinMax(cntOdd);
            return;
        }

        if (len == 2) {
            divideAdd(sumOfNumChar(numStr.charAt(0), numStr.charAt(1)), cntOdd);
        }
        
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                String[] dividedArr = new String[] {
                    numStr.substring(0, i),
                    numStr.substring(i, j),
                    numStr.substring(j, len)
                };
                divideAdd(sumOfNumStr(dividedArr), cntOdd);
            }
        }
    }

    static int getCntOdd(String numStr) {
        int cntOdd = 0;
        for (char numChar : numStr.toCharArray()) {
            if ((numChar - '0') % 2 == 1) cntOdd++;
        }
        return cntOdd;
    }

    static String sumOfNumChar(char numChar1, char numChar2) {
        int sum = numChar1 + numChar2 - '0' * 2;
        return String.valueOf(sum);
    }

    static String sumOfNumStr(String[] numStrArr) {
        int sum = 0;
        for (String numStr : numStrArr) {
            sum += Integer.parseInt(trimZeroFront(numStr));
        }
        return String.valueOf(sum);
    }

    static String trimZeroFront(String numStr) {
        int beginIdx = 0;
        for (; beginIdx< numStr.length(); beginIdx++) {
            if (numStr.charAt(beginIdx) != '0') break;
        }

        if (beginIdx== numStr.length()) return "0";
        return numStr.substring(beginIdx, numStr.length());
    }

    static void updateMinMax(int result) {
        minOddResult = Math.min(minOddResult, result);
        maxOddResult = Math.max(maxOddResult, result);
    }
}