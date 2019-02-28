package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Major;

public interface MajorService {

	List<Major> majors(boolean all);

	Major queryById(int id);
}
