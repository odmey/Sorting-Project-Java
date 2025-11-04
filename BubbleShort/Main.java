package BubbleShort;

import java.util.Random;
import java.util.Scanner;

public class Main {
    // Node untuk Linked List
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Struktur Linked List
    static class MyLinkedList {
        Node head;
        int size;

        void add(int value) {
            Node newNode = new Node(value);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        MyLinkedList deepCopy() {
            MyLinkedList copy = new MyLinkedList();
            Node current = head;
            while (current != null) {
                copy.add(current.data);
                current = current.next;
            }
            return copy;
        }

        String firstN(int n) {
            StringBuilder sb = new StringBuilder();
            Node current = head;
            int i = 0;
            while (current != null && i < n) {
                sb.append(current.data).append(" ");
                current = current.next;
                i++;
            }
            return sb.toString();
        }

        boolean isSorted() {
            if (head == null)
                return true;
            Node current = head;
            while (current.next != null) {
                if (current.data > current.next.data)
                    return false;
                current = current.next;
            }
            return true;
        }
    }

    // Hasil Sorting
    static class SortResult {
        long timeNs;
        long comparisons;
        long swaps;
    }

    // Algoritma Bubble Sort
    public static SortResult bubbleSort(MyLinkedList list) {
        SortResult result = new SortResult();
        if (list.head == null || list.head.next == null)
            return result;

        boolean swapped;
        long comparisons = 0, swaps = 0;
        long start = System.nanoTime();

        do {
            swapped = false;
            Node current = list.head;
            while (current.next != null) {
                comparisons++;
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swaps++;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        result.timeNs = System.nanoTime() - start;
        result.comparisons = comparisons;
        result.swaps = swaps;
        return result;
    }

    // Generate data acak
    static MyLinkedList generateRandomList(int size, int bound, long seed) {
        MyLinkedList list = new MyLinkedList();
        Random random = (seed == 0) ? new Random(System.nanoTime()) : new Random(seed);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }

    // Main Program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah data: ");
        int n = sc.nextInt();

        System.out.print("Masukkan batas nilai acak: ");
        int bound = sc.nextInt();

        System.out.print("Masukkan seed (0 untuk random tiap run): ");
        long seed = sc.nextLong();

        MyLinkedList original = generateRandomList(n, bound, seed);
        MyLinkedList list = original.deepCopy();

        System.out.println("\nPreview 10 elemen sebelum sorting: " + original.firstN(10));
        SortResult res = bubbleSort(list);

        System.out.println("\n=== HASIL BUBBLE SORT ===");
        System.out.println("Waktu eksekusi: " + (res.timeNs / 1_000_000.0) + " ms");
        System.out.println("Jumlah perbandingan: " + res.comparisons);
        System.out.println("Jumlah pertukaran: " + res.swaps);
        System.out.println("Apakah sudah terurut? " + list.isSorted());
        System.out.println("Preview 10 elemen sesudah sorting: " + list.firstN(10));

        sc.close();
    }
}
