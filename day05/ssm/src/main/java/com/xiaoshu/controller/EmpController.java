package com.xiaoshu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.Evo;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.EmpService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("emp")
public class EmpController extends LogController{
	static Logger logger = Logger.getLogger(EmpController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	@Autowired
	private EmpService empservice;
	

	
	
	@RequestMapping("empIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		//查询所有部门
		List<Dept> dList = empservice.findAlls();
		request.setAttribute("operationList", operationList);
		request.setAttribute("dList", dList);
		request.setAttribute("roleList", roleList);
		return "emp";
	}
	
	
	@RequestMapping(value="empList",method=RequestMethod.POST)
	public void empList(HttpServletRequest request,Evo evo,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Evo> eList= empservice.findEmpPage(evo, pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",eList.getTotal() );
			jsonObj.put("rows", eList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveEmp")
	public void reserveEmp(HttpServletRequest request,Emp emp,HttpServletResponse response){
		Integer eId = emp.geteId();
		JSONObject result=new JSONObject();
		try {
			if (eId != null) {   // userId不为空 说明是修改
				Emp userName = empservice.existUserWithUserName(emp.geteName());
				if(userName != null && userName.geteId().compareTo(eId)==0){
					emp.seteId(eId);
					empservice.updateEmp(emp);
					result.put("success", true);
				}else{
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
				
			}else {   // 添加
				if(empservice.existUserWithUserName(emp.geteName())==null){  // 没有重复可以添加
					empservice.addEmp(emp);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	@RequestMapping("deleteEmp")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				empservice.deleteEmp(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	

}
