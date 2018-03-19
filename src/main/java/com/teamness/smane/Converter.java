package com.teamness.smane;

public interface Converter<F, T> {

    T convert(F t);

    static Converter<?, ?> identity = new Converter<Object, Object>() {
        @Override
        public Object convert(Object t) {
            return t;
        }
    };

}
