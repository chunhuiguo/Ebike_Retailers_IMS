package com.luyuan.deal.dao;

import java.util.List;

import com.luyuan.deal.entity.CodeCreator;

public interface CodeCreatorDAO {
	
	public abstract CodeCreator findNumber(int dealerId, String prefix, String year);
	
	public abstract void update(CodeCreator transientInstance);
	
	public abstract void save(CodeCreator transientInstance);
}