package org.project.personal.accountapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtils {

    public static void loggingWithTime(Behavior behavior, BehaviorStatus status, String attribute, String conditionLabel, String condition) {

        StringBuilder builder = new StringBuilder();

        builder.append("\n")
                .append(attribute).append(" ")
                .append(behavior).append(" ")
                .append(status).append("\n")
                .append(behavior.toPastParticiple()).append(" time : {}\n")
                .append(conditionLabel).append(" : {}");

        log.info(builder.toString(), LocalDateTime.now(), condition);
    }

}
