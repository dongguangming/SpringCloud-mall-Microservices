package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<T> selectAll() {
    	return mapper.selectAll();
    }
    
    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByExample(Object example) {
    	return mapper.deleteByExample(example);
    }
    
    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity, Object example) {
    	return mapper.updateByExampleSelective(entity, example);
    }
    
    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Object example) {
    	return mapper.selectCountByExample(example);
    }
    
    //TODO 其他...
}

