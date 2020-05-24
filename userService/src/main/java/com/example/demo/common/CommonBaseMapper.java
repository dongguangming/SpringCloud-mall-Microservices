package com.example.demo.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;



/**
 * @author dgm
 */
public interface CommonBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

