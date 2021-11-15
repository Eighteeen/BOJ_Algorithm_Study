import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    //// 한 단어와 두 단어 설정을 나눠서 깔끔해보이네여
    static final Set<Character> ONE_LETTER_SYMBOLS = Set.of('h', 'b', 'c', 'n', 'o', 'f', 'p', 's', 'k', 'v', 'y', 'i', 'w', 'u');
    static final Set<String> TWO_LETTER_SYMBOLS = Set.of(
        "ba", "ca" , "ga", "la", "na", "pa", "ra", "ta", "db", "nb", "pb", "rb", "sb", "tb", "yb", "ac",
        "sc", "tc", "cd", "gd", "md", "nd", "pd", "be", "ce", "fe", "ge", "he", "ne", "re", "se", "te",
        "xe", "cf", "hf", "rf", "ag", "hg", "mg", "rg", "sg", "bh", "rh", "th", "bi", "li", "ni", "si",
        "ti", "bk", "al", "cl", "fl", "tl", "am", "cm", "fm", "pm", "sm", "tm", "cn", "in", "mn", "rn",
        "sn", "zn", "co", "ho", "mo", "no", "po", "np", "ar", "br", "cr", "er", "fr", "ir", "kr", "lr",
        "pr", "sr", "zr", "as", "cs", "ds", "es", "hs", "os", "at", "mt", "pt", "au", "cu", "eu", "lu",
        "pu", "ru", "lv", "dy");
    static String checkWord;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String word = br.readLine();
            sb.append(isPossibleSpeak(word) ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isPossibleSpeak(String word) {
        checkWord = word;
        visited = new boolean[checkWord.length()];
        return isPossibleSpeakCheckWord(0);
    }

    //// 깔끔해요,, 
    static boolean isPossibleSpeakCheckWord(int start) {
        if (start >= checkWord.length()) return true;
        if (visited[start]) return false;
        visited[start] = true;
        
        boolean isPossible = false;

        if (ONE_LETTER_SYMBOLS.contains(checkWord.charAt(start))) {
            isPossible |= isPossibleSpeakCheckWord(start + 1);
        }

        if (start + 1 < checkWord.length() && TWO_LETTER_SYMBOLS.contains(checkWord.substring(start, start + 2))) {
            isPossible |= isPossibleSpeakCheckWord(start + 2);
        }

        return isPossible;
    }
}