package com.virgin.enums;

public enum ShippingMethod {
	FREE(1), NEXT_BUSINESS_DAY(2);

	private final int opcion;

	private ShippingMethod(int opcion) {
		this.opcion = opcion;
	}

	public int getShippingMethod() {
		return this.opcion;
	}
}


