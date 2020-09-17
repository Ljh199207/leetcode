import com.ljh.suanfa.sort.QuickSort;
import org.junit.Test;

/**
 * @author ljh
 * @date 2020-09-07 16:17
 * 插入排序，内部排序
 * 将数组看成一个有序表和一个无序表，刚开始有序表只有一个，
 * 无序有n-1个，每次从无序取出一个放入有序表中。
 */
public class InsertSortTest {


    public void insertSort(int[] src) {
        for (int i = 1; i < src.length; i++) {
            int insertVal = src[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < src[insertIndex]) {
                src[insertIndex + 1] = src[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                src[insertIndex + 1] = insertVal;
            }
        }

    }

    @Test
    public void test() {
        int src[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            src[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        insertSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
        QuickSort.saymsg(src);
    }
}
