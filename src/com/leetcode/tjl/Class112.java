
public class Class112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null){
            return sum==0;
        }
        int tmpSum =root.val;
        if (root.right == null && root.left == null) {
            return tmpSum == sum;
        }
        if (root.left != null) {
            boolean flag = hasPathSum(root.left, sum - tmpSum);
            if (flag) {
                return flag;
            }
        }
        boolean flag = hasPathSum(root.right, sum - tmpSum);
        if (flag) {
            return flag;
        }
        return false;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
