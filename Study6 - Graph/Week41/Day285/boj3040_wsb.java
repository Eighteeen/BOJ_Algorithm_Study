import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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