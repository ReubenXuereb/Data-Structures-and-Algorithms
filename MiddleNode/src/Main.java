import java.util.LinkedList;

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

    public LinkedList middleNode(LinkedList linkedList) {
        int count = 0;
        LinkedList currentNode = linkedList;

        while (currentNode != null) {
            LinkedList nextNode = currentNode.next;
            count++;
        }

        LinkedList middleNode = linkedList;
        for (int i = 0; i < count / 2; i++) {
            middleNode = middleNode.next;
        }
        return middleNode;
    }


    public static void main(String[] args) {

    }
}