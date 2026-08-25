package org.eclipsefeaturesdemo.retail.debugging.debugshell;

import java.math.BigDecimal;

public record DebugShellPricingResponse(
        BigDecimal subtotal,
        BigDecimal discountAmount,
        BigDecimal finalTotal) {
}
