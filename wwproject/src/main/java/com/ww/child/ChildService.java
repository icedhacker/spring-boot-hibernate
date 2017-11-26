package com.ww.child;

public interface ChildService {
    ChildInfo getChildInfo(Long childId);

    ColorInfo getColorInfo(Long childId);

    long[] getParentSummary();

    void generateParentSummary(long personSize);
}
