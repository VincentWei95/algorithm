package string;

import java.util.Stack;

/**
 * 有效的括号序列：
 *
 * 给你一个括号序列，里面包含小括号，中括号和大括号。你要判断这个括号序列是否有效。
 * 有效的括号序列要求，每个左括号都必须有一个同类的右括号与它正确配对。另外，空字符串认为是有效的括号序列
 *
 * 比如说，给你的序列是：
 *
 * ()[]{}
 *
 * 小括号/中括号/大括号的左右括号都能正确配对，因此这是一个有效的括号序列
 *
 * 再比如说给你的序列是：
 *
 * ([)]
 *
 * 这里面虽然正好有一对小括号和一对中括号，但它们的顺序不对，括号间无法正确配对，因此这不是一个有效的括号序列
 *
 * 再比如给你的序列是：
 *
 * [()]
 *
 * 则是一个有效的括号序列
 */
public class ValidBrackets {

    public static void main(String[] args) {
        String brackets = "()[]{}";

        ValidBrackets main = new ValidBrackets();
        System.out.println(main.isValidBrackets(brackets));
    }

    /**
     * 需要一个辅助栈，遇到左括号就存入栈中，遇到右括号就弹栈对比是否相同，不相同直接返回false
     *
     * T:O(n)
     * S:O(n)
     */
    private boolean isValidBrackets(String brackets) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            // 如果是左括号，存进栈中
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char left = stack.peek();
                // 如果存进栈中的左括号和右括号不相同直接返回
                if (c == ')' && left != '(') return false;
                if (c == ']' && left != '[') return false;
                if (c == '}' && left != '{') return false;

                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 入栈配对左括号的右括号，在遇到右括号时就可以直接判断是否相同
     *
     * T:O(n)
     * S:O(n)
     */
    private boolean isValidBracketsShort(String brackets) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            // 如果字符是左括号则入栈对应的右括号
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            }
            // 如果遇到字符是右括号，直接判断字符和出栈的字符是否相同即可
            else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
