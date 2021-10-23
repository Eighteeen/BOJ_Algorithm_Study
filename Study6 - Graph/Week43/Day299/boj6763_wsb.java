import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 까알끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int speedLimit = Integer.parseInt(br.readLine());
        int recordedSpeed = Integer.parseInt(br.readLine());

        if (speedLimit >= recordedSpeed) {
            System.out.println("Congratulations, you are within the speed limit!");
        } else if (speedLimit + 20 >= recordedSpeed) {
            System.out.println("You are speeding and your fine is $100.");
        } else if (speedLimit + 30 >= recordedSpeed) {
            System.out.println("You are speeding and your fine is $270.");
        } else {
            System.out.println("You are speeding and your fine is $500.");
        }

        br.close();
    }
}