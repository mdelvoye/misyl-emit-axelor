package com.axelor.apps.misyl.custom.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.axelor.apps.base.service.app.AppBaseService;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.apps.supplychain.service.SaleOrderLineServiceSupplyChainImpl;
import com.axelor.exception.AxelorException;


public class MisylSaleOrderLineServiceImpl extends SaleOrderLineServiceSupplyChainImpl {
	
	@Override
	public Map<String, BigDecimal> computeValues(SaleOrder saleOrder, SaleOrderLine saleOrderLine)
      throws AxelorException {

    HashMap<String, BigDecimal> map = new HashMap<>();
    if (saleOrder == null
        || (saleOrderLine.getProduct() == null && saleOrderLine.getProductName() == null)
        || saleOrderLine.getPrice() == null
        || saleOrderLine.getQty() == null) {
      return map;
    }

    BigDecimal exTaxTotal;
    BigDecimal exTaxTotalEcoTax;
    BigDecimal exTaxTotalPrimary;
    
    BigDecimal companyExTaxTotal;
    BigDecimal inTaxTotal;
    BigDecimal companyInTaxTotal;
    BigDecimal priceDiscounted = this.computeDiscount(saleOrderLine);
    BigDecimal taxRate = BigDecimal.ZERO;

    if (saleOrderLine.getTaxLine() != null) {
      taxRate = saleOrderLine.getTaxLine().getValue();
    }

    if (!saleOrder.getInAti()) {
      exTaxTotal = this.computeAmount(saleOrderLine.getQty(), priceDiscounted,saleOrderLine.getProductEcoTax());
      exTaxTotalEcoTax = this.computeAmountEcoTax(saleOrderLine.getQty(), saleOrderLine.getProductEcoTax());
      exTaxTotalPrimary = this.computeAmount(saleOrderLine.getQty(), priceDiscounted);
      inTaxTotal = exTaxTotal.add(exTaxTotal.multiply(taxRate));
      companyExTaxTotal = this.getAmountInCompanyCurrency(exTaxTotal, saleOrder);
      companyInTaxTotal = companyExTaxTotal.add(companyExTaxTotal.multiply(taxRate));
    } else {
      inTaxTotal = this.computeAmount(saleOrderLine.getQty(), priceDiscounted);
      exTaxTotal = inTaxTotal.divide(taxRate.add(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
      companyInTaxTotal = this.getAmountInCompanyCurrency(inTaxTotal, saleOrder);
      companyExTaxTotal =
          companyInTaxTotal.divide(taxRate.add(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
      exTaxTotalEcoTax = this.computeAmountEcoTax(saleOrderLine.getQty(), saleOrderLine.getProductEcoTax());
      exTaxTotalPrimary = this.computeAmount(saleOrderLine.getQty(), priceDiscounted);
    }

    saleOrderLine.setInTaxTotal(inTaxTotal);
    saleOrderLine.setExTaxTotal(exTaxTotal);
    saleOrderLine.setPriceDiscounted(priceDiscounted);
    saleOrderLine.setCompanyInTaxTotal(companyInTaxTotal);
    saleOrderLine.setCompanyExTaxTotal(companyExTaxTotal);
    map.put("inTaxTotal", inTaxTotal);
    map.put("exTaxTotal", exTaxTotal);
    map.put("priceDiscounted", priceDiscounted);
    map.put("companyExTaxTotal", companyExTaxTotal);
    map.put("companyInTaxTotal", companyInTaxTotal);
    
    map.put("exTaxTotalEcoTax", exTaxTotalEcoTax);
    map.put("exTaxTotalPrimary", exTaxTotalPrimary);
    

    map.putAll(this.computeSubMargin(saleOrder, saleOrderLine));

    return map;
  }
	
	
	public BigDecimal computeAmount(BigDecimal quantity, BigDecimal price, BigDecimal productEcoTax) {
		/**
		 * On redéfinit ici le calcul du montant HT Net
		 * exTaxTotal = Montant remisé + EcoTax
		 */
		
		BigDecimal amountNoEcoTax =quantity.multiply(price);
		BigDecimal amountEcoTax = quantity.multiply(productEcoTax);
		BigDecimal amount = 
				amountNoEcoTax
					.add(amountEcoTax)
					.setScale(AppBaseService.DEFAULT_NB_DECIMAL_DIGITS, RoundingMode.HALF_EVEN);
	    return amount;
	  }
	
	public BigDecimal computeAmountPrimary(BigDecimal quantity, BigDecimal price) {
		/**
		 * Calcul du montant Brut (Prix Unitaire  - Remise) 
		 */
		
		BigDecimal amountNoEcoTax =quantity.multiply(price);
		BigDecimal amount = 
				amountNoEcoTax
					.setScale(AppBaseService.DEFAULT_NB_DECIMAL_DIGITS, RoundingMode.HALF_EVEN);
	    return amount;
	  }

	
	public BigDecimal computeAmountEcoTax(BigDecimal quantity, BigDecimal productEcoTax) {
		/**
		 * Calcul du montant de l'eco Taxe
		 */
		
		BigDecimal amountEcoTax = quantity.multiply(productEcoTax);
		BigDecimal amount = 
				amountEcoTax
					.setScale(AppBaseService.DEFAULT_NB_DECIMAL_DIGITS, RoundingMode.HALF_EVEN);
	    return amount;
	  }

}
