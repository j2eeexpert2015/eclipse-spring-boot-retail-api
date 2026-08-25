package org.eclipsefeaturesdemo.retail.debugging.debugshell;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class DebugShellPricingService {

    public DebugShellPricingResponse calculate(DebugShellPricingRequest request) {
        BigDecimal subtotal = request.subtotal().setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAmount = subtotal
                .multiply(request.discountRate())
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal finalTotal = subtotal
                .add(discountAmount)
                .setScale(2, RoundingMode.HALF_UP);

        return new DebugShellPricingResponse(subtotal, discountAmount, finalTotal);
    }
}
