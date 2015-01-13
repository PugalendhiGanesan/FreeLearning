/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pugalendhi.ganesan
 */
public class FullBinaryTree {

    final static String PROBLEM_NAME = "problem_B";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";
    
    static class Node {        
        Node(int i) {
            name = i;
            edges = new LinkedList<>();
        }
        final int name;
        final List<Node> edges;
        
        void add(Node node) {
            edges.add(node);
        }
    }
    
     int N;
     Map<Integer,Node> nodes;

    private void solve(Scanner sc, PrintWriter pw) {
         N = sc.nextInt();
         nodes = new HashMap<>();        
         for (int i=0; i<N-1; ++i) {         
            int _n = sc.nextInt();
            Node one = getNode(_n);
            int _e = sc.nextInt();
            Node two = getNode(_e);
            one.add(two);
            two.add(one);           
        }
        int min = Integer.MAX_VALUE;
        for (int i:nodes.keySet()) {
            min = Math.min(min, N - maxSubtreeNodes(nodes.get(i).name,0));
        }
        pw.print(min);
    }
    
    private int maxSubtreeNodes(int root, int parent) {
        int mx2no[] = new int[2];
        for (Node n:getNode(root).edges) {
            if (n.name == parent) continue;
            int mx = maxSubtreeNodes(n.name, root);
            int tmp=0;            
            for (int i=0; i<2; ++i) {
                if(mx2no[i]<mx) {
                    tmp = mx2no[i];
                    mx2no[i] = mx;
                    mx = tmp;
                }
            }   
        }
        if (mx2no[0]!= 0 && mx2no[1]!=0)
            return 1 + mx2no[0] + mx2no[1];
        return 1;
    }
    
    private Node getNode(int n) {
        Node node = nodes.get(n);
        if (node == null) {
            node = new Node(n);
            nodes.put(n, node);
        }
        return node;
    }
    
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new FullBinaryTree().solve(sc, pw);
                pw.println();
            }
            pw.flush();
        }
    }
}
