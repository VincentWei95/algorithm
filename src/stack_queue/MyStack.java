package stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225.用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 * 注意：
 *
 * 你只能使用队列的基本操作 —— 也就是push to back、peek/pop from front、size 和is empty这些操作。
 * 你所使用的语言也许不支持队列。你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 *
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 */
public class MyStack {
    private final Queue<Integer> queue = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        queue.add(x);
    }

    /**
     * 假设 queue 中有数据：
     *
     *  ----------------
     * | 1 | 2 | 3 | 4 |
     * -----------------
     *
     * 栈顶弹出数据即队列需要弹出数据 4，可以根据队列的 size - 1 从队头依次弹出，此时剩下在队头的就是数据 4：
     *
     *  ----------------
     * | 4 | 1 | 2 | 3 |
     * -----------------
     */
    public int pop() {
        int size = queue.size();
        size--;
        for (int i = size - 1; i >= 0; i--) {
            queue.add(queue.poll());
        }
        return queue.poll();
    }

    public int top() {
        int size = queue.size();
        size--;
        for (int i = size - 1; i >= 0; i--) {
            queue.add(queue.poll());
        }
        int result = queue.peek();
        queue.add(queue.poll());
        return result;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
