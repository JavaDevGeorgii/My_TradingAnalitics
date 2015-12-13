package com.analytics.spring.dao;

import java.io.File;
import java.util.List;

/**
 * Created by GEO on 05.05.15.
 */
public interface DealRepository {

    List<List<String>> preViewDataFromCSV(File file);
    int addDataToBase(List<String> listDataString, List<String> nameOfColumns);
    List<String> getResultList(List<String> stringList, List<Integer> intList);
    int savePersedListToDB(List<String> items, List<Integer> columns, List<List<String>> listOfData, String template);

}
