package com.board.DTO;


import com.board.entity.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardReadDTO {
    private final Long id;
    private final String title;
    private final String content;
    private final String writeUsername;
    private final int readCount;
    private final LocalDateTime registerTime;

    public BoardReadDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.readCount = entity.getReadCount();
        this.registerTime = entity.getRegisterTime();
        this.writeUsername = entity.getWriteUsername();
    }
}
