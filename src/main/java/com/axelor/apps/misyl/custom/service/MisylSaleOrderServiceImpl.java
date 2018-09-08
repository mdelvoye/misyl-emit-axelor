package com.axelor.apps.misyl.custom.service;

import com.axelor.apps.ReportFactory;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.misyl.sale.report.IReport;
import com.axelor.apps.sale.service.saleorder.SaleOrderServiceImpl;
import com.axelor.exception.AxelorException;

public class MisylSaleOrderServiceImpl extends SaleOrderServiceImpl {
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
}
