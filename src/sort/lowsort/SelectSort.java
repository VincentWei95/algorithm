package sort.lowsort;

/**
 * ѡ����������ѡ����һ����������ֵ����������1λ�öԱ�2λ�ã�1λ�öԱ�3λ�ã��Դ����ơ�
 * �������������Ǵ�С�������һ��������ɺ���С��һ����������ߣ�������������н�������д���������һ��λ�û���������ֵ�����Դ����ơ�
 * 
 * ʱ�临�Ӷȣ�O(n^2)������ð������죬��Ϊֻ������һ�ν���
 */
public class SelectSort {

	public static void main(String[] args) {
		SelectSort sort = new SelectSort(10);

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
	
	public SelectSort(int max) {
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
	
	// ѡ������
	public void sort() {
		int out, in, min;
		
		for (out = 0; out < index - 1; out++) {
			min = out; // ��ʼ���Ǳ�
			
			for (in = out + 1; in < index; in++) {
				if (elements[min] > elements[in]) 
					min = in;	// ֻ�����Ǳ�
			}
			
			// ����һ������ѭ����ֻ����һ�ν�������
			if (min != out) {
				int temp = elements[out];
				elements[out] = elements[min];
				elements[min] = temp;
			}
		}
	}

}
