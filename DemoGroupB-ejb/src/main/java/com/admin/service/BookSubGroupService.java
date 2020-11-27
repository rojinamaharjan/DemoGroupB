/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.service;

import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface BookSubGroupService {
    
    boolean createBookSubGroup(BookSubGroupDto bookSubGroupDto);
    boolean updateBookSubGroup(BookSubGroupDto bookSubGroupDto);
    boolean deleteBookSubGroup(BookSubGroupDto bookSubGroipDto);
    List<BookSubGroupDto>findByBookGroupId(BookGroupDto bookGroup);
    List<BookSubGroupDto>findAllBookSubGroup();
    List<BookGroupDto>findBooksGroupForDropDown();
}
