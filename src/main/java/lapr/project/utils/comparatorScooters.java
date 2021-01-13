/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Comparator;

import lapr.project.model.Scooter;

/**
 *
 * @author Simao
 */
public class comparatorScooters implements Comparator<Scooter>{
    
    public int compare(Scooter a, Scooter b){
        if(a.getID()>b.getID()){
            return 1;
        }else if(a.getID()<b.getID()){
            return -1;
        }else{
            return 0;
        }
    }
}
