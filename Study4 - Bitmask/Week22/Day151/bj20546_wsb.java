import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

////깔끔해요:-D
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int foundationMoney = Integer.parseInt(br.readLine());
        int[] stockPrices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int BNPAssets = getAssetsAfterBNPWay(foundationMoney, stockPrices);
        int TimingAssets = getAssetsAfterTimingWay(foundationMoney, stockPrices);

        String result = "SAMESAME";
        if (BNPAssets < TimingAssets) result = "TIMING";
        else if (BNPAssets > TimingAssets) result = "BNP";

        System.out.println(result);
        br.close();
    }

    static int getAssetsAfterBNPWay(int foundationMoney, int[] stockPrices) {
        int numOfStock = 0;
        for (int stockPrice : stockPrices) {
            int possibleNumOfStock = getNumOfPossibleNumOfStock(foundationMoney, stockPrice);
            foundationMoney -= stockPrice * possibleNumOfStock;
            numOfStock += possibleNumOfStock;
        }

        return getAssets(foundationMoney, stockPrices[stockPrices.length - 1], numOfStock);
    }

    static int getAssetsAfterTimingWay(int foundationMoney, int[] stockPrices) {
        int numOfStock = 0;
        int stockPriceOfEveDay = stockPrices[0];
        int gainDays = 0, sinkDays = 0;

        for (int stockPrice : stockPrices) {
            if (stockPriceOfEveDay < stockPrice) {
                gainDays++;
                sinkDays = 0;
            } else if (stockPriceOfEveDay > stockPrice) {
                sinkDays++;
                gainDays = 0;
            }

            if (gainDays > 2) {
                foundationMoney += stockPrice * numOfStock;
                numOfStock = 0;
            }
            if (sinkDays > 2) {
                int possibleNumOfStock = getNumOfPossibleNumOfStock(foundationMoney, stockPrice);
                foundationMoney -= stockPrice * possibleNumOfStock;
                numOfStock += possibleNumOfStock;
            }
            
            stockPriceOfEveDay = stockPrice;
        }

        return getAssets(foundationMoney, stockPrices[stockPrices.length - 1], numOfStock);
    }

    static int getNumOfPossibleNumOfStock(int foundationMoney, int stockPrice) {
        return foundationMoney / stockPrice;
    }

    static int getAssets(int money, int finalStockPrice, int numOfStock) {
        return money + finalStockPrice * numOfStock;
    }
}