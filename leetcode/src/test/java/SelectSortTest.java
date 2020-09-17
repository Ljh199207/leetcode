import com.ljh.suanfa.sort.QuickSort;
import org.junit.Test;

/**
 * @author ljh
 * @date 2020-09-07 14:53
 * 选择排序
 */
public class SelectSortTest {


    public void selectSort(int[] src) {

        for (int i = 0; i < src.length; i++) {
            int index = i;
            int min = src[i];
            for (int j = i + 1; j < src.length; j++) {
                if (min > src[j]) {
                    min = src[j];   //重置min
                    index = j;   //重置j
                }
            }
            if (index != i) {
                src[index] = src[i];
                src[i] = min;
            }
        }
    }

    @Test
    public void test(){
        int src[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            src[i]= (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        selectSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时："+(end -start));
        QuickSort.saymsg(src);
    }


}
