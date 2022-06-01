package com.kotlin.dataStructure;

import com.kotlin.alg.AlgUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Demo01 {


    public static void fun1() {


        final HashMap<String, String> map = new HashMap<>();
        map.put("as", "s");

        final ArrayList<String> list = new ArrayList<>();


        HashSet<AlgUtil.Node> hash = new HashSet<AlgUtil.Node>();




    }


    public static boolean hasCycle(AlgUtil.Node head) {
        if (head == null) {
            return false;
        }

        HashSet<AlgUtil.Node> hash = new HashSet<>();
        hash.add(head);

        while (head.next != null) {
            //Returns:true if this set did not already contain the specified element
            if (!hash.add(head.next)) {
                return true;
            }

            head = head.next;

        }
        return false;
    }


    //--------------------------------------------

    public static int[] sort(int[] arr)   {
        // 对 arr 进行拷贝，不改变参数内容
        //int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    //确定pivot 并进行排序
    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        //记录大小值的分界点
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
                //难点：当 index !=i 时，index记录的是arr[i] >= arr[pivot]对应的 i的位置

            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static void main(String[] args) {
        int[] arr = {1, -12, 2, 78, -7, 12, 32, 85, 42, 125, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        //        AlgUtil.Node n1 = new AlgUtil.Node(1);
//        AlgUtil.Node n2 = new AlgUtil.Node(3);
//        AlgUtil.Node n3 = new AlgUtil.Node(5);
//
//        n1.next = n2;
//        n2.next = n3;
//       n3.next = n1;

//    System.out.println( hasCycle(n1));

    }

}
