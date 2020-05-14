package com.SmartKent.lift.repository;

import com.SmartKent.lift.model.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiftRepository extends JpaRepository<Lift, Long> {
    @Override
    Optional<Lift> findById(@Param("liftId")Long liftId);

}
