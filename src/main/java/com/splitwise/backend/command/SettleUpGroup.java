package com.splitwise.backend.command;

import com.splitwise.backend.controller.SettleUpController;
import com.splitwise.backend.dto.ResponseStatus;
import com.splitwise.backend.dto.SettleUpGroupRequest;
import com.splitwise.backend.dto.SettleUpGroupResponse;
import com.splitwise.backend.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUpGroup implements Command {
    @Autowired
    private SettleUpController settleUpController;
    // Command should look like this : "SettleUpGroup 1"
    @Override
    public boolean matches(String[] command) {
        if(command.length >= 1 && command[0].equals("SettleUpGroup")) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String[] command) {
        //take data from command
        Long groupId = Long.parseLong(command[1]);
        //create request DTO
        SettleUpGroupRequest request = new SettleUpGroupRequest();
        request.setGroupId(groupId);
        //call controller and fetch response DTO
        SettleUpGroupResponse response = settleUpController.settleUp(request);
        if(response.getStatus().equals(ResponseStatus.FAILURE)){
            System.out.println(response.getMessage());
        }else{
        //print response DTO
            for(Transaction transaction: response.getTransactions()){
                System.out.println(transaction.getUserFrom().getName() + " should pay " + transaction.getUserTo().getName() + " Rs. " + transaction.getAmount());
            }
        }
    }
}
