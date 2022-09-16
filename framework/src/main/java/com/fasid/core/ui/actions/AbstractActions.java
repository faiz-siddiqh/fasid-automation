package com.fasid.core.ui.actions;

/**
 * This interface has to be implemented by all the Module actions class across project
 *
 * @param <T> - ModuleActionsClass
 */
public interface AbstractActions<T extends AbstractActions> {

    T hasLoaded();

}
