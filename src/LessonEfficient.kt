/**
 *
 * 高效制胜
 * @author Benhero
 * @date   2021-12-17
 */
object LessonEfficient {
    @JvmStatic
    fun main(args: Array<String>) {
        canPartition()
    }

    fun canPartition() {

    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。
     * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
//    fun canPartition(nums: IntArray): Boolean {
//
//    }

    fun climbStairs() {
        // 输入： 2
        // 输出： 2
        // 解释： 有两种方法可以爬到楼顶。
        // 1.  1 阶 + 1 阶
        // 2.  2 阶
        climbStairs(2).log()

        // 输入： 3
        // 输出： 3
        // 解释： 有三种方法可以爬到楼顶。
        // 1.  1 阶 + 1 阶 + 1 阶
        // 2.  1 阶 + 2 阶
        // 3.  2 阶 + 1 阶
        climbStairs(3).log()
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     */
    fun climbStairs(n: Int): Int {
        if (n == 1) {
            return 1
        }
        // 1. 动态规划
//        var result = IntArray(n + 1)
//        result[0] = 0
//        result[1] = 1
//        result[2] = 2
//        for (i in 3..n) {
//            result[i] = result[i - 1] + result[i - 2]
//        }
//        return result[n]

        // 2. 斐波那契数列
        var a = 1
        var b = 2
        var c = 2
        for (i in 3..n) {
            c = a + b
            a = b
            b = c
        }
        return c
    }

    fun fib() {
        // 输入：2
        // 输出：1
        // 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
        fib(2).log()

        // 输入：3
        // 输出：2
        // 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
        fib(3).log()

        // 输入：4
        // 输出：3
        // 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
        fib(4).log()

        // 5
        fib(5).log()

        // 8
        fib(6).log()

    }

    /**
     * 509. 斐波那契数
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。
     * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     */
    fun fib(n: Int): Int {
        if (n == 0) {
            return 0
        } else if (n == 1) {
            return 1
        }
//        var a = 0
//        var b = 1
//        var c = 0
//        for (i in 1 until n) {
//            c = a + b
//            a = b
//            b = c
//        }
//        return c

        return fib(n - 1) + fib(n - 2)
    }


    fun fourSum() {
        // 输入：nums = [1,0,-1,0,-2,2], target = 0
        // 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//        fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0).forEach { it.log() }

        // 输入：nums = [2,2,2,2,2], target = 8
        // 输出：[[2,2,2,2]]
//        fourSum(intArrayOf(2, 2, 2, 2, 2), 8).forEach { it.log() }

        fourSum(intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3), 0).forEach { it.log() }
    }

    /**
     * 18. 四数之和
     * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
     * 0 <= a, b, c, d< n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val result = arrayListOf<List<Int>>()
        nums.sort()
        val size = nums.size
        for (a in 0..size - 4) {
            for (b in a + 1..size - 3) {
                var c = b + 1
                var d = size - 1
                while (c < d) {
                    val sum = nums[a] + nums[b] + nums[c] + nums[d]
                    if (sum == target) {
                        val element = arrayListOf(nums[a], nums[b], nums[c], nums[d])
                        if (!result.contains(element)) {
                            result.add(element)
                        }
                        c++
                    } else if (sum > target) {
                        d--
                    } else {
                        c++
                    }
                }
            }
        }
        return result
    }
}