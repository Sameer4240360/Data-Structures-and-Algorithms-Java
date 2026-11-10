package graphlist;

/** An edge entry in a node's adjacency list (stores the name of the neighbouring node). */
class ENode {
    String d;
    ENode nx;

    public ENode(String d) {
        this.d = d;
        this.nx = null;
    }
}
