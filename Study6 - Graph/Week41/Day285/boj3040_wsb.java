import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 비트활용 알아가요! 
class Main {
    static final int MINE_DWARFS = 9;
    static final int SNOW_WHITE_DWARFS = 7;
    static final int SNOW_WHITE_DWARF_SUM = 100;

    static int[] dwarfCapNums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dwarfCapNums = new int[MINE_DWARFS];
        for (int i = 0; i < MINE_DWARFS; i++) {
            dwarfCapNums[i] = Integer.parseInt(br.readLine());
        }

        System.out.print(getSnowWhiteDwarfCapNums());
        br.close();
    }

    static String getSnowWhiteDwarfCapNums() {
        StringBuilder sb = new StringBuilder();

        int range = (1 << MINE_DWARFS);
        int start = (1 << SNOW_WHITE_DWARFS) - 1;
        for (int i = start; i < range; i++) {
            if (Integer.bitCount(i) != SNOW_WHITE_DWARFS) continue;

            //// (i & (1 << j)) == 0 이 무엇을 나타내는지 변수로 설정해주었으면 :22
            //// 굳이 해석하지 않아도 이해할 수 있을 것 같아요.
            int sum = 0;
            for (int j = 0; j < MINE_DWARFS; j++) {
                if ((i & (1 << j)) == 0) continue;
                sum += dwarfCapNums[j];
            }

            if (sum == SNOW_WHITE_DWARF_SUM) {
                for (int j = 0; j < MINE_DWARFS; j++) {
                    if ((i & (1 << j)) == 0) continue;
                    sb.append(dwarfCapNums[j])
                        .append("\n");
                }
                break;
            }
        }

        return sb.toString();
    }
}