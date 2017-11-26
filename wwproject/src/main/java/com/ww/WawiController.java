package com.ww;

import com.ww.child.ChildInfo;
import com.ww.child.ChildService;
import com.ww.child.ColorInfo;
import com.ww.house.House;
import com.ww.house.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class WawiController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private ChildService childService;

    @GetMapping("/house/owner/{ownerId}")
    public House getHouseByOwner(@NotNull @PathVariable("ownerId") Long ownerId) {
        return houseService.getHouseByOwner(ownerId);
    }

    @GetMapping("/child/info/{childId}")
    public ChildInfo getChildById(@NotNull @PathVariable("childId") Long childId) {
        return childService.getChildInfo(childId);
    }

    @GetMapping("/color/{childId}")
    public ColorInfo getColorForChild(@NotNull @PathVariable("childId") Long childId) {
        return childService.getColorInfo(childId);
    }

    @GetMapping("/persons/children")
    public long[] getParentSummary() {
        return childService.getParentSummary();
    }
}
