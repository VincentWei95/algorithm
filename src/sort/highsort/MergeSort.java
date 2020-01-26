package sort.highsort;

/**
 * 归并排序的核心思想是分治（recursion），把一个复杂的问题拆分成若干个子问题来求解
 *
 * 把数组从中间划分成两个子数组，一直递归地把子数组分成更小的子数组，直到子数组里面只有一个元素
 * 依次按照递归的返回顺序，不断地合并排好序的子数组，直到最后把整个数组的顺序排好
 * 
 * 时间复杂度：O(N*logN)
 *
 * 归并算法是一个不断递归的过程，假设数组的元素个数是n
 * 时间复杂度是T(n)的函数：T(n) = 2 * T(n/2) + O(n)
 *
 * 对于规模为n的问题，一共要进行log(n)层的大小切分
 * 每一层的合并复杂度都是O(n)
 * 所以整体的复杂度就是O(nlogn)
 *
 * 空间复杂度：O(n)
 *
 * 由于合并n个元素需要分配一个大小为n的额外数组，合并完成之后，这个数组的空间就会被释放
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = { 23, 47, 81, 95, 7, 14, 39, 55, 62, 74 };
		MergeSort sort = new MergeSort();
		int[] resultArr = sort.sort(arr, 0, arr.length - 1);
		for (int i = 0; i < resultArr.length; i++) {
			System.out.print(resultArr[i] + " ");
		}
	}
	
	public MergeSort() { }

	public int[] sort(int[] arr, int low, int height) {
		if (low >= height) return null;

		// 将数组分成两半
		// 相比起int mid = (low + height) / 2，下面的写法的好处是能够避免出现索引大小溢出情况
		int mid = low + (height - low) / 2;

		// 这里只是将数组不断划分
		sort(arr, low, mid);
		sort(arr, mid + 1, height);

		// 最终的排序合并处理是在merge()执行
		return merge(arr, low, height, mid);
	}

	public int[] merge(int[] arr, int low, int height, int mid) {
		// 因为比较会使用到原本的数组，所以需要复制一份
		int[] copy = arr.clone();
		// 从什么位置开始修改数组
		int k = low;

		// 左半边的起始位置
		int i = low;
		// 右半边的起始位置
		int j = mid + 1;

		while (k <= height) {
			// 左半边的数处理好了，只需要将右半边的数拷贝到数组即可
			if (i > mid) {
				arr[k++] = copy[j++];
			}
			// 右半边的数处理好了，只需要将左半边的数拷贝到数组即可
			else if (j > height) {
				arr[k++] = copy[i++];
			}
			// 右边的数小于左边的数
			else if (copy[j] < copy[i]) {
				arr[k++] = copy[j++];
			}
			// 左边的数小于右边的数
			else {
				arr[k++] = copy[i++];
			}
		}
		return arr;
	}
}
