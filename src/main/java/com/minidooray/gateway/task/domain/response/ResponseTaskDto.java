package com.minidooray.gateway.task.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ResponseTaskDto {

    private Long seq;

    private String title;

    private String content;

    private TaskPeriod taskPeriod;

    private MilestoneDto milestone;

    private List<TaskTagDto> taskTags;

    private ResponsePriorityDto priority;

    private MemberDto registrant;

    private List<MemberTaskDto> memberTasks;

    private List<CommentDto> comments;

    @Getter
    public class TaskPeriod {
        private LocalDate registeredDate;
        private LocalDate lastUpdateDate;
        private LocalDate recentDate;
    }

    @Getter
    public class MilestoneDto {
        private Long seq;
        private String name;
    }

    @Getter
    public static class TaskTagDto {
        private Long seq;
        private TagDto tag;

        @Getter
        public static class TagDto {
            private Long seq;
            private String name;
        }
    }

    @Getter
    public static class MemberDto {
        private Long seq;
        private String name;
    }

    @Getter
    @NoArgsConstructor
    public static class MemberTaskDto {
        private Long seq;
        private MemberDto member;
        private String type;
    }

    @Getter
    public static class CommentDto {
        private Long seq;
        private String content;
        private CommentPeriod period;
        private MemberDto member;
    }

    @Getter
    public static class CommentPeriod {
        private LocalDate registerDate;
        private LocalDate lastUpdateDate;
    }
}
