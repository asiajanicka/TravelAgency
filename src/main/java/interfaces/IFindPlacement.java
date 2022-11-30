package interfaces;

import java.util.List;

public interface IFindPlacement {
    IBook find(int number);
    List<IBook> findAllAvailable();
    IBook findFirstAvailable();
}
