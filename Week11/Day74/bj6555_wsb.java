import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static StringBuilder sb;
    static final int MAX_SIZE = 10, BASE_SIZE = 1;
    static List<StringBuilder>[] triangles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        triangles = new ArrayList[MAX_SIZE + 1];
        makeBaseTriangle();
        int TC;
        while ((TC = Integer.parseInt(br.readLine())) != 0) {
            makeTriangle(TC);
            printTriangle(TC);
        }

        System.out.print(sb);
        br.close();
    }

    static void makeTriangle(int size) {
        if (triangles[size] != null) return;

        int prevSize = size - 1;
        makeTriangle(prevSize);

        List<StringBuilder> triangle = triangles[size] = new ArrayList<>();
        List<StringBuilder> prevTriangle = triangles[prevSize];
        int cntLine = 0;
        int frontBlankNum, midBlankNum;
        frontBlankNum = midBlankNum = prevTriangle.size();
        for (StringBuilder line : prevTriangle) {
            StringBuilder top = new StringBuilder(" ".repeat(frontBlankNum)).append(line);
            StringBuilder bottom = new StringBuilder(line).append(" ".repeat(--midBlankNum)).append(line);
            triangle.add(cntLine++, top);
            triangle.add(bottom);
        }
    }

    static void makeBaseTriangle() {
        triangles[BASE_SIZE] = new ArrayList<>();
        triangles[BASE_SIZE].add(new StringBuilder(" /\\"));
        triangles[BASE_SIZE].add(new StringBuilder("/__\\"));
    }

    static void printTriangle(int size) {
        triangles[size].forEach(s -> sb.append(s).append("\n"));
        sb.append("\n");
    }
}