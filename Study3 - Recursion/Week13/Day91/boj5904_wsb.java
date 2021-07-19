import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;

    //// 굳굳 여러모로 더 효율적이네요
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        System.out.println(isMOfMooGame(0, 0, N) ? "m" : "o");

        br.close();
    }

    //// NthLetter를 객체처럼 대문자 시작으로 이름 지은 이유가 있나요?
    static boolean isMOfMooGame(int seqOrder, int sideLen, int NthLetter) {
        if (NthLetter < sideLen) return isMOfMooGame(seqOrder - 1, getPrevSideLen(seqOrder, sideLen, 2), NthLetter);

        NthLetter -= sideLen;
        if (NthLetter == 1) return true;
        int mooLen = seqOrder + 3;
        if (NthLetter <= mooLen) return false;

        NthLetter -= mooLen;
        if (NthLetter > sideLen) return isMOfMooGame(seqOrder + 1, getNextSideLen(sideLen, mooLen), N);
        return isMOfMooGame(seqOrder - 1, getPrevSideLen(seqOrder, sideLen, 2), NthLetter);
    }

    static int getNextSideLen(int sideLen, int middleLen) {
        return sideLen * 2 + middleLen;
    }

    //// orderDifference는 항상 2로 쓰여서 매개변수로 받지 않아도 될 것 같아요
    static int getPrevSideLen(int order, int sideLen, int orderDifference) {
        return (sideLen - order - orderDifference) / 2;
    }
}