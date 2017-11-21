package com.ww.child;

public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public ChildInfo getChildInfo(Long childId) {
        Child child = childRepository.findOne(childId);
        if (child != null) {
            ChildInfo.ChildInfoBuilder childInfoBuilder =
                    ChildInfo.builder()
                            .childId(child.getChildId())
                            .age(child.getAge())
                            .name(child.getName())
                            .parent(child.getParent());
            if (child.getMeals() != null && !child.getMeals().isEmpty()) {
                childInfoBuilder.meal(child.getMeals().get(0));
            }
            return childInfoBuilder.build();
        }
        return null;
    }
}
