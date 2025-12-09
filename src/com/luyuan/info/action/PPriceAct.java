package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.PPriceBiz;
import com.luyuan.info.biz.PartBiz;
import com.luyuan.info.biz.PriceTypeBiz;
import com.luyuan.info.entity.PPrice;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.PriceType;


public class PPriceAct extends ListAct {

	@Override
	public String execute() throws Exception {	
		return null;
	}
    //配件价格管理菜单入口
	public String pPrice(){
		if(part!=null)
			part=null;
		partList=partBiz.findPart((Integer) this.getSession().get("parentId"),1);
		part=partList.get(0);
		priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
		if(priceTypeList.size()==0)
			this.addFieldError("priceType.type", "请先在【基本信息-价格类型管理】中添加您所需要的价格类型名称！");
		if(ppriceList != null)
    		ppriceList.clear();
    	if(ppriceList == null)
    		ppriceList=new ArrayList<PPrice>();
    	for(int i=0;i < priceTypeList.size(); i++){
			  if(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()) != null)
				ppriceList.add(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()));
   		  else
   		ppriceList.add(new PPrice());		  
    	}	
		return "success";
	}
	
	//配件价格列表pPriceList.jsp
    public String pPriceList(){
   	
		if (submit == null)
			return "success";
		if(submit.equals("选择配件")){
			backString = "list";
			submit = null;
//			partList=partBiz.findPart((Integer) this.getSession().get("parentId"),1);
	   		return "query";
		}
		if (submit.equals("设置价格")) {
			submit = null;	
			priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
			return "add";
		}
		if (submit.equals("修改")) {
			submit = null;
			if(selectId == null)
			    return "success";
			if(selectId.size()>1)
				return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();
				pprice=ppriceBiz.findById(id);
				priceType=priceTypeBiz.findById(pprice.getPriceTypeId());
				selectId = null;
			}
			return "edit";		
		}
		if (submit.equals("删除")) {
			submit = null;
			if(selectId == null)
			return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();				
				ppriceBiz.delete(id);
				selectId = null;
			}
			return "delete";
		}
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
    
	public String pPriceDetail(){
		priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
	    	if(ppriceList != null)
	    		ppriceList.clear();
	    	if(ppriceList == null)
	    		ppriceList=new ArrayList<PPrice>();
	    	for(int i=0;i < priceTypeList.size(); i++){
				  if(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()) != null)
					ppriceList.add(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()));
	   		  else
	   		ppriceList.add(new PPrice());		  
	    	}	
		return "success";
	}
    //配件选择页面partForPrice.jsp
    public String partForPrice(){
    	
   	 if(submit == null){
   		 this.partList(true);
   		 return "success";
   	 }
   	 
   	 if(submit.equals("确定")){
   		 submit=null;
   		 if(selectId == null)
   			 return "success";
   		 for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();
				if(backString.equals("list")){	   	    	 
					part=partBiz.findById(id);
					priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
		   	    	if(ppriceList != null)
		   	    		ppriceList.clear();
		   	    	if(ppriceList == null)
		   	    		ppriceList=new ArrayList<PPrice>();
		   	    	int k=0;
		   	    	for(int i=0;i < priceTypeList.size(); i++){
		   				  if(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()) != null)
		   	   		      ppriceList.add(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()));
		   	   		  else{
		   	   			  k++;
		   	   		      ppriceList.add(new PPrice());
		   	   		  }	   	   			  		  
		   	    	}	
		   	    	if(k==priceTypeList.size())
						this.addFieldError("pprice.price", "该配件的价格还未设置，请先设置价格");
		   	    	part=partBiz.findById(part.getId());
		   	    	selectId = null;
		   	    	return "backList";
		   	     }
				if(backString.equals("add")){
					part=partBiz.findById(id);
					priceTypeList=priceTypeBiz.findByDealerIdAndPpFlag((Integer) this.getSession().get("parentId"),"配件");
		   	    	return "backAdd"; 
		   	     }
				if(backString.equals("sale")){
			   		part=partBiz.findById(id);
			   		priceTypeList=priceTypeBiz.findPriceType((Integer) this.getSession().get("parentId"),"配件","售价");
			 		if(ppriceList != null)
			    		ppriceList.clear();
			    	if(ppriceList == null)
			    		ppriceList=new ArrayList<PPrice>();
			    	for(int i=0;i < priceTypeList.size(); i++){
						  if(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()) != null)
							ppriceList.add(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()));
			   		  else
			   		ppriceList.add(new PPrice());		  
			    	}	
			    	part=partBiz.findById(part.getId()); 
		   	    	selectId = null;
		   	    	return "backSale";
			   		  
			   	  }
				
			}  		 
   	 }
   	 
   	if (submit.equals("筛选")) {
		submit = null;
		this.partList(false);
		return "success";
	}
   	 if(submit.equals("退出")){
   		 submit=null;
   		 return "exit";
   	 }   	 
       return "success";   		   	 
    }
    
     //配件价格添加页面
     public String pPriceAdd(){

    	 if(submit == null)
        	 return "success";
    	 if(submit.equals("选择配件")){
 			submit = null;
 			backString = "add";
 	   		return "choose";
 		}
         if(submit.equals("保存")){
        		 submit=null;
        		 String type1= "";
           		 String type2= "";
                 for(int i=0;i<priceTypeList.size();i++){                 
        	       if(ppriceList.get(i).getPrice() != null){
//        	    	   PPrice pprice = new PPrice();
        	    	   pprice=ppriceBiz.findPPrice((Integer) this.getSession().get("parentId"),priceTypeList.get(i).getId(),part.getCode());
        	    	   if(pprice == null){
        	    	   ppriceList.get(i).setDealerId((Integer) this.getSession().get("parentId"));
        	    	   ppriceList.get(i).setDealerShortName((String) this.getSession().get("parentShortName"));
        	    	   ppriceList.get(i).setPartId(part.getId());
        	    	   ppriceList.get(i).setPartCode(part.getCode());
        	    	   ppriceList.get(i).setPartName(part.getName());
        	    	   ppriceList.get(i).setSpecType(part.getSpecType());
        	    	   ppriceList.get(i).setPartColor(part.getPartColor());
        	    	   ppriceList.get(i).setPriceType(priceTypeList.get(i).getType());
        	    	   ppriceList.get(i).setPriceTypeId(priceTypeList.get(i).getId());
        		       ppriceBiz.save(ppriceList.get(i));  
        		       type1=type1+priceTypeList.get(i).getType()+"设置成功<br>";
        	    	   }
        	    	   else
        	    		   type2=type2+priceTypeList.get(i).getType()+"已经存在<br>"; 
        	    		   
        	     }       	       
        }
                 this.addFieldError("pprice1.price", type1);
           		 this.addFieldError("pprice2.price", type2);
           		 return "success";
    }
        	 if(submit.equals("返回")){
        		 submit=null;
        		 return "exit";
        	 }
        	 return"success";
     }
     //配件价格修改页面
     public String pPriceEdit(){
    	 
    	 if(submit == null)
        	 return "success";
    	 if(submit.equals("保存")){
    		 submit=null;
    		 ppriceBiz.update(pprice);
    		 return "save";
    	 }
    	 if(submit.equals("返回")){
    		 submit=null;
    		 return "exit";
    	 }
    	 return "success";
     }     
   
//   整车销售价格查询入口
 	public String pPriceQuery(){
 		
 		if(part!=null)
			part=null;
		partList=partBiz.findPart((Integer) this.getSession().get("parentId"),1);
		part=partList.get(0);
 		priceTypeList=priceTypeBiz.findPriceType((Integer) this.getSession().get("parentId"),"配件","售价");
 		if(ppriceList != null)
    		ppriceList.clear();
    	if(ppriceList == null)
    		ppriceList=new ArrayList<PPrice>();
    	for(int i=0;i < priceTypeList.size(); i++){
			  if(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()) != null)
				ppriceList.add(ppriceBiz.findByDealerIdAndPartIdAndPriceTypeId((Integer) this.getSession().get("parentId"), part.getId(), priceTypeList.get(i).getId()));
   		  else
   		ppriceList.add(new PPrice());		  
    	}	
		return "success";
 	}
 	
 	public String pPriceQueryList(){
 		
 		if(submit == null)
 	       	 return "success";
 		
 		if(submit.equals("选择配件")){
 			submit = null;
 			backString = "sale";
 	   		return "choose";
 		}
 		
 		if(submit.equals("退出")){
    		 submit=null;
    		 return "exit";
    	 }
 		return "success";		
 	}
 // 分页处理
	private void partList(boolean clearInputCondition) {

		if (partList != null)
			partList.clear();
		if (selectId != null)
			selectId.clear();
		if(clearInputCondition) {			
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList()!= null)
				page.getPropsValueList().clear();
		}
		page.setListAct("partForPrice.html");
		partList = partBiz.findPart(this, (Integer) this
				.getSession().get("parentId"));
	}
//   内部变量
     private String  backString;
   //entity   
    private PPrice pprice;
	public PPrice getPprice() {
		return pprice;
	}
	public void setPprice(PPrice pprice) {
		this.pprice = pprice;
	}
	
	private PriceType priceType;
	public PriceType getPriceType() {
		return priceType;
	}
	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}
	private Part part;	
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
		
	//biz
	private PPriceBiz ppriceBiz;
	public void setPpriceBiz(PPriceBiz ppriceBiz) {
		this.ppriceBiz = ppriceBiz;
	}
	
	private PriceTypeBiz priceTypeBiz;
	public void setPriceTypeBiz(PriceTypeBiz priceTypeBiz) {
		this.priceTypeBiz = priceTypeBiz;
	}
	
	private PartBiz partBiz;	
	public void setPartBiz(PartBiz partBiz) {
		this.partBiz = partBiz;
	}
	
    //list
	private List<PPrice> ppriceList;
	public List<PPrice> getPpriceList() {
		return ppriceList;
	}
	
	private List<PriceType> priceTypeList;
    public List<PriceType> getPriceTypeList() {
		return priceTypeList;
	}
	
	private List<Part> partList;
	public List<Part> getPartList() {
		return partList;
	}
	//submit
	private String submit;
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
    private List<Long> selectId;
	public List<Long> getSelectId() {
		return selectId;
	}
	public void setSelectId(List<Long> selectId) {
		this.selectId = selectId;
	}
}
