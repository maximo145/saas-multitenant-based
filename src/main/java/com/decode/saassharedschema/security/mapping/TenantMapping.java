package com.decode.saassharedschema.security.mapping;

import com.decode.saassharedschema.security.domain.entities.Tenant;
import com.decode.saassharedschema.security.service.dto.TenantDto;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TenantMapping extends ModelMapper implements Serializable {

    public TenantMapping() {
        super();
    }

    public List<TenantDto> modelList(List<Tenant> modelList) {
        return modelList.stream().map(item -> this.map(item, TenantDto.class))
                .collect(Collectors.toList());
    }

    public Tenant newModel(TenantDto tenantDto) {
        return this.map(tenantDto, Tenant.class);
    }

    public TenantDto modelDto(Tenant tenantDto) {
        return this.map(tenantDto, TenantDto.class);
    }
}
