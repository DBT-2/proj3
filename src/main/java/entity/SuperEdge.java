package entity;

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
}
