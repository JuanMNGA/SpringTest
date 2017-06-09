package com.atsistemas.simpleSpringCrud.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmruiz on 09/06/2017.
 */
@Service
public class CommonService {
	
    @Autowired
    private DozerBeanMapper dozer;

    public <T, S> T transform(S source, Class<T> destinationClass){
        return dozer.map(source, destinationClass);
    }

    public <T, S> List<T> transform(List<S> sources, Class<T> destinationClass) {
        List<T> res = new ArrayList<>();
        for (S source : sources)
            res.add(dozer.map(source, destinationClass));
        return res;
    }
}
