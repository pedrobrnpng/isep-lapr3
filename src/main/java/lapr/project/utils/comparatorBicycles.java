/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Comparator;
import lapr.project.model.Bicycle;

/**
 *
 * @author Simao
 */
public class comparatorBicycles implements Comparator<Bicycle>{
    
    public int compare(Bicycle a, Bicycle b){
       if(a.getID()>b.getID()){
            return 1;
        }else if(a.getID()<b.getID()){
            return -1;
        }else{
            return 0;
        }
    }
}
