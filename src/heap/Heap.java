package heap;

import java.util.Arrays;

public class Heap {

    public static void swap(int[] nums,int i,int j){
        if(i != j){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    public static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index-1) / 2]){
            swap(arr,index,(index-1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr,int heapSize,int index){
        int leftChild = 2 * index + 1;
        while (leftChild < heapSize){
            int largest = (leftChild + 1) < heapSize && arr[leftChild + 1] > arr[leftChild] ? leftChild + 1 : leftChild;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index)
                break;
            swap(arr,index,largest);
            index = largest;
            leftChild = index * 2 + 1;
        }
    }

    public static void heapSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        while (heapSize > 0){
            swap(arr,0,--heapSize);
            heapify(arr,heapSize,0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,1,5,2,5,7,5,6};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
