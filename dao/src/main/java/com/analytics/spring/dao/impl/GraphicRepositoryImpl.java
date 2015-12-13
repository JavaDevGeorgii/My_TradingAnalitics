package com.analytics.spring.dao.impl;

import com.analytics.spring.dao.GraphicRepository;
import com.analytics.spring.model.dto.CountDealsByTool;
import com.analytics.spring.model.dto.PAndLByDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Alexandr Velikiy on 06.05.2015.
 */

@Repository("graphicRepository")
@Transactional
public class GraphicRepositoryImpl implements GraphicRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CountDealsByTool> getCountDealsByTool() {

//        Query query = entityManager.createQuery("SELECT d.tool, COUNT(d.tool) from Deal d group by d.tool");
//        List<CountDealsByTool> resultList = query.getResultList();
            List<CountDealsByTool> resultList  = jdbcTemplate.query("SELECT tool, COUNT(tool) as countDeal from Deal group by tool",
                new BeanPropertyRowMapper(CountDealsByTool.class));

        return resultList;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List<PAndLByDay> getPAndLByDay() {

        String sql = ""
                + "SELECT "
                + "UNIX_TIMESTAMP(cast(close_time as date))*1000 as date, "
                + "SUM(cast(CASE action "
                + "WHEN 'SELL' THEN open_price-close_price "
                + "ELSE close_price-open_price "
                + "END*100000*quantity AS decimal(15, 2))) as profit "
                + "FROM Deal AS d "
                + "GROUP BY date "
                + "ORDER BY date";

        List<PAndLByDay> resultList  = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(PAndLByDay.class));

        return resultList;

    }

/*
    public List<Object[]> getPAndLByTool(String tool) {

        String sql = "SELECT "
                + "UNIX_TIMESTAMP(cast(close_time as date))*1000 as date, "
                + "SUM(cast(CASE action "
                + "WHEN 'SELL' THEN open_price-close_price "
                + "ELSE close_price-open_price "
                + "END*100000*quantity AS decimal(15, 2))) as profit "
                + "FROM Deal AS d where tool= 'usdchf' "
                + "GROUP BY date "
                + "ORDER BY date";


        //Query query = entityManager.createNativeQuery(sql);
        //Query query=entityManager.createNamedQuery(sql);
        Query query1 = entityManager.createQuery(sql, Deal.class);

        //query.setParameter("t1", tool);
        //query.setParameter("t1", tool);
        //query1.setParameter("t1", tool);

        List<Object[]> resultList = query1.getResultList();

        return resultList;
    }
 */


    public List<PAndLByDay> getPAndLByTool(String tool) {

        String t=tool.substring(1,tool.length()-1);
        String condition="where tool= '" + t + "' ";
        if(t.equals("TOTAL") || t.equals("total")) {
            condition = "";
        }

        String sql = "SELECT "
                + "UNIX_TIMESTAMP(cast(close_time as date))*1000 as date, "
                //+ "close_time as date, "
                + "SUM(cast(CASE action WHEN 'SELL' THEN(open_price-close_price) "
                + "ELSE close_price-open_price "
                + "END*100000*quantity AS decimal(15, 2))) as profit "
                + "FROM Deal AS d "+condition
                + "GROUP BY date "
                + "ORDER BY date";


        //Query query = entityManager.createNativeQuery(sql);

       // List<Object[]> resultList = query.getResultList();

//        List<PAndLByDay> resultList  = jdbcTemplate.query(sql,
//                new BeanPropertyRowMapper(PAndLByDay.class));


        List<PAndLByDay> resultList  = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(PAndLByDay.class));

        return resultList;
    }
}
