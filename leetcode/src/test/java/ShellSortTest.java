import org.junit.Test;

/**
 * @author ljh
 * @date 2020-09-09 11:01
 */
public class ShellSortTest {

    public void shellSort(int[] src) {

        for (int gaps = src.length / 2; gaps > 0; gaps /= 2) {

            for (int i = gaps; i < src.length; i++) {
                int tmp = src[i];
                int j = i - gaps;
                while (j >= 0 && tmp < src[j]) {
                    src[j + gaps] = src[j];
                    j -= gaps;
                }
                src[j+gaps] = tmp;

            }
        }

    }
    @Test
    public void test(){
        int src[] = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            src[i]= (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        shellSort(src);
        long end = System.currentTimeMillis();
        System.out.println("用时："+(end -start));
       // QuickSort.saymsg(src);
    }
}
