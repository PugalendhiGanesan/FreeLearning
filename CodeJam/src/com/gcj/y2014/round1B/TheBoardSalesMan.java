/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author pugalendhi.ganesan
 */
public class TheBoardSalesMan {

    final static String PROBLEM_NAME = "problem_1BC";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new TheBoardSalesMan().solve(sc, pw);
                pw.println();
            }
            pw.flush();
        }
    }
    private int N;
    private int E;

    static class Node {

        int zipcode;
        List<Node> edge;
        boolean visited = false;
        int i;

        public Node(int z,int i) {
            zipcode = z;
            this.i = i;
            edge = new ArrayList<>();
        }

        public void addEdge(Node e) {
            edge.add(e);
        }

        @Override
        public boolean equals(Object obj) {
            return zipcode == ((Node) obj).zipcode;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.zipcode;
            return hash;
        }
    }
    
    List<Node> DEAD;
    Node[]graph;

    private void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        E = sc.nextInt();
        graph = new Node[N+1];        
        Node rootNode = null;
        for (int i = 1; i < N+1; ++i) {
            Node n = new Node(sc.nextInt(),i);
            graph[i] = n;
            if (rootNode == null) {
                rootNode = n;
            } else if (rootNode.zipcode > n.zipcode) {
                rootNode = n;
            }
        }
        for (int i = 0; i < E; ++i) {
            int e1 = sc.nextInt();
            int e2 = sc.nextInt();
            graph[e1].edge.add(graph[e2]);
            graph[e2].edge.add(graph[e1]);
        }

        DEAD = new ArrayList<>(N);
        Stack<Node> ACTIVE = new Stack<>();
        ACTIVE.push(rootNode);
        String answer = "" + rootNode.zipcode;
        rootNode.visited = true;
        Node HEAD;
        Node next;
        while (!ACTIVE.isEmpty()) {
            HEAD = ACTIVE.peek();
            next = nextSmallestToVisit(ACTIVE);
            if (next==null||(!HEAD.edge.contains(next))) {
                DEAD.add(HEAD);
                ACTIVE.pop();
            } else {
                ACTIVE.push(next);
                answer = answer+next.zipcode;
                next.visited = true;
            }
        }
        pw.print(answer);
    }

    private boolean bfs(Node src, Node[] graph) {
        Queue<Node> Q = new LinkedList<>();
        boolean reached[] = new boolean[N+1];
        
//        for (int i=1; i<graph.length; ++i) {        
//            reached[i] = graph[i].visited;
//        }
        Q.add(src);
        reached[src.i] = true;
        while(!Q.isEmpty()) {
            Node v = Q.poll();
            Node tmp;
            for (int i=0; i<v.edge.size(); ++i) {
                tmp = v.edge.get(i);                
                if (!(reached[tmp.i])) {
                    Q.add(tmp);
                    reached[tmp.i] = true;
                }
            }
        }
        for (int i=1; i<reached.length; ++i) {
            if (!(reached[i]||graph[i].visited)) return false;
        }
        return true;
    }

    private Node nextSmallestToVisit(Stack<Node> ACTIVE) {
        Stack<Node> tmp = new Stack<>();
        Node HEAD;
        Node best = null;
        while (!ACTIVE.isEmpty()) {
            HEAD = ACTIVE.peek();
            for (int i = 0; i < HEAD.edge.size(); ++i) {
                Node edge = HEAD.edge.get(i);
                if (!edge.visited && (best == null || (best.zipcode > edge.zipcode))) {               
                    best = edge;
                }
            }
            tmp.push(HEAD);
            ACTIVE.pop();
            if (best == null || bfs(best,graph)) break;
        }
        while(!tmp.isEmpty()) {
            HEAD = tmp.pop();
            ACTIVE.push(HEAD);
        }
        return best;
    }
}
