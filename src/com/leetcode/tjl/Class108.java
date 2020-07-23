import java.util.LinkedList;
import java.util.Queue;

public class Class108 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
//        int[] nums = {0,1,2,3,4,5};
        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode);


    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        //这个题居然是简单类型的？我感觉有点像红黑树的自平衡呢？只是不区分红黑节点而已
        //1.子树旋转平衡，使高度减小1；
        // 2.如果1无法达到目的，则迭代向上，直到根节点，进行选择平衡
        if (nums.length == 0) {
            return null;
        }
        int diffH = 0;
        TreeNode treeNode = new TreeNode(nums[0]);
        TreeNode preNode = treeNode;
        TreeNode rootNode = treeNode;
        TreeNode preParent = null;
        for (int i = 1; i < nums.length; i++) {

            TreeNode newNode = new TreeNode(nums[i]);
            if (preNode.left == null) {

                if (preParent == null) {
                    newNode.left = preNode;
                    if (preNode == rootNode) {
                        rootNode = newNode;
                    }
                } else {
                    if (diffH > 0) {
                        rootNode = rotateTree(rootNode);
                        diffH-=2;
                    }
                    preParent.right = newNode;
                    newNode.left = preNode;
                    diffH++;
                }
            } else {
                preNode.right = newNode;
                preParent = preNode;
            }
            preNode = newNode;
        }
        return rootNode;
    }

    private static TreeNode rotateTree(TreeNode rootNode) {
        TreeNode tmpRoot = rootNode.right;
        rootNode.right = tmpRoot.left;
        tmpRoot.left = rootNode;
        return tmpRoot;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
