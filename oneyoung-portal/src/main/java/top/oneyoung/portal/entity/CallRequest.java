package top.oneyoung.portal.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * CallRequest
 *
 * @author oneyoung
 * @date 2021/4/25 17:57
 */
@Data
public class CallRequest {

    @NotNull(message = "不能为空")
    private String name;

    @Range(max = 2,message = "不能超过2")
    private Integer age;
}
