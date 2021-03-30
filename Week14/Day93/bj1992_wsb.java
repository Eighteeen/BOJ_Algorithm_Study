import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static boolean[][] videoPixels;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        videoPixels = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            makePixels(videoPixels[i], N, br.readLine());
        }

        System.out.println(encodingToQuadtree(N, 0, 0));
        br.close();
    }

    static void makePixels(boolean[] pixels, int pixelSize, String pixelInfo) {
        for (int i = 0; i < pixelSize; i++) {
            if (pixelInfo.charAt(i) == '1') pixels[i] = true;
        }
    }

    static String encodingToQuadtree(int size, int row, int col) {
        if (isSamePixels(size, row, col)) return (videoPixels[row][col] ? "1" : "0");

        StringBuilder sb = new StringBuilder();
        int half = size / 2;
        sb.append("(")
            .append(encodingToQuadtree(half, row, col))
            .append(encodingToQuadtree(half, row, col + half))
            .append(encodingToQuadtree(half, row + half, col))
            .append(encodingToQuadtree(half, row + half, col + half))
            .append(")");
        return sb.toString();
    }

    static boolean isSamePixels(int size, int row, int col) {
        if (size == 1) return true;
        
        boolean standardPixel = videoPixels[row][col];
        int rowLast = row + size, colLast = col + size;

        for (int i = row; i < rowLast; i++) {
            for (int j = col; j < colLast; j++) {
                if (standardPixel != videoPixels[i][j]) return false;
            }
        }

        return true;
    }
}