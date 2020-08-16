package com.xiaoshu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentExample;
import com.xiaoshu.entity.StudentExample.Criteria;
import com.xiaoshu.entity.Svo;
import com.xiaoshu.entity.Teacher;
import com.xiaoshu.entity.User;


@Service
public class StuService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	TeacherMapper teacherMapper;

	// 新增
	public void addStu(Svo svo) throws Exception {
		studentMapper.insert(svo);
	};

	// 修改
	public void updateStu(Svo svo) throws Exception {
		studentMapper.updateByPrimaryKeySelective(svo);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};

	
	// 通过用户名判断是否存在，（新增时不能重名）
	public Student existUserWithUserName(String name) throws Exception {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Student> sList = studentMapper.selectByExample(example);
		return sList.isEmpty()?null:sList.get(0);
	};

	

	public PageInfo<Svo> findStuPage(Svo svo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Svo> sList = studentMapper.selectStu(svo);
		PageInfo<Svo> pageInfo = new PageInfo<Svo>(sList);
		return pageInfo;
	}

	public List<Teacher> findAlls() {
		// TODO Auto-generated method stub
		return teacherMapper.selectAll() ;
	}

	public Map<String, Integer> echarts() {
		// TODO Auto-generated method stub
		return studentMapper.echarts();
	}

	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.insert(teacher);
		
	}


}
