package com.example.structure;


public class MyLinkedList {
    public static void main(String[] args) {

    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    private static class DoubleNode<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        DoubleNode(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
