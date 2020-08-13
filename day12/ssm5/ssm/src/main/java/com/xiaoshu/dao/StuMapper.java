package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuExample;
import com.xiaoshu.entity.Svo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StuMapper extends BaseMapper<Stu> {
    long countByExample(StuExample example);

    int deleteByExample(StuExample example);

    List<Stu> selectByExample(StuExample example);

    int updateByExampleSelective(@Param("record") Stu record, @Param("example") StuExample example);

    int updateByExample(@Param("record") Stu record, @Param("example") StuExample example);

	List<Svo> selectStu(Svo svo);

	Map<String, Integer> echarts();

	List<Svo> fingpage(Svo svo);
}