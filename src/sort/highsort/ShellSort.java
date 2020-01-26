package sort.highsort;

/**
 * 希尔排序：
 * 希尔排序是基于插入排序的，不同的地方在于，希尔排序扩大了序列对比间隔，并在这些有间隔的元素中进行插入排序，从而使数据项能大跨度的移动
 * 当这些数据项排过一趟序后，希尔排序算法减小数据项的间隔再排序，依此进行，最后是增量为1的排序。
 *
 * 时间复杂度：O(N*(logN)^2)
 * 但对于过大的数据，仍然会存在时间复杂度为O(n^2)
 */
public class ShellSort {

    public static void main(String[] args) {
        ShellSort sort = new ShellSort(10);
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
        sort.shellSort();
        sort.display();
    }

    private int index;
    private int[] elements;

    public ShellSort(int max) {
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

    public void shellSort() {
        // 排序间隔：h = 3 * h + 1 = 4，则第一次针对索引0、4、8进行插入排序
        // 第一次排序：
        // 原始序列：7 10 1 9 2 5 8 6 4 3
        //           0  1 2 3 4 5 6 7 8 9
        // 排后序列：2 10 1 9 4 5 8 6 7 3

        // 第二次排序：
        // 间隔固定，索引整体向右移动一位继续排序，即第二次针对索引1、5、9进行插入排序
        // 原始序列：2 10 1 9 4 5 8 6 7 3
        //           0 1  2 3 4 5 6 7 8 9
        // 排后序列：2 3  1 9 4 5 8 6 7 10

        // 第三次排序：
        // 间隔固定，索引整体向右移动一位继续排序，即第二次针对索引2、6进行插入排序
        // 原始序列：2 3  1 9 4 5 8 6 7 10
        //           0 1  2 3 4 5 6 7 8 9
        // 排后序列：2 3  1 9 4 5 8 6 7 10

        // 第四次排序：
        // 间隔固定，索引整体向右移动一位继续排序，即第二次针对索引3、7进行插入排序
        // 原始序列：2 3  1 9 4 5 8 6 7 10
        //           0 1  2 3 4 5 6 7 8 9
        // 排后序列：2 3  1 6 4 5 8 9 7 10
        // 完成一次增量为4的排序

        // 排序间隔：h = (h - 1) / 3 = 1
        // 继续排序...

        int in, out;

        int h = 1; // 初始序列间隔
        while (h <= index / 3) {
            h = 3 * h + 1; // 计算最大序列间隔
        }

        while (h > 0) {
            for (out = h; out < index; out++) {
                int temp = elements[out];
                in = out;

                while (in > h - 1 && elements[in - h] >= temp) {
                    elements[in] = elements[in - h];
                    in -= h;
                }
                elements[in] = temp;
            }
            h = (h - 1) / 3; // 每完成一次排序，减小序列间隔
        }
    }
}
