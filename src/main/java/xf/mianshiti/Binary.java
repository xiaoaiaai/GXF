package xf.mianshiti;

/**
 * 作者:小爱艾
 * 2019/7/17 19:44
 */
//二分查找的前提是：数组必须是有序的
public class Binary {

    public static void main(String[] args) {
        int[] arry = {1,2,3,4,5,6,7,8,9};
        Binary binary = new Binary();
        int index = binary(arry, 2);
        System.out.println(index);


    }

    public static int binary(int[] array,int x){
        int low = 0;
        int height = array.length-1;
        while(low<=height){
            int middle = (low + height)/2;
            if(x == array[middle]){

                return middle;
            }else if(x > array[middle]){

                low = middle + 1;

            }else {
                height = middle - 1;
            }


        }

        return 0;
    }

}
