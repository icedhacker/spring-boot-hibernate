package com.ww.child;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ColorInfo {
    private Long childId;

    private String color;

    private ColorType colorType;
}
