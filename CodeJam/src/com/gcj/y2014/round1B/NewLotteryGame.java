/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author pugalendhi.ganesan
 */
public class NewLotteryGame {

    final static String PROBLEM_NAME = "problem_1BB";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new NewLotteryGame().solve(sc, pw);
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
        int count = 0;

        int [] kList = new int[K];
        for (int i = 0; i < K; ++i) {
            kList[i] = i;
        }

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; ++j) {
                if (binraySearch(kList, i&j)) count++;
            }
        }        
        pw.print(count);
    }

    private boolean binraySearch(int arr[], int item) {
        return binraySearch(arr, item, 0, arr.length-1);
    }

    private boolean binraySearch(int arr[], int item, int low, int high) {

        if(low > high) return false;
        int middle = (low + high) / 2;       
        if (arr[middle] == item) {
            return true;
        }
        if (arr[middle] < item) {
            return binraySearch(arr, item, middle + 1, high);
        } else {
            return binraySearch(arr, item, low, middle - 1);
        }
    }

}

