package com.xiaoshu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.ContentcategoryMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentExample;
import com.xiaoshu.entity.ContentExample.Criteria;
import com.xiaoshu.entity.Contentcategory;
import com.xiaoshu.entity.Cvo;
import com.xiaoshu.entity.User;


@Service
public class ContentService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ContentMapper contentMapper;
	@Autowired
	ContentcategoryMapper contentcategoryMapper;


	// 修改
	public void updateContent(Cvo cvo) throws Exception {
		contentMapper.updateByPrimaryKeySelective(cvo);
	};

	// 删除
	public void deleteContent(Integer id) throws Exception {
		contentMapper.deleteByPrimaryKey(id);
	};

	
	// 通过用户名判断是否存在，（新增时不能重名）
	public Content existUserWithUserName(String contenttitle) throws Exception {
		//UserExample example = new UserExample();
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andContenttitleEqualTo(contenttitle);
		List<Content> userList = contentMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	public PageInfo<Cvo> findConPage(Cvo cvo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Cvo> cList = contentMapper.selectcontent(cvo);
		PageInfo<Cvo> pageInfo = new PageInfo<Cvo>(cList);
		return pageInfo;
	}

	public List<Contentcategory> findAlls() {
	
		return contentcategoryMapper.selectAll();
	}

	public Map<String, Integer> echarts() {
		// TODO Auto-generated method stub
		return contentMapper.echarts();
	}






}
