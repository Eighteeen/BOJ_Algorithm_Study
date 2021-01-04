import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 꽤 어려운 문제임에도 네이밍이 좋아 잘 읽히는 것 같습니다.
//// 깔끔해서 보기 좋았습니다.!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int lenRemoveTopBottom = 2 * N - 3;
        int lenOfMiddle = N - 1;
        int lenOfPattern = N - 2;
        String topBottom, middle, repeatPattern;
        
        topBottom = makeTopBottomOfX(N, lenRemoveTopBottom);
        middle = makeMiddleOfX(lenOfPattern, lenOfMiddle);
        repeatPattern = makeRepeatPatternOfX(lenOfPattern);

        sb.append(topBottom);
        for(int i = 1; i <= lenOfPattern; i++){
            sb.append(" ".repeat(i))
                .append(repeatPattern)
                .append(" ".repeat(2 * (lenOfMiddle - i) - 1))
                .append(repeatPattern)
                .append("\n");
        }
        sb.append(middle);
        for(int i = lenOfPattern; i >= 1; i--){
            sb.append(" ".repeat(i))
                .append(repeatPattern)
                .append(" ".repeat(2 * (lenOfMiddle - i) - 1))
                .append(repeatPattern)
                .append("\n");
        }
        sb.append(topBottom);
        System.out.print(sb);
    }

    static String makeTopBottomOfX(int starLen, int blankLen){
        return "*".repeat(starLen)
            + " ".repeat(blankLen)
            + "*".repeat(starLen)
            + "\n";
    }

    static String makeMiddleOfX(int starLen, int blankLen){
        return " ".repeat(blankLen)
            + ("*" + " ".repeat(starLen)).repeat(2)
            + "*\n";
    }

    static String makeRepeatPatternOfX(int blankLen){
        return "*" + " ".repeat(blankLen) + "*";
    }
}