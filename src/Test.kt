import java.util.*
import kotlin.math.sqrt

/**
 *
 *
 * @author Benhero
 * @date   2021/4/27
 */
object Test {

    private fun random() {
        val randomBox = RandomBox(intArrayOf(1, 2, 3, 4, 5).asList(), true)
        for (i in 0..20) {
            println(randomBox.random())
        }
        val list = hh.values().asList()
        val randomBox1 = RandomBox(list, true)
        for (i in 0..20) {
            println(randomBox1.random()?.name)
        }
    }

    enum class hh {
        A, B, C, D, E, F
    }

    /**
     * 随机盒子
     * 返回指定队列里的数据，且周期内不重复返回
     * @param list 指定队列
     * @param isRecycled 是否循环复用：若随机取出全部数据后，是否重新开始；否则返回null
     */
    class RandomBox<T>(list: List<T>, val isRecycled: Boolean) {
        val src = ArrayList<T>(list.size)
        val linkedList = LinkedList(list)

        init {
            list.forEach {
                src.add(it)
            }
        }

        fun random(): T? {
            if (linkedList.isEmpty()) {
                if (isRecycled) {
                    linkedList.addAll(src)
                } else {
                    return null
                }
            }
            val random = linkedList.random()
            linkedList.remove(random)
            return random
        }
    }

    /**
     * https://leetcode-cn.com/problems/divide-two-integers/
     * 29. 两数相除
     * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数dividend除以除数divisor得到的商。
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -24
     */
    fun divide() {
        println(divide(-12, -3))
        println(divide(1, 1))
        println(divide(Int.MIN_VALUE, -1))
        println(divide(1038925803, -2147483648))
    }

    fun divide(dividend: Int, divisor: Int): Int {
        var result = 0
        val revert = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)
        var a = dividend
        val b = divisor
        if (b == Int.MIN_VALUE) {
            return 0
        }
        if (a == b) {
            return 1
        }
        if (a > 0 && b < 0) {
            while (a >= -b) {
                result++
                a += b
            }
        } else if (a < 0 && b > 0) {
            while (a <= -b) {
                result++
                a += b
            }
        } else if (a < 0 && b < 0) {
            while (a <= b) {
                if (result == Int.MAX_VALUE) {
                    return Int.MAX_VALUE
                }
                result++
                a -= b
            }
        } else {
            while (a >= b) {
                result++
                a -= b
            }
        }

        return result * if (revert) -1 else 1
    }

    /**
     * https://leetcode-cn.com/problems/implement-strstr/
     * 28. 实现 strStr()
     *
     * 实现strStr()函数。
     * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
     * 如果不存在，则返回 -1 。
     */
    fun strStr() {
        println(strStr("hello", "ll"))
        println(strStr("aaaaa", "bba"))
        println(strStr("", ""))
        println(strStr("a", "ab"))
        println(strStr("mississippi", "issipi"))
    }

    fun strStr(a: String, b: String): Int {
        val aLength = a.length
        val bLength = b.length

        for (i in 0 until aLength) {
            var flag = true
            for (j in 0 until bLength) {
                if (i + j >= aLength || a[i + j] != b[j]) {
                    flag = false
                    break
                }
            }
            if (flag) {
                return i
            }
        }
        return if (bLength == 0) {
            0
        } else {
            -1
        }
    }


//    /**
//     * https://leetcode-cn.com/problems/4sum/
//     * 18. 四数之和
//     * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？
//     * 找出所有满足条件且不重复的四元组。
//     * 注意：答案中不可以包含重复的四元组。
//     */
//    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
//
//    }
//
//    fun removeDuplicates() {
//        removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4))
//    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * 26. 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var p = 0
        var q = 1
        while (q < nums.size) {
            if (nums[p] != nums[q]) {
                //112
                nums[p + 1] = nums[q]
                p++
            }
            q++
        }
//        println(nums.contentToString() + " : " + (p + 1))
        return p + 1
    }

    fun removeElement() {
        println(removeElement(intArrayOf(), 0))
        println(removeElement(intArrayOf(1), 1))
        println(removeElement(intArrayOf(3, 2, 2, 3), 3))
        println(removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2))
    }

    /**
     * https://leetcode-cn.com/problems/remove-element/
     * 27. 移除元素
     * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    fun removeElement1(nums: IntArray, value: Int): Int {
        // 找到相同元素，将右边的都往左移
        // 问题：移动次数过多了，比如只有首位相同时
        var flag = 0
        nums.forEachIndexed { index, i ->
            nums[index - flag] = i
            if (i == value) {
                flag++
            }
        }
//        println(nums.contentToString())
        return nums.size - flag
    }

    fun removeElement(nums: IntArray, value: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            if (nums[left] == value) {
                swap(nums, left, right)
                right--
            } else {
                left++
            }
        }
//        println(nums.contentToString())
        return right + 1
    }

    private fun swap(nums: IntArray, left: Int, right: Int) {
        val temp = nums[left]
        nums[left] = nums[right]
        nums[right] = temp
    }

    private fun testThreeSumClosest() {
//        println("结果: " + threeSumClosest(intArrayOf(0, 2, 1, -3), 1))
        println("结果: " + threeSumClosest(intArrayOf(1, 1, -1, -1, 3), -1))
    }

    /**
     * https://leetcode-cn.com/problems/3sum-closest/submissions/
     * 16. 最接近的三数之和
     * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        println(nums.contentToString())
        println()
        var result = if (target >= 0) Int.MAX_VALUE else Int.MIN_VALUE
        for (a in 0..(nums.size - 3)) {
            var left = a + 1
            var right = nums.size - 1

            println("a = " + a + " : " + nums[a])
            while (right > left) {
                val sum = nums[a] + nums[left] + nums[right]
                // 累加值 - 目标 < 上次结果 - 目标
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum
                    println("更新: " + sum + " : " + nums[a] + " : " + nums[left] + " : " + nums[right])
                }
                if (sum > target) {
                    right--
                } else {
                    left++
                }
            }
        }
        return result
    }


    var li = ListNode(5)
    var v = li.`val`

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        fun log() {
            print("[")
            print(`val`)
            var n: ListNode? = next
            while (n != null) {
                print(" , ")
                print(n.`val`)
                n = n.next
            }
            print("]")
        }
    }

    fun testMergeTwoLists() {
        //输入：l1 = [1,2,4], l2 = [1,3,4]
        //输出：[1,1,2,3,4,4]

        //输入：l1 = [], l2 = []
        //输出：[]

        //输入：l1 = [], l2 = [0]
        //输出：[0]

//        arrayToListNode(intArrayOf(1, 4, 7, 19, 828))
        mergeTwoLists(arrayToListNode(intArrayOf()), arrayToListNode(intArrayOf()))?.log()
        mergeTwoLists(arrayToListNode(intArrayOf()), arrayToListNode(intArrayOf(5)))?.log()
        mergeTwoLists(arrayToListNode(intArrayOf(1, 2, 4)), arrayToListNode(intArrayOf(1)))?.log()
        mergeTwoLists(arrayToListNode(intArrayOf(1, 4, 7, 19, 828)), arrayToListNode(intArrayOf(5)))?.log()
    }

    private fun arrayToListNode(intArray: IntArray): ListNode? {
        if (intArray.isEmpty()) {
            return null
        }
        val head = ListNode(intArray[0])
        var cur = head
        for (i in 1 until intArray.size) {
            cur.next = ListNode(intArray[i])
            cur = cur.next!!
        }
        return head
    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2

        val preHead = ListNode(Int.MIN_VALUE)
        var result = preHead

        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                result.next = l1
                l1 = l1.next
            } else {
                result.next = l2
                l2 = l2.next
            }
            result = result.next!!
        }

        result.next = l1 ?: l2
        return preHead.next
    }

    fun testSearchInsert() {
//        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 5)) // 2
//        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
//        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 7)) // 4
//        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 0)) // 0
//        println("结果: " + searchInsert(intArrayOf(1), 0)) // 0
//        println("结果: " + searchInsert(intArrayOf(1), 2)) // 1
//        println("结果: " + searchInsert(intArrayOf(1, 3), 1)) // 0
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        if (nums.isEmpty()) {
            return 0
        }
        if (target < nums[l]) {
            return 0
        }
        if (target > nums[r]) {
            return r + 1
        }
        var index = nums.size / 2
        while (l != index) {
            if (target == nums[index]) {
                return index
            } else if (target < nums[index]) {
                r = index

            } else {
                l = index
            }
            index = (l + r) / 2
        }
        return if (nums[l] == target) index else index + 1
    }

    fun testMaxSubArray() {
//        输入：nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
//        输出：6
//        解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

//        输入：nums = [1]
//        输出：1

//        输入：nums = [0]
//        输出：0

//        输入：nums = [-1]
//        输出：-1

//        输入：nums = [-100000]
//        输出：-100000
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     */
    fun maxSubArray(nums: IntArray): Int {
        return 0
    }

    fun testLengthOfLastWord() {
        // 输入：s = "Hello World"
        // 输出：5
        println(lengthOfLastWord("Hello World"))

        // 输入：s = "   fly me   to   the moon  "
        // 输出：4
        println(lengthOfLastWord("   fly me   to   the moon  "))

        // 输入：s = "luffy is still joyboy"
        // 输出：6
        println(lengthOfLastWord("luffy is still joyboy"))

//        val split = "luffy is still joyboy".split(" ")
//        println(split)
    }

    /**
     * 58. 最后一个单词的长度
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     */
    fun lengthOfLastWord(s: String): Int {
        val l = s.length
        var result = 0
        for (a in 0 until l) {
            val isLetter = s[l - a - 1] != ' '

            if (isLetter) {
                result++
            } else if (result != 0) {
                return result
            }
        }
        return result
    }

    fun testPlusOne() {
        // 输入：digits = [1,2,3]
        // 输出：[1,2,4]
        // 解释：输入数组表示数字 123。
        println(plusOne(intArrayOf(1, 2, 3)).toList())

//        // 输入：digits = [4,3,2,1]
//        // 输出：[4,3,2,2]
//        // 解释：输入数组表示数字 4321。
        println(plusOne(intArrayOf(4, 3, 2, 1)).toList())

//        // 输入：digits = [0]
//        // 输出：[1]
        println(plusOne(intArrayOf(0)).toList())

        println(plusOne(intArrayOf(9)).toList())

        println(plusOne(intArrayOf(9, 9)).toList())
    }

    /**
     * 66. 加一
     *
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    fun plusOne(digits: IntArray): IntArray {
        var increase = false
        for (index in digits.indices) {
            val i = digits.size - 1 - index
            if (index == 0) {
                if (digits[digits.size - 1] == 9) {
                    digits[digits.size - 1] = 0
                    increase = true
                } else {
                    digits[digits.size - 1] = digits[digits.size - 1] + 1
                    break
                }
            } else {
                if (increase) {
                    if (digits[i] + 1 == 10) {
                        digits[i] = 0
                        increase = true
                    } else {
                        digits[i] = digits[i] + 1
                        increase = false
                    }
                } else {
                    break
                }
            }
        }
        if (increase) {
            val intArray = IntArray(digits.size + 1)
            intArray[0] = 1
            for (i in 1 until intArray.size) {
                intArray[i] = digits[i - 1]
            }
            return intArray
        }
        return digits
    }

    fun plusOne2(digits: IntArray): IntArray {
        val n = digits.size
        for (i in n - 1 downTo 0) {
            if (digits[i] != 9) {
                ++digits[i]
                for (j in i + 1 until n) {
                    digits[j] = 0
                }
                return digits
            }
        }

        // digits 中所有的元素均为 9
        val ans = IntArray(n + 1)
        ans[0] = 1
        return ans
    }

    fun testAddBinary() {
        // 输入: a = "11", b = "1"
        // 输出: "100"
        println(addBinary("11", "1"))

        // 输入: a = "1010", b = "1011"
        // 输出: "10101"
        println(addBinary("1010", "1011"))
    }

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     */
    fun addBinary(a: String, b: String): String {
        var index = 0
        var increase = false
        val result = StringBuilder()
        while (a.length > index || b.length > index) {
            val aa = if (a.length > index) {
                a[a.length - 1 - index]
            } else {
                '0'
            }

            val bb = if (b.length > index) {
                b[b.length - 1 - index]
            } else {
                '0'
            }
            if (aa == '0' && bb == '0') {
                result.append(if (increase) '1' else '0')
                increase = false
            } else if (aa == '1' && bb == '1') {
                result.append(if (increase) '1' else '0')
                increase = true
            } else {
                result.append(if (increase) '0' else '1')
            }
            index++
        }

        if (increase) {
            result.append('1')
        }
        return result.reverse().toString()
    }

    fun testMySqrt() {
        // 输入：x = 4
        // 输出：2

        // 输入：x = 8
        // 输出：2
        // 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。

        println(sqrt(Int.MAX_VALUE.toDouble()).toInt())

        checkMySqrt(0)
        checkMySqrt(1)
        checkMySqrt(4)
        checkMySqrt(8)
        checkMySqrt(2147483647)
        checkMySqrt(2147395599)
    }

    fun checkMySqrt(x: Int) {
        println("" + sqrt(x.toDouble()).toInt() + " : " + mySqrt(x))
    }

    /**
     * 69. Sqrt(x)
     * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     */
    fun mySqrt(x: Int): Int {
        var l = 0
        var r = 46340
        while (l < r - 1) {
            val mid = (l + r) / 2
            if (mid * mid < x) {
                l = mid
            } else {
                r = mid
            }
        }
        return if (r * r > x) l else r
    }

    private fun testMyLinkedList() {
        val linkedList = MyLinkedList()
//        linkedList.addAtHead(1)
//        linkedList.addAtTail(3)
//        linkedList.addAtIndex(1, 2)   //链表变为1-> 2-> 3
//        linkedList.head?.log()
//        println(linkedList.get(1))            //返回2
//        linkedList.deleteAtIndex(1)  //现在链表是1-> 3
//        println(linkedList.get(1))            //返回3

//        println("====")
//        println("** " + linkedList.get(0))
//        println("** " + linkedList.get(1))
//        println("** " + linkedList.get(2))
//        println("** " + linkedList.get(3))
//        println("** " + linkedList.get(4))
//        println("====")
//        linkedList.addAtHead(100)
//        linkedList.addAtHead(105)
//        linkedList.head?.log()
//        linkedList.addAtTail(19)
//        linkedList.head?.log()

        linkedList.addAtIndex(0, 10)
        linkedList.addAtIndex(0, 20)
        linkedList.addAtIndex(1, 30)
        linkedList.head?.log()

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

    /**
     * 707. 设计链表
     * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val和next。val是当前节点的值，next是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
     */
    class MyLinkedList {
        var head: Node? = null

        /**
         * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
         */
        fun get(index: Int): Int {
            var cur = 0
            var temp = head
            while (index != cur) {
                temp = temp?.next
                cur++
            }
            return temp?.value ?: -1
        }

        /**
         * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         */
        fun addAtHead(`val`: Int) {
            if (head == null) {
                head = Node(`val`)
            } else {
                val temp = Node(`val`)
                temp.next = head
                head = temp
            }
        }

        /**
         * 将值为 val 的节点追加到链表的最后一个元素。
         */
        fun addAtTail(`val`: Int) {
            var temp = head
            while (temp?.next != null) {
                temp = temp.next
            }
            if (temp == null) {
                head = Node(`val`)
            } else {
                temp.next = Node(`val`)
            }
        }

        /**
         * 在链表中的第index个节点之前添加值为val 的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。
         * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
         */
        fun addAtIndex(index: Int, `val`: Int) {
            if (index < 0) {
                addAtTail(`val`)
                return
            } else if (index == 0) {
                addAtHead(`val`)
                return
            }
            var cur = 0
            var temp = head
            var last: Node? = temp
            while (index != cur) {
                last = temp
                temp = temp?.next
                cur++
            }
            if (temp != null || cur == index) {
                last?.next = Node(`val`)
                last?.next?.next = temp
            }
        }

        /**
         * 如果索引 index 有效，则删除链表中的第 index 个节点。
         */
        fun deleteAtIndex(index: Int) {
            var cur = 0
            var temp = head
            var last: Node? = temp
            while (index != cur) {
                last = temp
                temp = temp?.next
                cur++
            }
            if (index == 0) {
                head = head?.next
            } else if (temp != null || cur == index) {
                last?.next = temp?.next
            }
        }
    }
}