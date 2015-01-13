/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pugalendhi.ganesan
 */
public class NewLotteryGame2 {

    final static String PROBLEM_NAME = "problem_1BB";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new NewLotteryGame2().solve(sc, pw);
                pw.println();
            }
            pw.flush();
        }
    }

    private int A;
    private int B;
    private int K;

    private void solve(Scanner sc, PrintWriter pw) {
        A = sc.nextInt();
        B = sc.nextInt();
        K = sc.nextInt();
        long count = countPairs(63, false, false, false, A, B, K);
        pw.print(count);
       // System.out.println(count(4,false,5));
       // System.out.println(func(3,4,2));
    }
    
    Map<String,Long> countCache = new HashMap<>();
    
    private String convert(int i,boolean lessA, boolean lessB, boolean lessK) {
        return i+(lessA?"true":"false")+(lessB?"true":"false")+(lessK?"true":"false");
    }
    
    private int count (int i, boolean lessM, int M) {
        if (i == -1) {
            return lessM  ? 1 : 0;
        }
        boolean maxM = lessM || getBit(M, i) == 1;
        int count = count(i - 1, maxM, M);
        if (maxM) {
            count += count(i - 1, lessM, M);
        }
        return count;
    }
    
    private int func(int a, int b, int k) {
        if (a==0||b==0||k==0) return 0;
        if (a==1||b==1) return 1;
        return func((a+1)>>1, (b+1)>>1, (k+1)>>1) + 
           func((a+1)>>1, b>>1, (k+1)>>1) + 
           func(a>>1, (b+1)>>1, (k+1)>>1) + 
           func(a>>1, b>>1, k>>1);
    }

    private long countPairs(int i, boolean lessA, boolean lessB, boolean lessK, long a, long b, long k) {
        Long c = countCache.get(convert(i,lessA,lessB,lessK));
        if (c!=null) return c;
        if (i == -1) {
            return lessA && lessB && lessK ? 1 : 0;
        }

        boolean maxA = lessA || getBit(a, i) == 1;
        boolean maxB = lessB || getBit(b, i) == 1;
        boolean maxK = lessK || getBit(k, i) == 1;

        // Use value 0 for a, b, and k which is always possible. See (1).
        long count = countPairs(i - 1, maxA, maxB, maxK, a, b, k);

        // Use value 1 for a, and 0 for b and k. See (2).
        if (maxA) {
            count += countPairs(i - 1, lessA, maxB, maxK, a, b, k);
        }
        // Use value 1 for b, and 0 for a and k. See (3)
        if (maxB) {
            count += countPairs(i - 1, maxA, lessB, maxK, a, b, k);
        }
        // Use value 1 for a, b, and k. See (4)
        if (maxA && maxB && maxK) {
            count += countPairs(i - 1, lessA, lessB, lessK, a, b, k);
        }
        countCache.put(convert(i,lessA,lessB,lessK), count);
        return count;
    }

    private int getBit(long num, int i) {
        return (int) ((num >> i) & 1);
    }

}
