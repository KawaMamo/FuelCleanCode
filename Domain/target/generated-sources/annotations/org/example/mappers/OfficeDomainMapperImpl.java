package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.model.Office;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-06T21:31:56+0300",
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

        return officeResponse;
    }
}
