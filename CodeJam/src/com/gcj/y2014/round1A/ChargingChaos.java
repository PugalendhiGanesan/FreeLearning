/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcj.y2014.round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pugal
 */
public class ChargingChaos {

    final static String PROBLEM_NAME = "problem_A";
    final static String WORK_DIR = "/home/pugal/FreeLearning/CodeJam/" + PROBLEM_NAME + "/";

    public void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int L = sc.nextInt();

        long min = Long.MAX_VALUE;

        List<Long> outLet = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            addToSet(sc.next(), outLet);
        }

        List<Long> devices = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            addToSet(sc.next(), devices);
        }

        for (int i = 0; i < N; ++i) {
            long d = devices.get(0);
            long fl = outLet.get(i) ^ d;
            if (match(devices, flip(outLet, fl))) {
                int bc = Long.bitCount(fl);
                if (min > bc) {
                    min = bc;
                }
            }
        }

        if (min == Long.MAX_VALUE) {
            pw.print("NOT POSSIBLE");
        } else {
            pw.print(min);
        }
    }

    private boolean match(List<Long> device, List<Long> outlet) {
        for (long d : device) {
            if (!outlet.contains(d)) {
                return false;
            }
        }
        return true;
        //return device.stream().noneMatch((d) -> (!outlet.contains(d)));
    }

    private List<Long> flip(List<Long> outLet, long fl) {
        List<Long> foutLet = new ArrayList<>(outLet.size());
        for (int i = 0; i < outLet.size(); ++i) {
            foutLet.add(outLet.get(i) ^ fl);
        }
        return foutLet;
    }

    private Long addToSet(String s, List<Long> list) {
        long eL = 0L;
        for (int i = 0; i < s.length(); ++i) {
            char a = s.charAt(i);
            eL = eL << 1;
            if (a == '1') {
                eL = eL | 1;
            }
        }
        list.add(eL);
        return eL;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + "input.in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + "output.txt"));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new ChargingChaos().solve(sc, pw);
            pw.println();
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}

