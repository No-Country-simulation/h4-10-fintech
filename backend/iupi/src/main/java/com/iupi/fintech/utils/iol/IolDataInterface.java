package com.iupi.fintech.utils.iol;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.models.generic.ProductoFCI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IolDataInterface {

   List<ProductoFciDto> getIolData(String userIupi);

   ProductoFCI getIolDataBySimbolo(String user, String simbolo);
}
