package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Debt;

public interface DebtService {

    Debt findDebtById(Long id);

    Debt saveNewDebt(Debt debt);

    void deleteDebtById(Long id);

}
