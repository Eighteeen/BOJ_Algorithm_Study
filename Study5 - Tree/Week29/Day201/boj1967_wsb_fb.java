import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//// 필요없는 import!
//// -> 원래 시도하던 로직에서 필요했는데 지우는 걸 깜빡했네요. ㄱㅅ!

class Main {
    static int maxDistance;
    static Node maxDiameterEndNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        NodeMap nodeMap = new NodeMap();

        int n = Integer.parseInt(br.readLine());
        while (--n > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int parentData = Integer.parseInt(nodeInfo[0]);
            int childData = Integer.parseInt(nodeInfo[1]);
            int distance = Integer.parseInt(nodeInfo[2]);
            
            nodeMap.setNodes(parentData, childData, distance);
        }
        
        //// 왜 같은 함수를 2번 호출해야 하는지에 대한 힌트가 없어 로직을 이해하기 어려웠읍니다..
            //// -> 함수명은 'maxDistance를 세팅하기 위해 깊이 우선 탐색을 한다' 로
            //// 의미가 그대로 전해진다고 생각하여 그대로 두었고,
            //// 변수명을 maxDistanceNode (가장 먼 거리의 노드) 에서 maxDiameterEndNode (가장 긴 지름의 끝 노드) 로 변경하여
            //// 의미를 직접적으로 알 수 있게끔 변경해보았습니다.
        dfsForSetMaxDistance(nodeMap.findNode(1), 0);
        dfsForSetMaxDistance(maxDiameterEndNode, 0);
        System.out.println(maxDistance);

        br.close();
    }

    // 현재는 dfs 실행해 가장 먼 노드부터 다시 dfs를 실행하는 로직.
    /*
        원래는 리프 노드인 것들에서 모두 dfs를 실행하려 했음.
        해당 로직에서 현재 메서드 호출에서의 방문 노드와 전체 메서드 호출에서의 방문 노드를 구별할 때 로직이 꼬여서 너무 복잡해짐.
        또한 해당 로직을 이용하여 전체 메소드 호출 관점으로 본다면
        방문했던 곳을 또 다시 방문할 수도 있는 구조이기 때문에 효율적인 로직도 아니었음.
        위의 상황으로 인해서 이번 풀이가 오래걸림.
        한 가지 로직에 꽂혔다고 그곳에만 집중하면 안 될 것 같다는 교훈을 얻음... 가끔 포기도 필요하다... => ㅇㄱㄹㅇ
    */
    //// 정말 재귀함수 활용 잘하시네요
    static void dfsForSetMaxDistance(Node node, int untilDistance) {
        if (node == null || node.isVisited()) return;

        if (maxDistance < untilDistance) {
            maxDistance = untilDistance;
            maxDiameterEndNode = node;
        }

        node.toggleVisit();
        dfsForSetMaxDistance(node.getParent(), untilDistance + node.getDistance());
        for (Node childNode : node.getChildList()) {
            dfsForSetMaxDistance(childNode, untilDistance + childNode.getDistance());
        }
        node.toggleVisit();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNodes(int parentData, int childData, int distance) {
        Node parentNode = getNode(parentData);
        Node childNode = getNode(childData);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
        childNode.setDistance(distance);
    }

    private Node getNode (int data) {
        Node node;
        if (nodeMap.containsKey(data)) {
            node = nodeMap.get(data);
        } else {
            node = new Node(data);
            nodeMap.put(data, node);
        }
        return node;
    }
}

class Node {
    private int data;
    private int distanceFromParent = 0;
    private Node parent;
    private List<Node> childList;
    private boolean isVisited = false;

    public Node(int data) {
        this.data = data;
        childList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public int getDistance() {
        return distanceFromParent;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public void setDistance(int distanceFromParent) {
        this.distanceFromParent = distanceFromParent;
    }

    public void toggleVisit() {
        isVisited = !isVisited;
    }

    public void addChild(Node node) {
        childList.add(node);
    }
}