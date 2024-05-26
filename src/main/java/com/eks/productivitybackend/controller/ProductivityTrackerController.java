package com.eks.productivitybackend.controller;

import com.eks.productivitybackend.service.ProductivityTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/productivity")
@RequiredArgsConstructor
public class ProductivityTrackerController {

    private final ProductivityTrackerService productivityTrackerService;


    @PostMapping("/{cardId}")
    public ResponseEntity<String> trackProductivity(@PathVariable String cardId, @RequestHeader(value="productivity_tracker_id", required = false) String productivityTrackerId,
                                            @RequestHeader(value="work_session_id", required = false) String workSessionId) {

        if (productivityTrackerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing productivity_tracker_id header");
        }

        return ResponseEntity.ok(productivityTrackerService.handleProductivity(cardId, productivityTrackerId, workSessionId));
    }
}
