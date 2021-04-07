// 문제풀이 실패 : 모범답안

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
            char check = baseFormula.charAt(checkIdx++);

            // 괄호 시작하기도 전에 나오거나 괄호 안에 있는 놈은 일단 sb에 담아둠
            if (check == ')') break;
            if (check != '(') {
                sb.append(check);
                continue;
            }

            // ( 를 만나면 
            Set<String> lowerFormulaSet = new HashSet<>();
            for (String formula : canMakeFormulaSet()) {
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, sb).toString());
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, '(').insert(0, sb).append(')').toString());
            }

            if (formulaSet.isEmpty()) formulaSet.addAll(lowerFormulaSet);
            else formulaSet = combineStringInSets(formulaSet, lowerFormulaSet);

            sb = new StringBuilder();
        }

        // formulaSet이 비었으면 담아둔 것 formulaSet에 추가
        if (formulaSet.isEmpty())  formulaSet.add(sb.toString());
        // 안비었으면 
        else formulaSet = combineStringSet(formulaSet, sb.toString());

        return formulaSet;
    }

    static Set<String> combineStringSet(Set<String> set, String toAdd) {
        Set<String> resultSet = new HashSet<>();
        for (String str : set) {
            resultSet.add(new StringBuilder(str).append(toAdd).toString());
        }
        return resultSet;
    }

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