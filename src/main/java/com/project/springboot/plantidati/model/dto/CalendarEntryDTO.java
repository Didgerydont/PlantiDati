package com.project.springboot.plantidati.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CalendarEntryDTO {

    private int entryId;
    private Date date;
    private Integer dayTemp;
    private Integer nightTemp;
    private Float waterAmount;
    private Float nutrientAmount;
    private Integer height;
    private Integer width;
    private Integer growthStage;
    private boolean pestIssues;
    private int pestImpact;
    private boolean diseaseIssues;
    private int diseaseImpact;
    private String comment;
    private int lightAmount;
    private boolean harvested;
    private Float yield;
    private Byte[] image;

}

