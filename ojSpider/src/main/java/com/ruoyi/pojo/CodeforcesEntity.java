package com.ruoyi.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class CodeforcesEntity {


    private String status;
    private List<ResultDTO> result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        private Integer id;
        private Integer contestId;
        private Integer creationTimeSeconds;
        private Integer relativeTimeSeconds;
        private ProblemDTO problem;
        private AuthorDTO author;
        private String programmingLanguage;
        private String verdict;
        private String testset;
        private Integer passedTestCount;
        private Integer timeConsumedMillis;
        private Integer memoryConsumedBytes;

        @NoArgsConstructor
        @Data
        public static class ProblemDTO {
            private Integer contestId;
            private String index;
            private String name;
            private String type;
            private Integer rating;
            private List<String> tags;
        }

        @NoArgsConstructor
        @Data
        public static class AuthorDTO {
            private Integer contestId;
            private List<MembersDTO> members;
            private String participantType;
            private Boolean ghost;
            private Integer startTimeSeconds;

            @NoArgsConstructor
            @Data
            public static class MembersDTO {
                private String handle;
            }
        }
    }

}
