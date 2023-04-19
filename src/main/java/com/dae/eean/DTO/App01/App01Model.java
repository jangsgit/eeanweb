package com.dae.eean.DTO.App01;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class App01Model {private int id;
    @NonNull @Builder.Default private String name = "NULL NAME";
    @NonNull @Builder.Default private String email = "NULL EMAIL";
}
