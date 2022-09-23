package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    //화면에서 전달되는 page 파라미터 size 라는 파라미터를 수집
    //기본 값은 1페이지에 15라는 기본값

    private int page;
    private int size;

    public PageRequestDTO(){
        this.page = 1;
        this.size= 15;
    }


    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1 , size, sort);
        // 1페이지 경우 0이 될 수 있도록 -1값 넣음
        // 결과 처리 페이지 -> ResultDTO

    }
}
