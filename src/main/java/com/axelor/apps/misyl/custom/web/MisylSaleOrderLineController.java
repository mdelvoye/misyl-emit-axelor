package com.axelor.apps.misyl.custom.web;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.apps.sale.web.SaleOrderLineController;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class MisylSaleOrderLineController extends SaleOrderLineController{
	 
	
	
	public void getProductInformation(ActionRequest request, ActionResponse response) {
		super.getProductInformation(request, response);
		Context context = request.getContext();
	    SaleOrderLine saleOrderLine = context.asType(SaleOrderLine.class);
	    Product product = saleOrderLine.getProduct();
		
		try {
			 response.setValue("productEcoTax", product.getProductEcoTax());
			 response.setValue("purchasePrice", product.getPurchasePrice());
		 }
		 catch (Exception e) {
		      System.err.println(e);
		 }
	}
	
}
