package com.splitwise.backend.controller;

import com.splitwise.backend.service.SettleUpService;
import com.splitwise.backend.dto.ResponseStatus;
import com.splitwise.backend.dto.SettleUpGroupRequest;
import com.splitwise.backend.dto.SettleUpGroupResponse;
import com.splitwise.backend.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;
    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }
    public SettleUpGroupResponse settleUp(SettleUpGroupRequest settleUpGroupRequest) {
        SettleUpGroupResponse settleUpGroupResponse = new SettleUpGroupResponse();
        try{
            List<Transaction> transactions = settleUpService.settleUp(settleUpGroupRequest.getGroupId());
            settleUpGroupResponse.setTransactions(transactions);
            settleUpGroupResponse.setStatus(ResponseStatus.SUCCESS);
            settleUpGroupResponse.setMessage("Settled up successfully");
            settleUpGroupResponse.setGroupId(settleUpGroupRequest.getGroupId());
        }catch(Exception e){
            settleUpGroupResponse.setStatus(ResponseStatus.FAILURE);
            settleUpGroupResponse.setMessage(e.getMessage());
        }
        return settleUpGroupResponse;
    }
}
