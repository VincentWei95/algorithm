package stack_queue;

import java.util.Stack;

/**
 * 232.用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明：
 *
 * 你只能使用标准的栈操作 —— 也就是只有push to top,peek/pop from top,size, 和is empty操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 * 示例：
 *
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用 100 次 push、pop、peek 和 empty
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
 */
public class MyQueue {
    private final Stack<Integer> inStack = new Stack<>();
    private final Stack<Integer> outStack = new Stack<>();

    /**
     * inStack 添加数据，outStack 负责出栈
     * 当 outStack 为空时，inStack 的数据全部出栈添加到 outStack
     *
     * 1、
     * queue.push(1)
     * queue.push(2)
     *
     * inStack:
     *  ----
     * | 2 |
     * -----
     * | 1 |
     * -----
     *
     * 2、
     * queue.peek()：查看队头数据
     *
     * outStack 为空，inStack 数据全部出栈添加到 outStack，最后 outStack.peek() 就是队头数据
     *
     * outStack：
     *  ----
     * | 1 |
     * -----
     * | 2 |
     * -----
     *
     * 3、
     * queue.pop()
     *
     * outStack 弹出栈顶
     *
     * outStack：弹出数据 1
     * -----
     * | 2 |
     * -----
     */
    public MyQueue() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
