/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookGroupDao;
import com.admin.dao.StatusDao;

import com.admin.dto.BookGroupDto;
import com.admin.dto.CollegeDto;
import com.admin.mapper.BookGroupMapper;
import com.admin.mapper.StatusMapper;
import com.admin.service.BookGroupService;
import com.payrollSystem.entity.common.BookGroup;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Administrator
 */
@Stateless
public class BookGroupServiceImpl  implements BookGroupService{
    @EJB
    private AdminDao adminDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private BookGroupDao bookGroupDao;

    @Override
    public boolean addBookGroup(BookGroupDto bookGroupDto) {
        bookGroupDto.setCreatedDate(new Date());
        return bookGroupDao.save(this.convertToBookGroup(bookGroupDto));
    }

    @Override
    public boolean updateBookGroup(BookGroupDto bookGroupDto) {
        bookGroupDto.setLastUpdatedDate(new Date());
        return bookGroupDao.modify(this.convertToBookGroup(bookGroupDto));
    }

    @Override
    public boolean deleteBookGroup(BookGroupDto bookGroupDto) {
        bookGroupDto.setDeletedDate(new Date());
        return bookGroupDao.delete(bookGroupDto.getId());
    }

    @Override
    public BookGroupDto findBookGroupById(long id) {
        return BookGroupMapper.convertToDto(bookGroupDao.getById(id));
    }

    @Override
    public List<BookGroupDto> findAllBookGroups() {
        return bookGroupDao.findAll().stream().map(BookGroupMapper::convertToDto).collect(Collectors.toList());
    }
   
    public BookGroup convertToBookGroup(BookGroupDto bookGroupDto){
        BookGroup bookGroup = new BookGroup();
        bookGroup.setId(bookGroupDto.getId());
        bookGroup.setName(bookGroupDto.getName());
        bookGroup.setDescription(bookGroupDto.getDescription());
        bookGroup.setCreatedDate(bookGroupDto.getCreatedDate());
        bookGroup.setLastUpdatedDate(bookGroupDto.getLastUpdatedDate());
        bookGroup.setCreatedByAdmin(adminDao.getById(bookGroupDto.getCreatedByAdminDto().getId()));
        bookGroup.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        return bookGroup;
    } 
    
    public BookGroupDto convertToDto(BookGroup bookGroup){
        BookGroupDto bookGroupDto = new BookGroupDto();
        bookGroupDto.setId(bookGroup.getId());
        bookGroupDto.setName(bookGroup.getName());
        bookGroupDto.setDescription(bookGroup.getDescription());
        bookGroupDto.setCreatedDate(bookGroup.getCreatedDate());
        bookGroupDto.setLastUpdatedDate(bookGroup.getLastUpdatedDate());
        bookGroupDto.setDeletedDate(bookGroup.getDeletedDate());
        bookGroupDto.setStatusDto(StatusMapper.convertToDto(bookGroup.getStatus()));
        return bookGroupDto;
    }
}


   
