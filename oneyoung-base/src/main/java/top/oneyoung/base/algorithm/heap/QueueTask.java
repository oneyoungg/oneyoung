package top.oneyoung.base.algorithm.heap;

import lombok.Getter;
import lombok.Setter;

/**
 * QueueTask
 *
 * @author oneyoung
 * @since 2023/8/21 17:53
 */
@Getter
@Setter
public class QueueTask implements Comparable<QueueTask> {

    /**
     * 优先级
     */
    private int weight;

    /**
     * 任务名称
     */
    private String name;


    public QueueTask(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public void execute() {
        System.out.println("weight: " + weight + ", name: " + name + " is executing...");
    }

    @Override
    public int compareTo(QueueTask o) {
        return this.weight - o.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QueueTask) {
            QueueTask queueTask = (QueueTask) obj;
            return this.weight == queueTask.weight && this.name.equals(queueTask.name);
        }
        return false;
    }
}
