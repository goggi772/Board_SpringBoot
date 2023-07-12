package com.board.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@AllArgsConstructor
public class BoardUpdateDTO {  //게시글 수정시 사용되는 DTO
    private Long id;

    private String title;

    private String content;

}
