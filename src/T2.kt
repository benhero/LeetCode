/**
 *
 *
 * @author Benhero
 * @date   2021/11/29
 */
object T2 {

    @JvmStatic
    fun main(args: Array<String>) {
        var list = arrayToNodeList(intArrayOf(0, 1, 2, 3, 4, 5, 6))!!

        // 链表翻转
        list.log()
        list.let { reverseNodeList(it).log() }

        list = arrayToNodeList(intArrayOf(0, 1, 2, 3, 4, 5, 6))!!
        // 查找中间位置值得
        println(findMidNode(list).value)
    }

    private fun reverseNodeList(node: Node): Node {
        var cur = node
        var next = node.next
        node.next = null
        while (next != null) {
            val temp = next.next
            next.next = cur
            cur = next
            next = temp
        }
        return cur
    }

    private fun reverseNodeList2(node: Node): Node {
        var curr: Node? = node
        var next: Node? = null
        var prev: Node? = null

        while (curr != null) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        return prev!!
    }

    private fun findMidNode(node: Node): Node {
        var slow: Node? = node
        var fast: Node? = node
        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow!!
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