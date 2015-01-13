/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author pugalendhi.ganesan
 */
public class ProperShuffle {
    
    final static String PROBLEM_NAME = "problem_C";
    final static String WORK_DIR = "E:\\Java\\CodeJam\\" + PROBLEM_NAME + "\\";
    
     public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new ProperShuffle().solve(sc, pw);
                pw.println();
            }
            pw.flush();
        }
    }
    private int N;

    private void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        int score = 0;
        for (int i=0; i<N; ++i) {
            if(sc.nextInt() <= i) score++;            
        }
        if (score < (472+500)/2 ) {
            pw.print("BAD");
        } else {
            pw.print("GOOD");
        }
    }
}
