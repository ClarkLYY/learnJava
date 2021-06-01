package sortLearn;

import java.util.Arrays;

public class InsertionSort implements IMutableSorter {

    //插入排序
    public void sort(int[] arr){
        for(int i=1;i<arr.length;i++){
            //c是需要插入有序数组中的牌
            int c = arr[i];
            //j是记录要插的位置，一开始是在有序数组的最右侧
            int j = i;

            for (;j>0 && arr[j-1]>c ;j--){
                arr[j] = arr[j-1];
            }
            arr[j] = c;
        }
    }

    //冒泡排序
    void bubbleSort(int[] arr){
        for(int i=arr.length; i>0; i--){
            for (int j=0; j<i-1; j++){
                if(arr[j+1]<arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    //选择排序
    void selectionSort(int[] arr){
        for(int i=arr.length-1; i>0; i--){
            int j = maxIndex(arr,i);
            swap(arr, i, j);
        }
    }

    static private void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static private int maxIndex(int[] arr, int i){
        int max = Integer.MIN_VALUE;
        int maxIndex = i;
        for(int k=i; k>=0; k--){
            if(max < arr[k]){
                max = arr[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[] arr = {8, 1, 3, 6, 7, 2, 9, 4, 5};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.selectionSort(arr);
        Arrays.stream(arr).boxed().forEach(System.out::print);
    }

}
