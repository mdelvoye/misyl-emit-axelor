package com.axelor.apps.misyl.custom.service;

import com.axelor.apps.ReportFactory;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.misyl.sale.report.IReport;
import com.axelor.apps.sale.service.saleorder.SaleOrderService;
import com.axelor.apps.sale.service.saleorder.SaleOrderServiceImpl;
import com.axelor.exception.AxelorException;
import com.axelor.i18n.I18n;

public class MisylSaleOrderServiceImpl implements SaleOrderService{
	@Override
	  public String getReportLink(
	      SaleOrder saleOrder, String name, String language, boolean proforma, String format)
	      throws AxelorException {

	    return ReportFactory.createReport(IReport.MS_SALES_ORDER, name + "-${date}")
	        .addParam("Locale", language)
	        .addParam("SaleOrderId", saleOrder.getId())
	        .addParam("ProformaInvoice", proforma)
	        .addFormat(format)
	        .generate()
	        .getFileLink();
	  }

	@Override
	  public String getFileName(SaleOrder saleOrder) {

	    return I18n.get("Sale order")
	        + " "
	        + saleOrder.getSaleOrderSeq()
	        + ((saleOrder.getVersionNumber() > 1) ? "-V" + saleOrder.getVersionNumber() : "");
	  }

	@Override
	public SaleOrder computeEndOfValidityDate(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void computeAddressStr(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableEditOrder(SaleOrder saleOrder) throws AxelorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateChanges(SaleOrder saleOrder, SaleOrder saleOrderView) throws AxelorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortSaleOrderLineList(SaleOrder saleOrder) {
		// TODO Auto-generated method stub
		
	}
}
