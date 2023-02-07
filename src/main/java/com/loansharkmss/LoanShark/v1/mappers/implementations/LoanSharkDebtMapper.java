package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.config.DebtStatus;
import com.loansharkmss.LoanShark.v1.dtos.DebtCard;
import com.loansharkmss.LoanShark.v1.dtos.DebtCreate;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.DebtMapper;
import com.loansharkmss.LoanShark.v1.model.Debt;
import com.loansharkmss.LoanShark.v1.service.interfaces.DebtService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;

public class LoanSharkDebtMapper implements DebtMapper {

    private UserService userService;

    public LoanSharkDebtMapper(UserService userService) {
        this.userService = userService;
    }

    public DebtCard DebtToDebtCard(Debt debt) {

        Long debtorId = null;
        String debtorUsername = null;
        String debtorFirstName = null;
        String debtorLastName = null;
        String debtorEmail = null;

        if (debt.getDebtor() != null){
            debtorId = debt.getDebtor().getId();
            debtorUsername = debt.getDebtor().getUsername() ;
            debtorFirstName = debt.getDebtor().getFirstName();
            debtorLastName = debt.getDebtor().getLastName();
            debtorEmail = debt.getDebtor().getEmail();
        }

        return new DebtCard(
                debt.getId(),
                debtorId,
                debtorUsername,
                debtorFirstName,
                debtorLastName,
                debtorEmail,
                debt.getValue(),
                debt.getDebtStatus()
        );
    }

    public Debt DebtCreateToDebt(DebtCreate debtCreate) {
        Debt debt = new Debt();

        debt.setDebtStatus(DebtStatus.ACTIVE);
        debt.setDebtor(userService.findUserById(debtCreate.getDebtorId()));
        debt.setValue(debtCreate.getValue());

        return debt;
    }
}
