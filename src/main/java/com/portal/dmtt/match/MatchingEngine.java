package com.portal.dmtt.match;

import com.portal.dmtt.model.exceptionMonitoring.FN_Old_Exception;
import com.portal.dmtt.model.exceptionMonitoring.FN_Result_Set;
import com.portal.dmtt.model.exceptionMonitoring.config.FN_FR_new_Location;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MatchingEngine {

    private final Logger logger = Logger.getLogger(MatchingEngine.class);


    private List<FN_Old_Exception> fnOldExceptionList;

    private List<FN_Result_Set> fnResultSetList;

    private List<FN_FR_new_Location> fnFrNewLocationList;

    private FN_Result_Set theFnResultSet;

    private FN_Old_Exception theFnOldException;


    /**
     * main Execution
     *
     * @return
     */
    public int execute() {

        int result = 0;

        System.out.println(">>>>>>>>>>>>>>>>Start Matching Engine<<<<<<<<<<<<<<<< ");

        System.out.println("Start: selectTheOldFnException ");
        result = selectTheOldFnException();
        if (result < 0) {
            logger.error("[Problem method at selectTheOldFnException]. Result: " + result);

            return -1;
        }

        System.out.println("Start: selectTheConfigurationFN ");
        result = selectTheConfigurationFN();
        if (result < 0) {
            logger.error("[Problem method at selectTheOldFnException]. Result: " + result);

            return -2;
        }

        System.out.println("Start: matchFN ");
        result = matchFN();
        if (result < 0) {
            logger.error("[Problem method at matchFN]. Result: " + result);

            return -3;
        }

        System.out.println("Start: theFnResultSet ");
        result = writeItemToDb(theFnResultSet);
        if (result < 0) {
            logger.error("[Problem method at write item to db]. Result: " + result);

            return -4;
        }

        System.out.println("Start: findNewFN ");
        result = findNewFN();
        if (result < 0) {
            logger.error("[Problem method at findNewFN]. Result: " + result);
            return -5;
        }

        System.out.println(">>>>>>>>>>>>>>>>End Matching Engine<<<<<<<<<<<<<<<< ");

        return result;
    }


    private int selectTheOldFnException() {

        int result = 0;

        // create session factory
        SessionFactory factory = new Configuration().configure("hibcfg.eMonitor.xml").addAnnotatedClass(FN_Old_Exception.class).buildSessionFactory();

        // create a session
        Session session = factory.openSession();

        try {
            String Query1 = "from FN_Old_Exception";

            // start a transaction
            session.beginTransaction();

            // query current reported watch list
            fnOldExceptionList = session.createQuery(Query1).list();

            // commit transaction
            session.getTransaction().commit();

            result = fnOldExceptionList.size();

            //System.out.println(fnOldExceptionList.toString());

            for (int i = 0; i < result; i++) {

                System.out.println("selectTheOldFnException ---->>>" + fnOldExceptionList.get(i).toString());
            }

        } catch (SessionException e) {

            logger.fatal("Select selectFnException_New: Caught an Exception" + e.getMessage());

        } finally {

            session.close();
            factory.close();

        }

        return result;

    }

    private int matchFN() {

        int result = 0;

        result = fnOldExceptionList.size();
        int sizeFNConfiguration = fnFrNewLocationList.size();

        for (int j = 0; j < sizeFNConfiguration; j++) {

            String matchSpId = fnFrNewLocationList.get(j).getMatch_SP_Id();
            String matchLocId = fnFrNewLocationList.get(j).getMatch_Loc_Id();
            String remarks = fnFrNewLocationList.get(j).getRemarks();


            theFnOldException = new FN_Old_Exception();

            for (int i = 0; i < result; i++) {

                if (((fnOldExceptionList.get(i).getSpId().equals(matchSpId))) && ((fnOldExceptionList.get(i).getLocId().equals(matchLocId)))) {

                    // set the new remarks after hit the logic
                    fnOldExceptionList.get(i).setRemarks(remarks);

                    String spId = fnOldExceptionList.get(i).getSpId();
                    String locId = fnOldExceptionList.get(i).getLocId();
                    String status = fnOldExceptionList.get(i).getxFerXmitStatus();
                    String fileName = fnOldExceptionList.get(i).getXferFileName();
                    String updateTS = fnOldExceptionList.get(i).getUpdateTs();
                    String yymm = fnOldExceptionList.get(i).getYYMM();
                    String Remarks = fnOldExceptionList.get(i).getRemarks();

                    //display message
                    //System.out.println("matchFN --->>>" + fnOldExceptionList.get(i).toString());

                    theFnResultSet = new FN_Result_Set(spId, locId, status, fileName, updateTS, yymm, Remarks);

                    writeItemToDb(theFnResultSet);
                }
            }
        }

        return result;
    }

    public int writeItemToDb(FN_Result_Set fn_result_set) {

        int result = 0;

        // create session factory
        SessionFactory factory = new Configuration().configure("hibcfg.eMonitor.xml").addAnnotatedClass(FN_Result_Set.class).buildSessionFactory();
        // create a session
        Session session = factory.openSession();

        try {

            session.beginTransaction();

            session.saveOrUpdate(fn_result_set);

            session.getTransaction().commit();

        } catch (HibernateException ex1) {
            ex1.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

            session.close();
            factory.close();

        }

        return result;
    }


    private int findNewFN() {

        int result = 0;

        result = fnOldExceptionList.size();

        for (int i = 0; i < result; i++) {

            if (fnOldExceptionList.get(i).getRemarks() == null) {

                String spId = fnOldExceptionList.get(i).getSpId();
                String locId = fnOldExceptionList.get(i).getLocId();
                String status = fnOldExceptionList.get(i).getxFerXmitStatus();
                String fileName = fnOldExceptionList.get(i).getXferFileName();
                String updateTS = fnOldExceptionList.get(i).getUpdateTs();
                String yymm = fnOldExceptionList.get(i).getYYMM();
                String Remarks = "New Location";

                theFnResultSet = new FN_Result_Set(spId, locId, status, fileName, updateTS, yymm, Remarks);

                writeItemToDb(theFnResultSet);

            }
        }

        return result;
    }

    private int selectTheConfigurationFN() {


        int result = 0;

        // create session factory
        SessionFactory factory = new Configuration().configure("hibcfg.eMonitor.xml").addAnnotatedClass(FN_FR_new_Location.class).buildSessionFactory();

        // create a session
        Session session = factory.openSession();

        try {
            String Query1 = "from FN_FR_new_Location";

            // start a transaction
            session.beginTransaction();

            // query current reported watch list
            fnFrNewLocationList = session.createQuery(Query1).list();

            // commit transaction
            session.getTransaction().commit();

            result = fnFrNewLocationList.size();

            //System.out.println(fnOldExceptionList.toString());
//
//            for (int i = 0; i < result; i++) {
//
//                System.out.println("selectTheOldFnException ---->>>" + fnFrNewLocationList.get(i).toString());
//            }

        } catch (SessionException e) {

            logger.fatal("Select fnFrNewLocationList : Caught an Exception" + e.getMessage());

        } finally {

            session.close();
            factory.close();

        }

        return result;

    }

}
