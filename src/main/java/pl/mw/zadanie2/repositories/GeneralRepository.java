package pl.mw.zadanie2.repositories;

import java.util.List;

/**
 * Interface representing general repository
 * used to manipulate objects in database
 *
 * @author Michał Wojda
 * @version alpha
 */
public interface GeneralRepository<T, K> {

    /**
     * Get one item
     *
     * @param id of generic type
     * @return item of generic type
     */
    T get(K id);

    /**
     * Return list of items
     *
     * @return List of generic items
     */
    List<T> getAll();

    /**
     * Add item of ganaric type into database
     *
     * @param item to be added into database
     */
    void add(T item);

    /**
     * Delete item of generic type from database
     *
     * @param id of the item to be deleted from database
     */
    void delete(K id);

    /**
     * Update item of generic type in database
     *
     * @param item to be updated in database
     */
    void update(T item);

}
