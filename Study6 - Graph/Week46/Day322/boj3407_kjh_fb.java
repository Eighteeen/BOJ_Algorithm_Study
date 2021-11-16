// 문제풀이 실패 : 모범답안
// 켱이 방문체크를 빼먹었다고 알려줬는데
// 엥? 방문 체크하라니? 같은 원소 기호 여러 번 방문해야 되지 않나? 이렇게 얕은 생각에서만 그쳤는데
// 방문체크라는게 인덱스 말한거였구나... 같은 인덱스를 하도 여러번 반복하니까 메모리 초과가 난거였구나....
// 함수가 어떻게 호출되는지 디버그할 생각도 안했던 것에 반성합니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
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

    // isPossibleSpeakCheckWord 함수를 사용하기 위한 초기화
    static boolean isPossibleSpeak(String word) {
        checkWord = word;
        visited = new boolean[checkWord.length()];
        return isPossibleSpeakCheckWord(0);
    }

    // 한 글자 일치하면 그렇게도 시도해보고 두 글자도 일치하면 그렇게도 시도해보고
    // 그렇게 끝까지 기호와 일치하면 true 하나라도 일치하지 않으면 false
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