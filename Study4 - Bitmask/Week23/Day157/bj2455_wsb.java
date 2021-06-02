import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int trainPeople = 0, maxTrainPeople = 0;
        for (int i = 0; i < 4; i++) {
            String[] trainInfo = br.readLine().split(" ");
            int getOffPeopleNum = Integer.parseInt(trainInfo[0]);
            int getOnPeopleNum = Integer.parseInt(trainInfo[1]);

            trainPeople -= getOffPeopleNum;
            trainPeople += getOnPeopleNum;

            maxTrainPeople = Math.max(maxTrainPeople, trainPeople);
        }

        System.out.println(maxTrainPeople);
        br.close();
    }
}