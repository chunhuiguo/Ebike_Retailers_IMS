package com.luyuan.info.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.luyuan.action.BaseAct;
import com.luyuan.info.biz.PriceBiz;
import com.luyuan.info.biz.PriceTypeBiz;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Price;
import com.luyuan.info.entity.PriceType;
import com.luyuan.info.entity.Product;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;
import com.opensymphony.xwork2.ActionContext;

public class PriceTypeAct extends BaseAct {

	@Override
	public String execute() throws Exception {	
		return null;
	}
    
	public String priceType(){
		
		priceTypeList=priceTypeBiz.findByDealerId((Integer) this.getSession().get("parentId"));		
		return "success";
	}
	
	public String typeDetail(){
		
		if(select.equals("all"))
				priceTypeList=priceTypeBiz.findByDealerId((Integer) this.getSession().get("parentId"));
			else if(select.equals("product")){
	    	    priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"整车");
			    select = "product";
			}
			else{
				priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
				select = "part";
			}
		return "success";
	}
	//价格类型列表
	public String typeList(){
		
		if (submit == null)
			return "success";
		
		if (submit.equals("筛选")) {
			submit = null;
			return "query";
		}
		if (submit.equals("添加")) {
			submit = null;
			if(priceType != null)
				priceType = null;				
			return "add";
		}
		if (submit.equals("修改")) {
			submit = null;
			if(selectId == null)
				return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Integer id = (Integer) it.next();
				priceType=priceTypeBiz.findById(id);
				selectId = null;
			}
			return "edit";
		}
//		if (submit.equals("删除")) {
//			submit = null;
//			if(selectId == null)
//				return "notdelete";
//			for (Iterator it = selectId.iterator(); it.hasNext();) {
//				Integer id = (Integer) it.next();
//				
//				System.out.println(id);
//				priceList = priceBiz.findByDealerId(dealerId);
//				System.out.println(priceList.get(1).getPriceTypeId());
//				for(int i = 0;i<priceList.size();i++){					
//				if(priceList.get(i).getPriceTypeId().equals(id))
//					priceBiz.delete(priceList.get(i).getId());					
//			}
//				priceTypeBiz.delete(id);
//				selectId = null;
//			return "delete";
//			
//			}
//		}
		
		if (submit.equals("打印")) {
			submit = null;
			return "success";
		}
		
		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
    //价格类型添加页面
     public String typeAdd(){
    	
		if (submit == null)
			return "success";
			
		if (submit.equals("保存")) {
			submit = null;
			priceType.setDealerId((Integer) this.getSession().get("parentId"));
			if (this.validation(priceType)){
				if (priceTypeBiz.validation(this, priceType,(Integer) this.getSession().get("parentId"))) {
					priceTypeBiz.save(priceType);
					return "save";
				}
			}else {
				return "success";
			}
			return "success";
		}	
		if (submit.equals("返回")) {
			submit = null;
			return "exit";
		}	
		return "success";
	}
     
     //价格类型修改页面
     public String typeEdit(){
    	 
    	 if(submit == null)
        	 return "success";
    	 
    	 if(submit.equals("保存")){
    		 submit=null;
    		 if (this.validation(priceType)){
  				if (priceTypeBiz.validation(this, priceType,(Integer) this.getSession().get("parentId"))) {
  					priceTypeBiz.update(priceType);
  					return "save";
  				}
  			}else {
  				return "success";
  			}
     		 return "success";
    	 }
    	 if(submit.equals("返回")){
    		 submit=null;
    		 return "exit";
    	 }
    	 return "success";
     }    
     //内部校验方法
  	private boolean validation(PriceType priceType){
  		boolean isError = false;
  		
  		if (priceType.getType().equals("")) {
  			this.addFieldError("priceType.type", "价格类型名称不能为空");
  			isError = true;
  		}
  		if (isError)
  			return false;
  		else
  			return true;
  	}
   //entity   
 	private PriceType priceType;
    public PriceType getPriceType() {
		return priceType;
	}
	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}
    private PriceType type;    
	public PriceType getType() {
		return type;
	}
	public void setType(PriceType type) {
		this.type = type;
	}

	private Price price;
    public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	
	private Product product;	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	//biz
	private PriceBiz priceBiz;
	public void setPriceBiz(PriceBiz priceBiz) {
		this.priceBiz = priceBiz;
	}
	
	private PriceTypeBiz priceTypeBiz;
	public void setPriceTypeBiz(PriceTypeBiz priceTypeBiz) {
		this.priceTypeBiz = priceTypeBiz;
	}
	
	private ProductBiz productBiz;	
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
	
	private UnitBiz unitBiz;	
    public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}

	//list
	private List<Price> priceList;
	public List<Price> getPriceList() {
		return priceList;
	}
	
	private List<PriceType> priceTypeList;
    public List<PriceType> getPriceTypeList() {
		return priceTypeList;
	}
    
    private List<PriceType> priceTypList;
	public List<PriceType> getPriceTypList() {
		return priceTypList;
	}
	
	private List<Product> productList;
	public List<Product> getProductList() {
		return productList;
	}
	//submit
	private String submit;	
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	private String select;
    public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}

	private List<Integer> selectId;
	public List<Integer> getSelectId() {
		return selectId;
	}
	public void setSelectId(List<Integer> selectId) {
		this.selectId = selectId;
	}
}
