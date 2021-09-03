package tree;

import java.util.List;

/**
 * N叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * 示例 1：
 *
 *                  1
 *                / | \
 *              3   2  4
 *            /  \
 *          5     6
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 *
 *                           1
 *                        / | \  \
 *                      2   3  4   5
 *                        / \  |  / \
 *                       6  7  8 9  10
 *                          |  |  |
 *                          11 12 13
 *                          |
 *                          14
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 */
public class NTreeMaxDepth {

    public static void main(String[] args) {

    }

    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;

        int max = 0;
        for (Node node : root.children) {
            max = Math.max(maxDepth(node), max);
        }
        return max;
    }

    public static final class Node {
        int val;
        List<Node> children;
    }
}
