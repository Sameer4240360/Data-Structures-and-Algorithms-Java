package graphmatrix;

/**
 * Undirected graph stored with an adjacency matrix.
 * Nodes are kept in arr[], and adj[i][j] == 1 means there is an edge between node i and node j.
 */
class Functions {
    Node[] arr;
    int[][] adj;
    int c;      // current number of nodes
    int lim;    // maximum number of nodes

    public Functions(int size) {
        lim = size;
        arr = new Node[lim];
        adj = new int[lim][lim];
        c = 0;
    }

    /** Returns the index of the node with this name, or -1. */
    private int getI(String n) {
        for (int i = 0; i < c; i++) {
            if (arr[i].n.equalsIgnoreCase(n)) return i;
        }
        return -1;
    }

    public void addN(String n) {
        n = n.trim();
        if (n.isEmpty()) {
            System.out.println("Node name cannot be empty:");
            return;
        }
        if (c == lim) {
            System.out.println("Graph is Full:");
            return;
        }
        if (getI(n) != -1) {
            System.out.println("Node already exists:");
            return;
        }
        arr[c] = new Node(n);
        c++;
        System.out.println("Node Added:");
    }

    public void addE(String n1, String n2) {
        int i = getI(n1.trim());
        int j = getI(n2.trim());

        if (i != -1 && j != -1) {
            if (i == j) {
                System.out.println("A node cannot be connected to itself:");
                return;
            }
            adj[i][j] = 1;
            adj[j][i] = 1;
            System.out.println("Edge Added:");
        } else {
            System.out.println("One or both nodes not found:");
        }
    }

    public void srchE(String n) {
        int i = getI(n.trim());
        if (i != -1) {
            System.out.println("Edges for " + arr[i].n + ":");
            boolean found = false;
            for (int j = 0; j < c; j++) {
                if (adj[i][j] == 1) {
                    System.out.println("- " + arr[j].n);
                    found = true;
                }
            }
            if (!found) System.out.println("No edges found:");
        } else {
            System.out.println("Node not found:");
        }
    }

    public void chkE(String n1, String n2) {
        int i = getI(n1.trim());
        int j = getI(n2.trim());

        if (i != -1 && j != -1) {
            if (adj[i][j] == 1) {
                System.out.println("Yes a direct edge exists between " + arr[i].n + " and " + arr[j].n);
            } else {
                System.out.println("No direct edge exists.");
            }
        } else {
            System.out.println("Nodes not found:");
        }
    }

    public void delN(String n) {
        int x = getI(n.trim());
        if (x == -1) {
            System.out.println("Node not found:");
            return;
        }

        // Shift nodes left
        for (int i = x; i < c - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[c - 1] = null;

        // Shift matrix rows up
        for (int i = x; i < c - 1; i++) {
            for (int j = 0; j < c; j++) {
                adj[i][j] = adj[i + 1][j];
            }
        }

        // Shift matrix columns left
        for (int i = 0; i < c; i++) {
            for (int j = x; j < c - 1; j++) {
                adj[i][j] = adj[i][j + 1];
            }
        }

        // Clear the old last row and column so a node added later does not inherit stale edges
        for (int k = 0; k < c; k++) {
            adj[c - 1][k] = 0;
            adj[k][c - 1] = 0;
        }

        c--;
        System.out.println("Node and its edges deleted:");
    }

    public void delE(String n1, String n2) {
        int i = getI(n1.trim());
        int j = getI(n2.trim());

        if (i != -1 && j != -1) {
            if (adj[i][j] == 1) {
                adj[i][j] = 0;
                adj[j][i] = 0;
                System.out.println("Edge deleted:");
            } else {
                System.out.println("No edge existed.");
            }
        } else {
            System.out.println("Nodes not found:");
        }
    }

    public void dfs(String start) {
        int idx = getI(start.trim());
        if (idx == -1) {
            System.out.println("Node not found:");
            return;
        }
        System.out.print("DFS Traversal:");
        boolean[] vis = new boolean[c];
        dfsR(idx, vis);
        System.out.println();
    }

    private void dfsR(int i, boolean[] vis) {
        vis[i] = true;
        System.out.print(arr[i].n + " ");

        for (int j = 0; j < c; j++) {
            if (adj[i][j] == 1 && !vis[j]) {
                dfsR(j, vis);
            }
        }
    }

    public void bfs(String start) {
        int idx = getI(start.trim());
        if (idx == -1) {
            System.out.println("Node not found:");
            return;
        }

        System.out.print("BFS Traversal:");
        boolean[] vis = new boolean[c];
        int[] q = new int[c];
        int f = 0, r = 0;

        vis[idx] = true;
        q[r] = idx;
        r++;

        while (f < r) {
            int curr = q[f];
            f++;
            System.out.print(arr[curr].n + " ");

            for (int j = 0; j < c; j++) {
                if (adj[curr][j] == 1 && !vis[j]) {
                    vis[j] = true;
                    q[r] = j;
                    r++;
                }
            }
        }
        System.out.println();
    }
}
