package tree.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 559.N叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * 示例1：
 *
 *              1
 *            / | \
 *           3  2  4
 *         / \
 *        5   6
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 */
public class NMaxDepth {

    /**
     * T:O(n)
     * S:O(n)
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node == null) continue;

                List<Node> childNodes = node.children;
                queue.addAll(childNodes);
            }
        }
        return depth;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
