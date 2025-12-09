package com.luyuan.sys.biz.impl;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.luyuan.sys.action.ValidationCodeAct;

/** 定义了结果类型，用于输出验证码图片
 *  通过  invocation 对象得到 ValidationCodeAct 实例
 *  调用该实例的方法输出图像
 *  在 strust-home 中增加了该结果类型的说明以及对  ValidationCodeAct 的 action 声明
*/
public class ValidationCodeResult implements Result {

	public void execute(ActionInvocation invocation) throws Exception {
		ValidationCodeAct action = (ValidationCodeAct)invocation.getAction();
		HttpServletResponse rs = ServletActionContext.getResponse();
		
		/** 禁止图片缓存 */
		rs.setHeader("Pargma", "no-cache");
		rs.setHeader("Cache-Control", "no-cache");
		rs.setDateHeader("Expires", 0);
		
		/** 发送媒体类型 */
		rs.setContentType(action.getContentType());
		rs.setContentLength(action.getContentLength());
		
		ServletOutputStream sos = rs.getOutputStream();
		/** 输出图片   */
		sos.write(action.getImageInBytes());
		sos.close();

	}

}
