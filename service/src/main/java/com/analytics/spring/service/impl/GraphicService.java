package com.analytics.spring.service.impl;


import com.analytics.spring.dao.GraphicRepository;
import com.analytics.spring.model.dto.CountDealsByTool;
import com.analytics.spring.model.dto.PAndLByDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alexandr Velikiy on 06.05.2015.
 */
@Service
public class GraphicService implements com.analytics.spring.service.GraphicService {

    @Autowired
    private GraphicRepository graphicRepository;

    @Override
    public List<CountDealsByTool> getCountDealsByTool() {
        return  graphicRepository.getCountDealsByTool();
    }

    @Override
    public List<PAndLByDay> getPAndLByDay() {
        return graphicRepository.getPAndLByDay();
    }

    @Override
    public List<PAndLByDay> getPAndLByTool(String tool){
        return graphicRepository.getPAndLByTool(tool);
    }
}
