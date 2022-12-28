package org.jjm.utils;

@FunctionalInterface
public interface Checker<T> {
    boolean check(T t);
}
