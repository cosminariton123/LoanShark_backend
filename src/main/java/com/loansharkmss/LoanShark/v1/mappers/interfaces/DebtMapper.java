package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.DebtCard;
import com.loansharkmss.LoanShark.v1.dtos.DebtCreate;
import com.loansharkmss.LoanShark.v1.model.Debt;

public interface DebtMapper {

    DebtCard DebtToDebtCard(Debt debt);

    Debt DebtCreateToDebt(DebtCreate debtCreate);

}
