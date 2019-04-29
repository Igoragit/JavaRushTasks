package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if(versionHistoryMap!=null){
          if(versionHistoryMap.containsKey(rollbackVersion)){
              currentVersion=rollbackVersion;
              Map<Integer, String> tmp = new LinkedHashMap<>();
              for (Map.Entry<Integer,String> in: versionHistoryMap.entrySet()){
                  if(in.getKey()<=currentVersion){
                      tmp.put(in.getKey(),in.getValue());
                  }
              }
              versionHistoryMap=tmp;
              tmp.clear();
          }
          else {
              return false;
          }
        }
        return true;
    }
}
