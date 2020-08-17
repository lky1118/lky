package com.xiaoshu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.BankMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonExample;
import com.xiaoshu.entity.PersonExample.Criteria;
import com.xiaoshu.entity.Pvo;
import com.xiaoshu.entity.User;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	PersonMapper personMapper;
	@Autowired
	BankMapper bankMapper;


	// 新增
	public void addPersom(Pvo pvo) throws Exception {
		personMapper.insert(pvo);
	};

	// 修改
	public void updateperson(Pvo pvo) throws Exception {
		personMapper.updateByPrimaryKeySelective(pvo);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	};

	
	// 通过用户名判断是否存在，（新增时不能重名）
	public Person existUserWithUserName(String pName) throws Exception {
		PersonExample example = new PersonExample();
		Criteria criteria = example.createCriteria();
		criteria.andPNameEqualTo(pName);
		List<Person> pList = personMapper.selectByExample(example);
		return pList.isEmpty()?null:pList.get(0);
	};


	public PageInfo<Pvo> findPersonPage(Pvo pvo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Pvo> pList = personMapper.selectPerson(pvo);
		PageInfo<Pvo> pageInfo = new PageInfo<Pvo>(pList);
		return pageInfo;
	}

	public List<Bank> findAlls() {
		// TODO Auto-generated method stub
		return bankMapper.selectAll();
	}




	public Map<String, Integer> echarts() {
		// TODO Auto-generated method stub
		return personMapper.echarts();
	}


}
