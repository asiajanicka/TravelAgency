package org.jjm.interfaces;

import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;

import java.util.List;

public interface IFindAvailablePlacement {
    IBook find(int number) throws NoPlacementException, NoPlacementAvailableException;

    List<IBook> findAllAvailable();

    IBook findFirstAvailable() throws NoPlacementAvailableException;

}
