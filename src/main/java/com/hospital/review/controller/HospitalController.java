package com.hospital.review.controller;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import com.hospital.review.domain.dto.HospitalReadResponse;
import com.hospital.review.domain.dto.ReviewCreateRequest;
import com.hospital.review.domain.dto.ReviewCreateResponse;
import com.hospital.review.domain.dto.ReviewReadResponse;
import com.hospital.review.service.HospitalService;
import com.hospital.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    public HospitalController(ReviewService reviewService, HospitalService hospitalService) {
        this.reviewService = reviewService;
        this.hospitalService = hospitalService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        log.info("{}", reviewCreateRequest);
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }
    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> get(@PathVariable Long id) {
        Hospital hospital = hospitalService.findById(id);
        HospitalReadResponse response = HospitalReadResponse.fromEntity(hospital);
        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ReviewReadResponse> get(@PathVariable Long id) {
//        Review review = reviewService.getReview(id);
//        ReviewReadResponse response = ReviewReadResponse.fromEntity(review);
//        return ResponseEntity.ok().body(response);
//    }
}