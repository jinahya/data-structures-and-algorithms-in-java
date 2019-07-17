package com.github.jinahya.sakila.persistence;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import static com.github.jinahya.sakila.persistence.Category.comparingName;
import static java.util.Collections.unmodifiableSortedSet;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * An integration test class for {@link CategoryService}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;q
 */
class CategoryServiceIT extends BaseEntityServiceIT<CategoryService, Category> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The total number of {@link Category}.
     */
    static final int CATEGORY_COUNT = entityCountAsInt(Category.class);

    /**
     * Returns a random instance of {@link Category}.
     *
     * @return a random instance of {@link Category}.
     */
    static Category randomCategory() {
        return randomEntity(Category.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A sorted set of values of {@link Category#ATTRIBUTE_NAME_NAME} attribute.
     */
    static final SortedSet<String> NAMES;

    static {
        try {
            NAMES = unmodifiableSortedSet(applyResourceScanner("category_set_name.txt", s -> {
                final SortedSet<String> set = new TreeSet<>();
                while (s.hasNext()) {
                    set.add(s.next());
                }
                return set;
            }));
        } catch (final IOException ioe) {
            throw new InstantiationError(ioe.getMessage());
        }
    }

    static final Condition<String> A_NAME_IN_DATABASE = new Condition<>(NAMES::contains, "a name is in database");

    static final Condition<Category> A_CATEGORY_WHOSE_NAME_IS_IN_DATABASE = new Condition<>(
            l -> A_NAME_IN_DATABASE.matches(l.getName()), "a category whose name is in database");

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Provides arguments for {@link #testStreamOrderedByName(boolean, Integer, Integer)} method.
     *
     * @return a stream of arguments.
     */
    private static Stream<Arguments> argumentsForTestingStreamOrderedByName() {
        return range(8, 17).mapToObj(i -> arguments(
                current().nextBoolean(), firstResult(Category.class), maxResults(Category.class)));
    }

    /**
     * Provides arguments for {@link #testFindByName(String)} method.
     *
     * @return a stream of arguments.
     */
    private static Stream<Arguments> argumentsForTestingFindByName() {
        return range(8, 17).mapToObj(i -> arguments(
                NAMES.stream()
                        .skip(current().nextLong(NAMES.size()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("fix me if you see me"))));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    CategoryServiceIT() {
        super(CategoryService.class, Category.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link CategoryService#streamOrderedByName(boolean, Integer, Integer)} method.
     *
     * @param ascendingOrder a value for {@code ascendingOrder} parameter.
     * @param firstResult    a value for {@code firstResult} parameter.
     * @param maxResults     a value for {@code maxResults} parameter.
     */
    // TODO: 2019-07-16 enable, assert fails, implement, assert passes.
    @Disabled
    @MethodSource({"argumentsForTestingStreamOrderedByName"})
    @ParameterizedTest
    void testStreamOrderedByName(final boolean ascendingOrder, @PositiveOrZero final Integer firstResult,
                                 @Positive final Integer maxResults) {
        final List<Category> list = serviceInstance()
                .streamOrderedByName(ascendingOrder, firstResult, maxResults)
                .collect(toList());
        assertThat(list)
                .isNotNull()
                .isSortedAccordingTo(comparingName(ascendingOrder))
                .hasSizeLessThan(ofNullable(maxResults).orElse(Integer.MAX_VALUE))
        ;
    }

    // ------------------------------------------------------------------------------------------------------ findByName

    /**
     * Asserts {@link CategoryService#findByName(String)} returns empty for an unknown category.
     */
    @Test
    void assertFindByNameReturnsEmptyIfNameIsUnknown() {
        final String name = "周星馳";
        assertThat(NAMES).doesNotContain(name);
        assertThat(serviceInstance().findByName(name)).isEmpty();
    }

    /**
     * Tests {@link CategoryService#findByName(String)}.
     *
     * @param name a value for {@code name} parameter.
     */
    @MethodSource({"argumentsForTestingFindByName"})
    @ParameterizedTest
    void testFindByName(@NotNull final String name) {
        assertThat(serviceInstance().findByName(name))
                .isPresent()
                .hasValueSatisfying(category -> assertThat(category.getName()).isEqualTo(name))
        ;
    }
}