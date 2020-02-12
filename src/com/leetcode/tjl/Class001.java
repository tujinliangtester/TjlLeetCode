package com.leetcode.tjl;
public class Class001 {
	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			int secondeNum=target-nums[i];
			for (int j = i+1; j < nums.length; j++) {
				
				if (nums[j] == secondeNum) {

					return new int[] { i, j };
				}
			}
		}
		return null;

	}
}
