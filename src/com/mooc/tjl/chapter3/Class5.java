package com.mooc.tjl.chapter3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import javax.xml.soap.Node;

public class Class5 {
	public static <E> void main(String[] args) {
		int status = 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		ArrayList<Integer> afterBackStatusArrayList = new ArrayList<Integer>();
		ArrayList<Integer> afterPastStatusArrayList = new ArrayList<Integer>();
//		ArrayList<Integer> lastStatusArrayList = new ArrayList<Integer>();
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < 32; i++) {
			afterBackStatusArrayList.add(0 - i);
			afterPastStatusArrayList.add(0 - i);
		}

		ArrayList<LinkedList<Integer>> afterPastStatusArrayLincked = new ArrayList<LinkedList<Integer>>();
		ArrayList<LinkedList<Integer>> afterBackStatusArrayLincked = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < 32; i++) {
			afterPastStatusArrayLincked.add(i, new LinkedList<Integer>());
			afterBackStatusArrayLincked.add(i, new LinkedList<Integer>());
		}

		afterBackStatusArrayList.set(0, status);
		queue.offer(status);
		ArrayList<Integer> pastMover = new ArrayList<Integer>();
		for (int j = 1; j <= 8; j = j << 1) {
			for (int i = 1; i <= j; i = i << 1) {
				pastMover.add((j << 1) + i);
			}
		}
		ArrayList<Integer> backMover = new ArrayList<Integer>();
		for (int i = 1; i <= 16; i = i << 1) {
			backMover.add(i);
		}

		while (!queue.isEmpty()) {
			status = queue.poll();
			for (Integer pastInteger : pastMover) {
				// �Ϸ���
				if ((pastInteger & status) == 0) {
					int afterPastMoveStatus = status ^ pastInteger;
					// ״̬Ǩ���ظ���
					LinkedList<Integer> afterPastStatusLinckedList = afterPastStatusArrayLincked
							.get(afterPastMoveStatus);
					// û�и�״̬Ǩ��
					if (!afterPastStatusLinckedList.contains(status)) {
						afterPastStatusLinckedList.add(status);

						// ���״̬
						if (afterPastMoveStatus == 31) {

							continue;
						}

						for (Integer backInteger : backMover) {
							// �Ϸ���
							if ((backInteger & afterPastMoveStatus) > 0) {
								int afterBackMoveStatus = afterPastMoveStatus ^ backInteger;
								// ״̬Ǩ���ظ���

								LinkedList<Integer> afterBackStatusLinckedList = afterBackStatusArrayLincked
										.get(afterBackMoveStatus);
								// û�и�״̬Ǩ��
								if (!afterBackStatusLinckedList.contains(afterPastMoveStatus)) {
									afterBackStatusLinckedList.add(afterPastMoveStatus);
									queue.offer(afterBackMoveStatus);
								}

							}

						}
					}
				}
			}

		}

		System.out.println(afterPastStatusArrayLincked.toString());
		System.out.println(afterBackStatusArrayLincked.toString());
		ArrayList<Integer> deepArrayList = new ArrayList<Integer>();
		System.out.println(afterPastStatusArrayLincked.get(31).toString());

		System.out.println("************************************");
		findChild(afterPastStatusArrayLincked, afterBackStatusArrayLincked, 31, deepArrayList);
		System.out.println(num);
		//��ӱ�ע
	}

	static Stack<Integer> stack = new Stack<Integer>();
	static int num=0;

	public static void findChild(ArrayList<LinkedList<Integer>> afterPastStatusArrayLincked,
			ArrayList<LinkedList<Integer>> afterBackStatusArrayLincked, Integer index, ArrayList<Integer> arrayList) {
		LinkedList<Integer> pastLinkedList = afterPastStatusArrayLincked.get(index);
		stack.push(arrayList.size());
		for (Integer integer : pastLinkedList) {
			arrayList.add(integer);
			if (integer == 0) {
				System.out.println(arrayList.toString());
				num++;
				arrayList.remove(arrayList.size() - 1);
				arrayList.remove(arrayList.size() - 1);
				continue;
			}
			LinkedList<Integer> backLinkedList = afterBackStatusArrayLincked.get(integer);
			stack.push(arrayList.size());
			for (Integer integer2 : backLinkedList) {
				arrayList.add(integer2);

				findChild(afterPastStatusArrayLincked, afterBackStatusArrayLincked, integer2, arrayList);

			}

			Integer tmpK = stack.peek();
			stack.pop();
			int tmpSize = arrayList.size();
			for (int i = 0; i < tmpSize - tmpK + 1; i++) {
				arrayList.remove(arrayList.size() - 1);
			}

		}

		Integer tmpK = stack.peek();
		stack.pop();
		int tmpSize = arrayList.size();

		for (int i = 0; i < tmpSize - tmpK + 1; i++) {
			try {
				arrayList.remove(arrayList.size() - 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}

	}
}
