package com.teamness.smane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Handleable<F, T> {
    protected List<Pair<Function<F, T>, Consumer<T>>> handlers = new ArrayList<>();

    public void addHandler(Function<F, T> converter, Consumer<T> handler) {
        handlers.add(new Pair<>(converter, handler));
    }

    private void handle(F data) {
        handlers.forEach(p -> p.second.accept(p.first.apply(data)));
    }

}
