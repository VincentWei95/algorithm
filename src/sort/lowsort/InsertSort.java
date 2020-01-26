package sort.lowsort;

/**
 * 插入排序，指定一个数作为基值，（一般是第二个开始作为基值[elements[out]]），以基值作为分界线，基值以后默认为有序不处理，
 * 只对基值之前的数据进行排序（包括基值，用局部变量存储第一次基值数值），递减的与基值对比[elements[in-1]>=temp]，
 * 满足条件，则存储的索引[in]的值覆盖被上一个值覆盖，直到结束；
 * 剩下的空位则由基值补上[elements[in]=temp]
 *		
 * 简单说：确认基值的外层循环在递增，内层循环以基值为分界线在递减
 * 
 * 时间复杂度：O(n^2)，比冒泡排序快一倍，比选择排序略快
 */
public class InsertSort {

	public static void main(String[] args) {
		InsertSort sort = new InsertSort(10);

		sort.insert(10);
		sort.insert(3);
		sort.insert(5);
		sort.insert(6);
		sort.insert(7);
		sort.insert(9);
		sort.insert(8);
		sort.insert(2);
		sort.insert(1);
		sort.insert(4);

		sort.display();
		sort.sort();
		sort.display();
	}
	
	private int index;
	private int[] elements;
	
	public InsertSort(int max) {
		elements = new int[max];
		index = 0;
	}
	
	public void insert(int element) {
		elements[index] = element;
		index++;
	}
	
	public void display() {
		for (int i = 0; i < index; i++) {
			System.out.print(elements[i] + " ");
		}
		System.out.println();
	}
	
	public void sort() {
		int in, out;
		
		// 第一次循环
		// 5  3  2  4  1
		// out=1
		//   in
		//   temp
		// 5  3  2  4  1
		// in=1,elements[0]=5 >= 3
		//    5  2  4  1  in=0
		// 3  5  2  4  1
		
		// 第二次循环
		// 3  5  2  4  1
		// out=2
		//       in
		//       temp
		// 3  5  2  4  1
		// in=2, elements[1]=5 >= 2
		// 3     5  4  1   in=1
		// in=1,elements[0]=3 >= 2
		//    3  5  4  1   in=0
		// 2  3  5  4  1
		
		// 第三次循环
		// 2  3  5  4  1
		// out=3
		//          in
		//          temp
		// 2  3  5  4  1
		// in=3,elements[2]=5 >= 4
		// 2  3     5  1   in=2
		// in=2,elements[1]=3 >= 5
		// 2  3  4  5  1
		
		// 第四次循环
		// 2  3  4  5  1
		// out=4
		//             in
		//             temp
		// 2  3  4  5  1
		// in=4,elements[3]=5 >= 1
		// 2  3  4     5   in=3
		// in=3,elements[2]=4 >= 1
		// 2  3     4  5   in=2
		// in=2,elements[1]=3 >= 1
		// 2     3  4  5   in=1
		// in=1,elements[0]=2 >= 1
		//    2  3  4  5   in=0
		// 1  2  3  4  5
		
		// 插入排序：指定一个数作为基值
		//（一般是第二个开始作为基值[elements[out]]），以基值作为分界线，递减的与基值对比[elements[in-1]>=temp]，
		// 满足条件，则存储的索引[in]的值覆盖被上一个值覆盖，直到结束
		// 剩下的空位则由基值补上[elements[in]=temp]
		
		// 确认基值的外层循环在递增，内层循环以基值为分界线在递减
		
		// 插入排序
		for (out = 1; out < index; out++) {
			int temp = elements[out]; // 基值
			in = out; // 递减索引
			while (in > 0 && elements[in-1] >= temp) { // 基值为分界线，基值之前的每一个值与基值进行对比，满足条件则
				elements[in] = elements[in-1];
				--in;
			}
			elements[in] = temp;
		}
	}
	
	public void sorts() {
		int out, in;
		for (out = 1; out < index; out++) {
			int temp = elements[out];
			in = out;
			for (;in > 0 && elements[in-1] >= temp ; --in) {
				elements[in] = elements[in-1];
			}
			elements[in] = temp;
		}
	}

}
