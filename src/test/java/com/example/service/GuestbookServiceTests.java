package com.example.service;


import com.example.dto.GuestbookDTO;
import com.example.dto.PageRequestDTO;
import com.example.dto.PageResultDTO;
import com.example.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testResister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample title..")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1).size(15).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV : " +resultDTO.isPrev());
        System.out.println("NEXT : " +resultDTO.isNext());
        System.out.println("TOTAL : " +resultDTO.getTotalPage());
        System.out.println("-----------------------------------------------------");
        for (GuestbookDTO guestbookDTO: resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }

        System.out.println("=========================================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
}
