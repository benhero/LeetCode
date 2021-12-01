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
        maxSubArray()
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