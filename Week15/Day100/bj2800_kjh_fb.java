// 문제풀이 실패 : 모범답안
// 모범답안 해석 실패

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {
    static String baseFormula;
    static int baseFormulaLen;
    static int checkIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        baseFormula = br.readLine();
        baseFormulaLen = baseFormula.length();

        Set<String> formulaSet = canMakeFormulaSet();
        formulaSet.remove(baseFormula);

        List<String> formulaList = new ArrayList<>(formulaSet);
        Collections.sort(formulaList);
        formulaList.forEach(f -> sb.append(f).append('\n'));

        System.out.print(sb);
        br.close();
    }

    static Set<String> canMakeFormulaSet() {
        Set<String> formulaSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while(checkIdx < baseFormulaLen) {
            System.out.println(formulaSet);
            char check = baseFormula.charAt(checkIdx++);

            if (check == ')') break;
            if (check != '(') {
                sb.append(check); // 괄호 안 담아두기
                continue;
            }

            // '(' 만나면
            Set<String> lowerFormulaSet = new HashSet<>();
            // 괄호 안 내용 기반으로 formula들 만듬
            // 그리고 그것들 순회
            for (String formula : canMakeFormulaSet()) {
                // 괄호열리기 전에 
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, sb).toString());
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, '(').insert(0, sb).append(')').toString());
            }

            if (formulaSet.isEmpty()) formulaSet.addAll(lowerFormulaSet);
            else formulaSet = combineStringInSets(formulaSet, lowerFormulaSet);

            sb = new StringBuilder();
        }
        System.out.println(formulaSet);

        if (formulaSet.isEmpty())  formulaSet.add(sb.toString());
        else formulaSet = combineStringSet(formulaSet, sb.toString());

        return formulaSet;
    }

    // Set 맨 뒤에 문자열 일괄추가
    static Set<String> combineStringSet(Set<String> set, String toAdd) {
        Set<String> resultSet = new HashSet<>();
        for (String str : set) {
            resultSet.add(new StringBuilder(str).append(toAdd).toString());
        }
        return resultSet;
    }

    // Set 일괄 잇기
    static Set<String> combineStringInSets(Set<String> firstSet, Set<String> secondSet) {
        Set<String> combineSet = new HashSet<>();
        for (String str1 : firstSet) {
            for (String str2 : secondSet) {
                combineSet.add(new StringBuilder(str1).append(str2).toString());
            }
        }
        return combineSet;
    }
}