package designPatterns.singleton.enumSingleton;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String name;
    private Integer age;
    private String address;
}
