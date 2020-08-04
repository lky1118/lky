package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.TbGoods;
import com.xiaoshu.entity.TbGoodsExample;
import com.xiaoshu.vo.Gvo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodsMapper extends BaseMapper<TbGoods> {
    long countByExample(TbGoodsExample example);

    int deleteByExample(TbGoodsExample example);

    List<TbGoods> selectByExample(TbGoodsExample example);

    int updateByExampleSelective(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    int updateByExample(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

	List<Gvo> findPage(Gvo g);


}