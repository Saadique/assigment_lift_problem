package com.SmartKent.lift.controller;

import com.SmartKent.lift.model.Lift;
import com.SmartKent.lift.repository.LiftRepository;
import com.SmartKent.lift.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class Controller {


    @Autowired
    LiftService liftService;


    @Autowired
    Lift lift1 = new Lift();


// Assumed that a person can request only one specific lift at a time

    @GetMapping("/lift/")
    public Map<String, Integer> getLift(@RequestParam int fromFloor, @RequestParam int toFloor) throws InterruptedException {
        lift1.setLiftId(1L);
        int EST = lift1.pickAndDrop(fromFloor, toFloor);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("EST", EST);
        return map;
    }


}
