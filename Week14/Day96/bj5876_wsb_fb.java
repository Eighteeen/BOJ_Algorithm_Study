import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        char[][] horseshoes = new char[N][];
        for (int i = 0; i < N; i++) horseshoes[i] = br.readLine().toCharArray();

        Balance horseshoeBalance = new Balance(horseshoes, '(', ')');
        System.out.println(horseshoeBalance.maxPerfectlyBalanceLen(0, 0));

        br.close();
    }
}

class Balance {
    private char[][] materials;
    private boolean[][] isCheckMaterials;
    private int materialSize;
    private char openMaterial;
    private char closeMaterial;

    public Balance(char[][] materials, char openMaterial, char closeMaterial) {
        this.materials = materials;
        this.materialSize = materials.length;
        this.openMaterial = openMaterial;
        this.closeMaterial = closeMaterial;
    }

    public int maxPerfectlyBalanceLen(int startRow, int startCol) {
        isCheckMaterials = new boolean[materialSize][materialSize];
        return maxPerfectlyBalanceLen(startRow, startCol, 0, 0);
    }

    private int maxPerfectlyBalanceLen(int row, int col, int checkLen, int cntOpen) {
        if (row < 0 || col < 0 || row == materialSize || col == materialSize) return 0;
        if (isCheckMaterials[row][col]) return 0;

        if (materials[row][col] == openMaterial) {
            if (cntOpen < checkLen) return 0;
            cntOpen++;
        } else {
            if (cntOpen == 0) return 0;
            cntOpen--;
        }
        checkLen++;

        if (cntOpen == 0) return checkLen;

        isCheckMaterials[row][col] = true;
        //// 바로 57줄 내용 대입해도 무방합니다!
        //// -> 그렇네요 반영했어요!
        int maxLen = maxPerfectlyBalanceLen(row - 1, col, checkLen, cntOpen);
        maxLen = Math.max(maxLen, maxPerfectlyBalanceLen(row + 1, col, checkLen, cntOpen));
        maxLen = Math.max(maxLen, maxPerfectlyBalanceLen(row, col - 1, checkLen, cntOpen));
        maxLen = Math.max(maxLen, maxPerfectlyBalanceLen(row, col + 1, checkLen, cntOpen));
        isCheckMaterials[row][col] = false;
        return maxLen;
    }
}