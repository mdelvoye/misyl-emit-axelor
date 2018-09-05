package com.axelor.apps.sale.db.repo;

import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class SaleOrderLineRepository extends JpaRepository<SaleOrderLine> {

	public SaleOrderLineRepository() {
		super(SaleOrderLine.class);
	}

	public Query<SaleOrderLine> findAllBySaleOrder(SaleOrder saleOrder) {
		return Query.of(SaleOrderLine.class)
				.filter("self.saleOrder = :saleOrder")
				.bind("saleOrder", saleOrder);
	}

	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_TITLE = 1;
	public static final int TYPE_PACK = 2;

	// SALE SUPPLY SELECT
			public static final int SALE_SUPPLY_NONE = 0;
			public static final int SALE_SUPPLY_FROM_STOCK = 1;
			public static final int SALE_SUPPLY_PURCHASE = 2;
			public static final int SALE_SUPPLY_PRODUCE = 3;

	// DELIVERY STATE SELECT
	public static final int DELIVERY_STATE_NOT_DELIVERED = 1;
	public static final int DELIVERY_STATE_PARTIALLY_DELIVERED = 2;
	public static final int DELIVERY_STATE_DELIVERED = 3;
}

