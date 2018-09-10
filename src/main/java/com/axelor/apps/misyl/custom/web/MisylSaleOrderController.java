package com.axelor.apps.misyl.custom.web;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.eclipse.birt.core.exception.BirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.apps.misyl.custom.service.MisylSaleOrderServiceImpl;
import com.axelor.apps.report.engine.ReportSettings;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.repo.SaleOrderRepository;
import com.axelor.apps.sale.service.saleorder.SaleOrderService;
import com.axelor.apps.sale.web.SaleOrderController;
import com.axelor.exception.AxelorException;
import com.axelor.exception.service.TraceBackService;
import com.axelor.inject.Beans;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class MisylSaleOrderController extends SaleOrderController{
	
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Inject private SaleOrderRepository saleOrderRepo;
	
	public void compute(ActionRequest request, ActionResponse response) {
		super.compute(request, response);
	}
	
	/**
	   * Method that print the sale order as a Pdf
	   *
	   * @param request
	   * @param response
	   * @return
	   * @throws BirtException
	   * @throws IOException
	   */
	  public void showSaleOrder(ActionRequest request, ActionResponse response) throws AxelorException {

	    this.exportSaleOrder(request, response, false, ReportSettings.FORMAT_PDF);
	  }

	  /** Method that prints a proforma invoice as a PDF */
	  public void printProformaInvoice(ActionRequest request, ActionResponse response)
	      throws AxelorException {

	    this.exportSaleOrder(request, response, true, ReportSettings.FORMAT_PDF);
	  }

	  public void exportSaleOrderExcel(ActionRequest request, ActionResponse response)
	      throws AxelorException {

	    this.exportSaleOrder(request, response, false, ReportSettings.FORMAT_XLS);
	  }

	  public void exportSaleOrderWord(ActionRequest request, ActionResponse response)
	      throws AxelorException {

	    this.exportSaleOrder(request, response, false, ReportSettings.FORMAT_DOC);
	  }

	  public void exportSaleOrder(
	      ActionRequest request, ActionResponse response, boolean proforma, String format) {
	    try {
	    	
	    	logger.debug("Custom Controller");
	    	
	      SaleOrder saleOrder = request.getContext().asType(SaleOrder.class);

	      String language = ReportSettings.getPrintingLocale(saleOrder.getClientPartner());

	      MisylSaleOrderServiceImpl saleOrderService = Beans.get(MisylSaleOrderServiceImpl.class);

	      String name = saleOrderService.getFileName(saleOrder);

	      String fileLink = saleOrderService.getReportLink(saleOrder, name, language, proforma, format);

	      logger.debug("Printing " + name);

	      response.setView(ActionView.define(name).add("html", fileLink).map());
	    } catch (AxelorException e) {
	      TraceBackService.trace(response, e);
	    }
	  }
	
	
	
	
	
}
