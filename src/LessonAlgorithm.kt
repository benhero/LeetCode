/**
 *
 * 算法
 * @author Benhero
 * @date   2021/11/30
 */
object LessonAlgorithm {

    @JvmStatic
    fun main(args: Array<String>) {
        twoSum()
    }

    fun twoSum() {
        // 输入：numbers = [2,7,11,15], target = 9
        // 输出：[1,2]
        // 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
        println(twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())

        // 输入：numbers = [2,3,4], target = 6
        // 输出：[1,3]
        println(twoSum(intArrayOf(2, 3, 4), 6).contentToString())

        // 输入：numbers = [-1,0], target = -1
        // 输出：[1,2]
        println(twoSum(intArrayOf(-1, 0), -1).contentToString())

        println(twoSum(intArrayOf(2, 3, 4), 6).contentToString())
        println(twoSum(intArrayOf(3, 3), 6).contentToString())
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 给定一个已按照 非递减顺序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
     * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var l = 0
        var r = numbers.size - 1
        while (l < r) {
            val temp = numbers[l] + numbers[r]
            when {
                temp == target -> {
                    return intArrayOf(l + 1, r + 1)
                }
                temp > target -> {
                    r--
                }
                else -> {
                    l++
                }
            }
        }
        return intArrayOf(l + 1, r + 1)
    }

    fun moveZeroes() {
        // 输入: [0,1,0,3,12]
        // 输出: [1,3,12,0,0]
        moveZeroes(intArrayOf(0, 1, 0, 3, 12))
        moveZeroes(intArrayOf(1, 3, 12, 0, 0))
        moveZeroes(intArrayOf(0, 1, 0))
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     */
    fun moveZeroes(nums: IntArray): Unit {
        var zeroCount = 0
        nums.forEachIndexed { index, value ->
            if (value == 0) {
                zeroCount++
            } else if (zeroCount != 0) {
                nums[index - zeroCount] = value
                nums[index] = 0
            }
        }
        println(nums.contentToString())
    }

    fun rotate() {
        // 输入: nums = [1,2,3,4,5,6,7], k = 3
        // 输出: [5,6,7,1,2,3,4]
        // 解释:
        // 向右轮转 1 步: [7,1,2,3,4,5,6]
        // 向右轮转 2 步: [6,7,1,2,3,4,5]
        // 向右轮转 3 步: [5,6,7,1,2,3,4]
//        rotate(intArrayOf(1, 2, 3, 4, 5, 6, 7), 100)

        // 输入：nums = [-1,-100,3,99], k = 2
        // 输出：[3,99,-1,-100]
        // 解释:
        // 向右轮转 1 步: [99,-1,-100,3]
        // 向右轮转 2 步: [3,99,-1,-100]
//        rotate(intArrayOf(-1, -100, 3, 99), 2)


        rotate(intArrayOf(1, 2, 3), 0)
        rotate(intArrayOf(1, 2, 3), 1)
        rotate(intArrayOf(1, 2, 3), 2)
        rotate(intArrayOf(1, 2, 3), 3)
        rotate(intArrayOf(1, 2, 3), 4)
    }

    /**
     * 189. 轮转数组
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     */
    fun rotate(nums: IntArray, k: Int): Unit {
        // 1. 使用额外的数组
//        val size = nums.size
//        var l = Math.abs(size - k % size) % size
//        val r = (l + size - 1) % size
//        val result = IntArray(size)
//        var index = 0
//        while (r != l) {
//            result[index] = nums[l]
//            index++
//            l++
//            if (l > size - 1) {
//                l = 0
//            }
//        }
//        result[size - 1] = nums[l]
//        nums.forEachIndexed { index, i ->
//            nums[index] = result[index]
//        }
//        println(nums.contentToString())

        // 2. 使用额外的数组
        val n = nums.size
        val newArr = IntArray(n)
        for (i in 0 until n) {
            newArr[(i + k) % n] = nums[i]
        }
        System.arraycopy(newArr, 0, nums, 0, n)
        println(nums.contentToString())

        // 3. 循环链表
//        val size = nums.size
//        val l = Math.abs(size - k % size) % size
//        val list = ArrayList<Int>()
//        list.addAll(nums.asList())
//        for (i in 0 until size) {
//            if (l <= list.size - 1) {
//                nums[i] = list[l]
//                list.removeAt(l)
//            } else {
//                nums[i] = list[0]
//                list.removeAt(0)
//            }
//        }
//        println(nums.contentToString())
    }

    fun sortedSquares() {
        // 输入：nums = [-4,-1,0,3,10]
        // 输出：[0,1,9,16,100]
        // 解释：平方后，数组变为 [16,1,0,9,100]
        // 排序后，数组变为 [0,1,9,16,100]

        println(sortedSquares(intArrayOf(-4, -1, 0, 3, 10)).contentToString())
        println(sortedSquares(intArrayOf(-7, -3, 2, 3, 11)).contentToString())

        // 输入：nums = [-7,-3,2,3,11]
        // 输出：[4,9,9,49,121]
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     */
    fun sortedSquares(nums: IntArray): IntArray {
        // 1. 直接排序
//        nums.forEachIndexed { index, i ->
//            nums[index] = i * i
//        }
//        nums.sort()
//        return nums

        // 2. 双指针
        var l = 0
        var r = nums.size - 1
        val result = IntArray(nums.size)
        while (l <= r) {
            val ll = nums[l] * nums[l]
            val rr = nums[r] * nums[r]
            if (ll < rr) {
                result[r - l] = rr
                r--
            } else {
                result[r - l] = ll
                l++
            }
        }
        return result
    }

    fun search() {
        // 输入: nums = [-1,0,3,5,9,12], target = 9
        // 输出: 4
        // 解释: 9 出现在 nums 中并且下标为 4

        // 输入: nums = [-1,0,3,5,9,12], target = 2
        // 输出: -1
        // 解释: 2 不存在 nums 中因此返回 -1

//        println(search(intArrayOf(-1, 0, 3, 5, 9, 12), 9))
//        println(search(intArrayOf(-1, 0, 3, 5, 9, 12), 2))
//        println(search(intArrayOf(2, 4), 2))
//        println(search(intArrayOf(2, 4), 4))
//        println(search(intArrayOf(4), 4))
//        println(search(intArrayOf(1, 3, 5, 6), 7))

        println(
            search(
                intArrayOf(
                    1,
                    3,
                    5,
                    7,
                    9,
                    11,
                    13,
                    15,
                    17,
                    19,
                    21,
                    23,
                    25,
                    27,
                    29,
                    31,
                    33,
                    35,
                    37,
                    39,
                    41
                ), 4
            )
        )
    }

    /**
     * 704. 二分查找
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    fun search(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        println(r)
        var count = 0
        while (l <= r) {
            count++
            val mid = l + (r - l) / 2
            println("Round " + count + " : " + (nums[l]) + " : " + (nums[mid]) + " : " + (nums[r]))
            val mv = nums[mid]
            if (mv == target) {
                return mid
            }
            if (mv > target) {
                // 因为已经判断过该值了，所以要缩小范围，减少不必要的判断
                r = mid - 1
                println("rrrrr : " + l + " : " + r)
            } else {
                l = mid + 1
                println("lllll")
            }
        }
        return -1
    }

    fun solution() {
        // 输入：n = 5, bad = 4
        // 输出：4
        // 解释：
        // 调用 isBadVersion(3) -> false
        // 调用 isBadVersion(5)-> true
        // 调用 isBadVersion(4)-> true
        // 所以，4 是第一个错误的版本。

        // 输入：n = 1, bad = 1
        // 输出：1

        val solution = Solution()
        solution.firstVersion = 4
        println(solution.firstBadVersion(5))

        solution.firstVersion = 1
        println(solution.firstBadVersion(1))

        solution.firstVersion = 1702766719
        println(solution.firstBadVersion(2126753390))
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      def isBadVersion(version: Int): Boolean = {} */

    abstract class VersionControl {
//        abstract fun isBadVersion(version: Int): Boolean
    }

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     */
    class Solution : VersionControl() {
        var firstVersion: Int = 0
        fun isBadVersion(version: Int): Boolean {
//            println("Check Version : $version")
            return version >= firstVersion
        }

        fun firstBadVersion(n: Int): Int {
            var l = 1
            var r = n
            while (l < r) {
                val mid = l + (r - l) / 2
                if (isBadVersion(mid)) {
                    r = mid
                } else {
                    l = mid + 1
                }
            }
            return l
        }
    }

    fun searchInsert() {
        println("结果: " + searchInsert2(intArrayOf(1, 3, 5, 6), 5)) // 2
        println("结果: " + searchInsert2(intArrayOf(1, 3, 5, 6), 2)) // 1
        println("结果: " + searchInsert2(intArrayOf(1, 3, 5, 6), 7)) // 4
        println("结果: " + searchInsert2(intArrayOf(1, 3, 5, 6), 0)) // 0
        println("结果: " + searchInsert2(intArrayOf(1), 0)) // 0
        println("结果: " + searchInsert2(intArrayOf(1), 2)) // 1
        println("结果: " + searchInsert2(intArrayOf(1, 3), 1)) // 0
        println("结果: " + searchInsert2(intArrayOf(1, 3, 5, 6), 2)) // 1
        println("结果: " + searchInsert2(intArrayOf(1), 1)) // 0
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        while (l < r) {
            val mid = l + (r - l) / 2
            val mv = nums[mid]
            if (mv == target) {
                return mid
            }
            if (mv < target) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return if (nums[l] >= target) {
            l
        } else {
            l + 1
        }
    }

    fun searchInsert2(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            val mv = nums[mid]
            if (mv == target) {
                return mid
            }
            if (mv > target) {
                // 因为已经判断过该值了，所以要缩小范围，减少不必要的判断
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return l
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