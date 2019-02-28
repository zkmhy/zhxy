package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Student;

@Mapper
public interface StudentMapper {

	boolean existStudent(Integer mid);
	
	List<Student> students(Integer mid);
	
	List<Student> frees(@Param("lists")List<Student> lists,@Param("mid")Integer mid);

	List<Student> querys(@Param("lists")List<Student> lists,@Param("mid")Integer mid);
}
