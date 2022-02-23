package com.company;

public class Main {

    public static void findDistance(DirectionGraph friends) {
        friends.setAllEdgeMarks(false);
        Queue<Vertex> q = new Queue<Vertex>();
        Vertex currentVertex = friends.getVertex("Anna");
        currentVertex.setMark(true);
        q.enqueue(currentVertex);

        int threshold = 0; // How many vertices in current level
        int distance = 0; // In which level currently
        int counter = 0; // How many vertices from current level already counted
        int sizeNextLevel = 0; // How many vertices will be in the next level
        List<DistanceVertex> saved = new List<DistanceVertex>(); // Save for printing and saving result

        while (!q.isEmpty()) {
            currentVertex = q.front();
            q.dequeue();

            List<Vertex> neighbours = friends.getNeighbours(currentVertex);
            neighbours.toFirst();
            int newNeighbourCount = 0; // How many unmarked neighbours the current vertex has
            while (neighbours.hasAccess()) {
                if (!neighbours.getContent().isMarked()) {
                    q.enqueue(neighbours.getContent());
                    neighbours.getContent().setMark(true);
                    newNeighbourCount += 1;
                }
                neighbours.next();
            }
            sizeNextLevel += newNeighbourCount;
            saved.append(new DistanceVertex(currentVertex, distance));

            if (counter >= threshold) { // Ran through every vertex in current level -> Proceed to next
                threshold = sizeNextLevel;
                counter = 0; // Initialize values
                sizeNextLevel = 0;
                distance += 1;
            }

            counter += 1;
        }

        saved.toFirst();
        while (saved.hasAccess()) {
            saved.getContent().print();
            saved.next();
        }

        friends.setAllEdgeMarks(false);
    }

    public static int size(List<Vertex> l) {
        l.toFirst();
        int counter = 0;
        while (l.hasAccess()) {
            counter += 1;
            l.next();
        }
        return counter;
    }

    public static void main(String[] args) {
        Eulerweg ew = new Eulerweg(EulerMode.TOUR);
        ew.findEulerweg();

	    /*DirectionGraph friends = createGraph();

        findDistance(friends);*/

    }

    public static class DistanceVertex {
        Vertex v;
        int d;
        public DistanceVertex(Vertex pV, int pD) {
            this.v = pV;
            this.d = pD;
        }
        public boolean is(Vertex pV) {
            return this.v == pV;
        }
        public void print() {
            System.out.println(v.getID() + ": " + d);
        }
    }

    public static DirectionGraph createGraph() {
        DirectionGraph temp = new DirectionGraph();

        Vertex anna = new Vertex("Anna");
        Vertex chris = new Vertex("Chris");
        Vertex emil = new Vertex("Emil");
        Vertex greta = new Vertex("Greta");
        Vertex daniel = new Vertex("Daniel");
        Vertex jonas = new Vertex("Jonas");
        Vertex bela = new Vertex("Bela");
        Vertex hanna = new Vertex("Hanna");
        Vertex ina = new Vertex("Ina");
        Vertex flo = new Vertex("Flo");

        Edge ac = new Edge(anna, chris, 1);
        Edge ae = new Edge(anna, emil, 1);
        Edge ag = new Edge(anna, greta, 1);

        Edge bc = new Edge(bela, chris, 1);
        Edge bd = new Edge(bela, daniel, 1);

        Edge ca = new Edge(chris, anna, 1);
        Edge cd = new Edge(chris, daniel, 1);
        Edge ce = new Edge(chris, emil, 1);

        Edge db = new Edge(daniel, bela, 1);
        Edge de = new Edge(daniel, emil, 1);

        Edge ea = new Edge(emil, anna, 1);
        Edge ed = new Edge(emil, daniel, 1);
        Edge eg = new Edge(emil, greta, 1);
        Edge ej = new Edge(emil, jonas, 1);

        Edge fh = new Edge(flo, hanna, 1);

        Edge ga = new Edge(greta, anna, 1);
        Edge gd = new Edge(greta, jonas, 1);
        Edge ge = new Edge(greta, emil, 1);
        Edge gj = new Edge(greta, jonas, 1);

        Edge hi = new Edge(hanna, ina, 1);
        Edge hf = new Edge(hanna, flo, 1);

        Edge ih = new Edge(ina, hanna, 1);

        Edge je = new Edge(jonas, emil, 1);
        Edge jg = new Edge(jonas, greta, 1);

        temp.addVertex(anna);
        temp.addVertex(chris);
        temp.addVertex(emil);
        temp.addVertex(greta);
        temp.addVertex(daniel);
        temp.addVertex(jonas);
        temp.addVertex(bela);
        temp.addVertex(hanna);
        temp.addVertex(ina);
        temp.addVertex(flo);

        temp.addEdge(ac);
        temp.addEdge(ae);
        temp.addEdge(ag);
        temp.addEdge(bc);
        temp.addEdge(bd);
        temp.addEdge(ca);
        temp.addEdge(cd);
        temp.addEdge(ce);
        temp.addEdge(db);
        temp.addEdge(de);
        temp.addEdge(ea);
        temp.addEdge(ed);
        temp.addEdge(eg);
        temp.addEdge(ej);
        temp.addEdge(fh);
        temp.addEdge(ga);
        temp.addEdge(gd);
        temp.addEdge(ge);
        temp.addEdge(gj);
        temp.addEdge(hi);
        temp.addEdge(hf);
        temp.addEdge(ih);
        temp.addEdge(je);
        temp.addEdge(jg);

        return temp;
    }

}
