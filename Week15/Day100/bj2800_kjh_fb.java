// 문제풀이 실패 : 모범답안
// 한 문자씩 읽으며 처리하게 하는 O(N)의 알고리즘으로 모든 경우의 수를 만들게 한 뒤
// 컬렉션으로 sort

// O(N) 알고리즘을 처음부터 생각해내기 까다로워서
// O(N^2)같은 비효율적인 알고리즘만 시도해보고 벽에 막히는 듯 함

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
        formulaSet.remove(baseFormula); // 자기 자신만 제거

        List<String> formulaList = new ArrayList<>(formulaSet);
        // 정렬해버리기~
        Collections.sort(formulaList);
        formulaList.forEach(f -> sb.append(f).append('\n'));

        System.out.print(sb);
        br.close();
    }

    // (0/(0))
    static Set<String> canMakeFormulaSet() {
        Set<String> formulaSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while(checkIdx < baseFormulaLen) {
            char check = baseFormula.charAt(checkIdx++);

            if (check == ')') break;
            if (check != '(') {
                sb.append(check); // 괄호 안 담아두기
                continue;
            }

            // '(' 만나면
            Set<String> lowerFormulaSet = new HashSet<>();
            // 괄호 안 내용 기반으로 formula들 만듬
            for (String formula : canMakeFormulaSet()) {
                // 괄호 안을 쌩으로 한 것 추가
                // 괄호로 감싼 것 추가
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, sb).toString());
                lowerFormulaSet.add(new StringBuilder(formula).insert(0, '(').insert(0, sb).append(')').toString());
            }

            if (formulaSet.isEmpty()) formulaSet.addAll(lowerFormulaSet);
            // 이전 괄호에 이어 괄호가 또 열린 경우라면 => 합쳐줌
            else formulaSet = combineStringInSets(formulaSet, lowerFormulaSet);

            // 한 괄호 끝나고 다음으로 넘어가므로 괄호 안 내용 비워줌
            sb = new StringBuilder();
        }

        if (formulaSet.isEmpty())  formulaSet.add(sb.toString());
        // 괄호 다음에 일반문자가 있으면 붙여주기
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

    // Set 일괄 짝짓기
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