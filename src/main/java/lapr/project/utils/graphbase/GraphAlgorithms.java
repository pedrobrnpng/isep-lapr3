/*
* A collection of graph algorithms.
*/
package lapr.project.utils.graphbase;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author DEI-ESINF
 */

public class GraphAlgorithms {
    
   /**
   * Performs breadth-first search of a Graph starting in a Vertex 
     * @param <V>
     * @param <E>
   * @param g Graph instance
     * @param vert
   * @return qbfs a queue with the vertices of breadth-first search 
   */
    public static<V,E> LinkedList<V> BreadthFirstSearch(Graph<V,E> g, V vert){
        LinkedList<V> qbfs=new LinkedList<>();
        LinkedList<V> qaux=new LinkedList<>();
        try {
            qbfs.add(vert);
            qaux.add(vert);
            boolean[] visited=new boolean[g.allkeyVerts().length];
            visited[g.getKey(vert)]=true;
            while (!qaux.isEmpty()) {
                V vOrig=qaux.poll();
                for (V adj : g.adjVertices(vOrig)) {
                    if (!visited[g.getKey(adj)]) {
                        qbfs.add(adj);
                        qaux.add(adj);
                        visited[g.getKey(adj)]=true;
                    }
                }
            }
            return qbfs;
        } catch (NullPointerException ne) {
            return null;
        }
    }
   
   /**
   * Performs depth-first search starting in a Vertex   
   * @param g Graph instance
   * @param vOrig Vertex of graph g that will be the source of the search
   * @param visited set of discovered vertices
   * @param qdfs queue with vertices of depth-first search
   */
    private static<V,E> void DepthFirstSearch(Graph<V,E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs){
        qdfs.add(vOrig);
        visited[g.getKey(vOrig)]=true;
        for (V adj : g.adjVertices(vOrig)) {
            if (!visited[g.getKey(adj)]) {
                DepthFirstSearch(g, adj, visited, qdfs);
            }
        }
    }  
  
   /**
     * @param <V>
     * @param <E>
   * @param g Graph instance
     * @param vert
   * @return qdfs a queue with the vertices of depth-first search 
   */
    public static<V,E> LinkedList<V> DepthFirstSearch(Graph<V,E> g, V vert){
        try {
            g.getKey(vert);
        } catch (NullPointerException npe) {
            return null;
        }
        LinkedList<V> qdfs=new LinkedList<>();
        boolean[] visited=new boolean[g.allkeyVerts().length];
        DepthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }
   
    /**
   * Returns all paths from vOrig to vDest
   * @param g Graph instance
   * @param vOrig Vertex that will be the source of the path
   * @param vDest Vertex that will be the end of the path
   * @param visited set of discovered vertices
   * @param path stack with vertices of the current path (the path is in reverse order)
   * @param paths ArrayList with all the paths (in correct order)
   */
    private static<V,E> void allPaths(Graph<V,E> g, V vOrig, V vDest, boolean[] visited, LinkedList<V> path, ArrayList<LinkedList<V>> paths){
        visited[g.getKey(vOrig)]=true;
        path.add(vOrig);
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj==vDest) {
                path.add(vDest);
                paths.add(path);
                visited[g.getKey(vAdj)]=false;
                path.removeLast();
            } else {
                if (!visited[g.getKey(vAdj)]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }
        }
        visited[g.getKey(vOrig)]=false;
        path.removeLast();
    }
    
   /**
     * @param <V>
     * @param <E>
     * @param g
   * @param voInf information of the Vertex origin
     * @param vOrig
   * @param vdInf information of the Vertex destination 
     * @param vDest 
   * @return paths ArrayList with all paths from voInf to vdInf 
   */
    public static<V,E> ArrayList<LinkedList<V>> allPaths(Graph<V,E> g, V vOrig, V vDest) {
        boolean[] visited=new boolean[g.allkeyVerts().length];
        for (int i=0;i<visited.length;i++) {
            visited[i]=false;
        }
        LinkedList<V> path=new LinkedList<>();
        ArrayList<LinkedList<V>> paths=new ArrayList<>();
        allPaths(g, vOrig, vDest, visited, path, paths);
        return paths;
    }
    
    /**
   * Computes shortest-path distance from a source vertex to all reachable 
   * vertices of a graph g with nonnegative edge weights
   * This implementation uses Dijkstra's algorithm
     * @param <V>
     * @param <E>
   * @param g Graph instance
     * @param vOrig
   * @param visited set of discovered vertices
   * @param vertices minimum path vertices
     * @param pathKeys  
   * @param dist minimum distances
   */
    public static<V,E> void shortestPathLength(Graph<V,E> g, V vOrig, V[] vertices, boolean[] visited, int[] pathKeys, double[] dist){   
        int vkey = g.getKey(vOrig);
        dist[vkey] = 0;
        while (vkey != -1) {
            vOrig = vertices[vkey];
            visited[vkey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vkeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                if (!visited[vkeyAdj] && dist[vkeyAdj] > dist[vkey] + edge.getWeight()) {
                    dist[vkeyAdj] = dist[vkey] + edge.getWeight();
                    pathKeys[vkeyAdj] = vkey;
                }
            }

            double minDist = Double.MAX_VALUE;
            vkey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vkey = i;
                }
            }
        }
    }
    
    /**
    * Extracts from pathKeys the minimum path between voInf and vdInf
    * The path is constructed from the end to the beginning
     * @param <V>
     * @param <E>
    * @param g Graph instance
     * @param vOrig 
     * @param vDest 
     * @param verts 
    * @param pathkeys minimum path vertices keys  
    * @param path stack with the minimum path (correct order)
     * @param pathKeys
    */
    protected static<V,E> void getPath(Graph<V,E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path){
        if (!vOrig.equals(vDest)) {
            path.push(vDest);
            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            vDest = verts[prevVKey];
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        } else {
            path.push(vOrig);
        }
    }

    //shortest-path between vOrig and vDest

    /**
     *
     * @param <V>
     * @param <E>
     * @param g
     * @param vOrig
     * @param vDest
     * @param shortPath
     * @param c
     * @return
     */
    public static<V,E> double shortestPath(Graph<V,E> g, V vOrig, V vDest, LinkedList<V> shortPath,Class<?> c){
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts(c);
        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }
        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        double lengthPath = dist[g.getKey(vDest)];
        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;
    }
    
    //shortest-path between voInf and all other

    /**
     *
     * @param <V>
     * @param <E>
     * @param g
     * @param vOrig
     * @param paths
     * @param dists
     * @return
     */
    public static<V,E> boolean shortestPaths(Graph<V,E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList <Double> dists ){
      
        if (!g.validVertex(vOrig)) return false;
        
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];         
        double[] dist = new double [nverts]; 
        V[] vertices = g.allkeyVerts();
    
        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        
        dists.clear(); paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList <V> shortPath = new LinkedList<>();
            if (dist[i]!=Double.MAX_VALUE)
                getPath(g,vOrig,vertices[i],vertices,pathKeys,shortPath);                
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    } 
}