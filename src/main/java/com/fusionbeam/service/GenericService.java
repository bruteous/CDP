package com.fusionbeam.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MikeChen
 * Date: 9/9/12
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericService<T,S> {
    public  T create(S dto);
    public  T update(S dto);
    public  T delete(Long id);
    public  List<T> findAll();
    public  T findById(Long id);
}
