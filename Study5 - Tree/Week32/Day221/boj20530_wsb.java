import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// ㄲㄲ
//// 어느부분에서 시간초과가 났었나요?
//// -> NodeMap class의 setCycleNodeSet() 내에서
    //// 싸이클 노드들에 대한 adjSizeArr 데이터만 남기고
    //// adjSizeArr를 순회하며 싸이클 노드들을 cycleNodeSet에 추가해주도록 했는데,
    //// 그 부분만 제거하면 solve 되었습니다.
    //// 현재는 nodeArr를 돌며 add를 먼저해주고 adjSizeArr를 계속 순회하며 싸이클 노드가 아닌 것들을 제거해주는 방법인데,
    //// 정확히 어떤 차이가 있어서 시간초과가 나는지는 잘 파악이 되지 않아서 답답했던 문제입니다. (두 방법 모두 O(N))
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] treeAndQueryInfo = br.readLine().split(" ");
        int N = Integer.parseInt(treeAndQueryInfo[0]);
        int Q = Integer.parseInt(treeAndQueryInfo[1]);

        NodeMap nodeMap = new NodeMap(N);
        while (N-- > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(edgeInfo[0]);
            int dataB = Integer.parseInt(edgeInfo[1]);

            nodeMap.setNodes(dataA, dataB);
        }
        
        nodeMap.setCycleRootOfNodes();

        while (Q-- > 0) {
            String[] queryNodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(queryNodeInfo[0]);
            int dataB = Integer.parseInt(queryNodeInfo[1]);

            sb.append(nodeMap.isSameRoot(dataA, dataB) ? 1 : 2).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private Node[] nodeArr;
    private Set<Node> cycleNodeSet;
    private int[] cycleRootArr;
    private int[] adjSizeArr;

    public NodeMap(int size) {
        int arrLen = size + 1;
        nodeArr = new Node[arrLen];
        cycleNodeSet = new HashSet<>();
        cycleRootArr = new int[arrLen];
        adjSizeArr = new int[arrLen];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.setAdjacentNode(nodeB);
        nodeB.setAdjacentNode(nodeA);

        adjSizeArr[dataA]++;
        adjSizeArr[dataB]++;
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }

    private void setCycleNodeSet() {
        for (Node node : nodeArr) {
            if (node == null) continue;
            cycleNodeSet.add(node);
        }

        boolean isNotOnlyCycleLeft = true;
        while (isNotOnlyCycleLeft) {
            isNotOnlyCycleLeft = false;
            for (int i = 1; i < adjSizeArr.length; i++) {
                if (adjSizeArr[i] != 1) continue;

                isNotOnlyCycleLeft = true;

                Node node = findNode(i);
                cycleNodeSet.remove(node);
                adjSizeArr[i] = 0;
                for (Node adjNode : node.getAdjacentNodeList()) {
                    int adjNodeData = adjNode.getData();
                    if (adjSizeArr[adjNodeData] == 0) continue;
                    adjSizeArr[adjNodeData]--;
                }
            }
        }
    }

    public void setCycleRootOfNodes() {
        setCycleNodeSet();
        for (Node cycleNode : cycleNodeSet) {
            cycleNode.getAdjacentNodeList().removeAll(cycleNodeSet);
            setCycleRoot(cycleNode, cycleNode.getData());
        }
    }

    private void setCycleRoot(Node node, int rootData) {
        cycleRootArr[node.getData()] = rootData;
        for (Node child : node.getAdjacentNodeList()) {
            child.removeAdjacentNode(node);
            setCycleRoot(child, rootData);
        }
    }

    public boolean isSameRoot(int dataA, int dataB) {
        return cycleRootArr[dataA] == cycleRootArr[dataB];
    }
}

class Node {
    private int data;
    private List<Node> adjNodeList;

    public Node(int data) {
        this.data = data;
        adjNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public List<Node> getAdjacentNodeList() {
        return adjNodeList;
    }

    public int getAdjacentNodeSize() {
        return adjNodeList.size();
    }

    public void setAdjacentNode(Node node) {
        adjNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjNodeList.remove(node);
    }
}