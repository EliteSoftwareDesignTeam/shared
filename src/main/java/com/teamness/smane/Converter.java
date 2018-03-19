package com.teamness.smane;

public interface Converter<F, T> {

    T convert(F t);

    static <T> Converter<T, T> identity() {
        return new Converter<T, T>() {
            @Override
            public T convert(T t) {
                return t;
            }
        };
    }

}
