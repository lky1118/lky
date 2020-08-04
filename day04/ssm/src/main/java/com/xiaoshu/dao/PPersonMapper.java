package com.xiaoshu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.PPerson;
import com.xiaoshu.entity.PPersonExample;
import com.xiaoshu.vo.Pvo;

public interface PPersonMapper extends BaseMapper<PPerson> {
    long countByExample(PPersonExample example);

    int deleteByExample(PPersonExample example);

    List<PPerson> selectByExample(PPersonExample example);

    int updateByExampleSelective(@Param("record") PPerson record, @Param("example") PPersonExample example);

    int updateByExample(@Param("record") PPerson record, @Param("example") PPersonExample example);

	List<Pvo> findAll(Pvo g);

	void updatePerson(PPerson t);
}