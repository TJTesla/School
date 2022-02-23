package com.company;

public class DirectionGraph extends Graph {

    /**
     * Ein Objekt vom Typ Graph wird erstellt. Der von diesem Objekt
     * repraesentierte Graph ist leer.
     */
    public DirectionGraph(){
        super();
    }

    /**
     * Die Anfrage liefert alle Nachbarn des Knotens pVertex als neue Liste vom Typ List<Vertex>. Hat der Knoten
     * pVertex keine Nachbarn in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so
     * wird eine leere Liste zurueckgeliefert.
     */
    public List<Vertex> getNeighbours(Vertex pVertex){
        List<Vertex> result = new List<Vertex>();

        //Alle Kanten durchlaufen.
        edges.toFirst();
        while (edges.hasAccess()){

            //Wenn ein Knoten der Kante pVertex ist, den anderen als Nachbarn in die Ergebnisliste einfuegen.
            Vertex[] vertexPair = edges.getContent().getVertices();
            if (vertexPair[0] == pVertex) {
                result.append(vertexPair[1]);
            }
            edges.next();
        }
        return result;
    }

    /**
     * Die Anfrage liefert eine neue Liste alle inzidenten Kanten zum Knoten pVertex. Hat der Knoten
     * pVertex keine inzidenten Kanten in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so
     * wird eine leere Liste zurueckgeliefert.
     */
    public List<Edge> getEdges(Vertex pVertex){
        List<Edge> result = new List<Edge>();

        //Alle Kanten durchlaufen.
        edges.toFirst();
        while (edges.hasAccess()){

            //Wenn ein Knoten der Kante pVertex ist, dann Kante als inzidente Kante in die Ergebnisliste einfuegen.
            Vertex[] vertexPair = edges.getContent().getVertices();
            if (vertexPair[0] == pVertex) {
                result.append(edges.getContent());
            }
            edges.next();
        }
        return result;
    }

    public Edge getEdge(Vertex pVertex, Vertex pAnotherVertex){
        Edge result = null;

        //Kanten durchsuchen, solange keine passende gefunden wurde.
        edges.toFirst();
        while (edges.hasAccess() && result == null){

            //Pruefen, ob die Kante pVertex und pAnotherVertex verbindet.
            Vertex[] vertexPair = edges.getContent().getVertices();
            if (vertexPair[0] == pVertex && vertexPair[1] == pAnotherVertex) {
                //Kante als Ergebnis merken.
                result = edges.getContent();
            }
            edges.next();
        }
        return result;
    }

}
