package doublepointer.array.replenish;

/**
 * 88.合并两个有序数组：
 *
 * 给你两个递增排序的数组，你要把第二个数组合并到第一个，并使其仍然保持递增排序。两个数组中的元素个数会显式地给出，并且第一个数组的大小可以容纳下两个数组中所有的元素
 *
 * 比如说给你的两个数组是：
 *
 * 2, 4, _, _
 * 1, 3
 *
 * 它们都有 2 个元素。并且第一个数组后面有足够的空间来填充第二个数组。把第二个数组合并到第一个数组后，得到的是：
 *
 * 1, 2, 3, 4
 */
public class MergeTwoSortArray {

    public static void main(String[] args) {

    }

    /**
     * 使用双指针的方式实现，给出三个指针i，j，k
     * i指向第一个数组的最后一个元素位置，j指向第二个数组的最后一个元素位置，k指向第一个数组分配的足够空间的最后一个位置：
     *
     * 2, 4, _, _
     *    i     k
     *
     * 1, 3
     *    j
     *
     * 循环对比i和j指向位置的元素大小，如果arr1[i] > arr2[j]，将arr1[i]插入k指向的位置，i和k往前移动一位：
     *
     * 2, 4, _, 4
     * i     k
     *
     * 1, 3
     *    j
     *
     * 继续对比，arr1[i] < arr2[j]：
     *
     * 2, 4, 3, 4
     * i  k
     *
     * 1, 3
     * j
     *
     * 继续对比，arr[i] > arr2[j]
     *
     *    2, 2, 3, 4
     * i  k
     *
     * 1, 3
     * j
     *
     * 对比剩余的位置插入到前面：
     *
     * 1, 2, 3, 4
     *
     * T:O(m+n) m和n代表两个数组的长度
     * S:O(1)
     */
    private int[] mergeTwoSortArray(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = arr1.length + arr2.length - 1;
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else if (arr1[i] < arr2[j]) {
                arr1[k--] = arr2[j--];
            }
        }
        // 如果还有剩余，将剩下的元素插入到数组
        while (j >= 0) arr1[k--] = arr2[j--];

        return arr1;
    }
}
