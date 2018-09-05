package com.axelor.apps.sale.db.repo;

import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.db.JpaRepository;

public class SaleOrderRepository extends JpaRepository<SaleOrder> {

	public SaleOrderRepository() {
		super(SaleOrder.class);
	}

	// STATUS

	public static final int STATUS_DRAFT_QUOTATION = 1;
	public static final int STATUS_FINALIZED_QUOTATION = 2;
	public static final int STATUS_ORDER_CONFIRMED = 3;
	public static final int STATUS_ORDER_COMPLETED = 4;
	public static final int STATUS_CANCELED = 5;

	// INVOICE
	public static final int INVOICE_ALL = 1;
	public static final int INVOICE_PART = 2;
	public static final int INVOICE_LINES = 3;
	public static final int INVOICE_ADVANCE_PAYMENT = 4;

	// SALE ORDER TYPE
	public static final int SALE_ORDER_TYPE_NORMAL = 1;
	public static final int SALE_ORDER_TYPE_SUBSCRIPTION = 2;

	// DELIVERY STATE SELECT
	public static final int DELIVERY_STATE_NOT_DELIVERED = 1;
				public static final int DELIVERY_STATE_PARTIALLY_DELIVERED = 2;
				public static final int DELIVERY_STATE_DELIVERED = 3;
}

