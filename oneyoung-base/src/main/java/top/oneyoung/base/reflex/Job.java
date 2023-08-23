package top.oneyoung.base.reflex;

/**
 * Job
 *
 * @author oneyoung
 * @since 2023/8/23 19:34
 */
public class Job extends Base{

    private String jobName;

    private Integer jobAge;

    public Job() {
    }

    @Override
    public String toString() {
        return "Job{" +
            "jobName='" + jobName + '\'' +
            ", jobAge=" + jobAge +
            '}';
    }
}
