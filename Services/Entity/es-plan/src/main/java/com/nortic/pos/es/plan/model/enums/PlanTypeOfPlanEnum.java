package com.nortic.pos.es.plan.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanTypeOfPlanEnum {

    FREE("FREE"),

    BASIC("BASIC"),

    STANDARD("STANDARD"),

    PREMIUM("PREMIUM"),

    ENTERPRISE("ENTERPRISE");

    private final String value;

}
