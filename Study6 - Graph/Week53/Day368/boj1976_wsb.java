import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static boolean[][] isPossibleTravelCities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        isPossibleTravelCities = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            int[] citiesInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                isPossibleTravelCities[i][j + 1] = citiesInfo[j] == 1; 
            }
        }

        int[] travelPlanCities = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        setIsPossibleTravelCitiesByFloydWarshall(N);
        System.out.println(isPossibleTravel(travelPlanCities) ? "YES" : "NO");
        br.close();
    }

    static boolean isPossibleTravel(int[] travelPlanCities) {
        for (int i = 1; i < travelPlanCities.length; i++) {
            int fromCity = travelPlanCities[i - 1];
            int toCity = travelPlanCities[i];
            
            if (fromCity == toCity) continue;
            if (!isPossibleTravelCities[fromCity][toCity]) {
                return false;
            }
        }
        return true;
    }

    static void setIsPossibleTravelCitiesByFloydWarshall(int citySize) {
        for (int waypoint = 1; waypoint <= citySize; waypoint++) {
            for (int from = 1; from <= citySize; from++) {
                if (!isPossibleTravelCities[from][waypoint]) continue;
                for (int to = 1; to <= citySize; to++) {
                    if (isPossibleTravelCities[from][to]) continue;
                    boolean isPossibleTravelThroughWayPoint =
                        isPossibleTravelCities[from][waypoint] && isPossibleTravelCities[waypoint][to];
                    if (isPossibleTravelThroughWayPoint) {
                        isPossibleTravelCities[from][to] = true;
                    }
                }
            }
        }
    }
}