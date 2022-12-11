package org.jjm.interfaces;

import org.jjm.exceptions.NoPlacementAvailableException;

public interface IFindPlacementByType<T> {
    <T> IBook findByType(T t) throws NoPlacementAvailableException;
}
