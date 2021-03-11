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
        int TC;
        makeBaseTriangle();
        while ((TC = Integer.parseInt(br.readLine())) != 0) {
            makeTriangle(TC);
            printTriangle(TC);
        }

        System.out.print(sb);
        br.close();
    }

    static int verticalOfTriangle(int size) {
        return (int) Math.pow(2, size);
    }

    static void makeTriangle(int size) {
        if (triangles[size] != null) return;

        int prevSize = size - 1;
        makeTriangle(prevSize);

        List<StringBuilder> triangle = triangles[size] = new ArrayList<>();
        List<StringBuilder> prevTriangle = triangles[prevSize];
        int blankNum = verticalOfTriangle(prevSize);
        for (StringBuilder prev : prevTriangle) {
            StringBuilder current = new StringBuilder(" ".repeat(blankNum)).append(prev);
            triangle.add(current);
        }
        for (StringBuilder prev : prevTriangle) {
            StringBuilder current = new StringBuilder(prev).append(" ".repeat(--blankNum)).append(prev);
            triangle.add(current);
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