package com.axelor.apps.stock.db.repo;

import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.stock.db.StockMove;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class StockMoveRepository extends JpaRepository<StockMove> {

	public StockMoveRepository() {
		super(StockMove.class);
	}

	public StockMove findByName(String name) {
		return Query.of(StockMove.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	public Query<StockMove> findAllBySaleOrderAndStatus(SaleOrder saleOrder, Integer statusSelect) {
		return Query.of(StockMove.class)
				.filter("self.saleOrder = :saleOrder AND self.statusSelect = :statusSelect")
				.bind("saleOrder", saleOrder)
				.bind("statusSelect", statusSelect);
	}

	// STATUS SELECT
	public static final int STATUS_DRAFT = 1;
	public static final int STATUS_PLANNED = 2;
	public static final int STATUS_REALIZED = 3;
	public static final int STATUS_CANCELED = 4;

	// TYPE SELECT
	public static final int TYPE_INTERNAL = 1;
	public static final int TYPE_OUTGOING = 2;
	public static final int TYPE_INCOMING = 3;

	// CONFORMITY SELECT
	public static final int CONFORMITY_NONE = 1;
	public static final int CONFORMITY_COMPLIANT = 2;
	public static final int CONFORMITY_NON_COMPLIANT = 3;
}

