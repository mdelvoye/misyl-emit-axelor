/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2018 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.misyl.custom.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.misyl.custom.service.MisylSaleOrderComputeServiceImpl;
import com.axelor.apps.misyl.custom.service.MisylSaleOrderLineServiceImpl;
import com.axelor.apps.misyl.custom.service.MisylSaleOrderMarginServiceImpl;
import com.axelor.apps.sale.service.saleorder.SaleOrderMarginServiceImpl;
import com.axelor.apps.supplychain.service.SaleOrderComputeServiceSupplychainImpl;
import com.axelor.apps.supplychain.service.SaleOrderLineServiceSupplyChainImpl;


public class MisylCustomModule extends AxelorModule {

    @Override
    protected void configure() {
    	bind(SaleOrderLineServiceSupplyChainImpl.class).to(MisylSaleOrderLineServiceImpl.class);
    	bind(SaleOrderComputeServiceSupplychainImpl.class).to(MisylSaleOrderComputeServiceImpl.class);
    	bind(SaleOrderMarginServiceImpl.class).to(MisylSaleOrderMarginServiceImpl.class);
    }
}