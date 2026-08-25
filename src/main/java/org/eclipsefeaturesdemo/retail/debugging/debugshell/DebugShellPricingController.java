package org.eclipsefeaturesdemo.retail.debugging.debugshell;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug/debug-shell")
public class DebugShellPricingController {

    private final DebugShellPricingService pricingService;

    public DebugShellPricingController(DebugShellPricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/price")
    public DebugShellPricingResponse calculate(@RequestBody DebugShellPricingRequest request) {
        return pricingService.calculate(request);
    }
}
