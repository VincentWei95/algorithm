package halfsearch;

/**
 * 35.二分搜索插入位置：
 *
 * 给你一个递增排序的整数数组 nums，和一个目标值 target。你要在数组里找到 target，然后返回它的下标。
 * 如果找不到，则返回目标值应该插入的位置的下标，要求插入目标值后，数组仍然保持有序
 */
public class HalfSearchInsertPosition {

    public static void main(String[] args) {
        int[] arr = { -2, 0, 2, 4, 5, 8, 9 };
        HalfSearchInsertPosition main = new HalfSearchInsertPosition();
        System.out.println(main.halfSearchInsertPosition(arr, 5));
    }

    /**
     *
     * T:O(logn)
     * S:O(1)
     */
    private int halfSearchInsertPosition(int[] arr, int target) {
        if (arr == null || arr.length <= 0) return -1;

        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                max = mid - 1;
            } else if (arr[mid] < target) {
                min = mid + 1;
            }
        }
        // 返回插入的位置下标
        return min;
    }
}
