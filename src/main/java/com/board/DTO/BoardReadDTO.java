package com.board.DTO;


import com.board.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardReadDTO {  //게시글 불러올 때 사용하는 DTO
    private Long id;
    private String title;
    private String content;
    private String writeUsername;
    private int readCount;
    private LocalDateTime registerTime;

    public BoardReadDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.readCount = entity.getReadCount();
        this.registerTime = entity.getRegisterTime();
        this.writeUsername = entity.getWriteUsername();
    }
}
