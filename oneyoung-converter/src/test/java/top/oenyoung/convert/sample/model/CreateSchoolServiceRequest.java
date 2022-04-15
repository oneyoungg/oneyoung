package top.oenyoung.convert.sample.model;

import lombok.*;

/**
 * CreateServiceRequest
 *
 * @author oneyoung
 * @since 2022/4/15 17:02
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSchoolServiceRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}
