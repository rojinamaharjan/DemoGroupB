/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;

import com.admin.dto.BookSubGroupDto;
import com.payrollSystem.entity.common.BookSubGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class BookSubGroupMapper extends AbstractProfileMapper {
    public static BookSubGroupDto convertToDto(BookSubGroup bookSubGroup){
        BookSubGroupDto bookSubGroupDto = new BookSubGroupDto();
        convertCommonParameters(bookSubGroupDto, bookSubGroup);
        bookSubGroupDto.setBookGroupDto(BookGroupMapper.convertToDto(bookSubGroup.getBookGroup()));
        return bookSubGroupDto;
    }
    
    public static List<BookSubGroupDto> convertToDtos(List<BookSubGroup>bookSubGroups){
        return bookSubGroups.stream().map(BookSubGroupMapper::convertToDto).collect(Collectors.toList());
    }
}
