import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔꼼~
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] workInfo = br.readLine().split(" ");
        int N = Integer.parseInt(workInfo[0]);
        int S = Integer.parseInt(workInfo[1]);
        int D = Integer.parseInt(workInfo[2]);

        if ((N - 1) <= D) {
            System.out.println(0);
            return;
        }

        NodeMap nodeMap = new NodeMap();
        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(nodeInfo[0]);
            int dataB = Integer.parseInt(nodeInfo[1]);

            nodeMap.setNodes(dataA, dataB);
        }

        System.out.println(nodeMap.getRoundDistance(S, D));
        br.close();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private int distanceNotGoing;
    private int roundDistance;

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeMap.containsKey(data)) {
            node = nodeMap.get(data);
        } else {
            node = new Node(data);
            nodeMap.put(data, node);
        }
        return node;
    }

    public int getRoundDistance(int rootData, int distanceNotGoing) {
        Node root = findNode(rootData);
        this.distanceNotGoing = distanceNotGoing;
        roundDistance = 0;
        //// "'RoundDistance를 세팅하는 리프'로부터의 깊이"를 구하는 함수로 읽혀요
        //// setRoundDistanceAndReturnDepthFromLeaf는 어때요
        getDepthFromLeafSettingRoundDistance(root);
        return roundDistance * 2;
    }

    //// 저는 roundDistance를 따로 계산했는데 한번에 하니까 더 보기 쉬운 것 같아요
    private int getDepthFromLeafSettingRoundDistance(Node node) {
        if (node.isLeafNode()) return 0;
        
        int depthFromLeaf = 0;
        for (Node child : node.getAdjacentNodeList()) {
            child.removeAdjacentNode(node);
            int currentDepthFromLeaf = getDepthFromLeafSettingRoundDistance(child) + 1;
            if (currentDepthFromLeaf > distanceNotGoing) roundDistance++;
            depthFromLeaf = Math.max(depthFromLeaf, currentDepthFromLeaf);
        }

        return depthFromLeaf;
    }
}

class Node {
    private int data;
    private List<Node> adjacentNodeList;

    public Node(int data) {
        this.data = data;
        adjacentNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public List<Node> getAdjacentNodeList() {
        return adjacentNodeList;
    }

    public void addAdjacentNode(Node node) {
        adjacentNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjacentNodeList.remove(node);
    }

    public boolean isLeafNode() {
        return adjacentNodeList.isEmpty();
    }
}