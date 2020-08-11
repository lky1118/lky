package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.AreaMapper;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Area;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.SchoolExample;
import com.xiaoshu.entity.SchoolExample.Criteria;
import com.xiaoshu.entity.Svo;
import com.xiaoshu.entity.User;


@Service
public class SchoolService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	SchoolMapper schoolMapper;
	@Autowired
	AreaMapper areaMapper;

	

	// 新增
	public void addStudent(Svo svo) throws Exception {
		schoolMapper.insert(svo);
	};

	// 修改
	public void updateStudent(Svo svo) throws Exception {
		schoolMapper.updateByPrimaryKeySelective(svo);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public School existUserWithUserName(String name) throws Exception {
		SchoolExample example = new SchoolExample();
		Criteria criteria = example.createCriteria();
		criteria.andSchoolnameEqualTo(name);
		List<School> sList = schoolMapper.selectByExample(example);
		return sList.isEmpty()?null:sList.get(0);
	};
	

	public PageInfo<Svo> findSchoolPage(Svo svo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Svo> sList = schoolMapper.selectschool(svo);
		PageInfo<Svo> pageInfo = new PageInfo<Svo>(sList);
		return pageInfo;
	}

	public  List<Area> findAlls() {
		
		return  areaMapper.selectAll();
	}


}
