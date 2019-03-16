package com.ricardo.ratecalculator.repository;

import com.ricardo.ratecalculator.model.Lender;
import java.util.List;

public interface DataRepository {

    List<Lender> findAllOrderedByInterestRateAsc();

    List<Lender> findAll();
}
