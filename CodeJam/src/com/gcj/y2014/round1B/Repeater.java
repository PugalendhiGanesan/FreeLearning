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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pugalendhi.ganesan
 */
public class Repeater {

    final static String PROBLEM_NAME = "problem_1BA";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
                PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"))) {
            int caseCnt = sc.nextInt();
            for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
                pw.print("Case #" + (caseNum + 1) + ": ");
                new Repeater().solve(sc, pw);
                pw.println();
            }
            pw.flush();
        }
    }
    private int N;

    private void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        List<List<FrqCount>> frqLists = new ArrayList<>();
        boolean fegalaWon = false;
        for (int i = 0; i < N; ++i) {
            String word = sc.next();
            List<FrqCount> tmp = convert(word);
            if (!frqLists.isEmpty()) {
                if (frqLists.get(0).size() != tmp.size()) {
                    fegalaWon = true;
                }
            }
            frqLists.add(tmp);
        }
        
        if (fegalaWon) {
            pw.print("Fegla Won");
            return;
        }

        int diff = 0;

        for (int i = 0; i < frqLists.get(0).size(); ++i) {
            int[] arr = new int[frqLists.size()];
            for (int j = 0; j < frqLists.size(); ++j) {
                if(j>0 && frqLists.get(j).get(i).c !=  frqLists.get(j-1).get(i).c) {
                    pw.print("Fegla Won");
                    return;
                }
                arr[j] = frqLists.get(j).get(i).i;
            }
            int medianValue = median(arr);//(int) median.evaluate(arr);
            for (int k = 0; k < arr.length; ++k) {
                diff += Math.abs(medianValue - arr[k]);
            }
        }
        pw.print(diff);
    }

    private int median(int arr[]) {
        Arrays.sort(arr);
        int middle = arr.length / 2;
        return arr[middle];
    }

    static class FrqCount {

        char c;
        int i;

        public FrqCount(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }

    private List<FrqCount> convert(String word) {
        List<FrqCount> frqList = new ArrayList<>();
        if (word.length() == 0) {
            return frqList;
        }
        char c = word.charAt(0);
        int count = 1;
        for (int i = 1; i < word.length(); ++i) {
            if (c == word.charAt(i)) {
                count++;
            } else {
                FrqCount fq = new FrqCount(c, count);
                frqList.add(fq);
                c = word.charAt(i);
                count = 1;
            }
        }
        FrqCount fq = new FrqCount(c, count);
        frqList.add(fq);
        return frqList;
    }
}
