import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int INVITEES_NUM = 1;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Relationship schoolRelationship = Relationship.maxPeopleOf(n);

        while (m-- > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int a = Integer.parseInt(edgeInfo[0]);
            int b = Integer.parseInt(edgeInfo[1]);

            schoolRelationship.connectVertices(a, b);
        }

        System.out.println(schoolRelationship.getNumOfPeopleInvitedToParty(INVITEES_NUM));
        br.close();
    }
}

class Relationship extends Graph {
    public static Relationship maxPeopleOf(int maxPeople) {
        return new Relationship(maxPeople);
    }

    private Relationship(int maxPeople) {
        super(maxPeople);
    }

    public int getNumOfPeopleInvitedToParty(int inviteesIdx) {
        Vertex<Object> invitees = findVertex(inviteesIdx);
        if (isBeforeSetUp(invitees)) return 0;
        return getInvitedPeopleSet(invitees).size();
    }

    private Set<Vertex> getInvitedPeopleSet(Vertex<Object> invitees) {
        Set<Vertex> invitedPeople = new HashSet<>();
        for (Vertex<Object> friend : invitees.getAdjacentVertexList()) {
            invitedPeople.add(friend);
            invitedPeople.addAll(friend.getAdjacentVertexList());
        }
        invitedPeople.remove(invitees);
        return invitedPeople;
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;

    public static Graph maxSizeOf(int maxSize) {
        return new Graph(maxSize);
    }

    protected Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexArr = new Vertex[maxSize + 1];
    }

    public void setVertex(int vertexIdx) {
        getOrCreateVertex(vertexIdx);
    }

    public void setVertex(int vertexIdx, T value) {
        getOrCreateVertex(vertexIdx).setValue(value);
    }

    public Vertex<T> findVertex(int vertexIdx) {
        return vertexArr[vertexIdx];
    }

    protected boolean isBeforeSetUp(Vertex<T> vertex) {
        return vertex == null;
    }

    public void connectVertices(int vertexIdxA, int vertexIdxB) {
        Vertex<T> vertexA = getOrCreateVertex(vertexIdxA);
        Vertex<T> vertexB = getOrCreateVertex(vertexIdxB);

        vertexA.addAdjacentVertex(vertexB);
        vertexB.addAdjacentVertex(vertexA);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (isBeforeSetUp(vertexArr[vertexIdx])) {
            vertex = Vertex.indexOf(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    protected void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (isBeforeSetUp(vertex)) continue;
            vertex.resetVisit();
        }
    }
}

class Vertex<T> {
    private int idx;
    private T value;
    private boolean isVisited = false;
    private List<Vertex<T>> adjVertexList;

    public static Vertex indexOf(int index) {
        return new Vertex(index);
    }

    private Vertex(int idx) {
        this.idx = idx;
        adjVertexList = new ArrayList<>();
    }

    public int getIndex() {
        return idx;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void visit() {
        isVisited = true;
    }

    public void resetVisit() {
        isVisited = false;
    }

    public int getNumOfAdjacentVertex() {
        return adjVertexList.size();
    }

    public List<Vertex<T>> getAdjacentVertexList() {
        return adjVertexList;
    }

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.add(vertex);
    }

    public void removeAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.remove(vertex);
    }
}