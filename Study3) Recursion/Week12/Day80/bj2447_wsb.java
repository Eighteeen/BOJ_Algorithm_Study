import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 깔꼼효율쓰
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<String> starWindow = makeStarWindow(N);
        starWindow.forEach(w -> sb.append(w).append("\n"));

        System.out.print(sb);
        br.close();
    }

    static List<String> makeStarWindow(int size) {
        if (size < 3) return makeBaseStarWindow();

        int prevSize = size / 3;
        List<String> prevWindow = makeStarWindow(prevSize);
        List<String> window = new ArrayList<>();

        int cntLine = 0;
        String hole = " ".repeat(prevSize);
        for (String line : prevWindow) {
            StringBuilder newLine = new StringBuilder(line).append(hole).append(line);
            String frame = line.repeat(3);
            prevWindow.set(cntLine, frame);
            window.add(cntLine++, frame);
            window.add(newLine.toString());
        }
        window.addAll(prevWindow);

        return window;
    }

    static List<String> makeBaseStarWindow() {
        List<String> baseWindow = new ArrayList<>();
        baseWindow.add("*");
        return baseWindow;
    }
}