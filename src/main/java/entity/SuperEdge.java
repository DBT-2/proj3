package entity;

import java.util.Objects;

public class SuperEdge {
    public SuperNode from;
    public SuperNode to;

    public SuperEdge() {
    }

    public SuperEdge(SuperNode from, SuperNode to) {
        this.from = from;
        this.to = to;
    }
    public SuperNode anotherPoint(SuperNode v){
        if(this.from==v) return this.to;
        else return this.from;
    }

    @Override
    public String toString() {
        return "SuperEdge{" +
                  from.id +
                "," + to.id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperEdge superEdge = (SuperEdge) o;
        return from == superEdge.from &&
                to == superEdge.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
