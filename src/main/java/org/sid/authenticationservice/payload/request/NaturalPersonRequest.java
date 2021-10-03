package org.sid.authenticationservice.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.ECivilStatus;
import org.sid.authenticationservice.models.EGender;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPersonRequest extends  ContactRequest {

    @NotBlank
    @Size(max = 8)
    private String cin;
    @NotBlank
    @Size(max = 20)
    private String firstname;
    @NotBlank
    @Size(max = 20)
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ECivilStatus civilStatus;
    private int childNumber;
}
