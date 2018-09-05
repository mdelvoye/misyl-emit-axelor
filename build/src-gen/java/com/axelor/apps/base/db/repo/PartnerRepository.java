package com.axelor.apps.base.db.repo;

import com.axelor.apps.base.db.Partner;
import com.axelor.auth.db.User;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class PartnerRepository extends JpaRepository<Partner> {

	public PartnerRepository() {
		super(Partner.class);
	}

	public Partner findByName(String name) {
		return Query.of(Partner.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	public Partner findByUser(User user) {
		return Query.of(Partner.class)
				.filter("self.user = :user")
				.bind("user", user)
				.fetchOne();
	}

	public static final int PARTNER_TYPE_COMPANY = 1;
	public static final int PARTNER_TYPE_INDIVIDUAL = 2;

	public static final int PARTNER_TITLE_M = 1;
	public static final int PARTNER_TITLE_MS = 2;
	public static final int PARTNER_TITLE_DR = 3;
	public static final int PARTNER_TITLE_PROF = 4;
}

