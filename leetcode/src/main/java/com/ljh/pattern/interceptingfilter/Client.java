package com.ljh.pattern.interceptingfilter;

/**
 * @author ljh
 * @date 2019-11-04 09:53
 */
public class Client {

    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }
    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}
