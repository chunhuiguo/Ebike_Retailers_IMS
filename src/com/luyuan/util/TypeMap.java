package com.luyuan.util;

import java.util.HashMap;
import java.util.Map;

public class TypeMap {
	
	public static Map<String, String> typeMap = new HashMap<String, String>();
	
	static{
		typeMap.put("整车进货单", "purchase");
		typeMap.put("整车进货退货单", "purchaseBack");
		typeMap.put("整车销售单", "sale");
		typeMap.put("整车销售退货单", "saleBack");
		typeMap.put("整车调拨入库单", "transferIn");
		typeMap.put("整车调拨出库单", "transferOut");
		typeMap.put("整车报损单", "loss");
		typeMap.put("整车报溢单", "overflow");	
		typeMap.put("整车单据查询", "voucherQuery");
		typeMap.put("配件进货单", "ppurchase");
		typeMap.put("配件进货退货单", "ppurchaseBack");
		typeMap.put("配件销售单", "psale");
		typeMap.put("配件销售退货单", "psaleBack");
		typeMap.put("配件调拨入库单", "ptransferIn");
		typeMap.put("配件调拨出库单", "ptransferOut");
		typeMap.put("配件报损单", "ploss");
		typeMap.put("配件报溢单", "poverflow");	
		typeMap.put("配件单据查询", "pvoucherQuery");
		typeMap.put("收款单", "receipt");
		typeMap.put("付款单", "payment");
		typeMap.put("账款单据查询", "billQuery");
	}	
}
