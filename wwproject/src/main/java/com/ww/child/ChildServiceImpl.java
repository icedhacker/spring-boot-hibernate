package com.ww.child;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;

    private ParentSummaryRepository parentSummaryRepository;

    public ChildServiceImpl(ChildRepository childRepository, ParentSummaryRepository parentSummaryRepository) {
        this.childRepository = childRepository;
        this.parentSummaryRepository = parentSummaryRepository;
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
            Meal favMeal = child.getMeals().get(0);
            childInfoBuilder.mealInfo(
                    MealInfo.builder()
                            .mealId(favMeal.getMealId())
                            .inventedDate(favMeal.getInventedDate())
                            .name(favMeal.getName())
                            .build());
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

    @Override
    public long[] getParentSummary() {
        List<ParentSummary> parentSummaryList = parentSummaryRepository.findAll();
        IntSummaryStatistics summaryStatistics = parentSummaryList.stream()
                .map(ParentSummary::getAmountOfChildren)
                .mapToInt(Long::intValue)
                .summaryStatistics();
        int max = summaryStatistics.getMax();
        long[] results = new long[max + 1];
        parentSummaryList.parallelStream().forEach(
                parentSummary ->
                        results[parentSummary.getAmountOfChildren().intValue()] = parentSummary.getAmountOfPersons()
        );
        return results;
    }

    @Override
    public void generateParentSummary(long personSize) {
        List<Object[]> childrenSummaryList = childRepository.getChildCountByParent();
        log.info("Children Summary List size is {}", childrenSummaryList.size());
        Map<Long, Long> parentsMap = new ConcurrentHashMap<>();
        childrenSummaryList.parallelStream().forEach(
                summary -> {
                    Long childCount = ((BigInteger) summary[1]).longValue();
                    Long parentCount = 1L;
                    if (parentsMap.containsKey(childCount)) {
                        parentCount = parentCount + parentsMap.get(childCount);
                    }
                    parentsMap.put(childCount, parentCount);
                }
        );

        long personsCount = parentsMap.entrySet().parallelStream()
                .map(Map.Entry::getValue)
                .mapToLong(Long::longValue)
                .sum();
        parentsMap.put(0L, personSize - personsCount);

        List<ParentSummary> parentSummaryList = parentsMap.keySet()
                .parallelStream()
                .map(key -> new ParentSummary(parentsMap.get(key), key))
                .collect(Collectors.toList());
        parentSummaryRepository.deleteAll();
        parentSummaryRepository.save(parentSummaryList);
    }
}
