import java.util.*

/**
 *
 *
 * @author Benhero
 * @date   2021/6/1
 */
class T1 {
    fun isPowerOfFour(n: Int): Boolean {
        if (n < 1) {
            return false
        }
        var temp = n
        while (temp % 4 == 0) {
            temp /= 4
        }
        return temp == 1
    }

    fun isPowerOfFour2(n: Int): Boolean {
        if (n == 1) {
            return true
        }
        if (n % 2 != 0 || n <= 0) {
            return false
        }
        val map = intArrayOf(
            4,
            16,
            64,
            256,
            1024,
            4096,
            16384,
            65536,
            262144,
            1048576,
            4194304,
            16777216,
            67108864,
            268435456,
            1073741824
        )
        return map.contains(n)
    }

    fun testIntToRoman() {
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
//        1 <= num <= 3999
        // "MCMXCIV"
        println(intToRoman(1994))
    }

    fun intToRoman(num: Int): String {
        val builder = StringBuilder()
        if (num >= 1000) {
            val t = num / 1000
            for (i in 0 until t) {
                builder.append("M")
            }
        }

        if (num >= 100) {
            intToRoman(builder, num % 1000 / 100, "C", "D", "M")
        }

        if (num >= 10) {
            intToRoman(builder, num % 100 / 10, "X", "L", "C")
        }

        intToRoman(builder, num % 10, "I", "V", "X")
        return builder.toString()
    }

    private fun intToRoman(builder: StringBuilder, parseNum: Int, one: String, five: String, ten: String) {
        var parseNum = parseNum
        if (parseNum < 5) {
            if (parseNum == 4) {
                builder.append(one).append(five)
            } else {
                for (i in 0 until parseNum) {
                    builder.append(one)
                }
            }
        } else {
            if (parseNum == 9) {
                builder.append(one).append(ten)
            } else {
                parseNum -= 5
                builder.append(five)
                for (i in 0 until parseNum) {
                    builder.append(one)
                }
            }
        }
    }

    fun testMaxArea(): Int {
        return maxArea2(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))
    }

    fun maxArea(array: IntArray): Int {
        // 确定的大小至少为数组的长度
//    var max = array.size - 1
        var max = 0
        for (i in 0 until array.size - 1) {
            var x = 0
            val iValue = array[i]
            if (iValue * (array.size - i - 1) <= max) {
                // 当前高度 * 余下的长度 <= 最大值，则跳出没必要的计算
                continue
            }
            for (j in i + 1 until array.size) {
                x++
                max = max.coerceAtLeast(Math.min(iValue, array[j]) * x)

            }
        }
        return max
    }

    fun maxArea2(array: IntArray): Int {
        var max = 0
        var left = 0
        var right = array.size - 1
        while (left != right) {
            val leftValue = array[left]
            val rightValue = array[right]
            val minHeight = leftValue.coerceAtMost(rightValue)
            max = max.coerceAtLeast(minHeight * (right - left))
            if (minHeight == leftValue) {
                left++
            } else {
                right--
            }
        }
        return max
    }

    fun testThreeSum() {
        //  1. 三个元素 a，b，c ，使得 a + b + c = 0
        //  2. 且不重复的三元组
        val threeSum = threeSum(intArrayOf(-1, 0, 1, 2, -1, -4))
//    val threeSum = threeSum(intArrayOf(0, 0, 0, 0))
        println(threeSum.toString())
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = ArrayList<List<Int>>()
        val set = HashSet<Int>()
        nums.forEach {
            set.add(it)
        }

        var lastIValue = 0
        var lastJValue = 0
        for (i in nums.indices) {
            val iValue = nums[i]
            if (i != 0 && lastIValue == iValue) {
                lastIValue = iValue
                continue
            }
            lastIValue = iValue

            for (j in i + 1 until nums.size) {
                val jValue = nums[j]
                val src = iValue + jValue

                if (j != 1 && lastJValue == jValue) {
                    lastJValue = jValue
                    continue
                }
                lastJValue = jValue


                if (set.contains(-src) && nums.lastIndexOf(-src) > j) {
                    result.add(arrayListOf(iValue, jValue, -src))
                }
            }
        }

        return result
    }

    /**
     * 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     */
    private fun isBracketValid(s: String): Boolean {
        val stack = Stack<Char>()
        s.forEach {
            when (it) {
                '(', '[', '{' -> {
                    stack.push(it)
                }
                ')' -> {
                    stack.ifEmpty { return false }
                    val top = stack.pop()
                    if (top != '(') {
                        return false
                    }
                }
                ']' -> {
                    stack.ifEmpty { return false }
                    val top = stack.pop()
                    if (top != '[') {
                        return false
                    }
                }
                '}' -> {
                    stack.ifEmpty { return false }
                    val top = stack.pop()
                    if (top != '{') {
                        return false
                    }
                }
                else -> {
                }
            }
        }
        return stack.isEmpty()
    }

    private fun createArray() {
        // 创建指定大小的数组
        val array1 = Array(5) { i -> i * i }

        // 创建指定类型指定大小的数组
        val array2 = IntArray(10)
        val arr5 = IntArray(6) { it }

        // 创建指定类型指定大小的空数组
        val array3 = arrayOfNulls<Int>(5)

        // 创建基本类型的数组 明确元素
        val array4 = intArrayOf(1, 2, 3, 4, 5)

        // 创建空数组
        val empty = emptyArray<Int>()

        println(empty.contentToString())
    }

    private fun bs(arr: IntArray, target: Int, begin: Int, end: Int): Int {
        if (begin == end) {
            return if (target == arr[begin]) {
                begin
            } else {
                -1
            }
        }
        val middle = (begin + end) / 2
        if (target == arr[middle]) {
            return middle
        }
        return if (arr[begin] <= arr[middle - 1]) {
            if (arr[begin] <= target && target <= arr[middle - 1]) {
                bs(arr, target, begin, middle - 1)
            } else {
                bs(arr, target, middle + 1, end)
            }
        } else {
            if (arr[middle + 1] <= target && target <= arr[end]) {
                bs(arr, target, middle + 1, end)
            } else {
                bs(arr, target, begin, middle - 1)
            }
        }
    }
}