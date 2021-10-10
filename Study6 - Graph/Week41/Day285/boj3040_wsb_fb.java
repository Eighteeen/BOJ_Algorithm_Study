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
            //// -> 비트 조합 활용 시에는 항상 이렇게 써서 변수로 설정할 생각을 못 했네요.
                //// 변수 설정을 하면서 적절하게 연산도 살짝 변경하였습니다.
            int sum = 0;
            for (int j = 0; j < MINE_DWARFS; j++) {
                boolean isCombinationElement = (i & (1 << j)) != 0;
                if (isCombinationElement) {
                    sum += dwarfCapNums[j];
                }
            }

            if (sum == SNOW_WHITE_DWARF_SUM) {
                for (int j = 0; j < MINE_DWARFS; j++) {
                    boolean isCombinationElement = (i & (1 << j)) != 0;
                    if (isCombinationElement) {
                        sb.append(dwarfCapNums[j])
                            .append("\n");
                    }
                }
                break;
            }
        }

        return sb.toString();
    }
}