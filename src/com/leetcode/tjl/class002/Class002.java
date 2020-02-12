package com.leetcode.tjl.class002;

public class Class002 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(0);
		myAddTwoNumbers(l1, l2, l3, 0);
		return l3;
	}

	public static ListNode myAddTwoNumbers(ListNode l1, ListNode l2, ListNode l3, int tmpPre) {
		if (l1==null & l2==null & tmpPre==0) {
			return null;
		}
		int l1Val=0;
		int l2Val=0;
		ListNode l1Next=null,l2Next=null;
		if (l1!=null) {
			l1Val=l1.val;
			l1Next=l1.next;
		}
		if (l2!=null) {
			l2Val=l2.val;
			l2Next=l2.next;
		}
		int tmpVal=l1Val + l2Val + tmpPre;
		
		int tmpNextVal = 0;
		if (tmpVal > 9) {
			tmpVal = tmpVal - 10;
			tmpNextVal = 1;
		}
		if (l3==null) {
			l3=new ListNode(0);
		}
		l3.val = tmpVal;
		ListNode nextReturn = myAddTwoNumbers(l1Next, l2Next, l3.next, tmpNextVal);
		if (nextReturn != null) {
			l3.next = nextReturn;
		}
		return l3;
	}
	
	public static void main(String[] args) {
//		ListNode l1=new ListNode(2);
//		ListNode l12=new ListNode(4);
//		ListNode l13=new ListNode(3);
//		l1.next=l12;
//		l12.next=l13;
//		
//		ListNode l2=new ListNode(5);
//		ListNode l22=new ListNode(6);
//		ListNode l23=new ListNode(4);
//		l2.next=l22;
//		l22.next=l23;
		
		ListNode l1=new ListNode(5);
		ListNode l2=new ListNode(5);
		
		ListNode l3=addTwoNumbers(l1, l2);
		
		while (l3!=null) {
			System.out.println(l3.val);
			l3=l3.next;
		}
	}
}
