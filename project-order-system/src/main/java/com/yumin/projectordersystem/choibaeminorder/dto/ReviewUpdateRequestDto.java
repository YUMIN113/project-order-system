package com.yumin.projectordersystem.choibaeminorder.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private Long reviewId;

    private String reviewContent;

    private Integer reviewScore;

}
