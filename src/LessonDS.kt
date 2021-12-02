/**
 *
 * 数据结构
 *
 * @author chenbenbin
 * @date   2021/11/30
 */
object LessonDS {

    @JvmStatic
    fun main(args: Array<String>) {
        merge()
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