package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.TbGoodstype;
import com.xiaoshu.entity.TbGoodstypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodstypeMapper extends BaseMapper<TbGoodstype> {
    long countByExample(TbGoodstypeExample example);

    int deleteByExample(TbGoodstypeExample example);

    List<TbGoodstype> selectByExample(TbGoodstypeExample example);

    int updateByExampleSelective(@Param("record") TbGoodstype record, @Param("example") TbGoodstypeExample example);

    int updateByExample(@Param("record") TbGoodstype record, @Param("example") TbGoodstypeExample example);
}