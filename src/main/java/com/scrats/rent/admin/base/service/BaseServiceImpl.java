package com.scrats.rent.admin.base.service;

import com.github.pagehelper.PageHelper;
import com.scrats.rent.admin.base.mapper.BaseMapper;
import com.scrats.rent.admin.common.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/31 10:35.
 */
public class BaseServiceImpl<T, D extends BaseMapper<T>> implements BaseService<T, D> {

    @Autowired
    protected D dao;

    @Override
    public List<T> select(T var1) {
        return dao.select(var1);
    }

    @Override
    public T selectByPrimaryKey(Object var1) {
        return dao.selectByPrimaryKey(var1);
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public T selectOne(T var1) {
        return dao.selectOne(var1);
    }

    @Override
    public int selectCount(T var1) {
        return dao.selectCount(var1);
    }

    @Override
    public List<T> selectByIds(String ids) {
        return dao.selectByIds(ids);
    }

    @Override
    public int insert(T var1) {
        return dao.insert(var1);
    }

    @Override
    public int insertSelective(T var1) {
        return dao.insertSelective(var1);
    }

    @Override
    public int insertList(List<T> var1) {
        return dao.insertList(var1);
    }

    @Override
    public int updateByPrimaryKey(T var1){
        return  dao.updateByPrimaryKey(var1);
    }

    @Override
    public int updateByPrimaryKeySelective(T var1){
        return  dao.updateByPrimaryKeySelective(var1);
    }

    @Override
    public int delete(T var1) {
        return dao.delete(var1);
    }

    @Override
    public int deleteByPrimaryKey(Object var1) {
        return dao.deleteByPrimaryKey(var1);
    }

    @Override
    public int deleteByIds(String ids) {
        return dao.deleteByIds(ids);
    }

    @Override
    public PageInfo<T> select(int page, int rows, T var1) {
        PageHelper.startPage(page, rows);
        List<T> list = dao.select(var1);
        return new PageInfo<T>(list);
    }

    @Override
    public T findBy(String property, Object value) throws TooManyResultsException {
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> modelClass = (Class<T>) pt.getActualTypeArguments()[0];

            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return dao.selectOne(model);
        } catch (ReflectiveOperationException e) {
            //throw new ServiceException(e.getMessage(), e); 最好使用一个自定义异常
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public List<T> findListBy(String property, Object value) {

        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> modelClass = (Class<T>) pt.getActualTypeArguments()[0];
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return dao.select(model);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}
