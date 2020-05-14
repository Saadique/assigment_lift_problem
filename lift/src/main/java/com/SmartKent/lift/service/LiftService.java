package com.SmartKent.lift.service;

import com.SmartKent.lift.model.Lift;
import com.SmartKent.lift.repository.LiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LiftService {
    @Autowired
    private LiftRepository liftRepository;

    public Lift findById(Long id){
        return liftRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Lift dosent exist" + id)
        );
    }

}
