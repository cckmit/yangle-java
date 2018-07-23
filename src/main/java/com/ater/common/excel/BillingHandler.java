package com.ater.common.excel;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHanlderResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.ater.modules.entity.FzBillingEntity;
import com.ater.modules.service.FzBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BillingHandler implements IExcelVerifyHandler<FzBillingEntity> {

    @Autowired
    private FzBillingService fzBillingService;

    private static BillingHandler billingHandler;

    @PostConstruct
    public void init() {
        billingHandler = this;
        billingHandler.fzBillingService = this.fzBillingService;
    }

    public ExcelVerifyHanlderResult verifyHandler(FzBillingEntity obj){
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult();
        result.setSuccess(verifyBilling(obj));
        return result;
    }

    private boolean verifyBilling(FzBillingEntity obj) {
        String invoiceNumber = obj.getInvoiceNumber();
        //判断发票号是否为空或null
        if (com.mysql.jdbc.StringUtils.isNullOrEmpty(invoiceNumber))
            return false;
        //调用fzBillingService中方法  格式  billingHandler.fzBillingService
        billingHandler.fzBillingService.queryObject(1L);
        return true;
    }

}
