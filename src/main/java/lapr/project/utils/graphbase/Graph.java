package lapr.project.utils.graphbase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */

public class Graph<V,E> implements GraphInterface<V,E> {
    
    private int numVert;
    private int numEdge;
    private final boolean isDirected;
    private final Map<V,Vertex<V,E>> vertices;  //all Vertices of the graph 
    
    // Constructs an empty graph (either undirected or directed)

    /**
     *
     * @param directed
     */
    public Graph(boolean directed) { 
        numVert=0; 
        numEdge=0;
        isDirected=directed;
        vertices = new LinkedHashMap<>();
    }
      
    /**
     *
     * @return
     */
    public int numVertices(){ return numVert; }
      
    /**
     *
     * @return
     */
    public Iterable<V> vertices() { return vertices.keySet(); }
    
    /**
     *
     * @param vert
     * @return
     */
    public boolean validVertex(V vert) { 
        
        if (vertices.get(vert) == null)
            return false; 
        
        return true;
    }
    
    /**
     *
     * @param vert
     * @return
     */
    public int getKey(V vert) { return vertices.get(vert).getKey(); }
    
    /**
     *
     * @return
     */
    public V[] allkeyVerts() {
        
        //V[] keyverts = (V[]) new Object[numVert];
        V  vertElem = null; 
        for (Vertex<V,E> vert : vertices.values()) 
            vertElem = vert.getElement() ;            // To get type
        @SuppressWarnings("unchecked")
        V[] keyverts = (V [])Array.newInstance(vertElem.getClass(), numVert); 
        
        for (Vertex<V,E> vert : vertices.values())
            keyverts[vert.getKey()]=vert.getElement();
        
        return keyverts;
    }
    
    /**
     *
     * @param c
     * @return
     */
    public V[] allkeyVerts(Class<?> c) {
        
        //V[] keyverts = (V[]) new Object[numVert];
        V  vertElem = null; 
        for (Vertex<V,E> vert : vertices.values()) 
            vertElem = vert.getElement() ;            // To get type
        @SuppressWarnings("unchecked")
        V[] keyverts = (V [])Array.newInstance(c, numVert); 
        
        for (Vertex<V,E> vert : vertices.values())
            keyverts[vert.getKey()]=vert.getElement();
        
        return keyverts;
    }
    
    /**
     *
     * @param vert
     * @return
     */
    public Iterable<V> adjVertices(V vert){ 
        
        if (!validVertex(vert)) 
           return null;
        
        Vertex<V,E> vertex = vertices.get(vert); 
        
        return vertex.getAllAdjVerts(); 
    }
    
    /**
     *
     * @return
     */
    public int numEdges(){ return numEdge; }
    
    /**
     *
     * @return
     */
    public Iterable<Edge<V,E>> edges() {
        List<Edge<V,E>> list= new ArrayList<>();
        for(V v: vertices.keySet()) {
           Vertex<V,E> v2= vertices.get(v);
           for(Edge<V,E> all: v2.getAllOutEdges()) {
               list.add(all);
           }
        }
        return list;
    }
    
    /**
     *
     * @param vOrig
     * @param vDest
     * @return
     */
    public Edge<V,E> getEdge(V vOrig, V vDest){
       
        if (!validVertex(vOrig) || !validVertex(vDest)) 
           return null;
        
        Vertex<V,E> vorig = vertices.get(vOrig);
        
        return vorig.getEdge(vDest);
    }
    
    /**
     *
     * @param edge
     * @return
     */
    public V[] endVertices(Edge<V,E> edge){ 
        
        if (edge == null)
           return null;
        
        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest())) 
           return null;
          
        Vertex<V,E> vorig = vertices.get(edge.getVOrig());
        
        if (!edge.equals(vorig.getEdge(edge.getVDest())))
            return null;
        
        return edge.getEndpoints();
    }
    
    /**
     *
     * @param vert
     * @param edge
     * @return
     */
    public V opposite(V vert, Edge<V,E> edge){
        
        if (!validVertex(vert)) 
           return null;
        
        Vertex<V,E> vertex = vertices.get(vert); 
      
        return vertex.getAdjVert(edge);
    }
    
    public int outDegree(V vert){
 
        if (!validVertex(vert)) 
           return -1;
        
        Vertex<V,E> vertex = vertices.get(vert);  
        
        return vertex.numAdjVerts();
    }
     
    public int inDegree(V vert){
         
        if (!validVertex(vert)) 
           return -1;
                
        int degree=0;       
        for (V otherVert : vertices.keySet()) 
            if (getEdge(otherVert,vert) != null)
                degree++;
             
        return degree;
    }
        
    /**
     *
     * @param vert
     * @return
     */
    public Iterable<Edge<V,E>> outgoingEdges(V vert){
 
        if (!validVertex(vert)) 
            return null;
        
        Vertex<V,E> vertex = vertices.get(vert);           
        
        return vertex.getAllOutEdges();
    }
    
    /**
     *
     * @param vert
     * @return
     */
    public Iterable<Edge<V,E>> incomingEdges(V vert){
        List<Edge<V,E>> list= new ArrayList<>();
        for(V v: vertices.keySet()) {
           Vertex<V,E> v2= vertices.get(v);
           for(Edge<V,E> all: v2.getAllOutEdges()) {
               if(all.getVDest().equals(vert)) {
                   list.add(all);
               }
           }
        }
        return list;
    }
            
    /**
     *
     * @param vert
     * @return
     */
    public boolean insertVertex(V vert){
         
        if (validVertex(vert)) 
            return false;
        
        Vertex<V,E> vertex = new Vertex<>(numVert,vert);
        vertices.put(vert,vertex);
        numVert++;
        
        return true;
    }
    
    /**
     *
     * @param vert
     * @param n
     * @return
     */
    public boolean insertVertex(V vert,int n){
         
        if (validVertex(vert)) 
            return false;
        
        Vertex<V,E> vertex = new Vertex<>(n,vert);
        vertices.put(vert,vertex);
        numVert++;
        
        return true;
    }
    
    /**
     *
     * @param vOrig
     * @param vDest
     * @param eInf
     * @param eWeight
     * @return
     */
    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight){
        
        if (getEdge(vOrig,vDest) != null)
            return false;
        
        if (!validVertex(vOrig))
           insertVertex(vOrig);
        
        if (!validVertex(vDest))
           insertVertex(vDest);
        
        Vertex<V,E> vorig = vertices.get(vOrig);
        Vertex<V,E> vdest = vertices.get(vDest);
        
        Edge<V,E> newEdge = new Edge<>(eInf,eWeight,vorig,vdest);
        vorig.addAdjVert(vDest,newEdge);
        numEdge++;
             
        //if graph is not direct insert other edge in the opposite direction 
        if (!isDirected) 
            // if vDest different vOrig
            if (getEdge(vDest,vOrig) == null){
                Edge<V,E> otherEdge = new Edge<>(eInf,eWeight,vdest,vorig);
                vdest.addAdjVert(vOrig,otherEdge);
                numEdge++;
            }     
         
        return true ;
    }
    
    /**
     *
     * @param vert
     * @return
     */
    public boolean removeVertex(V vert){
        
        if (!validVertex(vert)) 
            return false;

        //remove all edges that point to vert
        for (Edge<V,E> edge : incomingEdges(vert)){
            V vadj = edge.getVOrig();
            removeEdge(vadj,vert);
        }
        
        Vertex<V,E> vertex = vertices.get(vert); 
        
        //update the keys of subsequent vertices in the map
        for (Vertex<V,E> v : vertices.values()){
            int keyVert = v.getKey();
            if ( keyVert > vertex.getKey()){
                keyVert = keyVert-1;
                v.setKey(keyVert);
            }      
        }
        //The edges that live from vert are removed with the vertex    
        vertices.remove(vert);
           
        numVert--;
        
        return true;
    }
    
    /**
     *
     * @param vOrig
     * @param vDest
     * @return
     */
    public boolean removeEdge(V vOrig, V vDest) {
        
        if (!validVertex(vOrig) || !validVertex(vDest)) 
           return false;
       
        Edge<V,E> edge = getEdge(vOrig,vDest);
        
        if (edge == null)
          return false;
        
        Vertex<V,E> vorig = vertices.get(vOrig);
    
        vorig.remAdjVert(vDest);
        numEdge--;
        
        //if graph is not direct 
        if (!isDirected){ 
            edge = getEdge(vDest,vOrig);
            if (edge != null){
               Vertex<V,E> vdest = vertices.get(vDest);
               vdest.remAdjVert(vOrig);
               numEdge--;
            }
        }
        return true;
    }
           
   
    //Returns a clone of the graph 
    public Graph<V,E> clone() {
        
        Graph<V,E> newObject = new Graph<>(this.isDirected);
        
        //insert all vertices
        for (V vert : vertices.keySet())   
            newObject.insertVertex(vert);
        
        //insert all edges
        for (V vert1 : vertices.keySet()) 
            for (Edge<V,E> e : this.outgoingEdges(vert1))  
                if (e != null){
                   V vert2=this.opposite(vert1,e);
                   newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }
        
        return newObject;
    }

    /* equals implementation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    public boolean equals(Object otherObj) {

        if (this == otherObj) 
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Graph<V,E> otherGraph = (Graph<V,E>) otherObj;
        
        if (numVert != otherGraph.numVertices() || numEdge != otherGraph.numEdges()) 
            return false;

        //graph must have same vertices
        boolean eqvertex;
        for (V v1 : this.vertices()){
            eqvertex=false;
            for (V v2 : otherGraph.vertices())
                if (v1.equals(v2))
                   eqvertex=true;
            
            if (!eqvertex)
              return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.numVert;
        hash = 19 * hash + this.numEdge;
        hash = 19 * hash;
        return hash;
    }
    
    

    //string representation
    @Override
    public String toString() {
        String s="" ;
        if (numVert == 0) {
            s = "\nGraph not defined!";
        } 
        else {
            s = "Graph: "+ numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V,E> vert : vertices.values()) 
                s += vert + "\n" ;
        }
        return s ;
    }
}