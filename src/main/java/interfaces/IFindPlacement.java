package interfaces;

import exceptions.NoPlacementAvailableException;
import exceptions.NoPlacementException;

import java.util.List;

public interface IFindPlacement {
    IBook find(int number) throws NoPlacementException, NoPlacementAvailableException;

    List<IBook> findAllAvailable();

    IBook findFirstAvailable() throws NoPlacementAvailableException;
}
