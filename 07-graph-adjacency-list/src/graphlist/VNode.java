package graphlist;

/** A vertex (user) in the graph, with the head of its adjacency list. */
class VNode {
    String n;
    ENode eH;   // head of the edge list
    VNode nx;   // next vertex

    public VNode(String n) {
        this.n = n;
        this.eH = null;
        this.nx = null;
    }
}
