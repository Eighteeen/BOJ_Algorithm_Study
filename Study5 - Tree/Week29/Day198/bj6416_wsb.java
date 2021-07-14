import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCnt = 0;
        while (true) {
            String input;
            boolean isFinishLine = false;
            List<String> edgeList = new ArrayList<>();

            while (true) {
                input = br.readLine();
                if (input.isEmpty()) continue;
                if (isFinishLine = input.contains("-")) break;

                edgeList.addAll(Arrays.stream(input.split("  ")).collect(Collectors.toList()));
                if (edgeList.contains("0 0")) {
                    edgeList.remove("0 0");
                    break;
                }
            }

            if (isFinishLine) break;

            testCnt++;
            if (isTree(edgeList)) sb.append(String.format("Case %d is a tree.\n", testCnt));
            else sb.append(String.format("Case %d is not a tree.\n", testCnt));
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isTree(List<String> edgeList) {
        if (edgeList.isEmpty()) return true;

        Map<Integer, List<Integer>> parentMap = new HashMap<>();
        Set<Integer> childSet = new HashSet<>();

        for (String edge : edgeList) {
            String[] nodes = edge.split(" ");
            int parentData = Integer.parseInt(nodes[0]);
            int childData = Integer.parseInt(nodes[1]);

            setParentMap(parentMap, parentData, childData);
            if (childSet.contains(childData)) return false;
            childSet.add(childData);
        }

        Set<Integer> nodeSet = new HashSet<>(parentMap.keySet());

        nodeSet.addAll(childSet);
        if ((nodeSet.size() - 1) != edgeList.size()) return false;

        nodeSet.removeAll(childSet);
        if (nodeSet.size() != 1) return false;

        if (isLoop(parentMap)) return false;

        return true;
    }

    static boolean isLoop(Map<Integer, List<Integer>> parentMap) {
        for (Integer parentData : parentMap.keySet()) {
            List<Integer> descendantList = getDescendantList(parentMap, parentData, parentData);
            if (descendantList.contains(parentData)) return true;
        }

        return false;
    }

    static List<Integer> getDescendantList(Map<Integer, List<Integer>> parentMap, int ancestorData,int parentData) {
        List<Integer> descendantList = new ArrayList<>();

        List<Integer> childList = parentMap.get(parentData);
        if (childList == null) return descendantList;
        descendantList.addAll(childList);

        for (int childData : childList) {
            if (childData == ancestorData) return descendantList;
            descendantList.addAll(getDescendantList(parentMap, ancestorData, childData));
        }

        return descendantList.stream().distinct().collect(Collectors.toList());
    }

    static void setParentMap(Map<Integer, List<Integer>> parentMap, int parentData, int childData) {
        if (parentMap.containsKey(parentData)) {
            parentMap.get(parentData).add(childData);
            return;
        }

        List<Integer> childList = new ArrayList<>();
        childList.add(childData);
        parentMap.put(parentData, childList);
    }
}