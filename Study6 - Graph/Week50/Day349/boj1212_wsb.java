import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// BigInteger를 쓰지않겠다는 의지 리스펙
//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final String[] binaryStrs = {"0", "1", "10", "11", "100", "101", "110", "111"};
        final String[] binary3digitStrs = {"000", "001", "010", "011", "100", "101", "110", "111"};

        char[] octalChars = br.readLine().toCharArray();
        sb.append(binaryStrs[charToInt(octalChars[0])]);
        for (int i = 1; i < octalChars.length; i++) {
            sb.append(binary3digitStrs[charToInt(octalChars[i])]);
        }

        System.out.println(sb);
        br.close();
    }

    static int charToInt(char numChar) {
        return numChar - '0';
    }
}