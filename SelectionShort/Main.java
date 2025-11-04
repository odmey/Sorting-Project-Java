package SelectionShort;

// import java.util.Random;
// import java.util.Scanner;

public class Main {

    // Node untuk singly linked list
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Implementasi linked list sederhana
    static class MyLinkedList {
        Node head;
        int size;

        MyLinkedList() {
            head = null;
            size = 0;
        }

        void add(int value) {
            Node n = new Node(value);
            if (head == null)
                head = n;
            else {
                Node cur = head;
                while (cur.next != null)
                    cur = cur.next;
                cur.next = n;
            }
            size++;
        }

        MyLinkedList deepCopy() {
            MyLinkedList copy = new MyLinkedList();
            Node cur = head;
            while (cur != null) {
                copy.add(cur.data);
                cur = cur.next;
            }
            return copy;
        }

        String firstNToString(int n) {
            StringBuilder sb = new StringBuilder();
            Node cur = head;
            int cnt = 0;
            while (cur != null && cnt < n) {
                sb.append(cur.data);
                if (cnt < n - 1 && cur.next != null)
                    sb.append(" ");
                cur = cur.next;
                cnt++;
            }
            return sb.toString();
        }

        boolean isSorted() {
            if (head == null)
                return true;
            Node cur = head;
            while (cur.next != null) {
                if (cur.data > cur.next.data)
                    return false;
                cur = cur.next;
            }
            return true;
        }

    }

}
