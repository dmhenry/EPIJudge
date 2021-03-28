package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;

public class CircularQueue {

    public static class Queue {

        private Integer[] arr;
        int capacity, size, frontIdx, rearIdx;

        public Queue(int capacity) {
            if (capacity <= 0)
                throw new IllegalArgumentException();

            this.arr = new Integer[capacity];
            this.capacity = capacity;
            this.size = 0;
            this.frontIdx = 0;
            this.rearIdx = -1;
        }

        public void enqueue(Integer x) {
            if (isFull()) {
                Integer[] temp = new Integer[2 * capacity];
                for (int srcIdx = frontIdx, destIdx = 0; destIdx < capacity; srcIdx = (srcIdx + 1) % capacity, destIdx++) {
                    temp[destIdx] = arr[srcIdx];
                }
                frontIdx = 0;
                rearIdx = size - 1;
                arr = temp;
                capacity *= 2;
            }
            rearIdx = (rearIdx + 1) % capacity;
            arr[rearIdx] = x;
            size++;
        }

        public Integer dequeue() {
            if (isEmpty())
                throw new IllegalStateException();

            Integer result = arr[frontIdx];
            frontIdx = (frontIdx + 1) % capacity;
            size--;
            return result;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        private boolean isFull() {
            return size() == capacity;
        }

        private boolean isEmpty() {
            return (size == 0);
        }

    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }

        @Override
        public String toString() {
            return op;
        }
    }

    @EpiTest(testDataFile = "circular_queue.tsv")
    public static void queueTester(List<QueueOp> ops) throws TestFailure {
        Queue q = new Queue(1);
        int opIdx = 0;
        for (QueueOp op : ops) {
            switch (op.op) {
                case "Queue":
                    q = new Queue(op.arg);
                    break;
                case "enqueue":
                    q.enqueue(op.arg);
                    break;
                case "dequeue":
                    int result = q.dequeue();
                    if (result != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s);
                    }
                    break;
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
