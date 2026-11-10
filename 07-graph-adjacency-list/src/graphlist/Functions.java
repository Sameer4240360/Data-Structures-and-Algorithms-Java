package graphlist;

/**
 * Undirected graph stored with adjacency lists (linked list of vertices,
 * each with a linked list of its neighbours).
 */
class Functions {
    VNode hd;

    public Functions() {
        hd = null;
    }

    /** Returns the vertex with this name (case-insensitive), or null. */
    private VNode fnd(String n) {
        VNode t = hd;
        while (t != null) {
            if (t.n.equalsIgnoreCase(n)) return t;
            t = t.nx;
        }
        return null;
    }

    private int countNodes() {
        int cnt = 0;
        VNode t = hd;
        while (t != null) {
            cnt++;
            t = t.nx;
        }
        return cnt;
    }

    /** True if vertex u already has an edge to v. */
    private boolean hasEdge(VNode u, String v) {
        ENode e = u.eH;
        while (e != null) {
            if (e.d.equalsIgnoreCase(v)) return true;
            e = e.nx;
        }
        return false;
    }

    public void addN(String n) {
        n = n.trim();
        if (n.isEmpty()) {
            System.out.println("Node name cannot be empty:");
            return;
        }
        if (fnd(n) != null) {
            System.out.println("Node already exists:");
            return;
        }
        VNode nV = new VNode(n);
        if (hd == null) {
            hd = nV;
        } else {
            VNode t = hd;
            while (t.nx != null) t = t.nx;
            t.nx = nV;
        }
        System.out.println("Node Added:");
    }

    public void addE(String u, String v) {
        VNode n1 = fnd(u.trim());
        VNode n2 = fnd(v.trim());

        if (n1 != null && n2 != null) {
            if (n1 == n2) {
                System.out.println("A node cannot be connected to itself!");
                return;
            }
            if (hasEdge(n1, n2.n)) {
                System.out.println("Edge already exists!");
                return;
            }
            // Store the real node names (not the typed text) so the case always matches
            ENode e1 = new ENode(n2.n);
            e1.nx = n1.eH;
            n1.eH = e1;

            ENode e2 = new ENode(n1.n);
            e2.nx = n2.eH;
            n2.eH = e2;

            System.out.println("Edge Added!");
        } else {
            System.out.println("One or both nodes not found!");
        }
    }

    public void srchE(String n) {
        VNode t = fnd(n.trim());
        if (t != null) {
            System.out.println("Edges (Friends) for " + t.n + ":");
            ENode e = t.eH;
            if (e == null) System.out.println("No edges found.");
            while (e != null) {
                System.out.println("- " + e.d);
                e = e.nx;
            }
        } else {
            System.out.println("Node not found!");
        }
    }

    public void chkE(String u, String v) {
        VNode n1 = fnd(u.trim());
        VNode n2 = fnd(v.trim());
        if (n1 != null && n2 != null) {
            if (hasEdge(n1, n2.n)) {
                System.out.println("Yes, direct edge exists between " + n1.n + " and " + n2.n);
            } else {
                System.out.println("No direct edge exists.");
            }
        } else {
            System.out.println("Nodes not found!");
        }
    }

    /** Removes the edge from vertex u to v (one direction only). */
    private void dE(VNode u, String v) {
        if (u.eH == null) return;
        if (u.eH.d.equalsIgnoreCase(v)) {
            u.eH = u.eH.nx;
            return;
        }
        ENode e = u.eH;
        while (e.nx != null && !e.nx.d.equalsIgnoreCase(v)) {
            e = e.nx;
        }
        if (e.nx != null) {
            e.nx = e.nx.nx;
        }
    }

    public void delE(String u, String v) {
        VNode n1 = fnd(u.trim());
        VNode n2 = fnd(v.trim());

        if (n1 != null && n2 != null) {
            if (!hasEdge(n1, n2.n)) {
                System.out.println("No edge existed.");
                return;
            }
            dE(n1, n2.n);
            dE(n2, n1.n);
            System.out.println("Edge Deleted!");
        } else {
            System.out.println("Nodes not found!");
        }
    }

    public void delN(String n) {
        VNode tgt = fnd(n.trim());
        if (tgt == null) {
            System.out.println("Node not found!");
            return;
        }

        // Remove every edge that points to this node
        VNode t = hd;
        while (t != null) {
            dE(t, tgt.n);
            t = t.nx;
        }

        // Remove the node itself
        if (hd == tgt) {
            hd = hd.nx;
        } else {
            t = hd;
            while (t.nx != tgt) {
                t = t.nx;
            }
            t.nx = tgt.nx;
        }
        System.out.println("Node and all its edges deleted!");
    }

    private boolean isVis(String s, String[] v, int vc) {
        for (int i = 0; i < vc; i++) {
            if (v[i].equalsIgnoreCase(s)) return true;
        }
        return false;
    }

    public void dfs(String start) {
        VNode s = fnd(start.trim());
        if (s == null) {
            System.out.println("Node not found!");
            return;
        }
        String[] vis = new String[countNodes()];   // sized to the graph, so it never overflows
        int[] vc = {0};
        System.out.print("DFS Traversal: ");
        dfsR(s.n, vis, vc);
        System.out.println();
    }

    private void dfsR(String curr, String[] vis, int[] vc) {
        vis[vc[0]++] = curr;
        System.out.print(curr + " ");

        VNode t = fnd(curr);
        if (t != null) {
            ENode e = t.eH;
            while (e != null) {
                if (!isVis(e.d, vis, vc[0])) {
                    dfsR(e.d, vis, vc);
                }
                e = e.nx;
            }
        }
    }

    public void bfs(String start) {
        VNode s = fnd(start.trim());
        if (s == null) {
            System.out.println("Node not found!");
            return;
        }

        int total = countNodes();
        String[] vis = new String[total];
        int vc = 0;
        String[] q = new String[total];
        int f = 0, r = 0;

        vis[vc++] = s.n;
        q[r++] = s.n;
        System.out.print("BFS Traversal: ");

        while (f < r) {
            String curr = q[f++];
            System.out.print(curr + " ");

            VNode t = fnd(curr);
            if (t != null) {
                ENode e = t.eH;
                while (e != null) {
                    if (!isVis(e.d, vis, vc)) {
                        vis[vc++] = e.d;
                        q[r++] = e.d;
                    }
                    e = e.nx;
                }
            }
        }
        System.out.println();
    }
}
