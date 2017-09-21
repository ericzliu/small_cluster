import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionFind {
    class UFNode {
        public Node node;
        public int rank;
        public UFNode parent;

        boolean isRoot() {
            return this == parent;
        }

        @Override
        public String toString() {
            return "UFNode{" +
                    "node=" + node.id +
                    ", rank=" + rank +
                    ", parent=" + parent.node.id +
                    '}';
        }
    }

    HashMap<Integer, UFNode> nodesMap = new HashMap<>();
    int partitionNumber;

    public void onInit(List<Node> nodes) {
        for (Node node :
                nodes) {
            int loc = node.id;
            UFNode ufNode = new UFNode();
            ufNode.node = node;
            ufNode.rank = 0;
            ufNode.parent = ufNode;
            nodesMap.put(loc, ufNode);
        }
        partitionNumber = nodes.size();
    }

    public int getPartitionNumber() {
        return partitionNumber;
    }

    public Node getPartitionLead(Node node) {
        UFNode ufNode = nodesMap.get(node.id);
        List<UFNode> path = new ArrayList<>();

        while (!ufNode.isRoot()) {
            path.add(ufNode);
            ufNode = ufNode.parent;
        }

        for (UFNode var : path) {
            var.parent = ufNode;
        }

        return ufNode.node;
    }

    public void mergePartition(Node lead1, Node lead2) {
        if (lead1.equals(lead2))
            return;

        UFNode par1 = nodesMap.get(lead1.id);
        UFNode par2 = nodesMap.get(lead2.id);
        int rank1 = par1.rank;
        int rank2 = par2.rank;
        if (rank1 >= rank2) {
            par2.parent = par1;
            par1.rank = Math.max(par1.rank, rank2 + 1);
        } else {
            par1.parent = par2;
        }
        partitionNumber -= 1;
    }
}
