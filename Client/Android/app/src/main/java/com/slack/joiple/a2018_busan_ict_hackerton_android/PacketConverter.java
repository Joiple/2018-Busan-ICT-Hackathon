package com.slack.joiple.a2018_busan_ict_hackerton_android;

import java.util.Iterator;
import java.util.TreeMap;

public class PacketConverter {
    TreeMap<String,String> map;
    public PacketConverter(){
        map=new TreeMap<String,String>();
    }
    public void addItem(String key,String val){
        map.put(key,val);
    }
    public String getItem(String key){
        return map.get(key);
    }
    public void removeItem(String key){
        map.remove(key);
    }


    public String pack(){
        String ret="{";
        Iterator<String> keys = map.keySet().iterator();
        while ( keys.hasNext() ) {
            String key = keys.next();
            ret+="'"+key+"' : "+"'"+map.get(key)+"'";
            if(keys.hasNext())ret+=",";
        }
        ret+="}";
        return ret;
    }
    public void unPack(String input){
        String data=input.substring(input.indexOf("\"data\":\"{")+"\"data\":\"{".length(),input.indexOf("}\",\"files\""));
        data.replace("'","");
        String[] field=data.split(",");
        for(int i=0;i<field.length;i++){
            String[] tmpField=field[i].split(" : ");
            map.put(tmpField[0],tmpField[1]);
        }
    }
}
