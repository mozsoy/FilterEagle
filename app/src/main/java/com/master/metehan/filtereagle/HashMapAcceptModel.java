package com.master.metehan.filtereagle;

/**
 * Created by Metehan on 6/19/2016.
 */
import java.util.HashMap;

public class HashMapAcceptModel {

    public HashMap<String, Integer> completeHashMapListList = new HashMap<String, Integer>();

    public HashMap<String, Integer> getCompleteHashMapListList() {
        return completeHashMapListList;
    }
    public void setCompleteHashMapListList(HashMap<String, Integer> completeHashMapListList) {
        this.completeHashMapListList = completeHashMapListList;
    }


    @Override
    public String toString() {
        return "HashMapAcceptModel [completeHashMapListList="
                + completeHashMapListList + "]";
    }
}
