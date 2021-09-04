import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int BEER_POWER_DISTANCE = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int maxPlace = n + 2;
            BeerStreet beerStreet = BeerStreet.maxPlaceOf(maxPlace);

            for (int i = 0; i < maxPlace; i++) {
                String[] coordinateInfo = br.readLine().split(" ");
                int x = Integer.parseInt(coordinateInfo[0]);
                int y = Integer.parseInt(coordinateInfo[1]);

                Point coordinate = Point.coordinateOf(x, y);
                beerStreet.setPlace(i, coordinate);
            }

            beerStreet.setOnceDistance(BEER_POWER_DISTANCE);
            beerStreet.setConnectPlaces();
            sb.append(beerStreet.isArrivedAtDestination(0, maxPlace - 1) ? "happy\n" : "sad\n");
        }

        System.out.println(sb);
        br.close();
    }
}

class BeerStreet extends Graph<Point> {
    private int onceDistance;
    private Vertex<Point> destination;

    public static BeerStreet maxPlaceOf(int maxPlace) {
        return new BeerStreet(maxPlace);
    }

    private BeerStreet(int maxPlace) {
        super(maxPlace);
    }

    public void setPlace(int placeIdx, Point coordinate) {
        setVertex(placeIdx, coordinate);
    }

    public void setOnceDistance(int distance) {
        onceDistance = distance;
    }

    public void setConnectPlaces() {
        for (Vertex<Point> place : vertexArr) {
            if (place == null) continue;
            Point placeCoordinate = place.getValue();
            for (Vertex<Point> comparePlace : vertexArr) {
                if (comparePlace == null || place == comparePlace) continue;

                Point comparePlaceCoordinate = comparePlace.getValue();
                if (placeCoordinate.getDifference(comparePlaceCoordinate) > onceDistance) continue;

                place.addAdjacentVertex(comparePlace);
                comparePlace.addAdjacentVertex(place);
            }
        }
    }

    public boolean isArrivedAtDestination(int fromPlaceIdx, int destinationIdx) {
        destination = vertexArr[destinationIdx];
        boolean isPossibleArrived = isArrivedAtDestination(vertexArr[fromPlaceIdx]);
        resetAllVisit();
        return isPossibleArrived;
    }

    private boolean isArrivedAtDestination(Vertex<Point> place) {
        if (place == destination) return true;
        place.visit();

        boolean isPossibleArrived = false;
        for (Vertex<Point> adjPlace : place.getAdjacentVertexList()) {
            if (adjPlace.isVisited()) continue;
            if (isArrivedAtDestination(adjPlace)) isPossibleArrived = true;
        }

        return isPossibleArrived;
    }
}

class Point {
    private int x;
    private int y;

    public static Point coordinateOf(int x, int y) {
        return new Point(x, y);
    }

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDifference(Point point) {
        return Math.abs(x - point.getX()) + Math.abs(y - point.getY());
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

    public void setVertex(int vertexIdx, T value) {
        getOrCreateVertex(vertexIdx).setValue(value);
    }

    public Vertex<T> findVertex(int vertexIdx) {
        return vertexArr[vertexIdx];
    }

    public void connectVertices(int vertexIdxA, int vertexIdxB) {
        Vertex<T> vertexA = getOrCreateVertex(vertexIdxA);
        Vertex<T> vertexB = getOrCreateVertex(vertexIdxB);

        vertexA.addAdjacentVertex(vertexB);
        vertexB.addAdjacentVertex(vertexA);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (vertexArr[vertexIdx] == null) {
            vertex = Vertex.indexOf(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    protected void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (vertex == null) continue;
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