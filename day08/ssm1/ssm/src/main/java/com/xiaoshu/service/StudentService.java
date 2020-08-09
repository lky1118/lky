package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentExample;
import com.xiaoshu.entity.StudentExample.Criteria;
import com.xiaoshu.entity.Svo;
import com.xiaoshu.entity.User;


@Service
public class StudentService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	SchoolMapper schoolMapper;


	// 新增
	public void addStudent(Student s) throws Exception {
		studentMapper.insert(s);
	};

	// 修改
	public void updateStudent(Student t) throws Exception {
		studentMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteStudent(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);
	};
	// 通过用户名判断是否存在，（新增时不能重名）
		public Student existUserWithUserName(String name) throws Exception {
			StudentExample example = new StudentExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<Student> userList = studentMapper.selectByExample(example);
			return userList.isEmpty()?null:userList.get(0);
		};



	public PageInfo<Svo> findStudentPage(Svo svo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
	
		
		List<Svo> sList = studentMapper.findStudent(svo);
		PageInfo<Svo> pageInfo = new PageInfo<Svo>(sList);
		return pageInfo;
	}

	public List<School> findAllgs() {
		// TODO Auto-generated method stub
		return schoolMapper.selectAll();
	}

	




}
