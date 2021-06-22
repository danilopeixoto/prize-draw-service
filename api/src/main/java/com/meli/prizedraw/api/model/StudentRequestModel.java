package com.meli.prizedraw.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestModel {
  @NotBlank(message = "The name field is required.")
  @JsonProperty("name")
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @NotNull(message = "The birth date field is required.")
  @JsonProperty("birth_date")
  private Date birthDate;

  @Range(min = 0, max = 1, message = "The grade field must have a value between 0 and 1.0.")
  @NotNull(message = "The grade field is required.")
  @JsonProperty("grade")
  private Double grade;
}
