package com.github.jinahya.data.structure.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
@Slf4j
public class BinarySearchTreeTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testSearch() {
        final BinarySearchTree<Integer> binarySearchTree = BinarySearchTree.newInstance();
        final Integer element = 1;
        assertFalse(binarySearchTree.search(element).isPresent());
        binarySearchTree.insert(element);
        final Optional<BinarySearchTreeNode<Integer>> node = binarySearchTree.search(1);
        assertTrue(node.isPresent());
        assertEquals(1, node.get().getValue());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testInsert() {
        final BinarySearchTree<Integer> binarySearchTree = BinarySearchTree.newInstance();
        IntStream.range(1, 128)
                .map(i -> current().nextInt())
                .boxed()
                .forEach(binarySearchTree::insert)
        ;
    }
}
