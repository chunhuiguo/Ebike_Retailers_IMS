/**
 * @(#)VoucherAct.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.luyuan.deal.dao.CodeCreatorDAO;
import com.luyuan.deal.entity.CodeCreator;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 自动生成编码的公共服务
 *
 * @author gch
 */

public class CodeCreatorService {

	public String createCode(String prefix) {
		
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());	
		String year = date.substring(0, 4);

		Integer dealerId = (Integer) ActionContext.getContext().getSession().get("parentId");
		CodeCreator codeCreator = codeCreatorDAO.findNumber(dealerId, prefix, year);
		String number = "0001";
		
		if( codeCreator != null) {			
			number = String.format("%1$04d", codeCreator.getNumberSerial());
			codeCreator.setNumberSerial( codeCreator.getNumberSerial() + 1 );
			codeCreatorDAO.update(codeCreator);	
		}
		else {
			codeCreator = new CodeCreator();			
			codeCreator.setDealerId(dealerId);
			codeCreator.setPrefix(prefix);
			codeCreator.setYear(year);
			codeCreator.setNumberSerial(2);
			codeCreatorDAO.save(codeCreator);			
		}
		return prefix + "-" + date + "-" +number;
	}
	
	//DAO
	private CodeCreatorDAO codeCreatorDAO;
	public void setCodeCreatorDAO(CodeCreatorDAO codeCreatorDAO) {
		this.codeCreatorDAO = codeCreatorDAO;
	}
	
}
