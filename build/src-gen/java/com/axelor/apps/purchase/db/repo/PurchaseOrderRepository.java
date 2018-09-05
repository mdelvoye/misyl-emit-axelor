package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.PurchaseOrder;
import com.axelor.db.JpaRepository;

public class PurchaseOrderRepository extends JpaRepository<PurchaseOrder> {

	public PurchaseOrderRepository() {
		super(PurchaseOrder.class);
	}

}

