package com.example.lab4.domain;

import java.util.Objects;

public class Tuple<E1, E2> {
    private E1 e1;
    private E2 e2;

    public Tuple() {}

    public Tuple(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 getLeft() {
        return e1;
    }

    public E2 getRight() {
        return e2;
    }

    public void setLeft(E1 e1) {
        this.e1 = e1;
    }

    public void setRight(E2 e2) {
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }

    public boolean equals(Object o) {
        if (!(o instanceof Tuple<?, ?>)) return false;
        return this.e1.equals(((Tuple<?, ?>) o).e1) && this.e2.equals(((Tuple<?, ?>) o).e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }
}
