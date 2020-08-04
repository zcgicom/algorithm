package com.itcat;

/**
 * 13-如何在双向链表A和B之间插入C?
 *
 * 假设定位到A节点，那么A.next就是B节点，这个是前提
 * 伪代码实现过程：//要保证下述代码的执行顺序，不能让链表断裂
 *      C.pre = A;
 *      C.next = A.next;
 *      A.next.pre = C;
 *      A.next = C;
 */
public class TestDoubleLinkList {
}
