package sort.highsort;

/**
 * 划分（快速排序会使用）：划分是选取一个数值作为基准值pivot，将数组中比pivot大的数据放在左边，比pivot小的数据放在右边
 */
public class Partition {

	public static void main(String[] args) {
		int maxSize = 16;
		Partition partition = new Partition(maxSize);

		for (int i = 0; i < maxSize; i++) {
			int n = (int) (Math.random() * 199);
			partition.insert(n);
		}

		partition.display();

		int pivot = 99;
		System.out.println("pivot is " + pivot);

		int partDex = partition.partitionIt(0, maxSize-1, pivot);

		System.out.println("partition is at index " + partDex);
		partition.display();
	}

	private int index;
	private int[] elements;
	
	public Partition(int max) {
		elements = new int[max];
		index = 0;
	}
	
	public void insert(int element) {
		elements[index++] = element;
	}
	
	public void display() {
		System.out.print("A = ");
		for (int i = 0; i < index; i++) 
			System.out.print(elements[i] + " ");
		System.out.println();
	}
	// 划分
	public int partitionIt(int left, int right, int pivot) {
		int leftPtr = left - 1; // 左指针，如果left=0，则在指在数组的最左边
		int rightPtr = right - 1; // 右指针，如果right=arr.length-1, 则在指在数组的最右边
		
		// 效果：比基准值pivot小的数据放在左边，比pivot大的放在右边
		while (true) {
			while (leftPtr < right && elements[++leftPtr] < pivot) // 比pivot小，不处理
				;
			while (rightPtr > left && elements[--rightPtr] > pivot) // 比pivot大，不处理
				;
			
			if (leftPtr >= rightPtr) // 左右两个指针重合表示已经结束，跳出循环 
				break;
			
			swap(leftPtr, rightPtr); // 经过左右指针的while循环，拿到了leftPtr比pivot大的索引和rightPtr比pivot小的索引，进行数据交换
		}
		return leftPtr;
	}
	
	public void swap(int leftPtr, int rightPtr) {
		int temp = elements[leftPtr];
		elements[leftPtr] = elements[rightPtr];
		elements[rightPtr] = temp;
	}
}
