package com.meli.prizedraw.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentPartialRequestModel {
  @Size(min = 1, message = "The name field must contains at least one character.")
  @JsonProperty("name")
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonProperty("birth_date")
  private Date birthDate;

  @Range(min = 0, max = 1, message = "The grade field must have a value between 0 and 1.0.")
  @JsonProperty("grade")
  private Double grade;
}
