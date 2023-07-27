package com.board.entity.board;

import com.board.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //게시글 id

    @Column(nullable = false, length = 30)
    private String writeUsername;   //작성자

    @Column(nullable = false, length = 50)
    private String title;  //제목

    @Column(nullable = false, length = 1000)
    private String content;  //내용

    @Column
    private int readCount;  //조회수

}
