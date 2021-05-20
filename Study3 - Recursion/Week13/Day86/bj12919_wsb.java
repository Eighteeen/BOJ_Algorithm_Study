import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int toMakeLen;
    static String toMake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        toMake = br.readLine();
        toMakeLen = toMake.length();

        System.out.println(canMake(S, '\u0000') ? 1 : 0);
        br.close();
    }

    //// StringBuilder 사용하는게 여러면에서 좋네용
    static boolean canMake(String material, char toAdd) {
        String reverseMaterial = new StringBuilder(material).reverse().toString();
        if (!toMake.contains(material) && !toMake.contains(reverseMaterial)) return false;
        if (toMakeLen == material.length()) {
            return toMake.equals(material);
        }

        StringBuilder process = new StringBuilder(material);
        if (toAdd != '\u0000') process.append(toAdd);
        if (toAdd == 'B') process.reverse();

        return canMake(process.toString(), 'A') || canMake(process.toString(), 'B');
    }
}