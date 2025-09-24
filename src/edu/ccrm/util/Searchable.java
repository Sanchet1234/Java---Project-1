package edu.ccrm.util;

import java.util.List;
import java.util.function.Predicate;

public interface Searchable<T> {
    List<T> search(Predicate<T> predicate);
}
