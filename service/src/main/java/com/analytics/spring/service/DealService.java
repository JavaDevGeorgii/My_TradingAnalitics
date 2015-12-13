package com.analytics.spring.service;

import java.io.File;
import java.util.List;

/**
 * Created by GEO on 08.05.15.
 */
public interface DealService {

    List<List<String>> preViewDataFromCSV(File file);
    int savePersedListToDB(List<String> items, List<Integer> columns, List<List<String>> listOfData, String template);
}
