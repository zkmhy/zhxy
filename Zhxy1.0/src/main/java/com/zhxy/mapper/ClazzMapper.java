package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.ClazzInfo;
import com.zhxy.domain.MyNotice;
import com.zhxy.domain.People;

/**
 * 
 * @author 晨曦
 *
 */
@Mapper
public interface ClazzMapper {

	Clazz queryById(int id);
	
	Clazz queryInfoById(int id);
	
	List<Clazz> allClazz();
	
	List<Clazz> queryAll();

	List<Clazz> teacherClazz(List<Integer> ids);
	
	List<Clazz> findClazz(@Param("teacher")People people,@Param("begin")Date begin,@Param("end")Date end,@Param("before")Date before);

	Integer isStudyByOnself(@Param("clazz")Clazz clazz,@Param("begin")Date date,@Param("end")Date end);
	
	List<Clazz> clazz(@Param("ids")List<Integer> id,@Param("grade")Integer grade);
	
	List<Clazz> notIndateClazz(@Param("ids")List<Integer> id,@Param("grade")Integer grade,@Param("date") String date);
	
	/**
	 *  根据消息ID查询有哪些班级
	 * @param id
	 * @return 班级集合
	 */
	List<Clazz> queryClazzByNotice(int id);
	
	int nextNum(@Param("gid")int gid,@Param("mid")Integer mid);
	
	boolean existClazz();
	
	List<MyNotice> queryNotices(int id);

	void appendClazz(ClazzInfo clazzInfo);
	
	void clazzStu(ClazzInfo clazzInfo);
	
	void clazzCurr(ClazzInfo clazzInfo);
}
