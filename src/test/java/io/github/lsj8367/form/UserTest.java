package io.github.lsj8367.form;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void 이메일이_null일_때() {
        final User user = new User(null, "홍길동", 12);
        final Set<ConstraintViolation<User>> validate = validator.validate(user);

        assertThat(validate.stream().findFirst()
            .get().getMessage()).isEqualTo("email is not null");
    }

    @Test
    void 이름_null() {
        final User user = new User("test@email.com", null, 12);
        final Set<ConstraintViolation<User>> validate = validator.validate(user);

        assertThat(validate.stream().findFirst()
            .get().getMessage()).isEqualTo("name is not null");
    }

    @Test
    void 이름_2글자에서_4글자_사이가_아닐_때() {
        final User min = new User("test@email.com", "홍", 22);
        final User max = new User("test@email.com", "홍홍홍홍홍", 22);

        final Set<ConstraintViolation<User>> minValidate = validator.validate(min);

        final Set<ConstraintViolation<User>> maxValidate = validator.validate(max);

        assertThat(minValidate.stream().findFirst().get().getMessage()).isEqualTo("name must be between 2 and 4");

        assertThat(maxValidate.stream().findFirst()
            .get().getMessage()).isEqualTo("name must be between 2 and 4");
    }

    @Test
    void 나이_1보다_작을_때() {
        final User user = new User("test@abc.com", "lsj", 0);

        final Set<ConstraintViolation<User>> validUser = validator.validate(user);

        assertThat(validUser.stream().findFirst().get().getMessage()).isEqualTo("age is more than 0");
    }

}