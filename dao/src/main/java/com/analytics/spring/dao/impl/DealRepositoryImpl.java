package com.analytics.spring.dao.impl;

import com.analytics.spring.dao.DealRepository;
import com.analytics.spring.model.Deal;
import com.analytics.spring.model.User;
import com.analytics.spring.model.enums.ActionEnum;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by GEO on 05.05.15.
 */
@Repository("dealRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class DealRepositoryImpl implements DealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private String selectDateFormat;
    private List<String> items;
    private List<Integer> columns;
    private List<Deal> dealList=new ArrayList<>();


    public int addDataToBase(List<String> listDataString, List<String> nameOfColumns) {

        //TODO add in method logged user
        User user = new User();
        user.setId(1L);

        Deal deal = new Deal();
        int countOfItemsAddedToDB = 0;

        for (int i = 0; i < nameOfColumns.size(); i++) {

            switch (nameOfColumns.get(i)) {

                case "tool": {
                    try {

                        if (listDataString.get(i) != null) {
                            deal.setTool(listDataString.get(i));
                            countOfItemsAddedToDB++;
                        }
                        break;
                    } catch (Exception e) {
                        break;
                    }
                }

                case "quantity": {
                    try {
                        Double dd = Double.parseDouble(listDataString.get(i));
                        if (dd > 0) {
                            deal.setQuantity(dd);
                            countOfItemsAddedToDB++;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        break;
                    }
                }

                case "action": {
                    try {
                        if (listDataString.get(i).matches("Sell")) {
                            deal.setAction(ActionEnum.SELL);
                            countOfItemsAddedToDB++;
                        } else {
                            deal.setAction(ActionEnum.BUY);
                            countOfItemsAddedToDB++;
                        }
                        break;
                    } catch (Exception e) {
                        break;
                    }
                }

                case "openPrice": {
                    try {
                        Double dd = Double.parseDouble(listDataString.get(i));
                        if (dd > 0) {
                            deal.setOpenPrice(dd);
                            countOfItemsAddedToDB++;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        break;
                    }
                }

                case "closePrice": {
                    try {
                        Double dd = Double.parseDouble(listDataString.get(i));
                        if (dd > 0) {
                            deal.setClosePrice(dd);
                            countOfItemsAddedToDB++;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        break;
                    }
                }

                case "openTime": {
                    try {
                        Timestamp timestamp = getTimestampFromString(listDataString.get(i).toString());
                        if (timestamp != null) {
                            deal.setOpenTime(timestamp);
                            countOfItemsAddedToDB++;
                        }
                        break;
                    } catch (Exception e) {
                        break;
                    }
                }

                case "closeTime": {
                    try {
                        Timestamp timestamp = getTimestampFromString(listDataString.get(i).toString());
                        if (timestamp != null) {
                            deal.setCloseTime(timestamp);
                            countOfItemsAddedToDB++;
                        }
                        break;
                    } catch (Exception e) {
                        break;
                    }
                }

            }
        }

        //insert User into row-deal
        deal.setUser(user);

        try {
            if (countOfItemsAddedToDB < nameOfColumns.size()) {
                return 0;
            }
            if (isDataNew(deal)) {
                //entityManager.persist(deal);
                dealList.add(deal);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public List<List<String>> preViewDataFromCSV(File file) {

        CSVReader reader;
        String[] nextLine;
        List<List<String>> listDataFromFile = new ArrayList<>();
        char delimiter = ',';

        try {
            //delimiter = ','
            reader = new CSVReader(new FileReader(file), delimiter);

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 2) {
                    delimiter = ';';
                    break;
                }

                listDataFromFile.add(asList(nextLine));
            }


            if (delimiter == ';') {
                //delimiter = ';'
                reader = new CSVReader(new FileReader(file), delimiter);

                while ((nextLine = reader.readNext()) != null) {

                    listDataFromFile.add(asList(nextLine));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDataFromFile;
    }


    public int savePersedListToDB
            (List<String> items, List<Integer> columns, List<List<String>> listOfData, String template) {

        this.selectDateFormat = template;
        this.items = items;
        this.columns = columns;

        int counterRowToAdd = 0;

        try {
            for (List<String> aListOfData : listOfData) {
                counterRowToAdd = counterRowToAdd + addDataToBase((getResultRow(aListOfData, columns)), items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!dealList.isEmpty()){
            try {
                synchronized (entityManager)
                {
                for(Deal deal:dealList) {
                    entityManager.persist(deal);
                }
            }
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }
        return counterRowToAdd;
    }

    //data validation for uniqueness
    private boolean isDataNew(Deal deal) {

        try {
            Query query = entityManager.createQuery("from Deal D where D.openTime = :openTime and " +
                    "D.closeTime=:closeTime and D.tool =:tool and D.openPrice=:openPrice and D.closePrice=:closePrice and D.user=:user_id");

            query.setParameter("openTime", deal.getOpenTime());
            query.setParameter("closeTime", deal.getCloseTime());
            query.setParameter("tool", deal.getTool());
            query.setParameter("openPrice", deal.getOpenPrice());
            query.setParameter("closePrice", deal.getClosePrice());
            query.setParameter("user_id", deal.getUser());
            List<Deal> dealList = query.getResultList();

            if(dealList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    // getting List with correct columns (), accept parsed List and List with number of columns
    public List<String> getResultList(List<String> stringList, List<Integer> intList) {
        List<String> resultList = new ArrayList<>();
        for (Integer iter : intList) {

            resultList.add(stringList.get(iter.intValue()));
        }
        return resultList;
    }


    // getting List with correct columns (), trimmed and parsed List and List with number of columns
    private List<String> getResultRow(List<String> stringList, List<Integer> intList) {
        List<String> resultList = new ArrayList<>();
        for (Integer iter : intList) {
            resultList.add(stringList.get(iter.intValue()));
        }
        return resultList;
    }

    private Timestamp getTimestampFromString(String string) {

        Timestamp timestamp = null;
        try {
            //use template 'selectDateFormat' for parsing Date format
            DateFormat dateFormat = new SimpleDateFormat(selectDateFormat);
            timestamp = new Timestamp(dateFormat.parse(string).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }


}