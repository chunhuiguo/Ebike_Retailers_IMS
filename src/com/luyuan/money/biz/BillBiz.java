package com.luyuan.money.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.money.entity.Dealing;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.money.entity.DealingDetail;
import com.luyuan.money.entity.PDealingBook;
import com.luyuan.util.Page;

/**
 * 
 */

public interface BillBiz {

	public void saveDealing(Dealing dealing,
			List<DealingDetail> dealingDetailList, boolean isError);

	public void updateDealing(Dealing dealing,
			List<DealingDetail> dealingDetailList);

	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId, boolean isChecked);
	
	public List<Dealing> findDealing(int dealerId, Dealing dealing);

	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId, String type,
			boolean isChecked, String errorType);
	
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId, String type,
			boolean isChecked);

	public void saveCheckDealing(Dealing dealing,
			List<DealingDetail> dealingDetailList, boolean isNew,
			boolean isError);

	public Dealing findDealing(int id);

	public List<DealingDetail> findDealingDetail(long parentDealingId);

	public void delete(Long id);

	public List<DealingBook> findDealingBook(IFieldValidation act, int subAccountId, String type);

	public List<DealingBook> findPDealingBook(IFieldValidation act, int subAccountId, String type);

	public void saveAgainst(Dealing dealing, List<DealingDetail> dealingDetailList,
			Integer userId);

	public DealingBook findDealingBookById(long id);

	public DealingBook findPDealingBookById(long id);
	
	public abstract List<Dealing> findDealing(IFieldValidation act, int dealerId, Dealing dealing);

	public Boolean validationDate( Integer shopId,String checkDate);
	
	public List<Dealing> findDraft( Integer shopId,  String beginDate,  String endDate);
}