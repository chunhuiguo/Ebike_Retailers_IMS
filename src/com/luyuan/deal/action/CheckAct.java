//package com.luyuan.deal.action;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.struts2.ServletActionContext;
//
//import com.luyuan.deal.entity.*;
//import com.luyuan.info.biz.ProductBiz;
//import com.luyuan.info.biz.WarehouseBiz;
//import com.luyuan.info.entity.Product;
//import com.luyuan.info.entity.Warehouse;
//import com.luyuan.info.biz.EmployeeBiz;
//import com.luyuan.info.entity.Employee;
//import com.luyuan.stock.biz.InventoryBiz;
//import com.luyuan.stock.entity.Inventory;
//import com.luyuan.sys.biz.UnitBiz;
//
//
//public class CheckAct  extends VoucherAct {
//	public String detail() {
//		warehouseList();
//		employeeList();
//		
///*		if(inventoryList != null)
//			inventoryList.clear();
//		if(inventoryAllList != null)
//			inventoryAllList.clear();
//		if(warehouseList != null)
//			warehouseList.clear();
//		
//		warehouseId = 0;
//		
//		if(this.getSession().get("unitType").equals("经销商")) {
//			
//			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"));
//			
//		}
//		else {
//			
//			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), false);
//			
//		}		
//		*/
//		return "success";
//	}
//	List<Inventory> inventorys;
//	public List<Inventory> getInventorys() {
//		return inventorys;
//	}
//
//
//	public String entercode(){
//		//voucher.setCheckDate(Integer.toString(wwwarehouseid));
//		totalQty = 0;
//		totalQtynew = 0;
//		inventorys = inventoryBiz.findByWarehouse(wwwarehouseid);
//		
//		//voucher.setCheckDate(Integer.toString(inventorys.size()));
//		String[] tar = voucher.getProductBarcodeTxt().split("\r\n");
//		for (int i=0;i < tar.length;i++){
//			for (int li=0;li < tar.length;li++){
//				if (tar[i].equals(tar[li]) && i != li){
//					this.addFieldError("voucher.codeback", "发现重复条码");
//					inventorys = null;
//					return "success";
//				}
//			}		
//		
//			if (tar[i].length() == 16){
//				boolean isfind = false ;
//				for (int li = 0 ; li < inventorys.size(); li++){
//					if (tar[i].substring(3,12).equals(inventorys.get(li).getProductCode())){
//						if (inventorys.get(li).getQtynew()==null){
//							inventorys.get(li).setQtynew(1);
//						}else{
//							inventorys.get(li).setQtynew(inventorys.get(li).getQtynew()+1);
//							
//						}
//						
//						
//						isfind = true;
//					}
//				
//				}
//				if (!isfind){
//					Product product = productBiz.findByCode(tar[i].substring(3,12));
//					if (product!= null){
//					inventorys.add(new Inventory(product.getId(), product.getSuffixName(), product.getCode(),
//							product.getColorName(), product.getUnit(), 0, 1));
//					}else{
//						this.addFieldError("voucher.codeback", "发现错误条码："+tar[i]);
//						inventorys = null;
//						return "success";
//						
//					}
//				}
//			}else{
//				this.addFieldError("voucher.codeback", "发现错误条码："+tar[i]);
//				inventorys = null;
//				return "success";
//			}
//			
//
//			
//			
//			//totalQtynew = totalQtynew + inventorys.get(li).getQtynew();
//		}
//
//		for(int iii = 0; iii < inventorys.size(); iii++) {
//			totalQty = totalQty + inventorys.get(iii).getQty();
//			if (inventorys.get(iii).getQtynew() != null){
//			totalQtynew = totalQtynew + inventorys.get(iii).getQtynew();
//			}else{
//				 inventorys.get(iii).setQtynew(0);
//			}
//		}
//		
//		return "success";
//	}
//	
//	
//	public String exportExcel() throws IOException {
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet(type + "库存盘点");
//		//列宽
//		this.setColumnWidth(sheet, this.getColumnWidthList());
//		HSSFRow row;
//		
//		row = sheet.createRow(0);
//		row.createCell(0).setCellValue("当前" + type + "库存盘点");		
//		//合并单元格
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
//		this.setRowStyle(row, this.getTitleStytle(workbook));
//		row = sheet.createRow(1);
//		row.createCell(0).setCellValue("序号");
//		row.createCell(1).setCellValue("商品编码");
//		row.createCell(2).setCellValue("商品名称");
//		row.createCell(3).setCellValue("商品颜色");
//		row.createCell(4).setCellValue("单位");
//		row.createCell(5).setCellValue("库存数量");
//		row.createCell(6).setCellValue("实际数量");
//		
//			
//		//格式
//		this.setRowStyle(row, this.getTitleStytle(workbook));
//		
//		//设置“内容”单元格的格式
//		int columns = 7;
//		
//		for(int i = 0; i < columns; i++)
//			sheet.setDefaultColumnStyle(i, this.getContentStytle(workbook));
//		
//		if(inventorys != null && inventorys.size() != 0) {			
//			//第3~n行
//			Inventory inventory;
//			for(int i = 0; i < inventorys.size(); i++) {
//				inventory = inventorys.get(i);			
//				row = sheet.createRow(i + 2);
//				row.createCell(0).setCellValue(i + 1);
//				//库存信息
//				row.createCell(1).setCellValue(inventory.getProductCode());
//				row.createCell(2).setCellValue(inventory.getProductName());
//				row.createCell(3).setCellValue(inventory.getProductColor());
//				row.createCell(4).setCellValue(inventory.getProductUnit());
//				row.createCell(5).setCellValue(inventory.getQty());
//				row.createCell(6).setCellValue(inventory.getQtynew());			
//			}
//		}
//		
//		HttpServletResponse response = ServletActionContext.getResponse();
//		//解决中文乱码问题
//		String fileName;
//		
//			fileName = URLEncoder.encode("当前" + type + "库存盘点", "UTF-8");
//		response.setHeader("Content-Type","application/vnd.ms-excel");
//        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls");
//		OutputStream outputStream = response.getOutputStream();
//		workbook.write(outputStream);
//		outputStream.flush();
//		outputStream.close();
//		//避免getOutputStream()方法已经得调用的异常
//		return null;
//	}
//	
//	protected HSSFCellStyle getTitleStytle(HSSFWorkbook workbook) {
//		HSSFFont font = workbook.createFont(); 
//        font.setFontHeightInPoints((short)11); 
//        font.setFontName("宋体"); 
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置粗体
//
//		HSSFCellStyle style = workbook.createCellStyle(); 
//        style.setFont(font); 
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//水平居中
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//        style.setFillBackgroundColor((short)255);
//        return style;
//	}
//	
//	//内容行格式
//	protected HSSFCellStyle getContentStytle(HSSFWorkbook workbook) {
//		HSSFFont font = workbook.createFont(); 
//        font.setFontHeightInPoints((short)11); 
//        font.setFontName("宋体"); 
//
//		HSSFCellStyle style = workbook.createCellStyle(); 
//        style.setFont(font); 
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//        return style;
//	}
//	
//	//设置一行中所有单元格的格式
//	protected void setRowStyle(HSSFRow row, HSSFCellStyle style) {
//		for(int i = 0; row.getCell(i) != null; i++)
//			row.getCell(i).setCellStyle(style);
//	}
//	
//	//设置列宽
//	protected void setColumnWidth(HSSFSheet sheet, List<Integer> columnWidthList) {
//		//根据自己调整的Excel列宽的像素值乘以50
//		for(int i = 0; i < columnWidthList.size(); i++)
//			sheet.setColumnWidth(i, columnWidthList.get(i) * 42);		
//	}
//	//得到各列的宽度
//	private List<Integer> getColumnWidthList() {
//		List<Integer> columnWidthList = new ArrayList<Integer>();
//		//宽度：手动调整Excel得到列宽的像素值
//		columnWidthList.add(37);
//		columnWidthList.add(110);
//		columnWidthList.add(150);
//		columnWidthList.add(90);
//		columnWidthList.add(150);
//		columnWidthList.add(110);
//		columnWidthList.add(70);
//		columnWidthList.add(50);
//		return columnWidthList;
//	}
//	
//	
//	
//	private int wwwarehouseid;
//	public int getWwwarehouseid() {
//		return wwwarehouseid;
//	}
//	public void setWwwarehouseid(int wwwarehouseid) {
//		this.wwwarehouseid = wwwarehouseid;
//	}
//	
//	private ProductBiz productBiz;
//	public void setProductBiz(ProductBiz productBiz) {
//		this.productBiz = productBiz;
//	}
//	
//	private WarehouseBiz warehouseBiz;
//	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
//		this.warehouseBiz = warehouseBiz;
//	}
//	private List<Warehouse> warehouseList;
//	public List<Warehouse> getWarehouseList() {
//		return warehouseList;
//	}	
//	private void warehouseList() {		
//		if(warehouseList != null)
//			warehouseList.clear();	
//		
//		boolean isDealer = false;
//		if( this.getSession().get("unitType").equals("经销商") )
//			isDealer = true;		
//		warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), isDealer);
//	}
//	private EmployeeBiz employeeBiz;
//	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
//		this.employeeBiz = employeeBiz;
//	}
//	private List<Employee> employeeList;
//	public List<Employee> getEmployeeList() {
//		return employeeList;
//	}	
//	private void employeeList() {		
//		if(employeeList != null)
//			employeeList.clear();	
//		
//		boolean isDealer = false;
//		if( this.getSession().get("unitType").equals("经销商") )
//			isDealer = true;		
//		employeeList = employeeBiz.findEmployee((Integer) this.getSession().get("unitId"), isDealer);
//	}
//	
//	private List<Inventory> inventoryList;	
//	public List<Inventory> getInventoryList() {
//		return inventoryList;
//	}
//	
//	private int warehouseId;
//	public int getWarehouseId() {
//		return warehouseId;
//	}
//	
//	private int totalQty = 0;
//	public int getTotalQty() {
//		return totalQty;
//	}
//	
//	private int totalQtynew = 0;
//	public int getTotalQtynew() {
//		return totalQtynew;
//	}
//	
//	private double total = 0;
//	public double getTotal() {
//		return total;
//	}
//	
//	private InventoryBiz inventoryBiz;
//	public void setInventoryBiz(InventoryBiz inventoryBiz) {
//		this.inventoryBiz = inventoryBiz;
//	}
//	
//	private String submit;	
//	public void setSubmit(String submit) {
//		this.submit = submit;
//	}
//	private UnitBiz unitBiz;
//	public void setUnitBiz(UnitBiz unitBiz) {
//		this.unitBiz = unitBiz;
//	}
//}
