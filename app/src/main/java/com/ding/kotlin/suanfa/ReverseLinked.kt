package com.ding.kotlin.suanfa

/**
 * 节点 class
 */
class Node(var value: Int? = 0) {
    var next: Node? = null
}

/**
 * 反转 单向链表
 */
fun reverseSolution(head: Node): Node? {
    if (head.next == null) {
        return head
    }
    var preNode: Node? = null
    var curNode: Node? = head
    while (curNode != null) {
        val temp: Node? = curNode.next
        curNode.next = preNode
        preNode = curNode
        curNode = temp
    }
    return preNode
}

