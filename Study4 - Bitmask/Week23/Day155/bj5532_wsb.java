import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼~
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vacationDays = Integer.parseInt(br.readLine());
        int mathTotalPage = Integer.parseInt(br.readLine());
        int languageTotalPage = Integer.parseInt(br.readLine());
        int mathPagePerDay = Integer.parseInt(br.readLine());
        int languagePagePerDay = Integer.parseInt(br.readLine());

        int mathTakeDays = getTakeDaysForTask(mathTotalPage, mathPagePerDay);
        int languageTakeDays = getTakeDaysForTask(languageTotalPage, languagePagePerDay);

        System.out.println(vacationDays - Math.max(mathTakeDays, languageTakeDays));
        br.close();
    }

    static int getTakeDaysForTask(int totalTask, int possibleTaskPerDay) {
        int takeDays = totalTask / possibleTaskPerDay;
        if (totalTask % possibleTaskPerDay != 0) takeDays++;
        return takeDays;
    }
}