package com.xjs.util;
 
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xjs.pojo.VeteranUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
 
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
 
import java.util.Map;
import java.util.NoSuchElementException;
 
public class ExcelUtil {
    public static boolean isObjectNull(Object obj){
        if (obj != null) {
            Class<?> objClass = obj.getClass();
            Method[] declaredMethods = objClass.getDeclaredMethods();
            if (declaredMethods.length > 0) {
                int methodCount = 0; // get 方法数量
                int nullValueCount = 0; // 结果为空

                for (Method declaredMethod : declaredMethods) {
                    String name = declaredMethod.getName();
                    if (name.startsWith("get") || name.startsWith("is")){
                        methodCount += 1;
                        try {
                            Object invoke = declaredMethod.invoke(obj);
                            if (invoke == null) {
                                nullValueCount += 1;
                            }
                        } catch (IllegalAccessException | InvocationTargetException e){
                            e.printStackTrace();
                        }
                    }
                }
                return methodCount == nullValueCount;
            }
        }
        return false;
    }
    /**
     * 导出人员设备信息数据列表
     * @param vo
     * @param outputStream
     * @return: "人员设备信息数据列表.xls"
     * @author Mike—GY
     * @since: 2022/8/2
     */

    public void exportDeviceInfoList(VeteranUser vo, ServletOutputStream outputStream) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一个新的table工作薄
        HSSFSheet sheet = wb.createSheet("设备信息");
        // 行
        HSSFRow row;
        // 单元格
        HSSFCell cell;
        // 表头
        String[] ths = {"序号","组织","日期","设备总数","在线设备","离线设备","在线率"};
        // 构建表头
        row = sheet.createRow(0);
        for(int i = 0; i< ths.length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(ths[i]);
        }
        //查询要导出的数据并将结果赋给data，目的是使代码简洁直观（有数据类型转换）
        List<VeteranUser>  data = (List<VeteranUser>)null;
        if(CollectionUtils.isNotEmpty(data)) {
            for (int i = 0;i< data.size(); i++) {
                VeteranUser entityClass2Vo = data.get(i);
                HSSFRow createRow = sheet.createRow(i + 1);
                //序号
                int number = i + 1;
                HSSFCell numberCell = createRow.createCell(0);
                numberCell.setCellValue(number);
                //组织名称
                String deptName = entityClass2Vo.getName();
                HSSFCell deptNameCell = createRow.createCell(1);
                if (StringUtils.isNotEmpty(deptName)) {
                    deptNameCell.setCellValue(deptName);
                }
                //记录日期
                Date recordDate = entityClass2Vo.getEnlistmentTime();
                HSSFCell recordDateCell = createRow.createCell(2);
                if (StringUtils.isNotEmpty(recordDate.toString())) {
                    recordDateCell.setCellValue(recordDate);
                }
                //设备总数
                Integer deviceTotalNum = entityClass2Vo.getAge();
                HSSFCell deviceTotalNumCell = createRow.createCell(3);
                if (deviceTotalNum != null) {
                    deviceTotalNumCell.setCellValue(deviceTotalNum);
                }
                //在线设备
                Integer onlineNum = entityClass2Vo.getStatus();
                HSSFCell onlineNumCell = createRow.createCell(4);
                if (onlineNum != null) {
                    onlineNumCell.setCellValue(onlineNum);
                }
                //离线设备
                Integer offlineNum = entityClass2Vo.getEnlistmentAge();
                HSSFCell offlineNumCell = createRow.createCell(5);
                if (offlineNum != null) {
                    offlineNumCell.setCellValue(offlineNum);
                }
                //在线率
                String onlineRate = entityClass2Vo.getLoginName();
                HSSFCell onlineRateCell = createRow.createCell(6);
                if (StringUtils.isNotEmpty(onlineRate)) {
                    onlineRateCell.setCellValue(onlineRate.toString());
                }
            }
        }
        //将数据写入之前创建的.xls文件
        wb.write(outputStream);
        //关闭HSSFWorkbook
        wb.close();
    }
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
 
    }
    //导出
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }
 
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }
 
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }
 
    //导入
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
 
 
 
}