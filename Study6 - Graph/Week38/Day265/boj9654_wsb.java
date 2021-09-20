import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 :22
class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("SHIP NAME      CLASS          DEPLOYMENT IN SERVICE").append("\n")
            .append("N2 Bomber      Heavy Fighter  Limited    21        ").append("\n")
            .append("J-Type 327     Light Combat   Unlimited  1         ").append("\n")
            .append("NX Cruiser     Medium Fighter Limited    18        ").append("\n")
            .append("N1 Starfighter Medium Fighter Unlimited  25        ").append("\n")
            .append("Royal Cruiser  Light Combat   Limited    4         ").append("\n");

        System.out.print(sb);
    }
}