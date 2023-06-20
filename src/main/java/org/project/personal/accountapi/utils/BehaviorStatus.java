package org.project.personal.accountapi.utils;

public enum BehaviorStatus{
    SUCCESS,
    FAIL;
    @Override
    public String toString(){
        return name();
    }
}