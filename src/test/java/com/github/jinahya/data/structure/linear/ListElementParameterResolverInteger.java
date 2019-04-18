package com.github.jinahya.data.structure.linear;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import static java.util.concurrent.ThreadLocalRandom.current;

class ListElementParameterResolverInteger extends ListElementParameterResolver<Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    Integer resolveParameterTyped(final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return current().nextInt();
    }
}
