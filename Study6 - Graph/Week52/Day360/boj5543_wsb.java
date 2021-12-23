import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int minPriceOfBurger = Integer.MAX_VALUE;
        int minPriceOfDrink = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            int priceOfBurger = Integer.parseInt(br.readLine());
            minPriceOfBurger = Math.min(minPriceOfBurger, priceOfBurger);
        }
        for (int i = 0; i < 2; i++) {
            int priceOfDrink = Integer.parseInt(br.readLine());
            minPriceOfDrink = Math.min(minPriceOfDrink, priceOfDrink);
        }

        System.out.println(minPriceOfBurger + minPriceOfDrink - 50);
        br.close();
    }
}