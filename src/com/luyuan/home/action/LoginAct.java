package com.luyuan.home.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.luyuan.action.BaseAct;
import com.luyuan.home.biz.LoginBiz;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.entity.Unit;
import com.luyuan.sys.entity.Users;
import com.luyuan.util.SQLConnection;

public class LoginAct  extends BaseAct {

	public String login() {
		return "login";
	}
	
	public String logout() {
		this.getSession().clear();
		return "logout";
	}
	
	public String enter() {
		
		//通过验证后，写入数据到session，
		this.setInfoOfSession(user, loginBiz.getUnitByUser(user));
		
//		System.out.println(SQLConnection.se);
//		Object se1 = getAppContext().getBean("lypsiSessionFactory");
//		LocalSessionFactoryBean se = (LocalSessionFactoryBean)getAppContext().getBean("lypsiSessionFactory");
//		BeanWrapper bwComp=new BeanWrapperImpl(se);
//		bwComp.setPropertyValue("hibernate.proxool.xml","proxool2.xml");
//		PropertyValue v=new PropertyValue("hibernate.proxool.pool_alias","DBPool2");
//		PropertyValue v1=new PropertyValue("hibernate.proxool.xml","proxool2.xml");
//	    bwComp.setPropertyValue(v);
//	    bwComp.setPropertyValue(v1);
		
//		try {
//			
//			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
//			Properties info = new Properties();
//			//info.setProperty("proxool.maximum-connection-count", "20");
//			info.setProperty("user", "sa");
//			info.setProperty("password", "123456");
//			String alias = "DBPool";
//			String driverClass = "net.sourceforge.jtds.jdbc.Driver";
//			String driverUrl = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=LYPSI2";
//			String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;
//			ProxoolFacade.registerConnectionPool(url, info);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		//connection = DriverManager.getConnection(url, info);
		//Properties info = new Properties();		
		//System.out.println(info.getProperty("proxool.alias"));
		//info.setProperty("proxool.maximum-connection-count", "10");
		
		//通过验证后，根据用户权限设置菜单，
		this.setUserMenu();
		
		//设置在线....?
		return "success";
	}
	
	public void validateEnter(){
		if(userCode.trim().equals(""))
			this.addFieldError("userCode", "请输入用户名");
		if(userCode.trim().equals(""))
			this.addFieldError("password", "请输入密码");
		if(userCode.trim().equals(""))
			this.addFieldError("verifyKey", "请输入验证码");
		
		validateVerifyKey();
		validateUserCode();
	}

	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	/**
	 * userCode合法的情况下才进行密码校验
	 * 出于安全的考虑，在客户端对userCode与password不做js远程校验
	 */
	private void validateUserCode(){
		user = loginBiz.getUserByCode(userCode);
		if (user == null ){
			this.addFieldError("userCode", "用户不存在！");
			return;
		}

		if (user.getOnline()){
			this.addFieldError("userCode", "该用户已经登录，您不能重复登录！");
			return;
		}

        if (user.getEnabled() != true){
        	this.addFieldError("userCode", "您的账号已失效！");
        	return;
        }
 
		if (!loginBiz.checkUser(user, encrypt, loginTime ) )
			this.addFieldError("password", "密码不正确！");

	}

	/**
	 *  "verifyKey"保存在session中，服务端校验
	 */
	private void validateVerifyKey(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String kaptchaExpected = (String)request.getSession() 
	    .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY); 
		
		if (null == verifyKey || !verifyKey.equalsIgnoreCase(kaptchaExpected)) 
			this.addFieldError("verifyKey", "验证码错误！");
	}
	
	
	private void setInfoOfSession(Users user , Unit unit){
		Map<String,Object> session = this.getSession();
		//用户信息
		session.put("userId", user.getUserId());
		session.put("unitId", user.getUnitId());			//用户所在单位 ID
		session.put("shortName", user.getCompanyCode());  	//用户所在单位 ShortName
		session.put("creatorCode",user.getCreatorCode());	//该用户建立者的编号
		session.put("isDealer",user.getIsDealer());			//true 为经销商用户，否则为系统用户
		session.put("userCode", user.getUserCode());		//用户账号
		session.put("userName", user.getUserName());
		session.put("userType",user.getUserType());			//用户类型：管理员 或 操作员
		session.put("parentId",unit.getParentId());         //对于“经销商”用户来说，parentId和unitId一样！！！
		session.put("parentShortName",unit.getParentShortName());
		session.put("unitType",unit.getType());
		session.put("databaseMap",unit.getDatabaseMap());
		//session.put("opcodes", operationDAO.setSelfOpcode(user.getUserCode()));
	}
	
	private void setUserMenu(){
		if( user != null ) {
			if(user.getUserType().equals("管理员")) {				
				Employee employee = employeeBiz.findByUserId(user.getUserId());
				if(employee == null) {					
					this.getSession().put("menu", loginBiz.getNoEmployeeMenu());
					return;
				}
			}
			
			this.getSession().put("menu", loginBiz.getMenu(user.getUserCode()));
		}
	}
	
	/**************************************************************************************************/
	//page field
	/**
	 * 没有使用user作为page field----因为只用到了Users的两个field：
	 * field:user.userCode->page:userCode
	 * field:user.password->page:password
	 * 
	 * 登录通过验证之后 user将不为空，参见validateUserCode()
	 * user的作用：通过访问数据库进行验证后，下次使用时，不再访问数据库
	 * 用于setInfoOfSession()与setUserMenu()
	 */
	private Users user = null;
	
	
	//page field
	private String userCode;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private String password;
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	/** 验证码 */
	private String verifyKey;
	public void setVerifyKey(String verifyKey) {
		this.verifyKey = verifyKey;
	}
	public String getVerifyKey() {
		return verifyKey;
	}

	/**时间戳，防止重放*/
	private String loginTime;
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginTime(){
		return this.loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**加密串*/
	private String encrypt;
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	/**************************************************************************************************/
	//biz
	
	private LoginBiz loginBiz;
	public void setLoginBiz(LoginBiz loginBiz) {
		this.loginBiz = loginBiz;
	}

	private EmployeeBiz employeeBiz;
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
}
