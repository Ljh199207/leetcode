import org.junit.Test;

/**
 * @author ljh
 * @date 2020-09-14 16:14
 */
public class QuickSortTest {

    /**
     * 快速排序
     *
     * @param src   原数组
     * @param start 开始下标
     * @param end   结束下标
     */
    public void quickSort(int[] src, int start, int end) {
        if (start < end) {
            int middle = getmiddle(src, start, end);
            quickSort(src, start, middle - 1);
            quickSort(src, middle + 1, end);
        }
    }


    public int getmiddle(int[] src, int start, int end) {
        //以数组第一个这个为中轴两边排序
        int tmp = src[start];
        while (start < end) {

            while (start < end && src[end] >= tmp) {
                end--;
            }
            //比中轴小的移到左端
            src[start] = src[end];
            while (start < end && src[start] <= tmp) {
                start++;
            }
            //比中轴大的移到右端
            src[end] = src[start];
        }
        src[start] = tmp;
        return start;
    }


    @Test
    public void test() {
        int src[] = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            src[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        quickSort(src, 0, src.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
       // QuickSort.saymsg(src);
    }
}
