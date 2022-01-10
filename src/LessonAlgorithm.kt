import java.util.*


/**
 *
 * 算法
 * @author Benhero
 * @date   2021/11/30
 */
object LessonAlgorithm {

    @JvmStatic
    fun main(args: Array<String>) {
        hammingWeight()
    }

    fun hammingWeight() {
        hammingWeight(3).log()
    }

    /**
     * 191. 位1的个数
     */
    fun hammingWeight(n: Int): Int {
        var result = 0
        var nn = n
        while (nn != 0) {
            nn = nn and (nn - 1)
            result++
        }
        return result
    }

    fun isPowerOfTwo() {
        isPowerOfTwo(2).log()
        isPowerOfTwo(4).log()
    }

    /**
     * 231. 2 的幂
     */
    fun isPowerOfTwo(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0
    }

    var dr = intArrayOf(-1, 0, 1, 0)
    var dc = intArrayOf(0, -1, 0, 1)

    fun orangesRotting(grid: Array<IntArray>): Int {
        val R = grid.size
        val C = grid[0].size
        val queue: Queue<Int> = ArrayDeque()
        val depth: MutableMap<Int, Int> = HashMap()
        for (r in 0 until R) {
            for (c in 0 until C) {
                if (grid[r][c] == 2) {
                    val code = r * C + c
                    queue.add(code)
                    depth[code] = 0
                }
            }
        }
        var ans = 0
        while (!queue.isEmpty()) {
            val code = queue.remove()
            val r = code / C
            val c = code % C
            for (k in 0..3) {
                val nr = r + dr[k]
                val nc = c + dc[k]
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2
                    val ncode = nr * C + nc
                    queue.add(ncode)
                    depth[ncode] = depth[code]!! + 1
                    ans = depth[ncode]!!
                }
            }
        }
        for (row in grid) {
            for (v in row) {
                if (v == 1) {
                    return -1
                }
            }
        }
        return ans
    }

    fun updateMatrix() {
        // 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
        // 输出：[[0,0,0],[0,1,0],[0,0,0]]

        updateMatrix(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 0),
            )
        )

        // 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
        // 输出：[[0,0,0],[0,1,0],[1,2,1]]

    }

    /**
     * 542. 01 矩阵
     */
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        // 先记录所有0的位置
        val zeroList = mutableListOf<IntArray>()
        val result = Array(mat.size) { IntArray(mat[0].size) { Int.MAX_VALUE } }
        mat.forEachIndexed { row, array ->
            array.forEachIndexed { column, value ->
                if (value == 0) {
                    zeroList.add(intArrayOf(column, row))
                    result[row][column] = 0
                }
            }
        }

        while (zeroList.isNotEmpty()) {
            val cell = zeroList.removeAt(0)
            for (i in 0 until 4) {
                val newColumn = cell[0] + dColumn[i]
                val newRow = cell[1] + dRow[i]
                if (isSafe(result, newRow, newColumn)) {
                    // 位置安全
                    val value = result[newRow][newColumn]
                    if (value != 0) {
                        // 原本是1的值

                    }

                }
            }
        }


        zeroList.forEach { it.log() }
        return arrayOf()
    }

    fun updateMatrix2(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size
        val dist = Array(m) { IntArray(n) }
        val seen = Array(m) { BooleanArray(n) }
        val zeroList = mutableListOf<IntArray>()
        // 将所有的 0 添加进初始队列中
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (mat[i][j] == 0) {
                    zeroList.add(intArrayOf(i, j))
                    seen[i][j] = true
                }
            }
        }

        // 广度优先搜索
        while (zeroList.isNotEmpty()) {
            val cell = zeroList.removeAt(0)
            val i = cell[0]
            val j = cell[1]
            for (d in 0..3) {
                val ni: Int = i + dColumn[d]
                val nj: Int = j + dRow[d]
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1
                    zeroList.add(intArrayOf(ni, nj))
                    seen[ni][nj] = true
                }
            }
        }
        return dist
    }

    fun connect() {
        connect(arrayToNodeList2(1, 2, 3, 4, 5, 6, 7))?.log()
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     */
    fun connect(root: Node?): Node? {
        val stack = mutableListOf<Node>()
        stack.add(root!!)
        val nextStack = mutableListOf<Node>()
        var lastNode: Node? = null
        while (stack.isNotEmpty() || nextStack.isNotEmpty()) {

            if (stack.isNotEmpty()) {
                val pop = stack[0]
                stack.removeAt(0)
                lastNode?.next = pop
                lastNode = pop

                pop.left?.let { nextStack.add(pop.left!!) }
                pop.right?.let { nextStack.add(pop.right!!) }
            } else {
                lastNode = null
                stack.addAll(nextStack)
                nextStack.clear()
            }
        }
        return root
    }

    fun mergeTrees() {
        mergeTrees(arrayToTreeNode(1, 3, 2, 5), arrayToTreeNode(2, 1, 3, null, 4, null, 7))?.log()
    }

    /**
     * 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
     */
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null) {
            return root2
        }
        if (root2 == null) {
            return root1
        }
        val result = TreeNode(root1.`val` + root2.`val`)
        result.left = mergeTrees(root1.left, root2.left)
        result.right = mergeTrees(root1.right, root2.right)
        return result
    }

    fun maxAreaOfIsland() {
        maxAreaOfIsland(
            arrayOf(
                intArrayOf(0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
                intArrayOf(1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
                intArrayOf(1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
            )
        ).log()
    }

    /**
     * 695. 岛屿的最大面积
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var result = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                // 遍历所有的方块，加入到堆栈中
                var cur = 0
                val stacki: Deque<Int> = LinkedList()
                val stackj: Deque<Int> = LinkedList()
                stacki.push(i)
                stackj.push(j)
                while (!stacki.isEmpty()) {
                    // 对所有的岛屿进行遍历
                    val cur_i = stacki.pop()
                    val cur_j = stackj.pop()
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.size || cur_j == grid[0].size || grid[cur_i][cur_j] != 1) {
                        // 判断位置是否越界 && 是否为海洋
                        continue
                    }
                    cur++
                    grid[cur_i][cur_j] = 0
                    val di = intArrayOf(0, 0, 1, -1)
                    val dj = intArrayOf(1, -1, 0, 0)
                    for (index in 0..3) {
                        val next_i = cur_i + di[index]
                        val next_j = cur_j + dj[index]
                        stacki.push(next_i)
                        stackj.push(next_j)
                    }
                }
                result = Math.max(result, cur)
            }
        }
        return result
    }

    fun floodFill() {
        // 输入:
        // image = [[1,1,1],[1,1,0],[1,0,1]]
        // sr = 1, sc = 1, newColor = 2
        // 输出: [[2,2,2],[2,2,0],[2,0,1]]
        // 解析:
        // 在图像的正中间，(坐标(sr,sc)=(1,1)),
        // 在路径上所有符合条件的像素点的颜色都被更改成2。
        // 注意，右下角的像素没有更改为2，
        // 因为它不是在上下左右四个方向上与初始点相连的像素点。
        floodFill(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 0, 1)
            ), 1, 1, 2
        ).forEach {
//            it.contentToString().log()
        }

        floodFill(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 1, 1)
            ), 1, 1, 1
        ).forEach {
            it.contentToString().log()
        }

        floodFill(
            arrayOf(
                intArrayOf(0, 0, 1),
                intArrayOf(0, 0, 0)
            ), 1, 1, 2
        ).forEach {
            it.contentToString().log()
        }
    }

    /**
     * 733. 图像渲染
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。=》FFFF，即16位
     * 给你一个坐标(sr, sc)表示图像渲染开始的像素值（行 ，列）和一个新的颜色值newColor，让你重新上色这幅图像。
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。
     * 将所有有记录的像素点的颜色值改为新的颜色值。最后返回经过上色渲染后的图像。
     */
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        val oldColor = image[sr][sc]
        if (oldColor == newColor) {
            return image
        }
        floodFillDfs(image, sr, sc, oldColor, newColor)
        return image
    }

    val queue = LinkedList<IntArray>()
    val dRow = intArrayOf(1, 0, 0, -1)
    val dColumn = intArrayOf(0, 1, -1, 0)
    fun floodFillDfs(image: Array<IntArray>, sr: Int, sc: Int, oldColor: Int, newColor: Int) {
        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor
            for (i in 0 until 4) {
                val newRow = sr + dRow[i]
                val newColumn = sc + dColumn[i]
                if (isSafe(image, newRow, newColumn) && image[newRow][newColumn] == oldColor) {
                    queue.add(intArrayOf(newRow, newColumn))
                }
            }
            while (!queue.isEmpty()) {
                val pop = queue.removeFirst()
                floodFillDfs(image, pop[0], pop[1], oldColor, newColor)
            }
        }
    }

    fun isSafe(image: Array<IntArray>, x: Int, y: Int): Boolean {
        return x >= 0 && x < image.size && y >= 0 && y < image[0].size
    }

    fun checkInclusion() {
        // 输入：s1 = "ab" s2 = "eidbaooo"
        // 输出：true
        // 解释：s2 包含 s1 的排列之一 ("ba").
        checkInclusion("ab", "eidbaooo").log()

        checkInclusion("ab", "cba").log()

        // 输入：s1= "ab" s2 = "eidboaoo"
        // 输出：false
        checkInclusion("ab", "eidboaoo").log()

        checkInclusion("hello", "ooolleoooleh").log()
    }

    /**
     * 567. 字符串的排列
     * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。
     * 如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        val map = HashMap<Char, Int>()
        s1.forEach {
            map[it] = if (map[it] == null) 1 else map[it]!!.plus(1)
        }
        var r = 0
        for (i in s2.indices) {
            r = Math.max(i, r)
            while (r < s2.length && map.containsKey(s2[r]) && map[s2[r]]!! > 0) {
                map[s2[r]] = map[s2[r]]!!.minus(1)

                var temp = 0
                map.values.forEach {
                    temp += it
                }
                if (temp == 0) {
                    return true
                }
                r++
            }
            if (map[s2[i]] != null) {
                map[s2[i]] = map[s2[i]]!! + 1
            }
        }
        return false
    }

    fun lengthOfLongestSubstring() {
        lengthOfLongestSubstring("tmmzuxt").log()

        // 输入: s = "abcabcbb"
        // 输出: 3
        // 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        lengthOfLongestSubstring("abcabcbb").log()


        // 输入: s = "bbbbb"
        // 输出: 1
        // 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        lengthOfLongestSubstring("bbbbb").log()

        // 输入: s = "pwwkew"
        // 输出: 3
        // 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
        // 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
        lengthOfLongestSubstring("pwwkew").log()

        // 输入: s = ""
        // 输出: 0
        lengthOfLongestSubstring("").log()
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var l = 0
        var r = 0
        var maxLength = 0
        val map = HashMap<Char, Int>()
        while (r < s.length) {
            // 判断是否为非重复字符串
            val rightValue = s[r]
//            "$r : $rightValue".log()
            if (map[rightValue] == null || map[rightValue] == -1) {
                map[rightValue] = r
                maxLength = Math.max(r - l + 1, maxLength)
//                println(
//                    """ Match: $l  :  $r  : ${s.subSequence(l, r + 1)}""".trimIndent()
//                )
                r++
            } else {
//                println(
//                    """ No : $l  :  $r  : ${s.subSequence(l, r + 1)}""".trimIndent()
//                )
                val newL = map[rightValue]!! + 1
                // 清空新旧重复字符之间的位置值
                for (i in l until newL) {
                    map[s[i]] = -1
                }
                map[rightValue] = r
                l = newL
                r++
            }
        }
        return maxLength
    }

    fun removeNthFromEnd() {
        // 输入：head = [1,2,3,4,5], n = 2
        // 输出：[1,2,3,5]
        removeNthFromEnd(arrayToListNodeList(intArrayOf(1, 2, 3, 4, 5)), 2)?.log()

        // 输入：head = [1], n = 1
        // 输出：[]
        removeNthFromEnd(arrayToListNodeList(intArrayOf(1)), 1)?.log()

        // 输入：head = [1,2], n = 1
        // 输出：[1]
        removeNthFromEnd(arrayToListNodeList(intArrayOf(1, 2, 3)), 3)?.log()
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // 通过n来保持距离，先移动n位，然后保持lastNode和targetNode的距离为n往右移动，
        // lastNode停下来时就是倒数第n个节点
        val leadNode = ListNode(n)
        leadNode.next = head
        var lastNode: ListNode? = leadNode
        var target = leadNode
        var listSize = 0
        for (i in 0 until n) {
            lastNode = lastNode?.next
            listSize++
        }

        while (lastNode?.next != null) {
            lastNode = lastNode.next
            target = target.next!!
        }
        target.next = target.next?.next

        return leadNode.next
    }

    fun middleNode() {
        // 输入：[1,2,3,4,5]
        // 输出：此列表中的结点 3 (序列化形式：[3,4,5])

        // 输入：[1,2,3,4,5,6]
        // 输出：此列表中的结点 4 (序列化形式：[4,5,6])

        middleNode(arrayToListNodeList(intArrayOf(1)))?.log()
        middleNode(arrayToListNodeList(intArrayOf(1, 2, 3, 4, 5)))?.log()
        middleNode(arrayToListNodeList(intArrayOf(1, 2, 3, 4, 5, 6)))?.log()
    }

    /**
     * 876. 链表的中间结点
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     */
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
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

    fun reverseWords() {
        // 输入："Let's take LeetCode contest"
        // 输出："s'teL ekat edoCteeL tsetnoc"
        // 提示：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
        reverseWords("Let's take LeetCode contest").log()
        reverseWords("I love u").log()
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    fun reverseWords(s: String): String {
//        var wordLength = 0
//        val builder = StringBuilder()
//        s.forEachIndexed { index, c ->
//            if (c == ' ') {
//                if (wordLength != 0) {
//                    // 词的结尾
//                    val l = index - wordLength
//                    var r = index - 1
//                    while (l <= r) {
//                        builder.append(s[r])
//                        r--
//                    }
//                }
//                wordLength = 0
//                builder.append(' ')
//            } else if (index == s.length - 1) {
//                // 词的结尾
//                val l = index - wordLength
//                var r = index
//                while (l <= r) {
//                    builder.append(s[r])
//                    r--
//                }
//            } else {
//                wordLength++
//            }
//        }
//        return builder.toString()

        val array = s.split(" ")
        val sb = StringBuilder(s.length)
        for (item in array) {
            sb.append(item.reversed()).append(" ")
        }
        return sb.toString().trim()
    }

    fun reverseString() {
        // 输入：s = ["h","e","l","l","o"]
        // 输出：["o","l","l","e","h"]

        // 输入：s = ["H","a","n","n","a","h"]
        // 输出：["h","a","n","n","a","H"]
        reverseString(charArrayOf('h', 'e', 'l', 'l', 'o'))
        reverseString(charArrayOf('H', 'a', 'n', 'n', 'a', 'h'))
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     */
    fun reverseString(s: CharArray) {
        var l = 0
        var r = s.size - 1
        while (l < r) {
            val temp = s[l]
            s[l] = s[r]
            s[r] = temp
            l++
            r--
        }
        s.log()
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

    class Node(var `val`: Int) {
        var next: Node? = null

        var left: Node? = null
        var right: Node? = null

        fun log() {
            print("[")
            print(`val`)
            var n: Node? = next
            while (n != null) {
                print(" , ")
                print(n.`val`)
                n = n.next
            }
            println("]")
        }
    }

    fun arrayToNodeList2(vararg array: Int): Node? {
        if (array.isEmpty()) {
            return null
        }
        val linkedList = LinkedList<Node>()
        val root = Node(array[0]!!)
        if (array.size == 1) {
            return root
        }
        linkedList.add(root)

        var i = 1
        while (i < array.size) {
            var node = array[i]?.let { Node(it) }
            val removeFirst = linkedList.removeFirst()

            removeFirst.left = node
            node?.let { linkedList.add(it) }

            i++
            if (i == array.size) {
                break
            }

            node = array[i]?.let { Node(it) }
            removeFirst.right = node
            i++
            node?.let { linkedList.add(it) }
        }

        return root
    }

    fun arrayToNodeList(vararg array: Int): Node? {
        val head: Node = Node(-1)
        var index: Node = head
        array.forEach {
            index.next = Node(it)
            index = index.next!!
        }
        return head.next
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

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        override fun toString(): String {
            return levelOrder(this).toString()
        }
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
}