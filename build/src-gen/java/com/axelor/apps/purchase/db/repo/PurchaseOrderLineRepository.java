package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.PurchaseOrderLine;
import com.axelor.db.JpaRepository;

public class PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine> {

	public PurchaseOrderLineRepository() {
		super(PurchaseOrderLine.class);
	}

}

