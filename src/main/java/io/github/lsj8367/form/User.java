package io.github.lsj8367.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class User {
    @NotNull(message = "email is not null")
    private String email;

    @NotNull(message = "name is not null")
    @Size(min = 2, max = 4, message = "name must be between 2 and 4")
    private String name;

    @Min(value = 1, message = "age is more than 0")
    private int age;

    public User(final String email, final String name, final int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }

}
