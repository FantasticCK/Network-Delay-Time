package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// Dijkstra
class Solution {
    class Node{
        List<int[]> neighbors;
        Node() {
            neighbors = new ArrayList<>();
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }
        for (int[] time : times) {
            nodes[time[0] - 1].neighbors.add(new int[]{time[1] - 1, time[2]});
        }

        int[] dis = new int[N];
        Set<Integer> visited = new HashSet<>();
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[K - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, K - 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (!visited.add(curr[1]))
                continue;
            for (int[] neighbor : nodes[curr[1]].neighbors) {
                if (visited.contains(neighbor[0]))
                    continue;
                if (neighbor[1] + dis[curr[1]] < dis[neighbor[0]]) {
                    dis[neighbor[0]] = neighbor[1] + dis[curr[1]];
                }
                pq.offer(new int[]{dis[neighbor[0]], neighbor[0]});

            }
        }
        // System.out.println(Arrays.toString(dis));
        int res = Integer.MIN_VALUE;
        for (int d : dis) {
            if (d == Integer.MAX_VALUE)
                return -1;
            res = Math.max(res, d);
        }
        return res;
    }
}
