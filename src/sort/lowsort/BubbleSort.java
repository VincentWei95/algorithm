package sort.lowsort;

/**
 * 冒泡排序，相邻的两个数之间进行对比，如1位置对比2位置，2位置对比3位置，以此类推。
 * 假设排序是需求从小到大，则第一次排序完成后，最大的数值将会在最后，在往后的排序中将不会再进行处理，往后的排序以此类推。
 * 
 * 时间复杂度对比：O(1)>O(logn)>O(n)>O(n^2)
 * 
 * 时间复杂度：O(n^2)
 * 无论何时，只要看到一个循环嵌套另一个循环，就可以怀疑这个算法的时间复杂度为O(n^2)
 */
public class BubbleSort {

	public static void main(String[] args) {
		BubbleSort sort = new BubbleSort(10);

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
	
	public BubbleSort(int max) {
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
		int out, in;
		
		// 第一次循环
		// 5  3  2  4  1
		// out=4
		// in=0,elements[0]=5 > elements[1]=3
		// 3  5  2  4  1
		// in=1,elements[1]=5 > elements[2]=2
		// 3  2  5  4  1
		// in=2,elements[2]=5 > elements[3]=4
		// 3  2  4  5  1
		// in=3,elements[3]=5 > elements[4]=1
		// 3  2  4  1  5
		
		// 第二次循环
		// 3  2  4  1  5
		// out=3
		// in=0,elements[0]=3 > elements[1]=2
		// 2  3  4  1  5
		// in=1,elements[1]=3 > elements[2]=4
		// 2  3  4  1  5
		// in=2,elements[2]=4 > elements[3]=1
		// 2  3  1  4  5
		
		// 第三次循环
		// 2  3  1  4  5
		// out=2
		// in=0,elements[0]=2 > elements[1]=3
		// 2  3  1  4  5
		// in=1,elements[1]=3 > elements[2]=1
		// 2  1  3  4  5
		
		// 第四次循环
		// 2  1  3  4  5
		// out=1
		// in=0,elements[0]=2 > elements[1]=1
		// 1  2  3  4  5
		
		// 冒泡排序
	    for (out = index - 1; out > 1; out--) { // 每执行一次排序，作为最后的一个数将不会在往后的排序中进行处理
	    	for (in = 0; in < out; in++) {
	    		if (elements[in] > elements[in+1]) {
	    			int temp = elements[in];
	    			elements[in] = elements[in+1];
	    			elements[in+1] = temp;
	    		}
	    	}
	    }

	    // 顺序写法
		for (out = 0; out < index - 1; out++) {
			for (in = 0; in < index - out - 1; in++) {
				if (elements[in] > elements[in + 1]) {
					int temp = elements[in];
					elements[in] = elements[in + 1];
					elements[in + 1] = temp;
				}
			}
		}

		// 优化写法
		boolean isChanged = true;
		for (out = 0; out < index - 1 && isChanged; out++) {
			isChanged = false;

			for (in = 0; in < index - out - 1; in++) {
				if (elements[in] > elements[in + 1]) {
					isChanged = true;

					int temp = elements[in];
					elements[in] = elements[in + 1];
					elements[in + 1] = temp;
				}
			}
		}
	}
}
