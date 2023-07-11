package com.board.DTO;

import com.board.entity.board.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardWriteDTO {
    private Long id;

    private String title;

    private String content;

    private String writeUsername;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writeUsername(writeUsername)
                .build();
    }

}
