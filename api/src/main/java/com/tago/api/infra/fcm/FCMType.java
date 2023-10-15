package com.tago.api.infra.fcm;

import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import lombok.Getter;

import java.util.function.Function;
@Getter
public enum FCMType {
    CREATE_TRIP_MEMBER("TAGO", (name) -> name + "님! 여행메이트가 한명 추가되었어요! 클릭해서 확인해보세요."),
    DELETE_TRIP_MEMBER("TAGO", (name) -> name + "님! 여행인원이 줄어들었어요. 클릭해서 확인해보세요."),
    COMPLETE_TRIP_MEMBER("TAGO", (name) -> name + "님! 여행인원이 모두 모집되었네요! 클릭해서 확인해보세요."),
    CREATE_DISPATCH("TAGO", (name) -> name + "님! 여행을 같이 할 기사님이 배정되었어요. 클릭해서 확인해보세요.")
    ;

    private final String title;
    private final Function<String, String> operator;

    private FCMType(String title, Function<String, String> operator) {
        this.title = title;
        this.operator = operator;
    }

    public static FCMType from(TripMemberEvent.Action action) {
        return switch (action) {
            case CREATE -> CREATE_TRIP_MEMBER;
            case DELETE -> DELETE_TRIP_MEMBER;
            case COMPLETE -> COMPLETE_TRIP_MEMBER;
        };
    }

    public String getContent(String name) {
        return operator.apply(name);
    }
}
