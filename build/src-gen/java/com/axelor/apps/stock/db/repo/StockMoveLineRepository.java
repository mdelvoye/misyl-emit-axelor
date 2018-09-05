package com.axelor.apps.stock.db.repo;

import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.stock.db.StockMoveLine;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class StockMoveLineRepository extends JpaRepository<StockMoveLine> {

	public StockMoveLineRepository() {
		super(StockMoveLine.class);
	}

	public Query<StockMoveLine> findAllBySaleOrder(SaleOrder saleOrder) {
		return Query.of(StockMoveLine.class)
				.filter("self.stockMove.saleOrder = :saleOrder")
				.bind("saleOrder", saleOrder);
	}

	public Query<StockMoveLine> findAllBySaleOrderAndStatusSelect(SaleOrder saleOrder, Integer statusSelect) {
		return Query.of(StockMoveLine.class)
				.filter("self.stockMove.saleOrder = :saleOrder AND self.stockMove.statusSelect = :statusSelect")
				.bind("saleOrder", saleOrder)
				.bind("statusSelect", statusSelect);
	}

	// CONFORMITY SELECT
	public static final int CONFORMITY_NONE = 1;
	public static final int CONFORMITY_COMPLIANT = 2;
	public static final int CONFORMITY_NON_COMPLIANT = 3;
}

