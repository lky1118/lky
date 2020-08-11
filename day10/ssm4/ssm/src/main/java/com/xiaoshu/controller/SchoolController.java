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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Area;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Svo;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.SchoolService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("school")
public class SchoolController extends LogController{
	static Logger logger = Logger.getLogger(SchoolController.class);

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("schoolIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		List<Area> aList = schoolService.findAlls();
		request.setAttribute("aList", aList);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", roleList);
		return "school";
	}
	
	
	@RequestMapping(value="schoolList",method=RequestMethod.POST)
	public void schoolList(HttpServletRequest request,Svo svo,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Svo> sList= schoolService.findSchoolPage(svo,pageNum,pageSize);
			
		
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",sList.getTotal() );
			jsonObj.put("rows", sList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	// 新增或修改
		@RequestMapping("reserveSchool")
		public void reserveUser(HttpServletRequest request,Svo svo,HttpServletResponse response){
			Integer userId = svo.getId();
			JSONObject result=new JSONObject();
			System.out.println(svo);
			try {
				if (userId != null) {   // userId不为空 说明是修改
				
						svo.setId(userId);
						schoolService.updateStudent(svo);
						result.put("success", true);
				
					
				}else {   // 添加
					if(schoolService.existUserWithUserName(svo.getSchoolname())==null){  // 没有重复可以添加
						schoolService.addStudent(svo);
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
	
	
	
	
}
