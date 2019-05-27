package algorithms.P3_Tours;

import java.util.ArrayList;

public class Path implements Comparable<Path> {
    Path() {
        this.vertices = new ArrayList<>();
        // this.vertcost = new ArrayList<>();
        this.cost = 0.0;
    }
    Path(ArrayList<Integer> verts, double c) {
        this.vertices = verts;
        this.cost = c;
    }

    public void addVertex(int id, Double cost) {
        this.vertices.add(id);
        // this.vertcost.add(cost);
        this.cost += cost;
    }

    public Path clone() {
        Path p = new Path();
        p.vertices = (ArrayList<Integer>) this.vertices.clone();
        // p.vertcost = (ArrayList<Double>) this.vertcost.clone();
        p.cost = this.cost;
        return p;
    }

    ArrayList<Integer> vertices;
    // ArrayList<Double> vertcost;
    Double cost;

    @Override
    public int compareTo(Path p1) {
        return this.cost.compareTo(p1.cost);
    }
}