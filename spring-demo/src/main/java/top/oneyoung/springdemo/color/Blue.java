package top.oneyoung.springdemo.color;

import lombok.Getter;
import lombok.Setter;

/**
 * Blue
 *
 * @author oneyoung
 * @since 2023/8/28 10:19
 */
@Getter
@Setter
public class Blue implements Color {
    private String name;

    @Override
    public String getColor() {
        return "blue";
    }
}
