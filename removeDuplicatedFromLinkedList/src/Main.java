

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    //keep going to the next node until no dublicate is found
    public LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        LinkedList currentNode = linkedList;

        while (currentNode != null) {
            LinkedList nextNode = currentNode.next;

            while (nextNode != null && (currentNode.value == nextNode.value)) {
                nextNode = nextNode.next;
            }
            currentNode.next = nextNode;
            currentNode = nextNode;
        }

        return linkedList;
    }

    public static void main(String[] args) {
    }
}