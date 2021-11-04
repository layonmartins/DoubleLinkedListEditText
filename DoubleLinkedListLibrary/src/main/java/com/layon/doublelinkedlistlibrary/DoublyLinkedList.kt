package com.layon.doublelinkedlistlibrary

fun main() {
    println()
    println("########### Hello, DoublyLinkedList##########")
    println()
    val list = DoublyLinkedList()

    list.addInTail("b")
    list.addInTail("c")
    list.addInHead("a")
    list.addInTail("d")
    list.delete("a")
    list.delete("b")
    list.delete("c")
    list.delete("d")
}

class DoublyLinkedList {
    var size = 0
    var head : Node? = null
    var tail : Node? = null

    inner class Node(var element: Any) {
        var prev : Node? = null
        var next : Node? = null
    }

    fun addInHead(element: Any) {
        println("\naddInHead($element)")
        var h = head
        val newNode = Node(element)
        newNode.next = head
        head = newNode
        if (h == null) tail = newNode else h.prev = newNode
        size++
        print()
    }

    fun addInTail(element: Any) {
        println("\naddInTail($element)")
        val newNode = Node(element)
        if(size == 0) head = newNode
        newNode.next = null
        newNode.prev = tail
        var oldTail = tail
        tail = newNode
        if(oldTail != null) oldTail.next = tail
        this.size++
        print()
    }

    fun getNode(element: Any) : Node? {
        if(size == 0){
            print("list is empty")
            return null
        }
        var it = head
        while(it != null){
            if(it.element == element){
                return it
            }
            it = it.next
        }
        return null
    }

    fun delete(element: Any) {
        println()
        println("delete($element)")
        var it = getNode(element)
        if (it != null){
            if(it == head) head = head?.next
            if(it == tail) tail = tail?.prev
            val previous = it.prev
            val nextious = it.next
            previous?.next = it
            nextious?.prev = it
            size--
            print()
        }
    }

    fun print(){
        if (size == 0) {
            print("list is empty")
            return
        }
        print("List: ")
        var n = head
        while(n != null){
            print("[${n.element}]")
            if(n.next != null) print("->")
            n = n.next
        }
        println(" size = $size")
    }
}