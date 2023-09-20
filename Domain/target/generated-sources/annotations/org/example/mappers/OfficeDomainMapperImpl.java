package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.model.Office;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-20T23:42:32+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OfficeDomainMapperImpl implements OfficeDomainMapper {

    @Override
    public Office requestToDomain(CreateOfficeRequest request) {
        if ( request == null ) {
            return null;
        }

        Office office = new Office();

        office.setName( request.getName() );

        return office;
    }

    @Override
    public OfficeResponse domainToResponse(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeResponse officeResponse = new OfficeResponse();

        officeResponse.setId( office.getId() );
        officeResponse.setName( office.getName() );
        officeResponse.setCreatedAt( office.getCreatedAt() );
        officeResponse.setUpdateAt( office.getUpdateAt() );

        return officeResponse;
    }

    @Override
    public Office requestToDomain(UpdateOfficeRequest request) {
        if ( request == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( request.getId() );
        office.setName( request.getName() );

        return office;
    }
}
