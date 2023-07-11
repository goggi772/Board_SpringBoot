package com.board.DTO;

import com.board.entity.BaseTimeEntity;
import com.board.entity.board.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardWriteDTO extends BaseTimeEntity {  //게시글 작성 시 사용되는 DTO
    private Long id;

    private String title;

    private String content;

    private String writeUsername;

    public Board toEntity() {  //처음 게시글 작성 할 때 Board 객체 생성
        return Board.builder()
                .title(title)
                .content(content)
                .writeUsername(writeUsername)
                .build();
    }

}
