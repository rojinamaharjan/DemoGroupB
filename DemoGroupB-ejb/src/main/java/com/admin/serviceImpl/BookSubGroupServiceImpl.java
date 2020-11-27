/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.dao.AdminDao;
import com.admin.dao.BookGroupDao;
import com.admin.dao.BookSubGroupDao;
import com.admin.dao.StatusDao;
import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.service.BookSubGroupService;

import com.admin.mapper.BookGroupMapper;
import com.admin.mapper.BookSubGroupMapper;

import com.payrollSystem.entity.common.BookSubGroup;
import com.payrollSystem.entity.common.BookGroup;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import com.admin.constant.StatusConstants;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.stream.Collectors;
/**
 *
 * @author Administrator
 */
@Stateless
public class BookSubGroupServiceImpl implements BookSubGroupService {
    @EJB
    private BookSubGroupDao bookSubGroupDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private BookGroupDao bookGroupDao;
    @EJB 
    private AdminDao adminDao;
    
    @Override
    public boolean createBookSubGroup(BookSubGroupDto bookSubGroupDto) {
        return bookSubGroupDao.save(this.convertToBookSubGroup(bookSubGroupDto));
    }

    @Override
    public boolean updateBookSubGroup(BookSubGroupDto bookSubGroupDto) {
        BookSubGroup bookSubGroup = bookSubGroupDao.getById(bookSubGroupDto.getId());
        bookSubGroup.setLastUpdatedDate(new Date());
        bookSubGroup.setUpdatedByAdmin(adminDao.getById(bookSubGroupDto.getUpdatedByAdminDto().getId()));
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setEditedCommonParameters(bookSubGroup, bookSubGroupDto);
        return bookSubGroupDao.modify(bookSubGroup);
    }

    @Override
    public boolean deleteBookSubGroup(BookSubGroupDto bookSubGroupDto) {
        BookSubGroup bookSubGroup = bookSubGroupDao.getById(bookSubGroupDto.getId());
        bookSubGroup.setDeletedDate(new Date());
        bookSubGroup.setDeletedByAdmin(adminDao.getById(bookSubGroupDto.getDeletedByAdminDto().getId()));
        bookSubGroup.setDeletedReason(bookSubGroupDto.getDeletedReason());
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        return bookSubGroupDao.modify(bookSubGroup);

    }

    @Override
    public List<BookSubGroupDto> findByBookGroupId(BookGroupDto bookGroup) {
        return Collections.emptyList();
    }

    @Override
    public List<BookSubGroupDto> findAllBookSubGroup() {
        return bookSubGroupDao.findAll().stream().map(BookSubGroupMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookGroupDto> findBooksGroupForDropDown() {
        return bookGroupDao.findAll().stream().map(BookGroupMapper::convertToDto).collect(Collectors.toList());
    }
    
    private void setEditedCommonParameters(BookSubGroup bookSubGroup,BookSubGroupDto bookSubGroupDto){
        bookSubGroup.setName(bookSubGroupDto.getName());
        bookSubGroup.setDescription(bookSubGroupDto.getDescription());
        
    }
    
    
    protected BookSubGroup convertToBookSubGroup(BookSubGroupDto bookSubGroupDto){
        BookSubGroup bookSubGroup =new BookSubGroup();
        bookSubGroup.setName(bookSubGroupDto.getName());
        bookSubGroup.setDescription(bookSubGroupDto.getDescription());
        bookSubGroup.setCreatedDate(bookSubGroupDto.getCreatedDate());
        bookSubGroup.setLastUpdatedDate(bookSubGroupDto.getLastUpdatedDate());
        bookSubGroup.setDeletedDate(bookSubGroupDto.getDeletedDate());
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        bookSubGroup.setBookGroup(bookGroupDao.getById(bookSubGroupDto.getBookGroupDto().getId()));
        bookSubGroup.setCreatedByAdmin(adminDao.getById(bookSubGroupDto.getCreatedByAdminDto().getId()));
        return bookSubGroup;
    }

    
}
