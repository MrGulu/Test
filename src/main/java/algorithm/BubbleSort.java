package algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author tangwenlong
 * @date 2020-01-02
 */
@Slf4j
public class BubbleSort {
    /**
     * @param array 待排序数组
     * @param n     数组长度
     * @description 冒泡排序
     * 此为改进版，新添加了flag标记，如果一开始是有序的最外层for循环一次即可，否则会循环n-1次！！！
     * 或者基本有序的情况下，也可以减少循环次数！！！
     */
    public static void sort(int[] array, int n) {
        int i, j, flag;
        for (i = 0; i < n - 1; i++) {
            // 初始化标记为0
            flag = 0;
            // 将a[0...i]中最大的数据放在末尾
            for (j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    // 若发生交换，则设标记为1
                    flag = 1;
                }
            }
            // 若没发生交换，则说明数列已有序。
            if (flag == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 3, 7, 1, 10};
        log.info("before sort:{}", array);
        sort(array, array.length);
        log.info("after sort:{}", array);

        log.info("*******************************************************");

        int[] array2 = new int[]{1, 2, 3, 4, 5};
        sort(array2, array2.length);
        log.info(Arrays.toString(array2));
    }
}
