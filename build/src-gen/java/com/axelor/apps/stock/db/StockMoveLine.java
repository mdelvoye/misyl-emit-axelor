package com.axelor.apps.stock.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Unit;
import com.axelor.apps.purchase.db.PurchaseOrderLine;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "STOCK_STOCK_MOVE_LINE", indexes = { @Index(columnList = "stock_move"), @Index(columnList = "planned_stock_move"), @Index(columnList = "product"), @Index(columnList = "unit"), @Index(columnList = "tracking_number"), @Index(columnList = "product_model"), @Index(columnList = "productName"), @Index(columnList = "customs_code_nomenclature"), @Index(columnList = "sale_order_line"), @Index(columnList = "purchase_order_line") })
public class StockMoveLine extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_STOCK_MOVE_LINE_SEQ")
	@SequenceGenerator(name = "STOCK_STOCK_MOVE_LINE_SEQ", sequenceName = "STOCK_STOCK_MOVE_LINE_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Stock move")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private StockMove stockMove;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private StockMove plannedStockMove;

	@Widget(title = "Product")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Product product;

	@Widget(title = "Expected Qty")
	private BigDecimal qty = BigDecimal.ZERO;

	@Widget(title = "Real Qty")
	private BigDecimal realQty = BigDecimal.ZERO;

	@Widget(title = "Unit")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Unit unit;

	@Widget(title = "Net weight")
	private BigDecimal netWeight = BigDecimal.ZERO;

	@Widget(title = "Total net weight")
	private BigDecimal totalNetWeight = BigDecimal.ZERO;

	@Widget(title = "Tracking Nbr.")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TrackingNumber trackingNumber;

	@Widget(title = "Conformity", selection = "stock.move.line.conformity.select")
	private Integer conformitySelect = 0;

	@Widget(title = "Shipped qty")
	private BigDecimal shippedQty = BigDecimal.ZERO;

	@Widget(title = "Shipped date")
	private LocalDate shippedDate;

	@Widget(title = "Product model")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Product productModel;

	@Widget(title = "Title")
	@NameColumn
	@NotNull
	private String productName;

	@Widget(title = "Description")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "text")
	private String description;

	@Widget(title = "Unit price")
	@Digits(integer = 10, fraction = 10)
	private BigDecimal unitPriceUntaxed = BigDecimal.ZERO;

	@Widget(title = "Unit price")
	@Digits(integer = 10, fraction = 10)
	private BigDecimal unitPriceTaxed = BigDecimal.ZERO;

	@Widget(title = "Product type", selection = "product.product.type.select")
	private String productTypeSelect;

	@Widget(title = "Seq.")
	private Integer sequence = 0;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private CustomsCodeNomenclature customsCodeNomenclature;

	private String customsCode;

	@Widget(title = "Logistical form lines")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockMoveLine", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LogisticalFormLine> logisticalFormLineList;

	@Widget(title = "Sales order line")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private SaleOrderLine saleOrderLine;

	@Widget(title = "Purchase order line")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private PurchaseOrderLine purchaseOrderLine;

	@Widget(title = "Reserved Qty")
	private BigDecimal reservedQty = BigDecimal.ZERO;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public StockMoveLine() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public StockMove getStockMove() {
		return stockMove;
	}

	public void setStockMove(StockMove stockMove) {
		this.stockMove = stockMove;
	}

	public StockMove getPlannedStockMove() {
		return plannedStockMove;
	}

	public void setPlannedStockMove(StockMove plannedStockMove) {
		this.plannedStockMove = plannedStockMove;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getQty() {
		return qty == null ? BigDecimal.ZERO : qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getRealQty() {
		return realQty == null ? BigDecimal.ZERO : realQty;
	}

	public void setRealQty(BigDecimal realQty) {
		this.realQty = realQty;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getNetWeight() {
		return netWeight == null ? BigDecimal.ZERO : netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public BigDecimal getTotalNetWeight() {
		return totalNetWeight == null ? BigDecimal.ZERO : totalNetWeight;
	}

	public void setTotalNetWeight(BigDecimal totalNetWeight) {
		this.totalNetWeight = totalNetWeight;
	}

	public TrackingNumber getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(TrackingNumber trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Integer getConformitySelect() {
		return conformitySelect == null ? 0 : conformitySelect;
	}

	public void setConformitySelect(Integer conformitySelect) {
		this.conformitySelect = conformitySelect;
	}

	public BigDecimal getShippedQty() {
		return shippedQty == null ? BigDecimal.ZERO : shippedQty;
	}

	public void setShippedQty(BigDecimal shippedQty) {
		this.shippedQty = shippedQty;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Product getProductModel() {
		return productModel;
	}

	public void setProductModel(Product productModel) {
		this.productModel = productModel;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPriceUntaxed() {
		return unitPriceUntaxed == null ? BigDecimal.ZERO : unitPriceUntaxed;
	}

	public void setUnitPriceUntaxed(BigDecimal unitPriceUntaxed) {
		this.unitPriceUntaxed = unitPriceUntaxed;
	}

	public BigDecimal getUnitPriceTaxed() {
		return unitPriceTaxed == null ? BigDecimal.ZERO : unitPriceTaxed;
	}

	public void setUnitPriceTaxed(BigDecimal unitPriceTaxed) {
		this.unitPriceTaxed = unitPriceTaxed;
	}

	public String getProductTypeSelect() {
		return productTypeSelect;
	}

	public void setProductTypeSelect(String productTypeSelect) {
		this.productTypeSelect = productTypeSelect;
	}

	public Integer getSequence() {
		return sequence == null ? 0 : sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public CustomsCodeNomenclature getCustomsCodeNomenclature() {
		return customsCodeNomenclature;
	}

	public void setCustomsCodeNomenclature(CustomsCodeNomenclature customsCodeNomenclature) {
		this.customsCodeNomenclature = customsCodeNomenclature;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public List<LogisticalFormLine> getLogisticalFormLineList() {
		return logisticalFormLineList;
	}

	public void setLogisticalFormLineList(List<LogisticalFormLine> logisticalFormLineList) {
		this.logisticalFormLineList = logisticalFormLineList;
	}

	/**
	 * Add the given {@link LogisticalFormLine} item to the {@code logisticalFormLineList}.
	 *
	 * <p>
	 * It sets {@code item.stockMoveLine = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addLogisticalFormLineListItem(LogisticalFormLine item) {
		if (getLogisticalFormLineList() == null) {
			setLogisticalFormLineList(new ArrayList<>());
		}
		getLogisticalFormLineList().add(item);
		item.setStockMoveLine(this);
	}

	/**
	 * Remove the given {@link LogisticalFormLine} item from the {@code logisticalFormLineList}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeLogisticalFormLineListItem(LogisticalFormLine item) {
		if (getLogisticalFormLineList() == null) {
			return;
		}
		getLogisticalFormLineList().remove(item);
	}

	/**
	 * Clear the {@code logisticalFormLineList} collection.
	 *
	 * <p>
	 * If you have to query {@link LogisticalFormLine} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearLogisticalFormLineList() {
		if (getLogisticalFormLineList() != null) {
			getLogisticalFormLineList().clear();
		}
	}

	public SaleOrderLine getSaleOrderLine() {
		return saleOrderLine;
	}

	public void setSaleOrderLine(SaleOrderLine saleOrderLine) {
		this.saleOrderLine = saleOrderLine;
	}

	public PurchaseOrderLine getPurchaseOrderLine() {
		return purchaseOrderLine;
	}

	public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		this.purchaseOrderLine = purchaseOrderLine;
	}

	public BigDecimal getReservedQty() {
		return reservedQty == null ? BigDecimal.ZERO : reservedQty;
	}

	public void setReservedQty(BigDecimal reservedQty) {
		this.reservedQty = reservedQty;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof StockMoveLine)) return false;

		final StockMoveLine other = (StockMoveLine) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("qty", getQty())
			.add("realQty", getRealQty())
			.add("netWeight", getNetWeight())
			.add("totalNetWeight", getTotalNetWeight())
			.add("conformitySelect", getConformitySelect())
			.add("shippedQty", getShippedQty())
			.add("shippedDate", getShippedDate())
			.add("productName", getProductName())
			.add("unitPriceUntaxed", getUnitPriceUntaxed())
			.add("unitPriceTaxed", getUnitPriceTaxed())
			.add("productTypeSelect", getProductTypeSelect())
			.omitNullValues()
			.toString();
	}
}
