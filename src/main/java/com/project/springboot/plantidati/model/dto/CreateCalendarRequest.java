package com.project.springboot.plantidati.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCalendarRequest {
    private int userId;
    private String title;
    private String location;
    private int varietyId;

}
