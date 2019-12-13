package easy;

/**
 * 有序数组中求和为给定值的两个数组：
 *
 * 给你一个按递增排序的有序整数数组和一个目标值，你要找到数组里两个整数，它们的和等于目标值，返回这两个整数的下标
 *
 * 比如数组 [1, 2, 3, 6, 8, 11]，目标值为10，那么数组数值2+8=10，所以需要返回这两个数值的角标[2,5]
 *
 * 限制条件：
 * ①给你的数组有且只有一个解，而且同一个元素不能使用两次（也就是你将不能使用类似选择排序的两层嵌套循环下标遍历）
 * ②返回结果的下标要从1开始（也就是返回的下标值要+1）
 */
public class GetTwoNumToGivenValueFromSortArr {

    public static void main(String[] args) {
        GetTwoNumToGivenValueFromSortArr main = new GetTwoNumToGivenValueFromSortArr();
        int[] result = main.getTwoNumToGivenValuePointer(new int[]{1, 2, 3, 6, 8, 11}, 10);
        for (int index : result) {
            System.out.println(index);
        }
    }

    /**
     * 方式1：双指针+二分法
     *
     * 左边指针left下标递增，右边指针right下标递减
     *
     * 如果arr[left] + arr[right] > target，因为数组是递增的，也就是说无论arr[right]加其他值肯定会大于target，所以right要-1
     * 如果arr[left] + arr[right] < target，因为数组是递增的，也就是说无论arr[left]加其他值肯定会小于target，所以left要+1
     *
     * T:O(n) 只循环一次
     * S:O(1)
     */
    private int[] getTwoNumToGivenValuePointer(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] > target) {
                right--;
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                return new int[] { left + 1, right + 1 };
            }
        }
        return new int[] { -1, -1 };
    }

}
