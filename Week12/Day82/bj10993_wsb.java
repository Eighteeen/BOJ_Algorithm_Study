import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<StringBuilder> starTriangleMark = makeStarTriangleMark(N);
        starTriangleMark.forEach(s -> sb.append(s).append("\n"));

        System.out.print(sb);
        br.close();
    }

    static List<StringBuilder> makeStarTriangleMark(int size) {
        if (size == 1) return makeBaseStarList();

        List<StringBuilder> prevMark = makeStarTriangleMark(size - 1);
        if (size % 2 == 0) return makeStarInvertedTriangleOutLine(prevMark);
        else return makeStarTriangleOutLine(prevMark);
    }

    static List<StringBuilder> makeBaseStarList() {
        List<StringBuilder> base = new ArrayList<>();
        base.add(new StringBuilder("*"));
        return base;
    }

    static List<StringBuilder> makeStarTriangleOutLine(List<StringBuilder> inlineList) {
        int inlineHeight = inlineList.size();
        List<StringBuilder> resultList = makeStarHat(inlineHeight, inlineHeight);

        int midBlankNum = 0;
        for (StringBuilder line : inlineList) {
            StringBuilder front = new StringBuilder();
            front.append(makeStarAfterBlank(inlineHeight--, 1))
                .append(" ".repeat(midBlankNum));
            line.insert(0, front);
            line.append(makeStarAfterBlank(2 * midBlankNum++, 1));
        }
        resultList.addAll(inlineList);
        resultList.add(new StringBuilder("*".repeat(resultList.size() * 2 + 1)));

        return resultList;
    }

    static List<StringBuilder> makeStarInvertedTriangleOutLine(List<StringBuilder> inlineList) {
        Collections.reverse(inlineList);
        List<StringBuilder> resultList = makeStarTriangleOutLine(inlineList);
        Collections.reverse(resultList);
        return resultList;
    }

    static List<StringBuilder> makeStarHat(int size, int frontBlankNum) {
        List<StringBuilder> starHat = new ArrayList<>();
        String frontBlank = " ".repeat(frontBlankNum);
        StringBuilder vertex = new StringBuilder();
        vertex.append(frontBlank)
            .append(makeStarAfterBlank(size--, 1));
        starHat.add(vertex);

        int midBlankNum = -1;
        for (int i = size; i > 0; i--) {
            StringBuilder line = new StringBuilder();
            line.append(frontBlank)
                .append(makeStarAfterBlank(i, 1))
                .append(makeStarAfterBlank(midBlankNum += 2, 1));
            starHat.add(line);
        }

        return starHat;
    }

    static String makeStarAfterBlank(int blankSize, int starSize) {
        StringBuilder result = new StringBuilder();
        result.append(" ".repeat(blankSize))
            .append("*".repeat(starSize));
        return result.toString();
    }
}