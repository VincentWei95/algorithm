package halfsearch;

/**
 * 二分搜索：
 *
 * 给你一个递增排序的整数数组 nums，和一个目标值 target。你要在数组里找到 target，然后返回它的下标。如果找不到则返回 -1
 *
 * 比如说，给你的数组是：
 *
 * -2, 0, 2, 4, 5, 8, 9
 *
 * 给你的目标值是 5。5 在这个数组中，找到后返回它的下标 4 即可
 */
public class HalfSearch {

    public static void main(String[] args) {
        int[] arr = { -2, 0, 2, 4, 5, 8, 9 };
        HalfSearch main = new HalfSearch();
        System.out.println(main.halfSearch(arr, -2));
    }

    /**
     * T:O(logn)
     * S:O(1)
     */
    private int halfSearch(int[] arr, int target) {
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

        return -1;
    }

    private int halfSearch2(int[] arr, int data) {
        int min = 0;
        int max = arr.length - 1;
        int mid = min + (max - min) / 2;

        while (arr[mid] != data) {
            if (arr[mid] > data) {
                max = mid - 1;
            } else if (arr[mid] < data) {
                min = mid + 1;
            }

            if (min > max)
                return -1;

            mid = min + (max - min) / 2;
        }
        return mid;
    }

    // 递归二分法搜索，时间复杂度：O(logn)
    private int halfSearch3(int[] arr, int min, int max, int target) {
        if (min > max) return -1;

        int mid = min + (max - min) / 2;

        if (arr[mid] == target) {
            return mid;
        } else {
            if (target > arr[mid]) {
                return halfSearch3(arr, mid + 1, max, target);
            } else {
                return halfSearch3(arr, min, mid - 1, target);
            }
        }
    }
}
