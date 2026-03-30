package com.example.FakeCommerce.Adapters;

import com.example.FakeCommerce.Schema.Review;
import com.example.FakeCommerce.dtos.GetReviewResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewAdapter {
    public List<GetReviewResponseDto> mapToGetReviewResponseDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(this::mapToGetReviewResponseDto)
                .collect(Collectors.toList());
    }

    public GetReviewResponseDto mapToGetReviewResponseDto(Review review) {
        return GetReviewResponseDto.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .orderId(review.getOrder().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
