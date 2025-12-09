/**
 * @(#)BaseAct.java  1.0 10/04/05
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */

package com.luyuan.action;

import com.luyuan.util.Page;

/**
 * field校验接口,解耦业务层与表现层。用于校验Action的FieldError
 * 
 * refactor：增加了用于获得page的方法，解决listAct传冗余参数的问题
 * 本接口通常仅对page的成员进行校验，这是由于封装PaginateHib导致对action的校验传递到了dao层。
 *
 * @author tom
 * 
 */

public interface IFieldValidation {
	
	public abstract void addFieldError(String fieldName, String errorMessage);
	
	public abstract Page getPage();
	
}
