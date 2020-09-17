package com.ljh.pattern.interceptingfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljh
 * @date 2019-11-04 09:50
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList();
    private Target target;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }
    public void setTarget(Target target){
        this.target = target;
    }
}
