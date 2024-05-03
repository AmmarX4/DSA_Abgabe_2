package de.unistuttgart.dsass2024.ex02.p5;

public class SimpleList<T extends Comparable<T>> implements ISimpleList<T> {
    private ISimpleListNode<T> head;
    private int size;

    public SimpleList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void prepend(T element) {
        ISimpleListNode<T> newNode = new SimpleListNode<T>();
        newNode.setElement(element);
        newNode.setNext(head); // the next of the to be added node is the head (in an empty list the head is null)
        head = newNode; // update the head to the new added Node
        size++;

    }

    @Override
    public T getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("the passed Index is out of bounds");
        }
        ISimpleListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getElement();
    }

    @Override
    // use bubbleSort
    public void sort() {
        if (isEmpty() || head.getNext() == null) return;
        boolean swapped;
        do {
            ISimpleListNode<T> currentNode = head;
            ISimpleListNode<T> nextNode = currentNode.getNext();
            ISimpleListNode<T> previousNode = null;
            swapped = false;

            while (nextNode != null) {
                if (currentNode.getElement().compareTo(nextNode.getElement()) > 0) {
                    swapped = true;
                    // we need to swap elements here ( be wary, linked list ! )
                    swap(currentNode, nextNode, previousNode);

                    // swap pointers to continue correct traversal
                    ISimpleListNode<T> temp = nextNode;
                    nextNode = currentNode;
                    currentNode = temp;

                } else {
                    //move all pointers one step to the right if no swap needed
                    previousNode = currentNode;
                    currentNode = nextNode;
                    nextNode = nextNode.getNext();
                }
            }
        } while (swapped);
    }

    private void swap(ISimpleListNode<T> currentNode, ISimpleListNode<T> nextNode, ISimpleListNode<T> previousNode) {
        currentNode.setNext(nextNode.getNext());
        nextNode.setNext(currentNode);
        if (previousNode != null) {
            previousNode.setNext(nextNode); // because nextNode has been swapped to the old position of currentNode
        } else {
            head = nextNode; //(head is currentNode at first) update when the first node is swapped
        }
    }

    //this method is used for tests and is not part of our submission
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ISimpleListNode<T> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}
