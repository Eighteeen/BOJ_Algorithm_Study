import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 효율적입니다!
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<StringBuilder> starTriangle = makeStarTriangle(N);
        starTriangle.forEach(s -> sb.append(s).append("\n"));

        System.out.print(sb);
        br.close();
    }

    static List<StringBuilder> makeStarTriangle(int size) {
        //// size가 1, 2로 들어올 가능성은 없지아너요?
        //// -> 넵 메인 프로그램 상으로만 본다면 그럴 가능성은 없지만, 현재 함수 자체로 봤을 때는 3이하의 수는 신경쓰지 않아도 된다는 느낌으로 작성했습니다!
        if (size <= 3) return makeBaseStarTriangle();

        int prevSize = size / 2;
        //// 재귀활용 굳
        List<StringBuilder> prevStarTriangle = makeStarTriangle(prevSize);
        List<StringBuilder> starTriangle = new ArrayList<>();

        String repeatBlank = " ".repeat(prevSize);
        for (StringBuilder line : prevStarTriangle) {
            String copyLine = line.toString();
            StringBuilder newLine = new StringBuilder(repeatBlank).append(copyLine).append(repeatBlank);
            starTriangle.add(newLine);
            line.append(" ").append(copyLine);
        }
        starTriangle.addAll(prevStarTriangle);

        return starTriangle;
    }

    static List<StringBuilder> makeBaseStarTriangle() {
        List<StringBuilder> baseTriangle = new ArrayList<>();
        StringBuilder baseStar;

        baseStar = new StringBuilder("  *  ");
        baseTriangle.add(baseStar);
        baseStar = new StringBuilder(" * * ");
        baseTriangle.add(baseStar);
        baseStar = new StringBuilder("*****");
        baseTriangle.add(baseStar);

        return baseTriangle;
    }
}