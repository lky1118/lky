package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.PCompany;
import com.xiaoshu.entity.PCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PCompanyMapper extends BaseMapper<PCompany> {
    long countByExample(PCompanyExample example);

    int deleteByExample(PCompanyExample example);

    List<PCompany> selectByExample(PCompanyExample example);

    int updateByExampleSelective(@Param("record") PCompany record, @Param("example") PCompanyExample example);

    int updateByExample(@Param("record") PCompany record, @Param("example") PCompanyExample example);
}