import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        if(N > 1){
            for(int i = N; i > 2; i--){
                sb.append(msgBottle(i));
            }
            sb.append(msgTwoBottles());
        }
        sb.append(msgOneBottle(N));

        System.out.print(sb);
    }

    static String msgBottle(int K){
        String msg = K + " bottles of beer on the wall, " + K + " bottles of beer.\n"
            + "Take one down and pass it around, " + (K - 1) + " bottles of beer on the wall.\n\n";
        return msg;
    }

    static String msgTwoBottles(){
        String msg = "2 bottles of beer on the wall, 2 bottles of beer.\n"
            + "Take one down and pass it around, 1 bottle of beer on the wall.\n\n";
        return msg;
    }

    static String msgOneBottle(int K){
        String msg = "1 bottle of beer on the wall, 1 bottle of beer.\n"
            + "Take one down and pass it around, no more bottles of beer on the wall.\n"
            + "\n"
            + "No more bottles of beer on the wall, no more bottles of beer.\n"
            + "Go to the store and buy some more, ";

        if(K > 1){
            msg += K + " bottles of beer on the wall.";
        }else{
            msg += "1 bottle of beer on the wall.";
        }
        return msg;
    }
}