import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        IslandCountry islandCountry = new IslandCountry(N);
        for (int i = 2; i <= N; i++) {
            String[] islandInfo = br.readLine().split(" ");
            boolean t = islandInfo[0].charAt(0) == 'S';
            int a = Integer.parseInt(islandInfo[1]);
            int p = Integer.parseInt(islandInfo[2]);

            islandCountry.setIslands(p, i, a);
            islandCountry.setIsland(i, t);
        }

        //// Specific이라는 단어를 어떤 의도로 쓰신건지 잘 이해가 안됐어요. 일단 저는 특정한, 명확한 이런 뜻으로 알고있거든요
        ///// 어.. 명확한 값, 특정한 값이 뭘 말하는걸까?
        //// -> 추상화를 통해서 여러 문제에서도 활용할 수 있는 클래스를 만들고 싶었어요. (내부적으로 계속 바꿔야 하는 것을 줄이고 싶었습니다.)
            //// 근데 확실히 너무 추상화를 하다보니 문제마다에서 이해하기 힘들어지는 부분이 있는 것 같습니다.
            //// 추상화한 클래스는 그대로 두고, 상속을 통해 문제마다에 특화된 클래스로 구현하여 명확하게 나타내봤습니다.
            //// 저도 추상화는 되었지만 문제에서의 특정한 변수명이나 함수명이 조금 아쉬웠는데, 피드백을 통해 더 고민하면서 상속을 적용할 수 있었습니다. 피드백 감사해요!
        System.out.println(islandCountry.getCanSaveNumOfSheep(1));
        br.close();
    }
}

class IslandCountry extends Tree {
    public IslandCountry(int size) {
        super(size);
    }

    public void setIslands(int fromIsland, int toIsland, int liveNumOfToIsland) {
        super.setNodes(fromIsland, toIsland, liveNumOfToIsland);
    }

    public void setIsland(int islandNum, boolean isSheepIsland) {
        super.setNode(islandNum, isSheepIsland);
    }

    public long getCanSaveNumOfSheep(int saveIslandNum) {
        return super.getCalcSpecificValueToRoot(saveIslandNum);
    }
}

class Tree {
    private Node[] nodeArr;

    public Tree(int size) {
        nodeArr = new Node[size + 1];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int parentData, int childData, int value) {
        Node parent = getNode(parentData);
        Node child = getNode(childData);

        parent.setAdjacentNode(child, value);
    }

    public void setNode(int data, boolean isSpecific) {
        if (!isSpecific) return;
        findNode(data).specific();
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }

    public long getCalcSpecificValueToRoot(int rootData) {
        Node root = findNode(rootData);
        return getCalcSpecificValue(root);
    }

    private long getCalcSpecificValue(Node node) {
        long calcValue = 0;
        for (Node child : node.getAdjacentNodeSet()) {
            child.removeAdjacentNode(node);

            long childCalcValue = getCalcSpecificValue(child);
            calcValue += childCalcValue;

            int childValue = node.getValue(child);
            if (child.isSpecific()) {
                calcValue += childValue;
                continue;
            }

            if (childCalcValue < childValue) calcValue -= childCalcValue;
            else calcValue -= childValue;
        }
        return calcValue;
    }
}

class Node {
    private int data;
    private boolean isSpecific = false;
    private Map<Node, Integer> edgeValueMap;

    public Node(int data) {
        this.data = data;
        edgeValueMap = new HashMap<>();
    }

    public int getData() {
        return data;
    }

    public boolean isSpecific() {
        return isSpecific;
    }

    public Map<Node, Integer> getEdgeValueMap() {
        return edgeValueMap;
    }

    public void specific() {
        isSpecific = true;
    }

    public Set<Node> getAdjacentNodeSet() {
        return edgeValueMap.keySet();
    }

    public int getValue(Node toNode) {
        return edgeValueMap.get(toNode);
    }

    public void setAdjacentNode(Node node, int value) {
        edgeValueMap.put(node, value);
    }

    public void removeAdjacentNode(Node node) {
        edgeValueMap.remove(node);
    }

    public boolean isLeafNode() {
        return edgeValueMap.isEmpty();
    }
}