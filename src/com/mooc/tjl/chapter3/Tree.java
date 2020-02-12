package com.mooc.tjl.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tree {
	private Object data;
	private List<Tree> childs;

	public Tree() {
		data = null;
		childs = new ArrayList();
		childs.clear();
	}

	public Tree(Object data) {
		this.data = data;
		childs = new ArrayList();
		childs.clear();
	}

	/**
	 * �������
	 * 
	 * @param tree ����
	 */
	public void addNode(Tree tree) {
		childs.add(tree);
	}

	/**
	 * �ÿ���
	 */
	public void clearTree() {
		data = null;
		childs.clear();
	}

	/**
	 * ��������� �ⷽ�����е����⣬�д�����
	 * 
	 * @return �������
	 */
	public int dept() {
		return dept(this);
	}

	/**
	 * ��������� �ⷽ�����е����⣬�д�����
	 * 
	 * @param tree
	 * @return
	 */
	private int dept(Tree tree) {
		if (tree.isEmpty()) {
			return 0;
		} else if (tree.isLeaf()) {
			return 1;
		} else {
			int n = childs.size();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				if (childs.get(i).isEmpty()) {
					a[i] = 0 + 1;
				} else {
					a[i] = dept(childs.get(i)) + 1;
				}
			}
			Arrays.sort(a);
			return a[n - 1];
		}
	}

	/**
	 * ���ص�i������
	 * 
	 * @param i
	 * @return
	 */
	public Tree getChild(int i) {
		return childs.get(i);
	}

	/**
	 * ���һ������ ���
	 * 
	 * @return
	 */
	public Tree getFirstChild() {
		return childs.get(0);

	}

	/**
	 * ����� һ�����ӽ��
	 * 
	 * @return
	 */
	public Tree getLastChild() {
		return childs.get(childs.size() - 1);
	}

	public List<Tree> getChilds() {
		return childs;
	}

	/**
	 * ��ø���������
	 * 
	 * @return
	 */
	public Object getRootData() {
		return data;
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * 
	 * @return ���Ϊ�գ�����true,���򷵻�false
	 */
	public boolean isEmpty() {
		if (childs.isEmpty() && data == null)
			return true;
		return false;
	}

	/**
	 * �ж��Ƿ�ΪҶ�ӽ��
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		if (childs.isEmpty())
			return true;
		return false;
	}

	/**
	 * �������
	 * 
	 * @return ���ĸ�
	 */
	public Tree root() {
		return this;
	}

	/**
	 * ���ø���������
	 */
	public void setRootData(Object data) {
		this.data = data;
	}

	/**
	 * ������ �ⷽ�����е����⣬�д�����
	 * 
	 * @return ���ĸ���
	 */
	public int size() {
		return size(this);
	}

	/**
	 * ������ �ⷽ�����е����⣬�д�����
	 * 
	 * @param tree
	 * @return
	 */
	private int size(Tree tree) {
		if (tree.isEmpty()) {
			return 0;
		} else if (tree.isLeaf()) {
			return 1;
		} else {
			int count = 1;
			int n = childs.size();
			for (int i = 0; i < n; i++) {
				if (!childs.get(i).isEmpty()) {
					count += size(childs.get(i));
				}
			}
			return count;
		}
	}
}
