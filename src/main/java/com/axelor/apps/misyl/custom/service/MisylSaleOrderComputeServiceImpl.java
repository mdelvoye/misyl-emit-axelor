package com.axelor.apps.misyl.custom.service;

import java.math.BigDecimal;

import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.apps.sale.db.SaleOrderLineTax;
import com.axelor.apps.sale.service.saleorder.SaleOrderLineService;
import com.axelor.apps.sale.service.saleorder.SaleOrderLineTaxService;
import com.axelor.apps.supplychain.service.SaleOrderComputeServiceSupplychainImpl;
import com.axelor.exception.AxelorException;
import com.google.inject.Inject;

public class MisylSaleOrderComputeServiceImpl  extends SaleOrderComputeServiceSupplychainImpl{

	@Inject
	public MisylSaleOrderComputeServiceImpl(SaleOrderLineService saleOrderLineService,
			SaleOrderLineTaxService saleOrderLineTaxService) {
		super(saleOrderLineService, saleOrderLineTaxService);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	  public void _computeSaleOrder(SaleOrder saleOrder) throws AxelorException {
		
	    saleOrder.setExTaxTotal(BigDecimal.ZERO);
	    saleOrder.setCompanyExTaxTotal(BigDecimal.ZERO);
	    saleOrder.setTaxTotal(BigDecimal.ZERO);
	    saleOrder.setInTaxTotal(BigDecimal.ZERO);
	    saleOrder.setExTaxTotalPrimary(BigDecimal.ZERO);
	    saleOrder.setExTaxTotalEcoTax(BigDecimal.ZERO);
	    
	    
	    for (SaleOrderLine saleOrderLine : saleOrder.getSaleOrderLineList()) {
	      saleOrder.setExTaxTotal(saleOrder.getExTaxTotal().add(saleOrderLine.getExTaxTotal()));
	      
	      //Misyl : Calcul du Montant HT Brut & Calcul du montant Eco Taxe HT
	      saleOrder.setExTaxTotalPrimary(saleOrder.getExTaxTotalPrimary().add(saleOrderLine.getExTaxTotalPrimary())); 
	      saleOrder.setExTaxTotalEcoTax(saleOrder.getExTaxTotalEcoTax().add(saleOrderLine.getExTaxTotalEcoTax()));
	      // Fin Misyl
	      
	      // In the company accounting currency
	      saleOrder.setCompanyExTaxTotal(
	          saleOrder.getCompanyExTaxTotal().add(saleOrderLine.getCompanyExTaxTotal()));
	      	  
	    }

	    for (SaleOrderLineTax saleOrderLineVat : saleOrder.getSaleOrderLineTaxList()) {

	      // In the sale order currency
	      saleOrder.setTaxTotal(saleOrder.getTaxTotal().add(saleOrderLineVat.getTaxTotal()));
	    }

	    saleOrder.setInTaxTotal(saleOrder.getExTaxTotal().add(saleOrder.getTaxTotal()));
	    saleOrder.setAdvanceTotal(computeTotalAdvancePayment(saleOrder));
	    
	  }

}
