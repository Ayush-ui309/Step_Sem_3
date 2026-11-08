public class Main {
    static class BusBay {
        private final int[] data;
        private final int capacity;
        private int front;
        private int size;

        BusBay(int capacity) {
            this.capacity = capacity;
            this.data = new int[capacity];
            this.front = 0;
            this.size = 0;
        }

        boolean insertFront(int bus) {
            if (isFull()) return false;
            front = (front - 1 + capacity) % capacity;
            data[front] = bus;
            size++;
            return true;
        }

        boolean insertLast(int bus) {
            if (isFull()) return false;
            data[(front + size) % capacity] = bus;
            size++;
            return true;
        }

        boolean deleteFront() {
            if (isEmpty()) return false;
            front = (front + 1) % capacity;
            size--;
            return true;
        }

        boolean deleteLast() {
            if (isEmpty()) return false;
            size--;
            return true;
        }

        int getFront() {
            return isEmpty() ? -1 : data[front];
        }

        int getRear() {
            return isEmpty() ? -1 : data[(front + size - 1) % capacity];
        }

        boolean isEmpty() { return size == 0; }

        boolean isFull() { return size == capacity; }
    }

    public static void main(String[] args) {
        BusBay b = new BusBay(3);
        System.out.println(b.insertLast(101)); // true
        System.out.println(b.insertLast(102)); // true
        System.out.println(b.insertFront(100)); // true
        System.out.println(b.insertFront(99));  // false (full)
        System.out.println(b.getRear());        // 102
        System.out.println(b.isFull());         // true
        System.out.println(b.deleteLast());     // true
        System.out.println(b.insertFront(98));  // true
        System.out.println(b.getFront());       // 98
    }
}
