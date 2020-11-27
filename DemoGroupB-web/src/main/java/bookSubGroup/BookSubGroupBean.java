/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookSubGroup;

import com.admin.dto.AdminDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.service.BookSubGroupService;
import com.admin.util.Utility;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import lombok.var;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
/**
 *
 * @author rojin
 */
@Getter
@Setter

@ManagedBean
@SessionScoped
public class BookSubGroupBean implements Serializable{
    
   @ManagedProperty(value="#{bookSubGroupDataModel}")
   private BookSubGroupDataModel bookSubGroupDataModel;
   
   @EJB
   private BookSubGroupService bookSubGroupService;
   private AdminDto adminDto;
   
   public void init(){
       adminDto = new AdminDto();
       adminDto.setId(1L);
   }
   
   public String save(){
       bookSubGroupDataModel.getBookSubGroupDto().setCreatedByAdminDto(adminDto);
       var response = bookSubGroupService.createBookSubGroup(bookSubGroupDataModel.getBookSubGroupDto());
       if(response)
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved Successfully",null));
       return navigateToPage();
   }
   
   public String update(){
       bookSubGroupDataModel.getBookSubGroupDto().setUpdatedByAdminDto(adminDto);
       var response = bookSubGroupService.updateBookSubGroup(bookSubGroupDataModel.getBookSubGroupDto());
       if (response)
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully",null));
       return navigateToPage(); 
   }
   
   public String saveUpdate(){
       return bookSubGroupDataModel.getBookSubGroupDto().getId() == null ? save():update();
   }
   public String delete(){
       bookSubGroupDataModel.getBookSubGroupDto().setDeletedByAdminDto(adminDto);
       var response = bookSubGroupService.deleteBookSubGroup(bookSubGroupDataModel.getBookSubGroupDto());
       if (response)
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Deleted Successfully",null));
       return navigateToPage();
   }
   
   public void loadBookGroupDropDownMenu(){
       bookSubGroupDataModel.setBookGroupDtoListForDropDown(bookSubGroupService.findBooksGroupForDropDown());
   }
   
   public String initCreate(){
       bookSubGroupDataModel.setBookSubGroupDto(new BookSubGroupDto());
       loadBookGroupDropDownMenu();
       bookSubGroupDataModel.setInitEdit(true);
       return returnToPage();
   }
   
   public String returnToPage(){
       return "bookSubGroup.xhtml?faces-redirect=true";
   }
   
   public String navigateToPage(){
       Utility.removeSessionBeanJSFDataModelObject("bookSubGroupDataModel");
       bookSubGroupDataModel = (BookSubGroupDataModel) Utility.getSessionObject("bookSubGroupDataModel");
       bookSubGroupDataModel.setBookSubGroupDtoList(bookSubGroupService.findAllBookSubGroup());
       return returnToPage();
   }
   
   public String initEdit(){
       bookSubGroupDataModel.setInitEdit(true);
       return returnToPage();
   }
}
