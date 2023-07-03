package com.ModuleConnectionAlgorithm;

import java.util.*;

public class Test {
    private final int V; // 顶点数
    private final Map<Integer, List<Integer>> adj; // 邻接表

    public Test(int V) {
        this.V = V;
        adj = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<Integer>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public List<List<Integer>> allTopologicalSorts() {
        int[] inDegree = new int[V]; // 记录每个顶点的入度
        for (int u : adj.keySet()) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        boolean[] visited = new boolean[V];
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(visited, inDegree, path, result);
        return result;
    }

    private void dfs(boolean[] visited, int[] inDegree, List<Integer> path, List<List<Integer>> result) {
        boolean flag = false; // 是否存在未访问的入度为0的顶点
        for (int u : adj.keySet()) {
            if (!visited[u] && inDegree[u] == 0) {
                visited[u] = true;
                path.add(u);
                for (int v : adj.get(u)) {
                    inDegree[v]--;
                }
                dfs(visited, inDegree, path, result);
                visited[u] = false;
                path.remove(path.size() - 1);
                for (int v : adj.get(u)) {
                    inDegree[v]++;
                }
                flag = true;
            }
        }
        if (!flag) { // 所有顶点都已经访问过
            result.add(new ArrayList<>(path));
        }
    }

    public static void main(String[] args) {
        Test g = new Test(7);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(1, 6);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(0, 3);
//        g.addEdge(0, 4);
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//        g.addEdge(1, 4);
        List<List<Integer>> allToPoSorts = g.allTopologicalSorts();
        for (List<Integer> toPoSort : allToPoSorts) {
            for (Integer integer : toPoSort) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        System.out.println("该衬衫总共有"+allToPoSorts.size()+"种连接组合方式！");
    }
}
