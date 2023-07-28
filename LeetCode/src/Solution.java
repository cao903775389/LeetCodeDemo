import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    //300. 最长递增子序列
    //输入：nums = [10,9,2,5,3,7,101,18]
    //输出：4
    //解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    public static int lengthOfLIS(int[] nums) {
        //动态规划
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //当前位置最小子序列长度为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //从头开始比较i之前某一位置的最长子序列长度
                if (nums[i] > nums[j]) {
                    //如果当前位置比i小，那么最长子序列为当前位置的最长子序长度+1
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //215. 数组中的第K个最大元素
    //输入: [3,2,1,5,6,4], k = 2
    //输出: 5
    public static int findKthLargest(int[] nums, int k) {
        //建小根堆
        buildHeap(nums);
        //堆化后的数组
        System.out.println("堆化后的数组为：" + Arrays.toString(nums));
        //排序，将堆顶元素和最后一个元素交换，然后从首个元素开始堆化到第n-1个元素
        int i = nums.length-1;
        while (i > 0) {
            swap(nums, i, 0);
            --i;
            heapify(nums, i, 0);
        }
        //堆排序后的数组
        System.out.println("堆排序后的数组为：" + Arrays.toString(nums));
        return nums[k-1];
    }

    private static void buildHeap(int[] nums) {
        //从首个非叶子结点，向上遍历
        //若完全二叉树从 0 开始进行编号，则第一个非叶子节点为 n/2−1；若完全二叉树从 1 开始进行编号，则第一个非叶子节点为 n/2。
        for (int i = nums.length/2 - 1; i >= 0; --i) {
            heapify(nums, nums.length-1, i);
        }
    }

    private static void heapify(int[] nums, int n, int i) {
        //从数组的第i个位置开始堆化，到第n个位置开始结束，构造大根堆
        while(true) {
            //比较当前节点和他的左右子节点
            int maxPosition = i;
            int left = 2*i+1;
            int right = 2*i+2;
            if (left <= n && nums[i] < nums[left]) {
                maxPosition = left;
            }
            if (right <= n && nums[maxPosition] < nums[right]) {
                maxPosition = right;
            }
            if (maxPosition == i) {
                break;
            }
            swap(nums, i, maxPosition);
            i = maxPosition;
        }
    }
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        //窗口左侧起点
        int left = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character letter = s.charAt(i);
            if (map.containsKey(letter)) {
                //当前字母重复，查找重复的字母位置，尝试移动到重复字母+1的位置
                left = Math.max(left, map.get(letter) + 1);
            }
            map.put(letter, i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    //200. 岛屿数量
    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                //从当前坐标开始执行DFS遍历
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }
    public static void dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return;
        }
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = 2;
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public static boolean inArea(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    //2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode cur = pre;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    //226. 翻转二叉树
//    public static TreeNode invertTree(TreeNode root) {
//
//    }
}

