package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 
 * @author dgm
 * @describe "通用crud接口"
 * @date 2020年5月20日
 */
@Service
public interface BaseService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int deleteByExample(Object example);
    
    int updateAll(T entity);

    int updateNotNull(T entity);
    
    int updateByExample(T entity, Object example);
    
    List<T> selectByExample(Object example);

    List<T> selectAll();
    
    int selectCountByExample(Object example);
    
    //TODO 其他...
}
