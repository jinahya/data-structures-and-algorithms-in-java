package com.github.jinahya.data.structure.linear;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

abstract class ListElementParameterResolver<E> implements ParameterResolver {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.isAnnotated(ListElementParameter.class);
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return resolveParameterTyped(parameterContext, extensionContext);
    }

    // -----------------------------------------------------------------------------------------------------------------
    abstract E resolveParameterTyped(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException;
}
