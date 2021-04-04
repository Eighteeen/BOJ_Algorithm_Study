import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static float baseLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] input = br.readLine().split(" ");
        String baseCode = input[0];
        baseLen = baseCode.length();
        long wantNth = Long.parseLong(input[1]);

        System.out.println(baseCode.charAt(nthLetterInCode(wantNth) - 1));
        br.close();
    }

    static int nthLetterInCode(long nthLetter) {
        if (nthLetter <= baseLen) return (int) nthLetter;
        
        long nthRotated = nthRotated(nthLetter);
        if (nthLetter == nthRotated) return nthLetterInCode(nthLetter - 1);
        return nthLetterInCode(nthLetter - nthRotated);
    }

    static long nthRotated(long nthLetter) {
        return (long) (Math.pow(2, repetition(nthLetter) - 1) * baseLen) + 1;
    }

    static int repetition(long nthLetter) {
        return (int) Math.ceil(Math.log(nthLetter / baseLen) / Math.log(2));
    }
}