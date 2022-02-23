package com.company;

/**
 * Write a description of class Eulerweg here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Eulerweg
{
    Graph friends;
    EulerMode selectedMode;

    public Eulerweg(EulerMode m) {
        selectedMode = m;
        friends = new Graph();
        
        friends = createGraph();
    }
    
    private boolean findPossibilityOfEuler() {
        friends.setAllEdgeMarks(false);
        Queue<Vertex> q = new Queue<Vertex>();

        // Nimm den ersten Knoten aus Liste in Graph Objekt
        // Umgeht verändern des pID Parameters beim Wechseln der Graphen
        List<Vertex> vertices = friends.getVertices();
        vertices.toFirst();
        Vertex currentVertex = vertices.getContent();

        currentVertex.setMark(true);
        q.enqueue(currentVertex);
        
        boolean result = true;
        
        while (!q.isEmpty()) {
            currentVertex = q.front();
            q.dequeue();
            
            List<Vertex> neighbours = friends.getNeighbours(currentVertex);
            neighbours.toFirst();
            while (neighbours.hasAccess()) {
                if (!neighbours.getContent().isMarked()) {
                    q.enqueue(neighbours.getContent());
                    neighbours.getContent().setMark(true);
                }
                neighbours.next();
            }
        }
        // Methode bis hier: Normale Breitensuche
        
        vertices.toFirst();
        int amountOdd = 0;
        while (vertices.hasAccess()) {
            // Überprüfe ob ungerade zugelassen werden (Tour vs. Weg) und
            // ob bisherige Anzahl ungeraden Kantenmengen nicht zu groß ist
            if (selectedMode == EulerMode.WEG &&
                    size(friends.getNeighbours(vertices.getContent())) % 2 != 0) {
                amountOdd += 1;
                vertices.next();
                continue;
            }
            // Überprüfe ob verbunden und ob Menge an Kanten gerade
            if (!(vertices.getContent().isMarked() &&
                    size(friends.getNeighbours(vertices.getContent())) % 2 == 0)) {
                result = false;
            }
            vertices.next();
        }
        // Anzahl ungerade MUSS 2 sein, wenn dies gebraucht wird
        if (amountOdd != 2 && selectedMode == EulerMode.WEG) {
            result = false;
        }
        friends.setAllEdgeMarks(false);
        return result;
    }

    public void findEulerweg() {
        if (!findPossibilityOfEuler()) {
            System.out.println("There is no possible Eulerweg/-tour :(");
            return;
        }
        friends.setAllEdgeMarks(false);
        Vertex startVertex;
        if (selectedMode == EulerMode.WEG) {
            startVertex = findOddEdge();
        } else {
            startVertex = findMaxEdgeAmount();
        }

        System.out.println("A possible Eulerweg/-tour would be: ");
        runEulerweg(startVertex);

        friends.setAllEdgeMarks(false);
    }

    private void runEulerweg(Vertex v) {
        // Abwandlung Tiefensuche
        // Kein Backtracking
        System.out.println(v.getID());

        List<Edge> edges = friends.getEdges(v);

        List<Vertex> neighbours = friends.getNeighbours(v);
        neighbours.toFirst();
        // Gehe alle Nachbarn durch, bis einer gefunden, der mind. noch zweite unmarkierte Kante hat
        // Bspw benötigt bei Haus-vom-Nikolaus (ADBACB)
        while (neighbours.hasAccess()) {
            Vertex checkingVertex = neighbours.getContent();
            if (amountOfUnmarkedEdges(friends.getEdges(checkingVertex)) > 1 &&
                    !friends.getEdge(v, checkingVertex).isMarked()) {
                friends.getEdge(v, checkingVertex).setMark(true);
                runEulerweg(checkingVertex);
                return;
            }
            neighbours.next();
        }

        // Falls jeder benachbarte Knoten nur noch eine unmarkierte Kante hat
        // Eine mögliche Kante aussuchen
        edges.toFirst();
        while (edges.hasAccess()) {
            if (!edges.getContent().isMarked()) {
                edges.getContent().setMark(true);
                Vertex[] vertexPair = edges.getContent().getVertices();
                runEulerweg(vertexPair[0] == v ? vertexPair[1] : vertexPair[0]);
                return;
            }
            edges.next();
        }

    }
    
    private int size(List l) {
        int counter = 0;
        l.toFirst();
        while (l.hasAccess()) {
            counter += 1;
            l.next();
        }
        return counter;
    }

    private int amountOfUnmarkedEdges(List<Edge> l) {
        int counter = 0;
        l.toFirst();
        while (l.hasAccess()) {
            if (!l.getContent().isMarked()) {
                counter += 1;
            }
            l.next();
        }
        return counter;
    }

    private Vertex findOddEdge() {
        List<Vertex> vertices = friends.getVertices();
        vertices.toFirst();
        Vertex max = vertices.getContent();
        int maxValue = size(friends.getEdges(max));

        while (vertices.hasAccess()) {
            int newSize = size( friends.getEdges(vertices.getContent()) );
            if (newSize % 2 != 0 && newSize < maxValue) {
                max = vertices.getContent();
                maxValue = newSize;
            }
            vertices.next();
        }

        return max;
    }

    private Vertex findMaxEdgeAmount() {
        List<Vertex> vertices = friends.getVertices();
        vertices.toFirst();
        Vertex max = vertices.getContent();
        int maxValue = size(friends.getEdges(max));

        while (vertices.hasAccess()) {
            int newSize = size( friends.getEdges(vertices.getContent()) );
            if (newSize > maxValue) {
                max = vertices.getContent();
                maxValue = newSize;
            }
            vertices.next();
        }

        return max;
    }
    
    private Graph createGraph() {
        Graph temp = new Graph();

        if (selectedMode == EulerMode.TOUR) {
            Vertex a = new Vertex("A");
            Vertex b = new Vertex("B");
            Vertex c = new Vertex("C");
            Vertex d = new Vertex("D");
            Vertex e = new Vertex("E");
            Vertex f = new Vertex("F");
            Vertex g = new Vertex("G");
            Vertex h = new Vertex("H");

            Edge ab = new Edge(a, b, 1);
            Edge ad = new Edge(a, d, 1);
            Edge bc = new Edge(b, c, 1);
            Edge cd = new Edge(c, d, 1);
            Edge de = new Edge(d, e, 1);
            Edge eh = new Edge(e, h, 1);
            Edge hf = new Edge(h, f, 1);
            Edge fg = new Edge(f, g, 1);
            Edge gh = new Edge(g, h, 1);
            Edge hd = new Edge(h, d, 1);

            temp.addVertex(h);
            temp.addVertex(b);
            temp.addVertex(c);
            temp.addVertex(d);
            temp.addVertex(e);
            temp.addVertex(f);
            temp.addVertex(g);
            temp.addVertex(a);

            temp.addEdge(ab);
            temp.addEdge(ad);
            temp.addEdge(bc);
            temp.addEdge(cd);
            temp.addEdge(de);
            temp.addEdge(eh);
            temp.addEdge(hf);
            temp.addEdge(fg);
            temp.addEdge(gh);
            temp.addEdge(hd);
        } else {
            Vertex a = new Vertex("A");
            Vertex b = new Vertex("B");
            Vertex c = new Vertex("C");
            Vertex d = new Vertex("D");
            Vertex e = new Vertex("E");
            Vertex f = new Vertex("F");

            Edge ab = new Edge(a, b, 1);
            Edge bc = new Edge(b, c, 1);
            Edge cd = new Edge(c, d, 1);
            Edge df = new Edge(d, f, 1);
            Edge fe = new Edge(f, e, 1);
            Edge ec = new Edge(e, c, 1);

            temp.addVertex(f);
            temp.addVertex(b);
            temp.addVertex(c);
            temp.addVertex(d);
            temp.addVertex(e);
            temp.addVertex(a);

            temp.addEdge(ab);
            temp.addEdge(bc);
            temp.addEdge(cd);
            temp.addEdge(df);
            temp.addEdge(fe);
            temp.addEdge(ec);

            /*
            Vertex a = new Vertex("A");
            Vertex b = new Vertex("B");
            Vertex c = new Vertex("C");
            Vertex d = new Vertex("D");
            Vertex e = new Vertex("E");

            Edge ab = new Edge(a, b, 1);
            Edge ac = new Edge(a, c, 1);
            Edge ad = new Edge(a, d, 1);
            Edge bc = new Edge(b, c, 1);
            Edge bd = new Edge(b, d, 1);
            Edge cd = new Edge(c, d, 1);
            Edge ce = new Edge(c, e, 1);
            Edge de = new Edge(d, e, 1);

            temp.addVertex(d);
            temp.addVertex(b);
            temp.addVertex(c);
            temp.addVertex(a);
            temp.addVertex(e);

            temp.addEdge(ab);
            temp.addEdge(ac);
            temp.addEdge(ad);
            temp.addEdge(bc);
            temp.addEdge(bd);
            temp.addEdge(cd);
            temp.addEdge(ce);
            temp.addEdge(de);

             */
        }

        return temp;
    }
}
