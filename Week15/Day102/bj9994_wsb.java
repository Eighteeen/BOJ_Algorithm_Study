import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String encryptedStr = br.readLine();
        System.out.println(howManyProduced(encryptedStr));

        br.close();
    }

    static int howManyProduced(String encryptedStr) {
        int len = encryptedStr.length();
        if (len % 2 == 0) return 0;

        int overlapLen = (len - 1) /2;
        int sumProduced = 0;

        for (int i = 0; i < len; i += overlapLen + 1) {
            String overlapStr = encryptedStr.substring(i, i + overlapLen);
            String beforeEncryptedStr;
            if (i == 0) beforeEncryptedStr = encryptedStr.substring(i + overlapLen, len);
            else beforeEncryptedStr = encryptedStr.substring(0, i);
            
            for (int j = 0; j < 2; j++) {
                String checkStr = beforeEncryptedStr.substring(j, j + overlapLen);
                if (checkStr.equals(overlapStr)) sumProduced += howManyProduced(beforeEncryptedStr) + 1;
            }
        }

        return sumProduced;
    }
}