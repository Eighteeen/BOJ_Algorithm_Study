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
    //// -> 대문자로 안 하면 nTh로 해야한다고 생각해서 너무 어색해보여 Nth로 작명했는데, 다시 생각해보니 그냥 nth로 많이 쓰네요 nth로 변경했어요!
    static boolean isMOfMooGame(int seqOrder, int sideLen, int nthLetter) {
        if (NthLetter < sideLen) return isMOfMooGame(seqOrder - 1, getPrevSideLen(seqOrder, sideLen, 2), nthLetter);

        nthLetter -= sideLen;
        if (nthLetter == 1) return true;
        int mooLen = seqOrder + 3;
        if (NthLetter <= mooLen) return false;

        nthLetter -= mooLen;
        if (nthLetter > sideLen) return isMOfMooGame(seqOrder + 1, getNextSideLen(sideLen, mooLen), N);
        return isMOfMooGame(seqOrder - 1, getPrevSideLen(seqOrder, sideLen, 2), nthLetter);
    }

    static int getNextSideLen(int sideLen, int middleLen) {
        return sideLen * 2 + middleLen;
    }

    //// orderDifference는 항상 2로 쓰여서 매개변수로 받지 않아도 될 것 같아요
    //// -> 꼭 해당 문제 뿐만 아니라 양쪽 사이드의 규칙성이 있는 규칙 문제에 쓰일 수 있는 함수라고 생각해서 매개함수로 받았기 때문에 유지하겠습니다!
    static int getPrevSideLen(int order, int sideLen, int orderDifference) {
        return (sideLen - order - orderDifference) / 2;
    }
}