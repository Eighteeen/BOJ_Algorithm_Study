import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼쓰
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] photoPixels;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        String[] pixelInfo = br.readLine().split(" ");
        for (int i  = 0; i < 2; i++) br.readLine();

        int pixelSize = Integer.parseInt(pixelInfo[2]);
        makePixels(pixelSize);
        sb.append(pixelSize).append("\n")
            .append(encodingToQuadtree(pixelSize, 0, 0)).append("\n");

        System.out.print(sb);
        br.close();
    }

    static void makePixels(int size) throws IOException {
        String[] pixelInfo;
        int convertedSize = size / 8;
        photoPixels = new boolean[size][size];
        
        for (int i = 0; i < size; i++) {
            //// 입력은 main에서 받아 매개변수로 보내는게 더 활용성 좋았을 것 같아요!
            pixelInfo = br.readLine().split(",");
            for (int j = 0; j < convertedSize; j++) {
                makePixels(convertHexToBinary(pixelInfo[j]), i, j * 8);
            }
        }
    }

    static void makePixels(String binaryString, int row, int col) {
        for (char b : binaryString.toCharArray()) {
            photoPixels[row][col++] = b == '1';
        }
    }

    static String encodingToQuadtree(int size, int row, int col) {
        if (isSamePixels(size, row, col)) return (photoPixels[row][col] ? "B" : "W");

        StringBuilder sb = new StringBuilder();
        int half = size / 2;
        sb.append("Q")
            .append(encodingToQuadtree(half, row, col))
            .append(encodingToQuadtree(half, row, col + half))
            .append(encodingToQuadtree(half, row + half, col))
            .append(encodingToQuadtree(half, row + half, col + half));

        return sb.toString();
    }

    static String convertHexToBinary(String hexString) {
        int hexNum = Integer.parseInt(hexString.substring(2), 16);
        String binary = Integer.toBinaryString(hexNum);
        StringBuilder sb = new StringBuilder("0".repeat(8 - binary.length())).append(binary);
        return sb.reverse().toString();
    }

    static boolean isSamePixels(int size, int row, int col) {
        boolean standardPixel = photoPixels[row][col];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (standardPixel != photoPixels[row][col++]) return false;
            }
            row++;
            col -= size;
        }
        return true;
    }
}