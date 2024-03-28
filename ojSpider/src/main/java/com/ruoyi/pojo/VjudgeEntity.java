package com.ruoyi.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor

@Data
public class VjudgeEntity {

    private List<DataDTO> data;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private Integer draw;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private Integer memory;
        private Integer access;
        private Integer statusType;
        private String avatarUrl;
        private Integer runtime;
        private String language;
        private String userName;
        private Integer userId;
        private String languageCanonical;
        private Boolean processing;
        private Integer runId;
        private Long time;
        private String oj;
        private Integer problemId;
        private Integer sourceLength;
        private String probNum;
        private String status;
        private Integer contestOpenness;
        private Integer contestId;
        private String contestNum;
        private Integer recordsTotal;
        private Integer recordsFiltered;
        private Integer draw;

    }
}
