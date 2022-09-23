package com.example.service;

import com.example.dto.GuestbookDTO;
import com.example.dto.PageRequestDTO;
import com.example.dto.PageResultDTO;
import com.example.entity.Guestbook;
import com.example.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class GuestbookServiceImpl implements GuestbookService{
    
    private final GuestbookRepository repository;
    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO---------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);
        return entity.getGno();
    }
    //getList() 부분은 entityToDTO()를 이용해서 java.util.Function을 생성한다.
    //이를 PageResultDTO로 구성한다.
    //PageResultDTO에는 JPA의 처리 결과인 Page<Entity>와 Function 을 전달하여 엔티티 객체들을 DTO리스트로 변환
    //그리고 화면에 페이지 처리와 필요한 값들을 생성한다.
    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity ->
                entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno){
        Optional<Guestbook> result = repository.findById(gno);
        return result.isPresent()? entityToDto(result.get()): null;
    }

}
