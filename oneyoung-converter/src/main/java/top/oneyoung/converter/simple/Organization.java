package top.oneyoung.converter.simple;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author oneyoung
 * @since 2019/12/10 10:01
 */
@Getter
@Setter
public class Organization {
    public static String a = "1";
    /**
     * 组织ID
     */
    private String id;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 组织编码，组织代号
     */
    private String code;
    /**
     * 组织所属企业ID
     */
    private String enterpriseId;
    /**
     * 组织父级ID
     */
    private String parentId;
    /**
     * 排序ID，展示使用
     */
    private Integer weight;
    /**
     * 组织的leader，可以有多个（一般是一个）
     */
    private String leaders;
    /**
     * 组织分类：子公司；组织
     */
    private String type;
    /**
     * 组织职能
     */
    private List<String> functions;

    private Employee employee;

    private List<Employee> employees;
}
