package com.iupi.fintech.services;

import com.iupi.fintech.dtos.rxFinanciera.RxFinancieraDto;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;

public interface RxFinaciera extends GenericServiceDto< RxFinanciera, RxFinancieraDto> {

    public void updateRxFinanciera(RxFinancieraDto requestDTO, Long id);
}
