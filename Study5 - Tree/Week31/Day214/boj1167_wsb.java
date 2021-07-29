import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int maxDistance;
    static Node maxDiameterEndNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        NodeMap nodeMap = new NodeMap();
        int V = Integer.parseInt(br.readLine());
        while (V-- > 0) {
            int[] nodeEdgeAndDistantArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int nodeNum = nodeEdgeAndDistantArr[0];
            Node node = nodeMap.findNode(nodeNum);

            int lastIdx = nodeEdgeAndDistantArr.length - 1;
            for (int i = 1; i < lastIdx; i++) {
                int adjacentNodeNum = nodeEdgeAndDistantArr[i];
                int distance = nodeEdgeAndDistantArr[++i];

                nodeMap.setNodes(node, adjacentNodeNum, distance);
            }
        }

        nodeMap.setOneWayTree(1);
        
        dfsForSetMaxDistance(nodeMap.findNode(1), 0);
        dfsForSetMaxDistance(maxDiameterEndNode, 0);
        System.out.println(maxDistance);

        br.close();
    }

    static void dfsForSetMaxDistance(Node node, int untilDistance) {
        if (node.isVisited()) return;

        if (maxDistance < untilDistance) {
            maxDistance = untilDistance;
            maxDiameterEndNode = node;
        }

        node.toggleVisit();
        Node parent = node.getParent();
        if (parent != null) dfsForSetMaxDistance(parent, untilDistance + parent.getDistance(node));
        for (Node childNode : node.getAdjacentNodeSet()) {
            dfsForSetMaxDistance(childNode, untilDistance + node.getDistance(childNode));
        }
        node.toggleVisit();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        Node node = nodeMap.get(data);
        if (node != null) return node;
        return getNode(data);
    }

    public void setOneWayTree(int rootData) {
        setOneWayTree(findNode(rootData));
    }

    private void setOneWayTree(Node root) {
        for (Node child : root.getAdjacentNodeSet()) {
            child.removeAdjacentNode(root);
            child.setParent(root);
            setOneWayTree(child);
        }
    }

    public void setNodes(Node node, int adjacentNodeData, int distance) {
        Node adjacentNode = getNode(adjacentNodeData);

        node.setAdjacentNode(adjacentNode, distance);
        adjacentNode.setAdjacentNode(node, distance);
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
}

class Node {
    private int data;
    private Node parent;
    private Map<Node, Integer> edgeDistanceMap;
    private boolean isVisited = false;

    public Node(int data) {
        this.data = data;
        edgeDistanceMap = new HashMap<>();
    }

    public int getData() {
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public Map<Node, Integer> getEdgeDistanceMap() {
        return edgeDistanceMap;
    }

    public Set<Node> getAdjacentNodeSet() {
        return edgeDistanceMap.keySet();
    }

    public int getDistance(Node toNode) {
        return edgeDistanceMap.get(toNode);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public void setAdjacentNode(Node node, int distance) {
        edgeDistanceMap.put(node, distance);
    }

    public void removeAdjacentNode(Node node) {
        edgeDistanceMap.remove(node);
    }

    public void toggleVisit() {
        isVisited = !isVisited;
    }
}