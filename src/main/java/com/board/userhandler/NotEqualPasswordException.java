package com.board.userhandler;

import com.board.entity.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotEqualPasswordException extends RuntimeException{
    private final ErrorCode errorCode;
}
