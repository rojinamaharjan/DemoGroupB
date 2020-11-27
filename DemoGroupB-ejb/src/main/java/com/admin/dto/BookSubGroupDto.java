/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dto;

import com.admin.dto.abstracts.AbstractCodeDto;
import java.util.Optional;

import lombok.*;
/**
 *
 * @author Administrator
 */
@Getter
@Setter

public class BookSubGroupDto extends AbstractCodeDto{
    private BookGroupDto bookGroupDto;
    
    public BookSubGroupDto(){
        this.bookGroupDto = Optional.ofNullable(bookGroupDto).orElse(new BookGroupDto());  
    }
}
