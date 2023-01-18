package com.querypro.apicomm.adapter.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String names;
    private String lastNames;
    private String email;
    private String password;
    private String tel;
    private Integer bonus;
}
