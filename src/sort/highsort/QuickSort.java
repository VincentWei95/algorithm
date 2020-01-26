package sort.highsort;

/**
 * 快速排序也采用了分治(recursion)的思想
 *
 * 把原始的数组筛选成较小和较大的两个子数组，然后递归地排序两个子数组
 * 在分成较小和较大的两个子数组过程中，如何选定一个基准值尤为关键
 *
 * 快速排序：
 * 在一个数组中选取一个基准值pivot，通过划分的方式将数组以pivot为基准分为左右两部分，左边的小于pivot值，右边的大于pivot值，
 * 通过递归的方式，每次执行划分都会获取到基准值索引，通过基准值索引再对左边的数组进行划分排序，直到不能再划分，则这一边的排序结束，开始另一边数组的划分排序
 *
 *
 * 最优情况下时间复杂度：O(nlogn)
 *
 * 时间复杂度：T(n) = 2 * T(n/2) + O(n)
 *
 * 把规模大小为n的问题分解成n/2的两个子问题；和基准值进行n-1次比较，n-1次比较的复杂度是O(n)
 *
 * 最复杂情况下时间复杂度：O(n^2)
 *
 * 每次在选择基准值的时候都不幸选择了子数组里的最大或最小值
 * 其中一个子数组长度为1，另一个长度只比父数组少1
 * 所以为了避免这种情况，需要随机获取基准值
 *
 * 空间复杂度：O(logn)
 *
 * 和归并排序不同，快速排序在每次递归过程中，只需要开辟O(1)的存储空间来完成交换操作实现直接对数组修改
 * 而递归次数为logn，所以它的整体空间复杂度完全取决于压堆栈的次数
 */
public class QuickSort {

	public static void main(String[] args) {
		QuickSort sort = new QuickSort(16);
		for (int i = 0; i < 16; i++) {
			int n = (int) (Math.random() * 99);
			sort.insert(n);
		}
		sort.display();
		sort.quickSort();
		sort.display();
	}
	
	private int index;
	private int[] elements;
	
	public QuickSort(int max) {
		elements = new int[max];
		index = 0;
	}
	
	public void insert(int element) {
		elements[index] = element;
		index++;
	}
	
	public void display() {
		System.out.print("A=");
		for (int i = 0; i < index;i ++) {
			System.out.print(elements[i] + " ");
		}
		System.out.println();
	}

	public void quickSort() {
		execQuickSort(0,  index-1);
	}
	
	// 快速排序
	public void execQuickSort(int left, int right) {
		if (right-left <= 0) return;
		
		int pivot = elements[right]; // 选择最后一个作为基准值进行划分操作
		int partition = partitionIt(left, right, pivot); // 拿到基准值的索引
		execQuickSort(left, partition-1); // 递归方式不断获取到基准值对数组左边进行划分交换排序，直到左边的排序完成后，执行下面的递归对右边的数组进行划分交换排序
		execQuickSort(partition+1, right);
	}

	// 划分
	private int partitionIt(int left, int right, int pivot) {
		int leftPtr = left-1; 
		int rightPtr = right;
		while (true) {
			while (elements[++leftPtr] < pivot)
				;
			while (rightPtr > 0 && elements[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr) break;
			
			swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right);
		return leftPtr;
	}
	
	private void swap(int left, int right) {
		int temp = elements[left];
		elements[left] = elements[right];
		elements[right] = temp;
	}

}
