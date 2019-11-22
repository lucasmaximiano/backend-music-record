package com.music.record.business;

import java.util.List;

import com.music.record.model.Disc;

public interface DiscBusiness {
	
	List<Disc> read(Integer page, Integer pageSize, String gender);
}
