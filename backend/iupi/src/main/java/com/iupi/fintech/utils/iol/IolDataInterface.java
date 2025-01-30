package com.iupi.fintech.utils.iol;

import com.iupi.fintech.models.generic.FondosComunInversion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IolDataInterface {

   List<FondosComunInversion> getIolData(String userIupi);
}
