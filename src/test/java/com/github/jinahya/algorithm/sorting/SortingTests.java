package com.github.jinahya.algorithm.sorting;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;

@Slf4j
class SortingTests {

    // -----------------------------------------------------------------------------------------------------------------
    @Getter
    static final class User {

        // -------------------------------------------------------------------------------------------------------------
        static Comparator<User> COMPARING_AGE = comparing(User::getAge);

        // -------------------------------------------------------------------------------------------------------------
        static final User JOHN = new User(2, "John");

        static final User JANE = new User(2, "Jane");

        // -------------------------------------------------------------------------------------------------------------
        User(final int age, final String name) {
            super();
            if (age < 0) {
                throw new IllegalArgumentException("age(" + age + ") < 0");
            }
            this.age = age;
            this.name = requireNonNull(name, "name is null");
        }

        // -------------------------------------------------------------------------------------------------------------
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return age == user.age &&
                   name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        // -------------------------------------------------------------------------------------------------------------
        @NotNull
        public final int age;

        @NotNull
        public final String name;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An unmodifiable list of users for testing stability.
     */
    private static final List<User> USERS_FOR_TESTING_STABILITY;

    static {
        final List<User> list = new ArrayList<>();
        list.add(new User(3, "Three"));
        list.add(User.JOHN);
        list.add(User.JANE);
        list.add(new User(1, "One"));
        USERS_FOR_TESTING_STABILITY = unmodifiableList(list);
    }

    static List<User> usersForTestingStability() {
        return new ArrayList<>(USERS_FOR_TESTING_STABILITY);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SortingTests() {
        super();
    }
}
