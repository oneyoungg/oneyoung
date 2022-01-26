package top.oneyoung.converter.simple;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author oneyoung
 * @since 2019/12/10 10:01
 */
@Getter
@Setter
public class Employee {
    /**
     * 员工ID
     */
    private String id;
    /**
     * 员工所属企业ID
     */
    private String enterpriseId;
    /**
     * 员工状态
     */
    private Integer status;
    /**
     * 工号
     */
    private String workId;
    /**
     * 职位
     */
    private String title;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 电话号码（特指座机）
     */
    private String telephone;
    /**
     * 工作地点（工位）
     */
    private String workPlace;
    /**
     * 入职时间
     */
    private Date hiredDate;
    /**
     * 身份证号码
     */
    private String identityCardNumber;
}
