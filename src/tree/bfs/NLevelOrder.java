package tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429.N叉树的层序遍历
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 *
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 示例 1：
 *
 *              1
 *            / | \
 *           3  2  4
 *         / \
 *        5  6
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 */
public class NLevelOrder {

    /**
     * T:O(n)
     * S:O(n)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);

                List<Node> childNodes = node.children;
                if (childNodes != null && !childNodes.isEmpty()) {
                    for (Node childNode : childNodes) {
                        queue.add(childNode);
                    }
                }
            }
            result.add(list);
        }
        return result;
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
