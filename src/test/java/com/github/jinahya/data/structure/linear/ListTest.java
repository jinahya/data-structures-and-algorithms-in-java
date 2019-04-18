package com.github.jinahya.data.structure.linear;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({WeldJunit5Extension.class, MockitoExtension.class, ListElementParameterResolverInteger.class})
@Slf4j
public abstract class ListTest<L extends List<E>, E> {

    // -----------------------------------------------------------------------------------------------------------------
    public ListTest(final Class<L> listClass) {
        super();
        this.listClass = requireNonNull(listClass, "listClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void assertGetAtThrowsIndexOutOfBoundsExceptionWhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class,
                     () -> selectAndGet().getAt(current().nextInt() | Integer.MIN_VALUE));
    }

    @Test
    public void assertGetAtThrowsIndexOutOfBoundsExceptionWhenIndexIsOutOfRange() {
        assertThrows(IndexOutOfBoundsException.class, () -> listInstance.select(listClass).get().getAt(0));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testSetAt(@ListElementParameter final E element) {
        assertThrows(IndexOutOfBoundsException.class, () -> selectAndGet().setAt(1, null));
        selectAndGet().setAt(0, element);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link List#insertAt(int, Object)} with given element.
     *
     * @param element the element to insert.
     */
    @Test
    public void testInsertAt(@ListElementParameter final E element) {
        listInstance.select(listClass).get().insertAt(0, element);
    }

    // -----------------------------------------------------------------------------------------------------------------
    L selectAndGet() {
        return listInstance.select(listClass).get();
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<L> listClass;

    @Inject
    private Instance<List<E>> listInstance;
}
