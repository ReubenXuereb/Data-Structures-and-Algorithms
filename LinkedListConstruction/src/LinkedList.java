
public class LinkedList {
    static class DoublyLinkedList {

        //first node in the list
        public Node head;
        //last node in the list
        public Node tail;

        //time O(N)
        //space O(1)
        public boolean containsNodeWithValue(int value) {
            Node currentNode = this.head;
//            while (currentNode != null) {
//                currentNode = currentNode.next;
//                if (currentNode == value) {
//                    return true;
//                }
//            }
            while (currentNode != null && currentNode.value != value) {
                currentNode = currentNode.next;
            }
            return false;
        }


        //time O(1)
        //space O(1)
        public void insertBefore(Node node, Node nodeToInsert) {
            //Let's say we have [<- 1 <-> 2 <-> 3 <-> 4 <-> 5 ->]
            //We want to move 3 before 2.
            //check if linked list has one node, then do nothing.
            //if node != head or tail, use remove() that node that needs to be inserted and update the new prev and next instead of it
            //Update 3.prev = 3.prev(which is 2).prev
            //Update 3.next = 3.pre
            if (nodeToInsert == head && nodeToInsert == tail) {
                return;
            }
            //here we set that 2 <-> 4
            remove(nodeToInsert);
            //REMEMBER TO TAKE INTO CONSIDERATION: node == 2 and nodeToInsert == 3
            //Now the new 3.prev = 2.prev. In other words 1
            //IMP: here we are setting the pointer that points 3 to 1
            nodeToInsert.prev = node.prev;
            // Now 3.next = 2
            //IMP: here we are setting the pointer that points 3 to 2
            nodeToInsert.next = node;

            //Currently we are looking like this 1 <- 3 -> 2
            //Now we need to set the pointers for 1 and 2 to both point back at 3. Remember it's a Doubly Linked List !!!
            if(node.prev == null) {
                // 3 will become the head
                this.head = nodeToInsert;
            }
            else {
                // 1 <-> 3
                //IMP: here we are setting the pointer that points 1 to 3
                node.prev.next = nodeToInsert;
            }
            // 1 <-> 3 <-> 2
            //IMP: here we are setting the pointer that points 2 to 3
            node.prev = nodeToInsert;
        }

        //time O(1)
        //space O(1)
        public void insertAfter(Node node, Node nodeToInsert) {
            //Same logic as insertBefore() but opposite
            if (nodeToInsert == head && nodeToInsert == tail) {
                return;
            }
            remove(nodeToInsert);
            nodeToInsert.prev = node;
            nodeToInsert.next = node.next;

            if (node.next == null) {
                this.tail = nodeToInsert;
            }
            else {
                node.next.prev = nodeToInsert;
            }
            node.next = nodeToInsert;
        }

        //time O(P)
        //space O(1)
        public void insertAtPosition(int position, Node nodeToInsert) {
            //if pos = 1, setHead(pos, node)
            //if pos != 1, set pointer to head and advance it through list.
            //when arriving at pos, insertBefore()
            //if pointer arrives at the end of the list, setTail()

            //Pos = 1 therefore it is of course going to be the Head
            if (position == 1) {
                setHead(nodeToInsert);
                return;
            }
            //Now we traverse the list until we arrive at our position
            Node currentNode = this.head;
            int currentPosition = 1;
            while (currentNode != null && currentPosition != position) {
                currentNode = currentNode.next;
                currentPosition++;
            }
            //Here we arrived at out position, so we are somewhere in the middle of the list.
            //Therefore, insert the nodeToInsert BEFORE the currentNode that we are at
            if (currentNode != null) {
                insertBefore(currentNode, nodeToInsert);
            } else {
                //We entered here because the position is something behind the tail and the currentNode in null
                //Therefore we set the tail as out nodeToInsert
                setTail(nodeToInsert);
            }

        }

        //time O(1)
        //space O(1)
        public void removeNodesWithValue(int value) {
            //first search for the value, containsNodeWithValue()
            //then remove()
            Node currentNode = this.head;
            while (currentNode != null) {
                //-------------------------------//
                //IMP step to remember
                //you can not do the following even if it makes sense:

                /*if (currentNode.value == value) {
                    remove(currentNode);
                }
                currentNode = currentNode.next*/

                //This is because currentNode = currentNode.next wil throw an exceptions since as stated in remove() you will loose access to currentNode.prev...
                //and currentNode.next values if you remove directly the currentNode first.
                //-------------------------------//
                Node nodeToRemove = currentNode;
                currentNode = currentNode.next;

                if (nodeToRemove.value == value) {
                    remove(currentNode);
                }

            }
        }

        //time O(1)
        //space O(1)
        public void remove(Node node) {
            //Let's say we have [<- 1 <-> 2 <-> 3 <-> 4 <-> 5 ->] and we want to remove 3
            //check if the head or the tail are going to be removed
            //check weither the new .next and .prev aren't null
            //then update the new .next and .prev
            //remove .next and .prev
            //-------------------------------//
            //lets say we want to remove 3
            //IMP step to remember: YOU CAN'T REMOVE THE WHERE CURRENTNODE.PREV AND CURRENTNODE.NEXT POINT(2 <-3-> 4) BEFORE UPDATING THE CURRENTNODE.NEXT.PREV(which is 2) AND .PREV.NEXT FIRST(which is 4)
            //this is because now you have no way of accessing the currentNode.prev(2) and currentNode.next(4) to update their pointers.
            //Firstly check if 3.prev != null and grab 3.prev.next = 3.next
            //Then check if 3.next != null and grab 3.next.prev = 3.prev
            //Now 2 and 4 are pointing to each other(2<->4), so now I can set 3.prev && 3.next == null
            //-------------------------------//
            if (node == head) {
                head = head.next;
            }
            if (node == tail) {
                tail = tail.prev;
            }
            removeNodeBindings(node);

        }

        public void removeNodeBindings(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = null;

        }

        public void setHead(Node node) {
            //do we have an empty linked list ?, then if head = null then head = node and tail = node
            //if linked list not empty ? insertBefore(head, node)
            if(this.head == null) {
                this.head = node;
                this.tail = node;
                return;
            }
            //REMEBER: node = nodeToInsert
            //therefore when entering insertBefore() method the node parameter is the head and nodeToInsert parameter is the node.
            insertBefore(this.head, node);
        }

        public void setTail(Node node) {
            //do we have an empty linked list ?, then if tail = null then head = node and tail = node
            //if linked list not empty ? insertAfter(tail, node)
            if(this.head == null) {
                this.head = node;
                return;
            }
            insertAfter(this.tail, node);
        }
    }

    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
