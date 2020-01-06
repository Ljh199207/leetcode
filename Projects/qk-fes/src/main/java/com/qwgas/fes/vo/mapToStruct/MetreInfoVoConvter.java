package com.qwgas.fes.vo.mapToStruct;

import com.qwgas.fes.vo.MetreInfoVo;
import com.qwgas.fes.vo.dcc.SearchMeter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MetreInfoVoConvter {
    @Mappings({
            @Mapping(source = "meterUserNo",target = "userNo",defaultValue = ""),
            @Mapping(source = "installAddress",target = "addrDes",defaultValue = ""),
            @Mapping(source = "installDate",target = "setUpTime",dateFormat = "yyyy-mm-dd HH24:MM:SS"),
            @Mapping(source = "standardModeAmount",target = "enableMeterReading")
    })
    MetreInfoVo domain2dto(SearchMeter searchMeter);
}
