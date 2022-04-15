package top.oenyoung.convert.sample.model;

import lombok.*;

/**
 * A
 *
 * @author oneyoung
 * @since 2022/4/15 17:01
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSchoolRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}
