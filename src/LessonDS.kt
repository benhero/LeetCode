import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


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
        isSymmetric()
    }

    fun isSymmetric() {
        isSymmetric(arrayToTreeNode(1, 2, 2, 3, 4, 4, 3)).log()
        isSymmetric(arrayToTreeNode(1, 2, 2, null, 3, null, 3)).log()
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        return isSymmetric(root?.left, root?.right)
    }

    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) {
            return true
        }
        if (left == null || right == null) {
            return false
        }
        return left.`val` == right.`val` && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
    }

    fun maxDepth() {
        // 二叉树：[3,9,20,null,null,15,7]
        // 深度为3
        val arrayToTreeNode = arrayToTreeNode(3, 9, 20, null, null, 15, 7)
        maxDepth(arrayToTreeNode).log()
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    var maxDepth = 0
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        dfsMaxDepth(root, 1)
        return maxDepth
    }

    fun dfsMaxDepth(node: TreeNode?, deep: Int) {
        maxDepth = Math.max(maxDepth, deep)
        if (node?.left != null) {
            dfsMaxDepth(node.left, deep + 1)
        }
        if (node?.right != null) {
            dfsMaxDepth(node.right, deep + 1)
        }
    }

    fun levelOrder() {
        // 二叉树：[3,9,20,null,null,15,7]
        // [ [3],  [9,20],  [15,7] ]
        val arrayToTreeNode = arrayToTreeNode(3, 9, 20, null, null, 15, 7)
        levelOrder(arrayToTreeNode).forEach { it.log() }
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

    fun testTraversal() {
        var ti = TreeNode(5)
        var v = ti.`val`
        // 输入：root = [1,null,2,3]
        // 输出：[1,2,3]

        // 输入：root = []
        // 输出：[]

        // 输入：root = [1]
        // 输出：[1]

        // 输入：root = [1,2]
        // 输出：[1,2]

        // 输入：root = [1,null,2]
        // 输出：[1,2]
        val root = TreeNode(1)
        root.right = TreeNode(2)
        Solution().preorderTraversal(null).log()

    }

    class Solution {
        val result = arrayListOf<Int>()

        /**
         * 144. 二叉树的前序遍历
         * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
         */
        fun preorderTraversal(root: TreeNode?): List<Int> {
            root?.`val`?.let { result.add(it) }
            root?.left?.let { preorderTraversal(it) }
            root?.right?.let { preorderTraversal(it) }
            return result
        }

        /**
         * 94. 二叉树的中序遍历
         */
        fun inorderTraversal(root: TreeNode?): List<Int> {
            root?.left?.let { inorderTraversal(it) }
            root?.`val`?.let { result.add(it) }
            root?.right?.let { inorderTraversal(it) }
            return result
        }

        /**
         * 145. 二叉树的后序遍历
         */
        fun postorderTraversal(src: TreeNode?): List<Int>? {
            var node: TreeNode? = src ?: return result

            val stack: Deque<TreeNode?> = LinkedList()
            var prev: TreeNode? = null
            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    // 深入到最左边的节点
                    stack.push(node)
                    node = node.left
                }
                // 由于上面为空时跳出，所以需要取出来最后一个左节点
                node = stack.pop()

                if (node!!.right == null || node.right === prev) {
                    // 右子树为空，直接加自身，然后将自己标记为已经加入过了，也就是prev，往上跳
                    result.add(node.`val`)
                    prev = node
                    node = null
                } else {
                    stack.push(node)
                    node = node.right
                }
            }
            return result
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun arrayToTreeNode(vararg array: Int?): TreeNode {
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

    fun testMyQueue() {
        /**
         * Your MyQueue object will be instantiated and called as such:
         * var obj = MyQueue()
         * obj.push(x)
         * var param_2 = obj.pop()
         * var param_3 = obj.peek()
         * var param_4 = obj.empty()
         */

        // 输入：
        // ["MyQueue", "push", "push", "peek", "pop", "empty"]
        // [[], [1], [2], [], [], []]
        // 输出：
        // [null, null, null, 1, 1, false]

        // 解释：
        val myQueue = MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek().log(); // return 1
        myQueue.pop().log(); // return 1, queue is [2]
        myQueue.empty().log(); // return false

    }

    /**
     * 232. 用栈实现队列
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     */
    class MyQueue() {
        val list = arrayListOf<Int>()

        /**
         * 将元素 x 推到队列的末尾
         */
        fun push(x: Int) {
            list.add(x)
        }

        /**
         * 从队列的开头移除并返回元素
         */
        fun pop(): Int {
            return if (list.isNotEmpty()) {
                val i = list[0]
                list.removeAt(0)
                i
            } else -1
        }

        /**
         * 返回队列开头的元素
         */
        fun peek(): Int {
            return if (list.isNotEmpty()) {
                val i = list[0]
                i
            } else -1
        }

        /**
         * 如果队列为空，返回 true ；否则，返回 false
         */
        fun empty(): Boolean {
            return list.isEmpty()
        }
    }

    fun isValid() {
        // 输入：s = "()"
        // 输出：true
        isValid("()").log()

        // 输入：s = "()[]{}"
        // 输出：true
        isValid("()[]{}").log()

        // 输入：s = "(]"
        // 输出：false
        isValid("(]").log()

        // 输入：s = "([)]"
        // 输出：false
        isValid("([)]").log()

        // 输入：s = "{[]}"
        // 输出：true
        isValid("{[]}").log()

        isValid("]").log()
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        s.forEach {
            when (it) {
                '(', '[', '{' -> {
                    stack.push(it)
                }
                ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false
                    }
                }
                ']' -> {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false
                    }
                }
                '}' -> {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false
                    }
                }
                else -> {

                }
            }
        }
        return stack.isEmpty()
    }

    fun deleteDuplicates() {
        // 输入：head = [1,1,2]
        // 输出：[1,2]
        deleteDuplicates(createListNodeList(1, 1, 2))?.log()

        // 输入：head = [1,1,2,3,3]
        // 输出：[1,2,3]
        deleteDuplicates(createListNodeList(1, 1, 2, 3, 3))?.log()

        deleteDuplicates(createListNodeList(1, 1, 1))?.log()
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     */
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var index = head
        while (index?.next != null) {
            if (index.`val` == index.next?.`val`) {
                index.next = index.next?.next
            } else {
                index = index.next
            }
        }
        return head
    }

    fun reverseList() {
        // 输入：head = [1,2,3,4,5]
        // 输出：[5,4,3,2,1]
        reverseList(arrayToListNodeList(intArrayOf(1, 2, 3, 4, 5)))?.log()

        // 输入：head = [1,2]
        // 输出：[2,1]
        reverseList(arrayToListNodeList(intArrayOf(2, 1)))?.log()

        // 输入：head = []
        // 输出：[]
        reverseList(arrayToListNodeList(intArrayOf()))?.log()
    }

    /**
     * 206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    fun reverseList(head: ListNode?): ListNode? {
        var cur: ListNode? = null
        var next = head
        while (next != null) {
            val temp = next.next
            next.next = cur
            cur = next
            next = temp
        }
        return cur
    }

    fun removeElements() {
        // 输入：head = [1,2,6,3,4,5,6], val = 6
        // 输出：[1,2,3,4,5]
        removeElements(arrayToListNodeList(intArrayOf(1, 2, 6, 3, 4, 5, 6)), 6)?.log()

        // 输入：head = [], val = 1
        // 输出：[]
        removeElements(arrayToListNodeList(intArrayOf()), 1)?.log()

        // 输入：head = [7,7,7,7], val = 7
        // 输出：[]
        removeElements(arrayToListNodeList(intArrayOf(7, 7, 7, 7)), 7)?.log()
    }

    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     */
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        var index = ListNode(-1)
        val temp = index
        index.next = head

        while (index.next != null) {
            if (index.next?.`val` == `val`) {
                index.next = index.next?.next
            } else {
                index = index.next!!
            }
        }

        return temp.next
    }

    fun mergeTwoLists() {
        mergeTwoLists(arrayToListNodeList(intArrayOf()), arrayToListNodeList(intArrayOf()))?.log()
        mergeTwoLists(arrayToListNodeList(intArrayOf()), arrayToListNodeList(intArrayOf(5)))?.log()
        mergeTwoLists(arrayToListNodeList(intArrayOf(1, 2, 4)), arrayToListNodeList(intArrayOf(1)))?.log()
        mergeTwoLists(arrayToListNodeList(intArrayOf(1, 4, 7, 19, 828)), arrayToListNodeList(intArrayOf(5)))?.log()
    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。
     * 新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var result = ListNode(-1)
        val head = result
        var l1 = list1
        var l2 = list2

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

        return head.next
    }

    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
            if (fast == slow) {
                return true
            }
        }
        return false
    }

    fun isAnagram() {
        // 输入: s = "anagram", t = "nagaram"
        // 输出: true
        isAnagram("anagram", "nagaram").log()

        // 输入: s = "rat", t = "car"
        // 输出: false
        isAnagram("rat", "car").log()
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }
        val map = IntArray(26)
        s.forEach {
            map[it - 'a']++
        }
        t.forEach {
            map[it - 'a']--
            if (map[it - 'a'] < 0) {
                return false
            }
        }
        return true
    }

    fun canConstruct() {
        //输入：ransomNote = "a", magazine = "b"
        //输出：false

        //输入：ransomNote = "aa", magazine = "ab"
        //输出：false

        //输入：ransomNote = "aa", magazine = "aab"
        //输出：true
        canConstruct("aa", "baa").log()
    }

    /**
     * 383. 赎金信
     * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
     * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，
     * 判断 ransomNote 能不能由 magazines 里面的字符构成。
     * 如果可以构成，返回 true ；否则返回 false 。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if (ransomNote.length > magazine.length) {
            return false
        }
//        1. HashMap存储
//        val map = HashMap<Char, Int>()
//        magazine.forEach {
//            map[it] = map[it]?.plus(1) ?: 1
//        }
//        ransomNote.forEach {
//            val value = map[it]
//            if (value == null) {
//                return false
//            } else {
//                if (value < 1) {
//                    return false
//                }
//                map[it] = value - 1
//            }
//        }
//        return true

        // 2. 数组存储
        val map = IntArray(26)
        magazine.forEach {
            map[it - 'a']++
        }
        ransomNote.forEach {
            map[it - 'a']--
            if (map[it - 'a'] < 0) {
                return false
            }
        }
        return true
    }

    fun firstUniqChar() {
        // s = "leetcode"
        // 返回 0
        firstUniqChar("leetcode").log()

        // s = "loveleetcode"
        // 返回 2
        firstUniqChar("loveleetcode").log()

    }

    /**
     * 387. 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    fun firstUniqChar(s: String): Int {
        // 记录每个字母的出现次数，然后找出第一个次数为1的
//        val map = LinkedHashMap<Char, Int>()
//        s.forEach {
//            map[it] = map[it]?.plus(1) ?: 1
//        }
//        map.keys.forEach {
//            if (map[it] == 1) {
//                it.log()
//                return s.indexOf(it)
//            }
//        }
//        return -1

        // 记录每个字母在字符串中的位置，如果非第一次出现，则置为-1
        val map = HashMap<Char, Int>()
        s.forEachIndexed { index, c ->
            if (map[c] == null) {
                // 第一次出现
                map[c] = index
            } else {
                map[c] = -1
            }
        }
        var result = -1
        map.forEach { t, u ->
            if (u != -1) {
                if (result == -1) {
                    result = u
                } else {
                    result = Math.min(result, u)
                }
            }
        }
        return result
    }

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     */
    fun setZeroes() {
        // 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
        // 输出：[[1,0,1],[0,0,0],[1,0,1]]
        setZeroes(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1)
            )
        )

        // 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
        // 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        setZeroes(
            arrayOf(
                intArrayOf(0, 1, 2, 0),
                intArrayOf(3, 4, 5, 2),
                intArrayOf(1, 3, 1, 5)
            )
        )
    }

    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size

        val row = BooleanArray(m)
        val columns = BooleanArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                val value = matrix[i][j]
                if (value == 0) {
                    row[i] = true
                    columns[j] = true
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (row[i] || columns[j]) {
                    matrix[i][j] = 0
                }
            }
        }

        matrix.forEach {
            print('[')
            for (c in it) {
                print("$c , ")
            }
            println(']')
        }
    }

    fun isValidSudoku() {
//        isValidSudoku(
//            arrayOf(
//                charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.')
//                , charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.')
//                , charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.')
//                , charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3')
//                , charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1')
//                , charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6')
//                , charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.')
//                , charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5')
//                , charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
//            )
//        ).log()

        isValidSudoku(
            arrayOf(
                charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )
        ).log()
    }

    /**
     * 36. 有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     */
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val row = Array(9) { CharArray(9) }
        val columns = Array(9) { CharArray(9) }
        val subboxes = Array(9) { CharArray(9) }
        board.forEach {
            print('[')
            for (c in it) {
                print("$c , ")
            }
            println(']')
        }
        for (i in 0..8) {
            for (j in 0..8) {
                val c = board[i][j]
                if (c == '.') {
                    continue
                }
                val index = i / 3 * 3 + j / 3
                val value = c - '0'
                val src = 0.toChar()
                val a = row[i][value - 1] != src
                val b = columns[j][value - 1] != src
                val d = subboxes[index][value - 1] != src
                if (a || b || d) {
                    // false : false : true : 6 : 3 : 4 : 6
                    println("$a : $b : $d : $i : $j : $index : $value")
                    return false
                }
                row[i][value - 1] = value.toChar()
                columns[j][value - 1] = value.toChar()
                subboxes[index][value - 1] = value.toChar()
            }
        }
        return true
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

    fun createListNodeList(vararg elements: Int): ListNode? {
        return arrayToListNodeList(elements)
    }

    fun arrayToListNodeList(array: IntArray): ListNode? {
        val head: ListNode = ListNode(-1)
        var index: ListNode = head
        array.forEach {
            index.next = ListNode(it)
            index = index.next!!
        }
        return head.next
    }

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
            println("]")
        }
    }
}