import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 전체적으로 코드가 깔끔해서 좋았습니다
//// Math Math Math가 난무해서 제 머리로는 어떤 원리로 돌아가는지는 이해할 수 없었읍니다..
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

    //// 1조번째의 글자(nthLetter)를 1조번 돌린 것?(nthRotated)
    //// 어떤걸 의도하는 내용인지 알기 어려워요 ㅠㅠ
    static long nthRotated(long nthLetter) {
        return (long) (Math.pow(2, repetition(nthLetter) - 1) * baseLen) + 1; //// 누구보다 수학을 사랑하시는 승빈씨..
    }

    //// nthLetter를 어떻게 반복(repetition)하는거고 왜 int로 반환되는지 이름만으로 알기 어려운 것 같아요
    static int repetition(long nthLetter) {
        return (int) Math.ceil(Math.log(nthLetter / baseLen) / Math.log(2));
    }
}