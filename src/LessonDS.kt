/**
 *
 * 数据结构
 *
 * @author Benhero
 * @date   2021/11/30
 */
object LessonDS {

    @JvmStatic
    fun main(args: Array<String>) {
        maxSubArray2()
    }

    fun generate() {
        generate(6).log()
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */
    fun generate(numRows: Int): List<List<Int>> {
        // 按照杨辉三角原理去计算除了头尾之外的即可
        val list = mutableListOf<List<Int>>()
        for (i in 0 until numRows) {
            val jj = mutableListOf<Int>()
            list.add(jj)
            for (j in 0..i) {
                if (j == 0 || j == i) {
                    jj.add(1)
                } else {
                    jj.add(list[i - 1][j - 1] + list[i - 1][j])
                }
            }
        }
        return list
    }

    fun matrixReshape() {
        "[".log()
        matrixReshape(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 2, 2).forEach {
            it.log()
        }
        "]".log()
    }

    /**
     * 566. 重塑矩阵
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，
     * 但保留其原始数据。
     * 给你一个由二维数组 mat 表示的m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     */
    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        // 1. 循环迭代原始数组去填充新的矩阵
//        if (mat[0].size * mat.size != r * c) {
//            return mat
//        }
//        val result = Array(r) { IntArray(c) }
//        var x = 0
//        var y = 0
//        result[y] = IntArray(c)
//        mat.forEach { m ->
//            m.forEach {
//                result[y][x] = it
//                x++
//                if (x >= c) {
//                    x = 0
//                    y++
//                }
//            }
//        }

        // 2. 把原有的看成一维数组去计算
        val x = mat[0].size
        val y = mat.size
        if (x * y != r * c) {
            return mat
        }
        val result = Array(r) { IntArray(c) }
        for (i in 0 until x * y) {
            result[i / c][i % c] = mat[i / y][i % y]
        }

        return result
    }

    fun maxSubArray2() {
        maxSubArray2(intArrayOf(1, 5, 2, 4, 3, 3, 4)).log()
    }

    /**
     * 返回最长的递增子序列（不需要连续）的长度
     */
    fun maxSubArray2(nums: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        nums.reverse()
        nums.forEach {
            var max = 0
            map.forEach { k, v ->
                if (it <= k) {
                    max = Math.max(v, max)
                }
            }
            map[it] = max + 1
        }
        var max = 0
        map.forEach { t, u ->
            println("$t : $u")
        }
        map.values.forEach {
            max = Math.max(max, it)
        }
        return max
    }

    fun maxSubArray() {
        // 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        // 输出：6
        // 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

        // 输入：nums = [1]
        // 输出：1

        // 输入：nums = [0]
        // 输出：0

        // 输入：nums = [-1]
        // 输出：-1

        // 输入：nums = [-100000]
        // 输出：-100000
    }

    /**
     * 53. 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */
//    fun maxSubArray(nums: IntArray): Int {
//
//    }

    fun maxProfit() {
        // 输入：[7,1,5,3,6,4]
        // 输出：5
        // 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
        // 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
        println(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))

        // 输入：prices = [7,6,4,3,1]
        // 输出：0
        // 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
        println(maxProfit(intArrayOf(7, 6, 4, 3, 1)))

        println(maxProfit(intArrayOf(5, 1, 1, 2, 3, 0)))

    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第i个元素prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
     * 设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     */
    fun maxProfit(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var result = 0
        prices.forEach {
            if (min > it) {
                min = it
            } else {
                result = Math.max(result, it - min)
            }
        }
        return result
    }

    fun intersect() {
        // 输入：nums1 = [1,2,2,1], nums2 = [2,2]
        // 输出：[2,2]
        println(intersect(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)).contentToString())

        // 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        // 输出：[4,9]
        println(intersect(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4)).contentToString())
    }

    /**
     * 350. 两个数组的交集 II
     * 给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。
     * 可以不考虑输出结果的顺序。
     */
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val map = HashMap<Int, Int>()
        nums1.forEach {
            val value = map[it]
            map[it] = if (value != null) value + 1 else 1
        }

        val list = mutableListOf<Int>()
        nums2.forEach {
            val value = map[it]
            if (value != null && value != 0) {
                map[it] = value - 1
                list.add(it)
            }
        }

        return list.toIntArray()
    }

    fun merge() {
        // 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        // 输出：[1,2,2,3,5,6]
        // 解释：需要合并 [1,2,3] 和 [2,5,6] 。
        // 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。

        // 输入：nums1 = [1], m = 1, nums2 = [], n = 0
        // 输出：[1]
        // 解释：需要合并 [1] 和 [] 。
        // 合并结果是 [1] 。

        // 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
        // 输出：[1]
        // 解释：需要合并的数组是 [] 和 [1] 。
        // 合并结果是 [1] 。
        // 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。

        merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
        merge(intArrayOf(1), 1, intArrayOf(), 0)
        merge(intArrayOf(0), 0, intArrayOf(1), 1)
        merge(intArrayOf(4, 5, 6, 0, 0, 0), 3, intArrayOf(1, 2, 3), 3)
    }

    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
     * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        // 1. 正向双指针
//        var l = 0
//        var r = 0
//        var index = 0
//        val result = IntArray(m + n)
//        while (index < m + n) {
//            val lv = if (l < m) nums1[l] else Int.MAX_VALUE
//            val rv = if (r < n) nums2[r] else Int.MAX_VALUE
//            result[index] =
//                if (lv <= rv) {
//                    l++
//                    lv
//                } else {
//                    r++
//                    rv
//                }
//            index++
//        }
//        System.arraycopy(result, 0, nums1, 0, m + n)
//        println(nums1.contentToString())

        // 2. 逆向双指针
        var l = m - 1
        var r = n - 1
        var index = m + n - 1
        while (index >= 0) {
            val lv = if (l >= 0) nums1[l] else Int.MIN_VALUE
            val rv = if (r >= 0) nums2[r] else Int.MIN_VALUE
            nums1[index] =
                if (lv <= rv) {
                    r--
                    rv
                } else {
                    l--
                    lv
                }
            index--
        }
        println(nums1.contentToString())
    }

    fun twoSum() {
        // 输入：nums = [2,7,11,15], target = 9
        // 输出：[0,1]
        // 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
        println(twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())

        // 输入：nums = [3,2,4], target = 6
        // 输出：[1,2]
        println(twoSum(intArrayOf(3, 2, 4), 6).contentToString())

        // 输入：nums = [3,3], target = 6
        // 输出：[0,1]
        println(twoSum(intArrayOf(3, 3), 6).contentToString())
    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { index, i ->
            val div = target - i
            val divIndex = map[div]
            if (divIndex != null) {
                return intArrayOf(divIndex, index)
            }
            map[i] = index
        }
        return intArrayOf()
    }

    fun containsDuplicate() {
        // 输入: [1,2,3,1]
        // 输出: true

        // 输入: [1,2,3,4]
        // 输出: false

        // 输入: [1,1,1,3,3,4,3,2,4,2]
        // 输出: true

        println(containsDuplicate(intArrayOf(1, 2, 3, 1)))
        println(containsDuplicate(intArrayOf(1, 2, 3, 4)))
        println(containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))

    }

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    fun containsDuplicate(nums: IntArray): Boolean {
        nums.sort()
        for (i in 0 until nums.size - 1) {
            if (nums[i] == nums[i + 1]) {
                return true
            }
        }
        return false

//        val set = HashSet<Int>()
//        nums.forEach {
//            if (set.contains(it)) {
//                return true
//            } else {
//                set.add(it)
//            }
//        }
//        return false
    }

    class Node(var value: Int) {
        var next: Node? = null

        fun log() {
            print("[")
            print(value)
            var n: Node? = next
            while (n != null) {
                print(" , ")
                print(n.value)
                n = n.next
            }
            println("]")
        }
    }

    fun arrayToNodeList(array: IntArray): Node? {
        val head: Node = Node(-1)
        var index: Node = head
        array.forEach {
            index.next = Node(it)
            index = index.next!!
        }
        return head.next
    }
}