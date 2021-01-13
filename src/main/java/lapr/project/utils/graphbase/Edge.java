package lapr.project.utils.graphbase;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * 
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */

public class Edge<V,E> implements Comparable<E> {
    
    private E element;           // Edge information
    private double weight;       // Edge weight
    private Vertex<V,E> vOrig;  // vertex origin
    private Vertex<V,E> vDest;  // vertex destination
    
    /**
     *
     */
    public Edge() {
        element = null; weight= 0.0; vOrig=null; vDest=null; } 
    
    /**
     *
     * @param eInf
     * @param ew
     * @param vo
     * @param vd
     */
    public Edge(E eInf, double ew, Vertex<V,E> vo, Vertex<V,E> vd) {
        element = eInf; weight= ew; vOrig=vo; vDest=vd;} 
  
    /**
     *
     * @return
     */
    public E getElement() { return element; }	 

    /**
     *
     * @param eInf
     */
    public void setElement(E eInf) { element = eInf; }
    
    /**
     *
     * @return
     */
    public double getWeight() { return weight; }	 

    /**
     *
     * @param ew
     */
    public void setWeight(double ew) { weight= ew; }
    
    /**
     *
     * @return
     */
    public V getVOrig() { 
        if (this.vOrig != null) 
            return vOrig.getElement(); 
        return null;
    }	 

    /**
     *
     * @param vo
     */
    public void setVOrig(Vertex<V,E> vo) { vOrig= vo; }
    
    /**
     *
     * @return
     */
    public V getVDest() { 
        if (this.vDest != null) 
            return vDest.getElement(); 
        return null; 
    }

    /**
     *
     * @param vd
     */
    public void setVDest(Vertex<V,E> vd) { vDest= vd; }
    
    /**
     *
     * @return
     */
    public V[] getEndpoints() { 
        
        V oElem=null, dElem=null, typeElem=null;
        
        if (this.vOrig != null) 
           oElem = vOrig.getElement();      
        
        if (this.vDest != null)
           dElem = vDest.getElement(); 
        
        if (oElem == null && dElem == null)
          return null;

        if (oElem != null)          // To get type
            typeElem = oElem;
        
        if (dElem != null)
            typeElem = dElem;
        @SuppressWarnings("unchecked")
        V[] endverts = (V [])Array.newInstance(typeElem.getClass(), 2);  

        endverts[0]= oElem; 
        endverts[1]= dElem;
        
        return endverts; 
    }
           
    @Override
    public boolean equals(Object otherObj) {
        
        if (this == otherObj){
            return true;
        }
        
        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked")
        Edge<V,E> otherEdge = (Edge<V,E>) otherObj;
        
        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null) ||
                (this.vOrig != null && otherEdge.vOrig == null))
            return false;
        
        if ((this.vDest == null && otherEdge.vDest!=null) ||
                (this.vDest != null && otherEdge.vDest == null))
            return false;
        
        if (this.vOrig != null && otherEdge.vOrig != null && 
                !this.vOrig.equals(otherEdge.vOrig))
                return false;
        
        if (this.vDest != null && otherEdge.vDest!=null && 
                !this.vDest.equals(otherEdge.vDest))
            return false;
      
        if (this.weight != otherEdge.weight)
            return false;
        
        if (this.element != null && otherEdge.element != null) 
           return this.element.equals(otherEdge.element);
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.element);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.vOrig);
        hash = 89 * hash + Objects.hashCode(this.vDest);
        return hash;
    }
    
    @Override
    public int compareTo(Object otherObject) {
        @SuppressWarnings("unchecked")
        Edge<V,E> other = (Edge<V,E>) otherObject ;
        if (this.weight < other.weight)  return -1;
        if (this.weight == other.weight) return 0;
        return 1;
    }
       
    @Override
    public Edge<V,E> clone() {
        
        Edge<V,E> newEdge = new Edge<>();
        
        newEdge.element = element;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;
        
        return newEdge;
    }
    
    @Override
    public String toString() {
        String st="";
        if (element != null)
           st= "      (" + element + ") - ";
        else
            st= "\t "; 
            
        if (weight != 0)
            st += weight +" - " +vDest.getElement()+ "\n";
        else
            st += vDest.getElement()+ "\n";

        return st;
    }

}
