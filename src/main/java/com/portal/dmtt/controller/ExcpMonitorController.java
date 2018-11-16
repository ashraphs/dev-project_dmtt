package com.portal.dmtt.controller;


import com.portal.dmtt.match.MatchingEngine;
import com.portal.dmtt.model.exceptionMonitoring.config.EXCP_File;
import com.portal.dmtt.model.exceptionMonitoring.config.FN_FR_new_Location;
import com.portal.dmtt.repo.dmttDAO;
import com.portal.dmtt.repo.excpFileREPO;
import com.portal.dmtt.repo.excpMonitorREPO;
import com.portal.dmtt.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ExcpMonitorController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss a");

    private static String UPLOADED_FOLDER = "D:/temp/";

    @Autowired
    private excpMonitorREPO monitorREPO;

    @Autowired
    private dmttDAO dmttDAO;

    @Autowired
    private excpFileREPO fileREPO;

    private ExcpMonitorController excpMonitorController;

    private List<EXCP_File> excp_fileList;

    private EXCP_File excp_file;

    @RequestMapping(value = {"/excpMonitor", "excp"}, method = RequestMethod.GET)
    public ModelAndView excpMonitor() {

        //page viewer
        ModelAndView mv = new ModelAndView("exceptionMonitoring");
        //add data object from the page to model
        mv.addObject("addNewLocation", new FN_FR_new_Location());
        //fetch data from the model
        List<FN_FR_new_Location> fnFrNewLocationList = dmttDAO.getLocation();
        //show the data as datatable
        mv.addObject("theFnFrNewLocationList", fnFrNewLocationList);


        //add data object from the page to model - EXP FILE
        mv.addObject("addNewUploadFile", new EXCP_File());

        //fetch data from the model
        List<EXCP_File> excp_fileList = dmttDAO.getExcpFile();
        //show the data as datatable
        mv.addObject("theExcpFileList", excp_fileList);

//        //----------------------
//        // file upload
//        //----------------------
//        mv.addObject ( "addFileUpload", new UploadFile () );

        return mv;
    }

    /**
     * Add - new location for FN and FR
     *
     * @return
     */
    @RequestMapping(value = "/addNewLocation", method = RequestMethod.POST)
    public String addNewLocation(@ModelAttribute FN_FR_new_Location theFnFrNewLocation, Model model, HttpServletRequest req) {

        if (null != theFnFrNewLocation) {

            if (!AppUtil.isObjectEmpty(theFnFrNewLocation.getMatch_SP_Id()) && !AppUtil.isObjectEmpty(theFnFrNewLocation.getMatch_Loc_Id()) && !AppUtil.isObjectEmpty(theFnFrNewLocation.getRemarks())) {

                FN_FR_new_Location fnFrNewLocation = new FN_FR_new_Location();
                fnFrNewLocation.setMatch_SP_Id(theFnFrNewLocation.getMatch_SP_Id());
                fnFrNewLocation.setMatch_Loc_Id(theFnFrNewLocation.getMatch_Loc_Id());
                fnFrNewLocation.setRemarks(theFnFrNewLocation.getRemarks());

                monitorREPO.save(fnFrNewLocation);

                System.out.println("monitor: " + fnFrNewLocation.toString());
            }
        }
        return "redirect:/excpMonitor";
    }

    /**
     * Delete - new location for FN and FR
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/addNewLocation/{id}", method = RequestMethod.GET, params = "actionDelete")
    public String deleteNewLocation(Model model, @PathVariable(required = true, name = "id") Long id) {

        int deleteRowById = dmttDAO.deleteNewLocation(id);

        return "redirect:/excpMonitor";

    }

    /**
     * Upload file to specified folder
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/addNewUploadFile", method = RequestMethod.POST)
    public String addUploadFile(@RequestParam(value = "theFile") MultipartFile file, @ModelAttribute EXCP_File theEXCPFile) {

        //Time stamp Date + Time
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStamp = sdf.format(timestamp);

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

            String file_Path = UPLOADED_FOLDER + "" + file.getOriginalFilename();

            System.out.println("the file path: " + UPLOADED_FOLDER + "" + file.getOriginalFilename());

            //---------------
            // save to all data to database
            //--------------
            if (null != theEXCPFile) {

                if (!AppUtil.isObjectEmpty(theEXCPFile.getFileName()) && !AppUtil.isObjectEmpty(theEXCPFile.getRemarks())) {

                    EXCP_File excpFile = new EXCP_File();
                    excpFile.setFileName(theEXCPFile.getFileName());
                    excpFile.setRemarks(theEXCPFile.getRemarks());
                    excpFile.setFilePath(file_Path);
                    excpFile.setTimeStamp(timeStamp);

                    //save to DB
                    fileREPO.save(excpFile);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return "redirect:/excpMonitor";
    }

    /**
     * Truncate the Table - fn_old_exception
     *
     * @return
     */
    @RequestMapping(value = "/deleteDb", method = RequestMethod.POST)
    public String clearDBException() {

        //truncate the table FN_OLD_EXCEPTION and FN_READY_EXCEPTION
        String[] theTable = {"fn_ready_exception", "fn_old_exception"};
        int size = theTable.length;

        for (int i = 0; i < size; i++) {

            dmttDAO.deleteDbException(theTable[i]);
        }

        return "redirect:/excpMonitor";
    }

    /**
     * Run Matching by selected Row
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/runMatching/{id}", method = RequestMethod.GET, params = "actionRun")
    public String runMatching(@PathVariable(required = true, name = "id") long id) {

        System.out.println("the id is: " + id);

        //truncate table before run
        try {

            String[] theTable = {"fn_ready_exception", "fn_old_exception"};
            int size = theTable.length;

            for (int i = 0; i < size; i++) {

                dmttDAO.deleteDbException(theTable[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //fetch data table from EXCP
        excp_fileList = dmttDAO.getExcpFile();

        int size = excp_fileList.size();

        //get the detail file to uplodd- id and name of file
        for (int i = 0; i < size; i++) {

            int fileId = excp_fileList.get(i).getId();
            String filePath = excp_fileList.get(i).getFilePath();

            //compare id and fine the File Name
            if ((fileId == id)) {

                //check if the file path is exist
                File tempFile = new File(filePath);
                boolean fileExist = tempFile.exists();

                if (fileExist = true) {

                    //upload to db
                    int uploadDB = dmttDAO.uploadListToDB(filePath);

                    try {

                        //Execute the matching engine
                        MatchingEngine me = new MatchingEngine();
                        me.execute();

                        System.out.println("End testing");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        return "redirect:/excpMonitor";
    }


}
