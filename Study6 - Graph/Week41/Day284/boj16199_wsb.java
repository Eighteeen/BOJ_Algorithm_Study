import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] birthdayInfo = br.readLine().split(" ");
        String[] standardDateInfo = br.readLine().split(" ");
        
        int birthdayYear = Integer.parseInt(birthdayInfo[0]);
        int birthdayMonth = Integer.parseInt(birthdayInfo[1]);
        int birthdayDay = Integer.parseInt(birthdayInfo[2]);

        int standardYear = Integer.parseInt(standardDateInfo[0]);
        int standardMonth = Integer.parseInt(standardDateInfo[1]);
        int standardDay = Integer.parseInt(standardDateInfo[2]);

        int yearAge = standardYear - birthdayYear;
        int countingAge = yearAge + 1;
        int onlyAge = yearAge - 1;
        if (standardMonth > birthdayMonth
            || (standardMonth == birthdayMonth && standardDay >= birthdayDay)) {
            onlyAge++;
        }

        sb.append(onlyAge).append("\n")
            .append(countingAge).append("\n")
            .append(yearAge).append("\n");

        System.out.print(sb);
        br.close();
    }
}