package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;

import com.xiaoshu.dao.GoodstypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsExample;
import com.xiaoshu.entity.GoodsExample.Criteria;
import com.xiaoshu.entity.Goodstype;
import com.xiaoshu.entity.Gvo;
import com.xiaoshu.entity.Svo;
import com.xiaoshu.entity.User;

@Service
public class GoodsService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	GoodstypeMapper goodstypeMapper;
	
	// 新增
	public void addGoods(Gvo gvo) throws Exception {
		goodsMapper.insert(gvo);
	};

	// 修改
	public void updateGood(Gvo gvo) throws Exception {
		goodsMapper.updateByPrimaryKeySelective(gvo);
	};


	// 通过用户名判断是否存在，（新增时不能重名）
	public Goods existUserWithUserName(String name) throws Exception {
		GoodsExample example = new GoodsExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Goods> userList = goodsMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};


	public PageInfo<Gvo> findGoodsPage(Gvo gvo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Gvo> goodList = goodsMapper.selectGoods(gvo);
		PageInfo<Gvo> pageInfo = new PageInfo<Gvo>(goodList);
		return pageInfo;
	}

	

	

	public List<Goodstype> findtype() {
		// TODO Auto-generated method stub
		return goodstypeMapper.selectAll();
	}


}
