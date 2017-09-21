public class Edge {
    public Node node1;
    public Node node2;
    public long cost;

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1.id +
                ", node2=" + node2.id +
                ", cost=" + cost +
                '}';
    }
}
