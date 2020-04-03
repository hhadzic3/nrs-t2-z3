package ba.unsa.etf.rpr;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;


public class GradoviReport extends JFrame {

    public void showReport(Connection conn)  {
        String reportSrcFile = getClass().getResource("/reports/gradovi.jrxml").getFile(); // fileNotFindException
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        } catch (JRException e) {
            e.printStackTrace();
        }
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        } catch (JRException e) {
            e.printStackTrace();
        }
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }

}
