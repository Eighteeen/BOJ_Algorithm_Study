import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int month = Integer.parseInt(br.readLine());
        int day = Integer.parseInt(br.readLine());

        System.out.println(getStatusForSpecialDay(month, day, 2, 18));
        br.close();
    }

    static String getStatusForSpecialDay(int wonderMonth, int wonderDay, int specialMonth, int specialDay) {
        if (wonderMonth < specialMonth) return "Before";
        if (wonderMonth > specialMonth) return "After";
        
        if (wonderDay < specialDay) return "Before";
        if (wonderDay > specialDay) return "After";

        return "Special";
    }
}