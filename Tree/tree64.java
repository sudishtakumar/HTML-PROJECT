// -- 👉👉Simple Approach✅✅|| Java || ❤️❤️✅✅ 🔥🔥🔥
// Intuition
// There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

// Approach
// Find the farthest node starting from node 0(can be any other node).
// Now from that node find the other farthest node which will give us the diameter of the tree(d1 and d2).
// Now the answer could be max(d1, d2, (d1+1)/2 + (d2+1)/2 + 1)

// Complexity
// Time complexity:
// Complexity
// Time complexity: O(n)
// Space complexity: O(n) -->

import java.util.*;
class Solution {
    private void build(List<List<Integer>> adj, int[][] edges) {
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
    }

    private void get(int[] p, List<List<Integer>> adj, int node, int dis, boolean[] vis) {
        int d = p[1];
        vis[node] = true;
        if (dis > d) {
            p[0] = node;
            p[1] = dis;
        }
        for (int neighbor : adj.get(node)) {
            if (!vis[neighbor]) {
                get(p, adj, neighbor, dis + 1, vis);
            }
        }
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        List<List<Integer>> adj1 = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < n; i++) adj1.add(new ArrayList<>());
        for (int i = 0; i < m; i++) adj2.add(new ArrayList<>());

        build(adj1, edges1);
        build(adj2, edges2);

        int[] p1 = new int[]{-1, Integer.MIN_VALUE};
        boolean[] vis = new boolean[n];
        get(p1, adj1, 0, 0, vis);

        int[] p2 = new int[]{-1, Integer.MIN_VALUE};
        Arrays.fill(vis, false);
        get(p2, adj1, p1[0], 0, vis);
        int d1 = p2[1];

        p1 = new int[]{-1, Integer.MIN_VALUE};
        vis = new boolean[m];
        get(p1, adj2, 0, 0, vis);

        p2 = new int[]{-1, Integer.MIN_VALUE};
        Arrays.fill(vis, false);
        get(p2, adj2, p1[0], 0, vis);
        int d2 = p2[1];

        if (edges1.length == 0) d1 = 0;
        if (edges2.length == 0) d2 = 0;

        return Math.max(Math.max(d1, d2), (d1 + 1) / 2 + (d2 + 1) / 2 + 1);
    }
}