package com.rsupport.mobile1.test.util

interface Mapper {
    interface DtoToDomain<Dto, Domain> {
        fun map(dto: Dto): Domain
    }

    interface DomainToDto<Dto, Domain> {
        fun map(domain: Domain): Dto
    }
}