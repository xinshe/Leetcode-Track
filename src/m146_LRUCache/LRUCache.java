package m146_LRUCache;

/**
 * 146. LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个LRU (最近最少使用) 缓存机制。它应该支持以下操作：
 * 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 * LRUCache cache = new LRUCache(2); // 2为缓存容量
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * 题目链接：https://leetcode-cn.com/problems/lru-cache
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node() {}
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    Map<Integer, Node> map = new HashMap<>();
    int size;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 首先判断key对应的元素是否存在，
     * 如果存在，返回元素值之前要将该元素对应的node移动到链表头部
     * 如果不存在，直接返回-1
     */
    public int get(int key) {
        Node cur = map.get(key);
        if (cur != null) {
            unlink(cur);
            addToHead(cur);
            return cur.value;
        } else {
            return -1;
        }
    }

    /**
     * 首先要判断key对应的元素是否存在，
     * 如果存在，找到对应的元素，修改它的值，并将其node移到队头
     * 如果不存在，在队头插入一个新节点，并check capacity
     */
    public void put(int key, int value) {
        Node cur = map.get(key);
        if (cur != null) {
            cur.value = value;
            unlink(cur);
            addToHead(cur);
        } else {
            cur = new Node(key, value);
            addToHead(cur);
            map.put(key, cur);
            if (++size > capacity) {
                map.remove(tail.pre.key);
                unlink(tail.pre);
                size--;
            }
        }
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void unlink(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
}
