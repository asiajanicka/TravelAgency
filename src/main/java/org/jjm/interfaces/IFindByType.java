package org.jjm.interfaces;

import org.jjm.exceptions.NoPlacementAvailableException;

public interface IFindByType<T,V> {
    V findByType(T t) throws NoPlacementAvailableException;
}
