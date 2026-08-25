package org.eclipsefeaturesdemo.retail.debugging.debugshell;

import java.math.BigDecimal;

public record DebugShellPricingRequest(
        BigDecimal subtotal,
        BigDecimal discountRate) {
}
