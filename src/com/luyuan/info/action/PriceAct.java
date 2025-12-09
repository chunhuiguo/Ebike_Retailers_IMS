package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.PriceBiz;
import com.luyuan.info.biz.PriceTypeBiz;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Price;
import com.luyuan.info.entity.PriceType;
import com.luyuan.info.entity.Product;

public class PriceAct extends ListAct {

	@Override
	public String execute() throws Exception {
		return null;
	}

	// 整车价格管理菜单入口
	public String price() {
		if (product != null)
			product = null;
		if (priceTypeList != null)
			priceTypeList.clear();
		productList = productBiz.findProduct((Integer) this.getSession().get(
				"parentId"));
		product = productList.get(0);
		priceTypeList = priceTypeBiz.findByDealerIdAndPpFlag((Integer) this
				.getSession().get("parentId"), "整车");
		if (priceTypeList.size() == 0)
			this.addFieldError("priceType.type",
					"请先在【基本信息-价格类型管理】中添加您所需要的价格类型名称！");
		if (priceList != null)
			priceList.clear();
		if (priceList == null)
			priceList = new ArrayList<Price>();
		for (int i = 0; i < priceTypeList.size(); i++) {
			if (priceBiz.findByDealerIdAndProductIdAndPriceTypeId(
					(Integer) this.getSession().get("parentId"), product
							.getId(), priceTypeList.get(i).getId()) != null)
				priceList.add(priceBiz
						.findByDealerIdAndProductIdAndPriceTypeId(
								(Integer) this.getSession().get("parentId"),
								product.getId(), priceTypeList.get(i).getId()));
			else
				priceList.add(new Price());
		}
		return "success";
	}

	// 整车价格列表页面priceList.jsp
	public String priceList() {

		if (submit == null)
			return "success";

		if (submit.equals("选择整车")) {
			backString = "list";
			submit = null;
			return "query";
		}

		if (submit.equals("设置价格")) {
			submit = null;
			priceTypeList = priceTypeBiz.findByDealerIdAndPpFlag((Integer) this
					.getSession().get("parentId"), "整车");
			return "add";
		}

		if (submit.equals("修改")) {
			submit = null;
			if (selectId == null)
				return "success";
			if (selectId.size() > 1)
				return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();
				price = priceBiz.findById(id);
				priceType = priceTypeBiz.findById(price.getPriceTypeId());
				selectId = null;
			}
			return "edit";
		}

		if (submit.equals("删除")) {
			submit = null;
			if (selectId == null)
				return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();
				priceBiz.delete(id);
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

	// 整车选择页面productForPrice.jsp
	public String productForPrice() {

		if (submit == null) {
			this.productList(true);
			return "success";
		}
		
		if (submit.equals("确定")) {
			submit = null;
			if (selectId == null)
				return "success";
			for (Iterator it = selectId.iterator(); it.hasNext();) {
				Long id = (Long) it.next();
				if (backString.equals("add")) {
					product = productBiz.findById(id);
					priceTypeList = priceTypeBiz.findByDealerIdAndPpFlag(
							(Integer) this.getSession().get("parentId"), "整车");
					return "backAdd";
				}
				if (backString.equals("list")) {
					product = productBiz.findById(id);
					String code = product.getCode().substring(0, 5) + "00"
							+ product.getCode().substring(7, 9);
					priceTypeList = priceTypeBiz.findByDealerIdAndPpFlag(
							(Integer) this.getSession().get("parentId"), "整车");
					if (priceList != null)
						priceList.clear();
					if (priceList == null)
						priceList = new ArrayList<Price>();
					int k=0;
					for (int i = 0; i < priceTypeList.size(); i++) {
						if (priceBiz.findPrice((Integer) this.getSession().get(
								"parentId"), priceTypeList.get(i).getId(),
								product.getCode()) != null)
							priceList.add(priceBiz.findPrice((Integer) this
									.getSession().get("parentId"),
									priceTypeList.get(i).getId(), product
											.getCode()));
						else if (priceBiz.findPrice((Integer) this.getSession()
								.get("parentId"), priceTypeList.get(i).getId(),
								code) != null)
							priceList.add(priceBiz.findPrice((Integer) this
									.getSession().get("parentId"),
									priceTypeList.get(i).getId(), code));
						else{
							k++;
							priceList.add(new Price());
						}
//						System.out.println(priceList.get(i).getPrice());
					}
					System.out.println(k);
					if(k==priceTypeList.size())
						this.addFieldError("price.price", "该整车的价格还未设置，请先设置价格");
					product = productBiz.findById(product.getId());
					selectId = null;
					return "backList";
				}
				if (backString.equals("sale")) {
					product = productBiz.findById(id);
					String code = product.getCode().substring(0, 5) + "00"
							+ product.getCode().substring(7, 9);
					priceTypeList = priceTypeBiz.findPriceType((Integer) this
							.getSession().get("parentId"), "整车", "售价");
					if (priceList != null)
						priceList.clear();
					if (priceList == null)
						priceList = new ArrayList<Price>();
					
					for (int i = 0; i < priceTypeList.size(); i++) {
						
						if (priceBiz.findPrice((Integer) this.getSession().get(
								"parentId"), priceTypeList.get(i).getId(),
								product.getCode()) != null)
							priceList.add(priceBiz.findPrice((Integer) this
									.getSession().get("parentId"),
									priceTypeList.get(i).getId(), product
											.getCode()));
						else if (priceBiz.findPrice((Integer) this.getSession()
								.get("parentId"), priceTypeList.get(i).getId(),
								code) != null)
							priceList.add(priceBiz.findPrice((Integer) this
									.getSession().get("parentId"),
									priceTypeList.get(i).getId(), code));
						else
							priceList.add(new Price());
					}
					product = productBiz.findById(product.getId());
					selectId = null;
					return "backSale";

				}
			}
		}

		if (submit.equals("筛选")) {
			submit = null;
			this.productList(false);
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}

		return "success";
	}

	// 整车价格添加页面
	public String priceAdd() {

		if (submit == null)
			return "success";

		if (submit.equals("选择整车")) {
			submit = null;
			backString = "add";
			return "choose";
		}

		if (submit.equals("保存")) {
			submit = null;
			if (radio.equals("否")) {
				String type1 = "";
				String type2 = "";
				String code = product.getCode().substring(0, 5) + "00"
						+ product.getCode().substring(7, 9);
				for (int i = 0; i < priceTypeList.size(); i++) {
					// System.out.println(code);
					if (priceList.get(i).getPrice() != null) {
						// Price price = new Price();
						price = priceBiz.findPrice((Integer) this.getSession()
								.get("parentId"), priceTypeList.get(i).getId(),
								code);
						if (price == null) {
							System.out.println("price==null");
							priceList.get(i)
									.setDealerId(
											(Integer) this.getSession().get(
													"parentId"));
							priceList.get(i).setDealerShortName(
									(String) this.getSession().get(
											"parentShortName"));
							priceList.get(i).setProductId(product.getId());
							priceList.get(i).setProductCode(code);
							priceList.get(i).setPrefixName(
									product.getPrefixName());
							priceList.get(i).setSuffixName(
									product.getSuffixName());
							priceList.get(i).setColorName("其他");
							priceList.get(i).setPriceType(
									priceTypeList.get(i).getType());
							priceList.get(i).setPriceTypeId(
									priceTypeList.get(i).getId());
							priceBiz.save(priceList.get(i));
							type1 = type1 + priceTypeList.get(i).getType()
									+ "设置成功<br>";
						}
						if (price != null) {
							System.out.println("price!=null");
							type2 = type2 + priceTypeList.get(i).getType()
									+ "已经存在<br>";
						}
					}
				}
				this.addFieldError("price1.price", type1);
				this.addFieldError("price2.price", type2);
				return "success";
			}
			if (radio.equals("是")) {
				String type1 = "";
				String type2 = "";
				for (int i = 0; i < priceTypeList.size(); i++) {
					// System.out.println(code);
					if (priceList.get(i).getPrice() != null) {
						Price price = new Price();
						price = priceBiz.findPrice((Integer) this.getSession()
								.get("parentId"), priceTypeList.get(i).getId(),
								product.getCode());
						if (price == null) {
							System.out.println("price==null");
							priceList.get(i)
									.setDealerId(
											(Integer) this.getSession().get(
													"parentId"));
							priceList.get(i).setDealerShortName(
									(String) this.getSession().get(
											"parentShortName"));
							priceList.get(i).setProductId(product.getId());
							priceList.get(i).setProductCode(product.getCode());
							priceList.get(i).setPrefixName(
									product.getPrefixName());
							priceList.get(i).setSuffixName(
									product.getSuffixName());
							priceList.get(i).setColorName(
									product.getColorName());
							priceList.get(i).setPriceType(
									priceTypeList.get(i).getType());
							priceList.get(i).setPriceTypeId(
									priceTypeList.get(i).getId());
							priceBiz.save(priceList.get(i));
							type1 = type1 + priceTypeList.get(i).getType()
									+ "设置成功<br>";
						}
						if (price != null) {
							System.out.println("price!=null");
							type2 = type2 + priceTypeList.get(i).getType()
									+ "已经存在<br>";
						}
					}
				}
				this.addFieldError("price1.price", type1);
				this.addFieldError("price2.price", type2);
				return "success";
			}

		}
		if (submit.equals("返回")) {
			submit = null;
			return "exit";
		}

		return "success";
	}

	// 整车价格修改页面
	public String priceEdit() {

		if (submit == null)
			return "success";

		if (submit.equals("保存")) {
			submit = null;
			priceBiz.update(price);
			return "save";
		}

		if (submit.equals("返回")) {
			submit = null;
			return "exit";
		}

		return "success";
	}

	// 整车销售价格查询入口
	public String priceQuery() {

		if (product != null)
			product = null;
		productList = productBiz.findProduct((Integer) this.getSession().get(
				"parentId"));
		product = productList.get(0);
		String code = product.getCode().substring(0, 5) + "00"
				+ product.getCode().substring(7, 9);
		priceTypeList = priceTypeBiz.findPriceType((Integer) this.getSession()
				.get("parentId"), "整车", "售价");
		if (priceList != null)
			priceList.clear();
		if (priceList == null)
			priceList = new ArrayList<Price>();
		for (int i = 0; i < priceTypeList.size(); i++) {
			if (priceBiz.findPrice((Integer) this.getSession().get("parentId"),
					priceTypeList.get(i).getId(), product.getCode()) != null)
				priceList.add(priceBiz.findPrice((Integer) this.getSession()
						.get("parentId"), priceTypeList.get(i).getId(), product
						.getCode()));
			else if (priceBiz.findPrice((Integer) this.getSession().get(
					"parentId"), priceTypeList.get(i).getId(), code) != null)
				priceList.add(priceBiz.findPrice((Integer) this.getSession()
						.get("parentId"), priceTypeList.get(i).getId(), code));
			else
				priceList.add(new Price());
		}
		return "success";
	}

	public String priceQueryList() {

		if (submit == null)
			return "success";

		if (submit.equals("选择整车")) {
			submit = null;
			backString = "sale";
			return "choose";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}

	// priceDetail.html
	public String priceDetail() {

		String code = product.getCode().substring(0, 5) + "00"
				+ product.getCode().substring(7, 9);
		priceTypeList = priceTypeBiz.findByDealerIdAndPpFlag((Integer) this
				.getSession().get("parentId"), "整车");
		if (priceList != null)
			priceList.clear();
		if (priceList == null)
			priceList = new ArrayList<Price>();
		for (int i = 0; i < priceTypeList.size(); i++) {
			if (priceBiz.findPrice((Integer) this.getSession().get("parentId"),
					priceTypeList.get(i).getId(), product.getCode()) != null)
				priceList.add(priceBiz.findPrice((Integer) this.getSession()
						.get("parentId"), priceTypeList.get(i).getId(), product
						.getCode()));
			else if (priceBiz.findPrice((Integer) this.getSession().get(
					"parentId"), priceTypeList.get(i).getId(), code) != null)
				priceList.add(priceBiz.findPrice((Integer) this.getSession()
						.get("parentId"), priceTypeList.get(i).getId(), code));
			else
				priceList.add(new Price());
		}

		return "success";
	}
    //分页处理
	private void productList(boolean clearInputCondition) {		
		if(productList != null)
			productList.clear();
		if (selectId != null)
			selectId.clear();	
		System.out.println(clearInputCondition);
		if(clearInputCondition) {			
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList()!= null)
				page.getPropsValueList().clear();
		}
		page.setListAct("productForPrice.html");				
		productList = productBiz.findProduct(this, (Integer) this.getSession().get("parentId"));		
	}
	
	// 内部变量
	private String backString;
	// entity
	private Price price;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	private PriceType priceType;

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// biz
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

	// list
	private List<Price> priceList;

	public List<Price> getPriceList() {
		return priceList;
	}

	private List<PriceType> priceTypeList;

	public List<PriceType> getPriceTypeList() {
		return priceTypeList;
	}

	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;

	}

	// submit
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

	private String radio;

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

}
