package com.board.entity.board;

import com.board.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@DynamicUpdate
                /* Entity가 변경된 컬럼만 Update를 시키게 하는 Hibernate의 Annotation
                  - 컬림이 많거나 테이블 인덱스가 많을 떄 사용 -> SQL 캐시 히트율이 더 떨어질 수 있기 때문
                 */
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

    public void updateContent(String title, String content) {  //게시글 수정
        this.title = title;
        this.content = content;
    }

}
