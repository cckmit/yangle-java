package com.ater.modules.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.ater.common.annotation.SysLog;
import com.ater.common.excel.BillingHandler;
import com.ater.common.utils.R;
import com.ater.modules.entity.FzBillingEntity;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/fz/billing")
public class FzBillingController {

    //引用application.yml中的变量
    @Value("${upload_dir}")
    private String uploadDir;

    /**
     * 导入事例
     */

    @SysLog("开票导入")
    @ResponseBody
    @RequestMapping(value = "import")
    @RequiresPermissions("fz:billing:import")
    public R importExcel1(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        // 读取文件
        if (!file.isEmpty()) {
            try {
                //设置参数
                ImportParams params = new ImportParams();
                // 数据处理  进行验证数据  验证条件在 BillingHandler 类中
                IExcelVerifyHandler<FzBillingEntity> handler = new BillingHandler();
                params.setVerifyHanlder(handler);
                params.setNeedVerfiy(true); //是否需要校验上传的Excel
                params.setHeadRows(2);       //设置表头行数

                ExcelImportResult<FzBillingEntity> result = ExcelImportUtil.importExcelMore(file.getInputStream(),
                        FzBillingEntity.class, params);

                //excel中有多个sheet时 设置开始读取的sheet位置(默认为0)
                params.setStartSheetIndex(1);
                ExcelImportResult<FzBillingEntity> result1 = ExcelImportUtil.importExcelMore(file.getInputStream(),
                        FzBillingEntity.class, params);

                //获取成功的记录
                List<FzBillingEntity> successList = new ArrayList<>();
                successList.addAll(result.getList());
                successList.addAll(result1.getList());

                //把失败的记录写入表格 文件名返回给前端
                String filePath;
                if (result.getFailList().size() == 0 && result1.getFailList().size() == 0) {
                    filePath = "";
                } else {
					String fileName = file.getOriginalFilename();
					String[] srr = fileName.split("/");
					String resultPath = UUID.randomUUID().toString().replaceAll("-", "") + srr[srr.length-1];
					filePath = uploadDir+resultPath;
					//失败的数据写入excel
                    FileOutputStream fos = new FileOutputStream(filePath);
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    exportExcel(workbook,result.getFailList(),"专用发票",filePath,0);
                    exportExcel(workbook,result1.getFailList(),"普通发票",filePath,1);
                    workbook.write(fos);
					filePath = "http://localhost:8088/ledger/resources/"+resultPath;
                }

                //执行插入或更新操作
				return R.ok1(saveFzBillingEntity(successList),filePath);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error("读取文件值不正确，请检查");
            }
        } else {
            return R.error("导入文件不存在，请重新选择文件！");
        }
    }

    private String saveFzBillingEntity(List<FzBillingEntity> successList) {
        return "插入条";
    }

    private void exportExcel(HSSFWorkbook workbook, List<FzBillingEntity> list, String sheetName, String filePath, int sheetNum) throws FileNotFoundException {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetName);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);

        String[] headers = {"发票号码","购方企业名称","开票日期","合同编号","金额","税率","税额","承办律师"};
        // 产生表格标题行
        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);

            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (list != null) {
            int index = 2;
            for (FzBillingEntity fzBillingEntity : list) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getInvoiceNumber());
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getInvoiceTitle());
                cellIndex = setCell(row,cellIndex,new SimpleDateFormat("yyyy-MM-dd").format(fzBillingEntity.getOpenDate()));
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getCaseNo());
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getAmount());
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getTax());
                cellIndex = setCell(row,cellIndex,fzBillingEntity.getTaxAmount());
                setCell(row,cellIndex,fzBillingEntity.getUndertakeLawyer());
                index++;
            }
        }
    }

    private int setCell(HSSFRow row,int cellIndex,Object obj){
        HSSFCell cell = row.createCell((short) cellIndex);
        String value = obj==null?"":obj.toString();
        cell.setCellValue(value);
        cellIndex++;
        return cellIndex;
    }
}

