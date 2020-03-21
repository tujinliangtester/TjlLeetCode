package com.leetcode.tjl.class004;

import java.util.Arrays;

public class Class004 {
    static float equalNum = (float) 0.1;

    static int[] getMidIndex(int[] m) {
        int len = m.length;
        int[] res = new int[2];
        if (len==0){
            return res;
        }
        if (len % 2 == 0) {
            res[0] = len / 2-1;
            res[1] = len / 2 ;
        } else {
            res[0] = len / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 3,5};
        int[] b = {2};
        System.out.println(myMain(a, b));
    }

    static float myMain(int[] a, int[] b) {
        //todo 这里会出现a b 两个数组同时为空的情况，应该是之前迭代逻辑中有异常，待后续调试修改
        if (a.length==0){
            if (b.length>0){
                return getMidValue(b, getMidIndex(b));
            }
            throw  new RuntimeException() ;
        }
        if (b.length==0){
            return getMidValue(a, getMidIndex(a));
        }
        int[] midIndexA = getMidIndex(a);
        int[] midIndexB = getMidIndex(b);
        float midValueA = 0.0f;
        float midValueB = 0.0f;
        float midValue;
        midValueA = getMidValue(a, midIndexA);
        midValueB = getMidValue(b, midIndexB);
        if (midValueA - midValueB < equalNum && midValueA - midValueB > -equalNum) {
            midValue = midValueA;
//            System.out.println(midValueA);
        } else if (midValueA - midValueB > equalNum) {
            int k = 0;
            //大值中位，等于，小值数组的最大值
            if (midValueA - b[b.length - 1] < equalNum && midValueA - b[b.length - 1] > -equalNum) {
                k = b.length - 1;
                // 长度足够
//                if (midIndexA[0] >= midIndexB[0]) {
                    midValue = myMain(Arrays.copyOfRange(a, midIndexA[0] - (k - midIndexB[0]), midIndexA[0] + 1),
                            Arrays.copyOfRange(b, midIndexB[0], k + 1)
                    );
//                }
                // 长度不够 todo
            }
            //大值中位，大于，小值数组的最大值 参考图思路细化2
            else if (midValueA - b[b.length - 1] > equalNum) {

                k = getCloseIndex(b[b.length - 1], Arrays.copyOfRange(a, 0, midIndexA[0]+1));
                //这里的y,w是指长度
                int y=midIndexA[0]+1-k;
                int w=midIndexB[0]+1-y;
                midValue = myMain(Arrays.copyOfRange(a, midIndexA[0] - (y+w), midIndexA[0]-y+1),
                        Arrays.copyOfRange(b, b.length-1-w, b.length));
            }
            //大值中位，小于，小值数组的最大值 参考图思路细化1
            else {
                k = getCloseIndex(midValueA, Arrays.copyOfRange(b, midIndexB[0], b.length));
                midValue = myMain(Arrays.copyOfRange(a, 0, midIndexA[0]+1),
                        Arrays.copyOfRange(b, midIndexB[0], k+1));
            }

        }
        else {
            midValue=myMain(b,a);
        }
        return midValue;
    }

    //获取数组m，给定中位id时对应的值
    static float getMidValue(int[] m, int[] midIndex) {
        float midValue;
        if (midIndex[1] > midIndex[0]) {
            midValue = (float) (m[midIndex[0]] + m[midIndex[1]]);
            midValue = midValue / 2;
        } else {
            midValue = m[midIndex[0]];
        }
        return midValue;
    }

    //获取m中，与v最接近的值对应的索引
    static int getCloseIndex(float v, int[] m) {
        int index;
        if (m.length == 0) {
            index = -1;
            return index;
        }
        if (v - m[m.length / 2] < equalNum && v - m[m.length / 2] > -equalNum) {
            index = m.length / 2;
        } else if (v - m[m.length / 2] > equalNum) {
            index = getCloseIndex(v, Arrays.copyOfRange(m, m.length / 2+1, m.length));
            if (index == -1) {
                index = m.length / 2;
            }
        } else {
            index = getCloseIndex(v, Arrays.copyOfRange(m, 0, m.length / 2));
            if (index == -1) {
                index = m.length / 2;
            }
        }
        return index;
    }
}
