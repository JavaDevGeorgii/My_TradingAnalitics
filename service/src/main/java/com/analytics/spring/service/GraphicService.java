package com.analytics.spring.service;

import com.analytics.spring.model.dto.CountDealsByTool;
import com.analytics.spring.model.dto.PAndLByDay;

import java.util.List;


/**
 * Created by Alexandr Velikiy on 06.05.2015.
 */
public interface GraphicService {

    List<CountDealsByTool> getCountDealsByTool();

    List<PAndLByDay> getPAndLByDay();

    List<PAndLByDay> getPAndLByTool(String tool);

}
