package org.sid.authenticationservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.User;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportingDocRequest {


    private Long id;
    @NotBlank
    private String type;
    @NotBlank
    private String path;
    private Long customer;



}

