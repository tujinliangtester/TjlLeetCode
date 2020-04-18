package com.leetcode.tjl.class004;

import java.util.Arrays;

public class Class004 {
    static float equalNum = (float) 0.1;
    static final String S2S = "single2single";
    static final String S2D = "single2double";
    static final String D2S = "double2single";
    static final String D2D = "double2double";

    public static void main(String[] args) throws Exception {
        int[] a = {3};
        int[] b = {2};
        System.out.println(myMain(a, b));
    }


    static float myMain(int[] a, int[] b) throws Exception {
        //todo 这里会出现a b 两个数组同时为空的情况，应该是之前迭代逻辑中有异常，待后续调试修改
        if (a.length == 0) {
            if (b.length > 0) {
                return getMidValue(b, getMidIndex(b));
            }
            throw new RuntimeException();
        }
        if (b.length == 0) {
            return getMidValue(a, getMidIndex(a));
        }
        int[] midIndexA = getMidIndex(a);
        int[] midIndexB = getMidIndex(b);
        float midValueA = 0.0f;
        float midValueB = 0.0f;
        float midValue;
        midValueA = getMidValue(a, midIndexA);
        midValueB = getMidValue(b, midIndexB);


        //首先用中位数来判断，然后在需要具体的index或者长度的时候
        int tmpMidIndexA;
        int tmpMidIndexB;
        String jt = judgeType(a, b);

        if (midValueA - midValueB < equalNum && midValueA - midValueB > -equalNum) {
            midValue = midValueA;
        } else if (midValueA - midValueB > equalNum) {
            int k = 0, ai = 0, aj = 0, bi = 0, bj = 0;
            tmpMidIndexB = bi;

            //大值中位，等于，小值数组的最大值
            if (midValueA - b[b.length - 1] < equalNum && midValueA - b[b.length - 1] > -equalNum) {
                k = b.length - 1;

                int[] tmpRes = getCopyIndex(jt, midIndexA, midIndexB, k);
                ai = tmpRes[2];
                aj = tmpRes[3];
                bi = tmpRes[4];
                bj = tmpRes[5];
                if (ai < 0 && bi < 0) {
                    throw new Exception("下标都小于0了！");
                }
                if (ai < 0) {
                    bi = bi + ai;
                    ai = 0;
                } else if (bi < 0) {
                    ai = ai + bi;
                    bi = 0;
                }
                int[] copyedA = judgeAndCopy(a, ai, aj);
                int[] copyedB = judgeAndCopy(b, bi, bj);

                if (copyedA == null || copyedA.length == 0) {
                    return getMidValueByArray(copyedB);
                }
                if (copyedB == null || copyedB.length == 0) {
                    return getMidValueByArray(copyedA);
                }
                return myMain(copyedA, copyedB);
            }
            //大值中位，大于，小值数组的最大值 参考图思路细化2
            //todo 感觉还是乱，不论是索引的起，还是止；还是递归的逻辑，都感觉非常非常的乱！ 后续跟进
            else if (midValueA - b[b.length - 1] > equalNum) {

                k = getCloseIndex(b[b.length - 1], Arrays.copyOfRange(a, 0, midIndexA[0] + 1), tmpMidIndexB);
                if (k == -1) {
                    // todo
                    // 做的时候感觉还是太复杂、混淆了，当然也可能这么做本身是对的，只是还需要再理一理
                    //这里暂时和下面的 w<0 中逻辑一样，调试了再说
                    int tmpIndex = (a.length - b.length) / 2 - 1;
                    return a[tmpIndex];
                }
                //k==0，意味着已经找到了第一位，这时直接跟 k==-1 时一样的处理
                else if (k == 0) {
                    //这里暂时和下面的 w<0 中逻辑一样，调试了再说
                    int tmpIndex = (a.length - b.length) / 2 - 1;
                    tmpIndex = tmpIndex >= 0 ? tmpIndex : 0;
                    return a[tmpIndex];
                }
                //这里的y,w是指长度
                int y = midIndexA[0] + 1 - k;
                int w = (b.length / 2 - y) / 2;
                //此时，b的长度不足了，直接将b放到a的前面，再从中找到（m+n）/2的位置，就是需要找的中位数
                if (w < 0) {
                    int tmpIndex = (a.length - b.length) / 2 - 1;
                    return a[tmpIndex];
                }
                midValue = myMain(Arrays.copyOfRange(a, midIndexA[0] - (y + w), midIndexA[0] - y + 1),
                        Arrays.copyOfRange(b, b.length - 1 - w, b.length));
            }
            //大值中位，小于，小值数组的最大值 参考图思路细化1 todo
            else {
                k = getCloseIndex(midValueA, Arrays.copyOfRange(b, tmpMidIndexB, b.length), tmpMidIndexB);
                if (k == -1) {
//                    todo
                    //这里暂时和下面的 w<0 中逻辑一样，调试了再说
                    int tmpIndex = (a.length - b.length) / 2 - 1;
                    return a[tmpIndex];
                }
                //k==0，意味着已经找到了第一位，这时直接跟 k==-1 时一样的处理
                else if (k == 0) {
                    //这里暂时和下面的 w<0 中逻辑一样，调试了再说
                    int tmpIndex = (a.length - b.length) / 2 - 1;
                    return a[tmpIndex];
                }
                midValue = myMain(Arrays.copyOfRange(a, 0, midIndexA[0] + 1),
                        Arrays.copyOfRange(b, midIndexB[0], k + 1));
            }

        } else {
            midValue = myMain(b, a);
        }
        return midValue;
    }

    static int[] getMidIndex(int[] m) {
        int len = m.length;
        int[] res = new int[2];
        if (len == 0) {
            return res;
        }
        if (len % 2 == 0) {
            res[0] = len / 2 - 1;
            res[1] = len / 2;
        } else {
            res[0] = len / 2;
        }
        return res;
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

    //获取数组m，直接获取中位值
    static float getMidValueByArray(int[] m) {
        int[] midIndex = getMidIndex(m);
        return getMidValue(m, midIndex);
    }


    /*
    //获取m中，与v最接近的值对应的索引
    // （等于v，或者比v小的最大值索引）
    todo 注意，这里有个问题，在取中位数，与getCloseIndex中，对于jt的判定和ai等的取值，是不是统一的？如果不是统一，那应该怎么处理
    todo 目前的想法只能是对每个jt，逐个进行试验查找，如果上诉两个能一致的话还比较好；另外，如果能将几个jt整合统一就更好了
    * @param v:目标值
     * @param m:目标数组
     * @param exIndex:在返回的索引基础上，额外增加的索引值
     @return
    * */
    static int getCloseIndex(float v, int[] m, int exIndex) {
        int index;
        if (m.length == 0) {
            index = -1;
            return index + exIndex;
        }
        float tmpMidValue = getMidValueByArray(m);
        int[] tmpMidIndex = getMidIndex(m);

        if (v - tmpMidValue < equalNum && v - tmpMidValue > -equalNum) {
            //todo 这也是一个关键地方
            return Math.max(tmpMidIndex[1], tmpMidIndex[0]) + exIndex;
        } else if (v - tmpMidValue > equalNum) {
            if (m.length == 1) {
                index = -1;
                return index + exIndex;
            }
            index = getCloseIndex(v, Arrays.copyOfRange(m, Math.max(tmpMidIndex[1], tmpMidIndex[0]), m.length), Math.max(tmpMidIndex[1], tmpMidIndex[0]) - 1);
            if (index < Math.max(tmpMidIndex[1], tmpMidIndex[0]) - 1) {
                return m.length / 2 + exIndex;
            }
        } else {
            if (m.length == 1) {
                if (v > m[0]) {
                    index = -1;
                    return index + exIndex;
                }
                index = 0;
                return index + exIndex;
            }
            index = getCloseIndex(v, Arrays.copyOfRange(m, 0, m.length / 2), Math.max(tmpMidIndex[1], tmpMidIndex[0]));
            if (index < Math.max(tmpMidIndex[1], tmpMidIndex[0])) {
                return m.length / 2 + exIndex;
            }
        }
        return index + exIndex;
    }

    static int[] judgeAndCopy(int[] m, int i, int j) {
        if (m.length == 0 || i >= j || j > m.length) {
            return null;
        }
        return Arrays.copyOfRange(m, i, j);
    }

    static String judgeType(int[] m, int[] n) {
        int[] midIndexM = getMidIndex(m);
        int[] midIndexN = getMidIndex(n);
        if (midIndexM[1] > midIndexM[0]) {
            if (midIndexN[1] > midIndexN[0]) {
                return D2D;
            }
            return D2S;
        }
        if (midIndexN[1] > midIndexN[0]) {
            return S2D;
        }
        return S2S;
    }

    static int[] getCopyIndex(String jt, int[] midIndexA, int[] midIndexB, int k) {
        //tmpRes中，值依次是tmpMidIndexA，tmpMidIndexB，ai，aj，bi，bj
        int[] tmpRes = new int[6];
        if (S2S.equals(jt)) {
            tmpRes[0] = midIndexA[0];
            tmpRes[1] = midIndexB[0];
        } else if (S2D.equals(jt)) {
            tmpRes[0] = midIndexA[0];
            tmpRes[1] = midIndexB[1];
        } else if (D2S.equals(jt)) {
            tmpRes[0] = midIndexA[0];
            tmpRes[1] = midIndexB[0];
            tmpRes[2] = tmpRes[0] - (k - tmpRes[1]) + 1;
            tmpRes[3] = tmpRes[0] + 1;
            tmpRes[4] = tmpRes[1];
            tmpRes[5] = k + 1;
        } else {
            tmpRes[0] = midIndexA[0];
            tmpRes[1] = midIndexB[1];
        }
        return tmpRes;
    }
}
