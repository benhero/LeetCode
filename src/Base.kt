import java.util.*

/**
 *
 * 基础
 * @author Benhero
 * @date   2021-12-19
 */
object Base {
    @JvmStatic
    fun main(args: Array<String>) {
        val l = System.currentTimeMillis()
        test()
        "总共耗时：${(System.currentTimeMillis() - l)} 毫秒".log()
    }

    fun test() {
        maxSubArraySum()
    }

    //**************************** 动态规划 ****************************//
    /**
     * 返回最长的递增子序列（不需要连续）的长度
     */
    fun maxSubArray(nums: IntArray): Int {
        val array = IntArray(nums.size) { 1 }
        for (i in nums.size - 1 downTo 0) { // i -> 4, 3, 2, 1, 0
            for (j in i + 1 until nums.size) { // j -> i后面的值
                if (nums[j] > nums[i]) {
                    array[i] = Math.max(array[i], array[j] + 1)
                }
            }
        }
        array.log()
        return array.maxOf { it }
    }

    fun maxSubArray() {
        val nums = intArrayOf(1, 5, 2, 4, 3)
        nums.log()
        maxSubArray(nums).log()
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 连续子序列最大和
     */
    fun maxSubArraySum(nums: IntArray): Int {
        // 每个位置代表该位置作为出发点的最大值
        val array = nums.copyOf()
        for (i in nums.size - 2 downTo 0) {
            // 从倒数第二个开始数
            if (array[i + 1] > 0) {
                // 如果它的下一位的值>0，那么加上他作为自己的值
                array[i] = array[i + 1] + nums[i]
            }
        }
//        array.log()
        array.sort()
        return array[array.size - 1]
//        return array.maxOf { it }
    }

    fun maxSubArraySum() {
//        val nums = intArrayOf(3, -4, 2, -1, 2, 6, -5, 4)
        val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
//        val nums = intArrayOf(1, 1, 2, 3)
        nums.log()
        maxSubArraySum(nums).log()
    }

    //**************************** 二分法 ****************************//
    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
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

    fun searchInsert() {
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 5)) // 2
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 7)) // 4
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 0)) // 0
        println("结果: " + searchInsert(intArrayOf(1), 0)) // 0
        println("结果: " + searchInsert(intArrayOf(1), 2)) // 1
        println("结果: " + searchInsert(intArrayOf(1, 3), 1)) // 0
        println("结果: " + searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
        println("结果: " + searchInsert(intArrayOf(1), 1)) // 0
    }

    //**************************** 中序遍历 ****************************//
    fun inOrderTraverse(root: TreeNode?) {
        // 1. 循环迭代法
        var node = root
        val stack: Deque<TreeNode> = LinkedList()
        while (!stack.isEmpty() || node != null) {
            // 先把左边迭代到底
            while (node != null) {
                stack.push(node)
                node = node.left
            }
            node = stack.pop()
            node.`val`.log()

            node = node.right
        }

        // 2. 递归法
//        if (node == null) {
//            return
//        }
//        inOrderTraverse(node.left)
//        node.`val`.log()
//        inOrderTraverse(node.right)
    }


    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return levelOrder(this).toString()
        }
    }

    fun arrayToTreeNode(vararg array: Int?): TreeNode? {
        if (array.isEmpty()) {
            return null
        }
        val linkedList = LinkedList<TreeNode>()
        val root = TreeNode(array[0]!!)
        if (array.size == 1) {
            return root
        }
        linkedList.add(root)

        var i = 1
        while (i < array.size) {
            var node = array[i]?.let { TreeNode(it) }
            val removeFirst = linkedList.removeFirst()

            removeFirst.left = node
            node?.let { linkedList.add(it) }

            i++
            if (i == array.size) {
                break
            }

            node = array[i]?.let { TreeNode(it) }
            removeFirst.right = node
            i++
            node?.let { linkedList.add(it) }
        }

        return root
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     */
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        // 临时队列，用于缓存待遍历的节点
        val queue = LinkedList<TreeNode>()
        val result = arrayListOf<ArrayList<Int>>()
        if (root == null) {
            return result
        }
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = arrayListOf<Int>()
            // 用于限制缓存队列里，对于当前层需要遍历多少的问题
            val size = queue.size

            for (i in 0 until size) {
                // 移除头部节点
                val node = queue.removeFirst()
                list.add(node.`val`)
                node.left?.let {
                    // 找到下一层的节点，并加入到待遍历的队列
                    queue.add(it)
                }
                node.right?.let {
                    queue.add(it)
                }
            }
            result.add(list)
        }
        return result


//        val queue = ArrayList<TreeNode>()
//        val result = arrayListOf<ArrayList<Int>>()
//        root?.let {
//            queue.add(it)
//            result.add(arrayListOf(root.`val`))
//        }
//        while (queue.isNotEmpty()) {
//            val list = arrayListOf<Int>()
//            val size = queue.size
//
//            for (i in 0 until size) {
//                val node = queue[i]
//                node.left?.let {
//                    // 找到下一层的节点，并加入到待遍历的队列，同时添加到结果队列中
//                    queue.add(it)
//                    list.add(it.`val`)
//                }
//                node.right?.let {
//                    queue.add(it)
//                    list.add(it.`val`)
//                }
//            }
//            // 移除已经遍历完成的节点
//            for (i in 0 until size) {
//                queue.removeAt(0)
//            }
//            if (list.isNotEmpty()) {
//                result.add(list)
//            }
//        }
//        return result
    }
}