package m146_LRUCache;

import java.util.HashMap;
import java.util.Iterator;

public class LRU<K, V> implements Iterable<K>{

    private Node head;
    private Node tail;
    private HashMap<K, Node> map;
    private int maxSize;

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize * 4 / 3);

        head = new Node(null, null);
        tail = new Node(null, null);

        head.next = tail;
        tail.pre = head;
    }

    class Node {
        Node pre;
        Node next;
        K k;
        V v; // v是node存储的具体值

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        unlink(node);
        appendHead(node);

        return node.v;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
            appendHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            appendHead(node);
        }

        if (map.size() > maxSize) {
            Node toRemove = removeTail();
            map.remove(toRemove.k);
        }
    }

    public void appendHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    public Node removeTail() {
        Node node = tail.pre;

        Node pre = node.pre;
        tail.pre = pre;
        pre.next = tail;

        node.pre = null;
        node.next = null;

        return node; // 已经删除的节点
    }

    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.next = null;
        node.pre = null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                K k = cur.k;
                cur = cur.next;
                return k;
            }
        };
    }
}
