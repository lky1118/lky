package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.PCompanyMapper;
import com.xiaoshu.dao.PPersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.PCompany;
import com.xiaoshu.entity.PPerson;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;
import com.xiaoshu.vo.Gvo;
import com.xiaoshu.vo.Pvo;

@Service
public class PPrersonService {

	@Autowired
	private PPersonMapper pPersonMapper;
	@Autowired
	private PCompanyMapper pCompanyMapper;
	
	/*// 新增
	public void addUser(User t) throws Exception {
		pPersonMapper.insert(t);
	};

	// 修改
	public void updateUser(User t) throws Exception {
		pPersonMapper.updateByPrimaryKeySelective(t);
	};

	


	// 通过用户名判断是否存在，（新增时不能重名）
	/*public User existUserWithUserName(String userName) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};
*/
	

	public PageInfo<Pvo> findUserPage(Pvo g, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Pvo> pList = pPersonMapper.findAll(g);
		PageInfo<Pvo> pageInfo = new PageInfo<Pvo>(pList);
		return pageInfo;
	}


	public List<PCompany> findAllgs() {
		//查询所有公司
		return pCompanyMapper.selectAll();
	}

	// 删除
		public void deletePerson(Integer id) throws Exception {
			pCompanyMapper.deleteByPrimaryKey(id);
		}
		// 修改
		public void updatePerson(PPerson t) throws Exception {
			pPersonMapper.updatePerson(t);
		};

		/*public void updatePerson(PPerson person)throws Exception {
			// TODO Auto-generated method stub
			
		}*/


		public void addPerson(PPerson p) throws Exception {
			pPersonMapper.insert(p);
			
		};



}
