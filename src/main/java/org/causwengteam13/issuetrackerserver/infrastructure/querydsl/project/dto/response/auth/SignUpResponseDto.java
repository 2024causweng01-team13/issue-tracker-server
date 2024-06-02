package org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth;

import org.causwengteam13.issuetrackerserver.common.ResponseCode;
import org.causwengteam13.issuetrackerserver.common.ResponseMessage;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import lombok.Getter;

@Getter
public class SignUpResponseDto extends ResponseDto {
    private SignUpResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto result = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_ID, ResponseMessage.DUPLICATED_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
    public static ResponseEntity<ResponseDto> duplicateName() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_NAME, ResponseMessage.DUPLICATED_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }

    
}
