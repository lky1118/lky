package com.xiaoshu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuExample;
import com.xiaoshu.entity.StuExample.Criteria;
import com.xiaoshu.entity.Svo;


@Service
public class StuService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	StuMapper stuMapper;
	@Autowired
	MajorMapper majorMapper;

	
	// 新增
	public void addStu(Svo svo) throws Exception {
		stuMapper.insert(svo);
	};

	// 修改
	public void updateStu(Svo svo) throws Exception {
		stuMapper.updateByPrimaryKeySelective(svo);
	};

	// 删除
	public void deleteStu(Integer id) throws Exception {
		stuMapper.deleteByPrimaryKey(id);
	};

	

	// 通过用户名判断是否存在，（新增时不能重名）
	public Stu existUserWithUserName(String sName) throws Exception {
		StuExample example = new StuExample();
		Criteria criteria = example.createCriteria();
		criteria.andSNameEqualTo(sName);
		List<Stu> stuList = stuMapper.selectByExample(example);
		return stuList.isEmpty()?null:stuList.get(0);
	};



	public PageInfo<Svo> findStuPage(Svo svo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Svo> stuList = stuMapper.selectStu(svo);
		PageInfo<Svo> pageInfo = new PageInfo<Svo>(stuList);
		return pageInfo;
	}

	public List<Major> findAllpage() {
		// TODO Auto-generated method stub
		return majorMapper.selectAll();
	}

	public Map<String, Integer> echarts() {
		// TODO Auto-generated method stub
		return stuMapper.echarts();
	}


	


}
