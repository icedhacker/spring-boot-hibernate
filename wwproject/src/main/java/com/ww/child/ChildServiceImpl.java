package com.ww.child;

public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public ChildInfo getChildInfo(Long childId) {
        Child child = childRepository.findOne(childId);
        if (child == null) {
            return null;
        }
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

    @Override
    public ColorInfo getColorInfo(Long childId) {
        Child child = childRepository.findOne(childId);
        if (child == null) {
            return null;
        }
        ColorInfo.ColorInfoBuilder colorInfoBuilder = ColorInfo.builder().childId(child.getChildId());
        if (child instanceof Son) {
            Son son = (Son) child;
            colorInfoBuilder.color(son.getBicycleColor());
            colorInfoBuilder.colorType(ColorType.BICYCLE_COLOR);
        } else if (child instanceof Daughter) {
            Daughter daughter = (Daughter) child;
            colorInfoBuilder.color(daughter.getHairColor());
            colorInfoBuilder.colorType(ColorType.HAIR_COLOR);
        }
        return colorInfoBuilder.build();
    }
}
