import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        if (size <= 3) return makeBaseStarTriangle();

        int prevSize = size / 2;
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