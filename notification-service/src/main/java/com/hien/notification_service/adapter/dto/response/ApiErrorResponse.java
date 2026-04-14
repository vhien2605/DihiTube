package com.hien.notification_service.adapter.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse extends ApiResponse implements Serializable {
    private String error;
    private String path;
}