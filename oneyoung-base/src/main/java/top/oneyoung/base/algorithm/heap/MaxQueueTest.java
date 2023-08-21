package top.oneyoung.base.algorithm.heap;

/**
 * MaxQueueTest
 *
 * @author oneyoung
 * @since 2023/8/21 17:57
 */
public class MaxQueueTest {

        public static void main(String[] args) {
            MaxPriorityQueue<QueueTask> maxPriorityQueue = new MaxPriorityQueue<>(10);
            maxPriorityQueue.add(new QueueTask(3, "task1"));
            maxPriorityQueue.add(new QueueTask(5, "task2"));
            maxPriorityQueue.add(new QueueTask(10, "task3"));
            maxPriorityQueue.add(new QueueTask(2, "task4"));
            maxPriorityQueue.add(new QueueTask(7, "task5"));
            maxPriorityQueue.add(new QueueTask(9, "task6"));
            maxPriorityQueue.add(new QueueTask(1, "task7"));
            maxPriorityQueue.add(new QueueTask(4, "task8"));
            maxPriorityQueue.add(new QueueTask(6, "task9"));
            maxPriorityQueue.add(new QueueTask(8, "task10"));
            while (!maxPriorityQueue.isEmpty()) {
                QueueTask queueTask = maxPriorityQueue.poll();
                queueTask.execute();
            }
        }
}
