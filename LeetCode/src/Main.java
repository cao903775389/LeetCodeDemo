public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int[] nums = {10, 9, 2,5,3,7,101,18};
        int res = Solution.lengthOfLIS(nums);
        System.out.println("最长递增子序列长度是" + res);

        int[] heap = {3,2,1,5,6,4};
        int k = 2;
        int topK = new Solution().findKthLargest(heap, k);
        System.out.println("最大的第"+ k + "个元素是" + topK);
    }
}

