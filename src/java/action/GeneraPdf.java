/**
 *
 * @author Esteban
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import daos.OracleDAOFactory;
import utilidades.Constantes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class GeneraPdf extends HttpServlet {

    Connection conexion = null;
    private String esExcel = "false";
    private String esPDF = "false";
    
    static {
    System.setProperty("java.awt.headless", "true");
}

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void destroy() {
        super.destroy();
    }

    public String getEsExcel() {
        return esExcel;
    }

    public void setEsExcel(String esExcel) {
        this.esExcel = esExcel;
    }

    public String getEsPDF() {
        return esPDF;
    }

    public void setEsPDF(String esPDF) {
        this.esPDF = esPDF;
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SI LEGO A GENERA PDF");
        String rutareports = Constantes.rutareportesjasper;
        String rutaImagenes = Constantes.rutaImages;
        //String rutaimagesreports = Constantes.rutaimagesreports;

        HashMap parameters = new HashMap();
        String nombrereporte = request.getParameter("TipoReporte");
        esExcel = request.getParameter("esExcel");
        esPDF = request.getParameter("esPDF");
        System.out.println("nombrereporte: " + nombrereporte);
        Constantes.enviaMensajeConsola("id aspirante: "+Integer.parseInt(request.getParameter("ID_ASPIRANTE")));
         Constantes.enviaMensajeConsola("id ciclo: "+request.getParameter("ID_CICLO"));
        

        //REPORTE FINAL PROYECTOS X UNIDAD
        if (nombrereporte.equals("becaFUR.jasper")) {
            parameters.put("ID_ASPIRANTE", Integer.parseInt(request.getParameter("ID_ASPIRANTE")));
            parameters.put("ID_CICLO", request.getParameter("ID_CICLO"));
            parameters.put("rutaimagen", Constantes.rutaImages);
            parameters.put("SUBREPORT_DIR", Constantes.rutareportesjasper);

        }

        ServletOutputStream servletOutputStream;

        try {
            System.out.println("llega aqui");
            OracleDAOFactory orac = new OracleDAOFactory();
            conexion = orac.createConnection();
            System.out.println("conexion: " + conexion);

            File file = new File(rutareports + nombrereporte);
            System.out.println("file: " + file);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

            if (esExcel.equalsIgnoreCase("true")) {
                ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conexion);
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                //exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,  Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, true);
                exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, false);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                exporter.exportReport();

                byte bytes[] = xlsReport.toByteArray();
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=informeExcel.xls");
                response.setContentLength(bytes.length);
                xlsReport.close();

                servletOutputStream = response.getOutputStream();
                servletOutputStream.write(bytes, 0, bytes.length);
                servletOutputStream.flush();
                servletOutputStream.close();
            }

            if (esPDF.equalsIgnoreCase("true")) {
                byte[] fichero = JasperRunManager.runReportToPdf(jasperReport, parameters, conexion);
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=Reporte.pdf");
                response.setHeader("Cache-Control", "max-age=30");
                response.setHeader("Pragma", "No-cache");
                response.setDateHeader("Expires", 0);
                response.setContentLength(fichero.length);
                response.setHeader("Content-Description", "Pdf");

                servletOutputStream = response.getOutputStream();
                servletOutputStream.write(fichero, 0, fichero.length);
                servletOutputStream.flush();
                servletOutputStream.close();
            }

        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        } finally {
            if (conexion != null) {
                try {
                    // System.out.println("Cerrando la conexion..........");
                    conexion.close();
                } catch (SQLException ex) {
                    System.out.println("No se pudo cerrar la conexion.");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private static boolean hasTempPermission() {

        if (System.getSecurityManager() == null) {
            return true;
        }
        File f = null;
        boolean hasPerm = false;
        try {
            f = Files.createTempFile("+~JT", ".tmp").toFile();
            f.delete();
            f = null;
            hasPerm = true;
        } catch (Throwable t) {
            /* inc. any kind of SecurityException */
        }
        return hasPerm;
    }
}
