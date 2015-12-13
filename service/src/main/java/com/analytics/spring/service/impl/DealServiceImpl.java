package com.analytics.spring.service.impl;

import com.analytics.spring.dao.DealRepository;
import com.analytics.spring.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by GEO on 08.05.15.
 */
@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    public List<List<String>> preViewDataFromCSV(File file){
        return dealRepository.preViewDataFromCSV(file);
    }

    @Override
    public int savePersedListToDB(List<String> items, List<Integer> columns, List<List<String>> listOfData, String template) {
        return dealRepository.savePersedListToDB(items, columns, listOfData, template);
    }
}
