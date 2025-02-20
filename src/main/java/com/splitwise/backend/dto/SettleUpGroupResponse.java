package com.splitwise.backend.dto;

import com.splitwise.backend.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponse {
    private long groupId;
    private List<Transaction> transactions;
    private ResponseStatus status;
    private String message;
}
