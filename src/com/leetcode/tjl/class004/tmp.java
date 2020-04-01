package com.leetcode.tjl.class004;

import java.util.Random;

/**
 * @author tujinliang
 * @date 2020/4/1 10:07
 */
public class tmp {
    public static void main(String[] args) {
        Random random = new Random();
        int[] l = {7, 8, 9, 10};
        for (int li : l) {
            int[] m = new int[li];
            for (int i = 0; i < li; i++) {
                m[i] = random.nextInt(100);
            }
            //sort
            for (int i = 0; i < li; i++) {
                for (int j = i + 1; j < li; j++) {
                    if (m[i] > m[j]) {
                        int t = m[i];
                        m[i] = m[j];
                        m[j] = t;
                    }
                }
            }
            for (int i = 0; i < li; i++) {
                System.out.print(m[i]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
