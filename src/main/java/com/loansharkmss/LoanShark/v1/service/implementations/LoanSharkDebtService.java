package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.BadRequest;
import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.model.Debt;
import com.loansharkmss.LoanShark.v1.repository.DebtRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.DebtService;
import org.springframework.stereotype.Service;

@Service
public class LoanSharkDebtService implements DebtService {

    private final DebtRepository debtRepository;

    public LoanSharkDebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    public Debt findDebtById(Long id) {
        Debt debt = debtRepository.findDebtById(id);

        if (debt == null)
            throw new NotFoundException("Debt with id " + id + " not found");

        return debt;
    }

    public Debt saveNewDebt(Debt debt) {
        return debtRepository.save(debt);
    }

    public void deleteDebtById(Long id) {
        findDebtById(id);
        Integer deletedCount = debtRepository.deleteDebtById(id);

        if (deletedCount > 0)
            return;

        throw new InternalServerError("Failed to delete debt with id " + id);
    }

}
