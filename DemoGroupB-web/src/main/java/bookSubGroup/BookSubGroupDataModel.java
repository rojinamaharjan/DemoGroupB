/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookSubGroup;

import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rojin
 */
@ManagedBean
@SessionScoped

@Getter
@Setter
public class BookSubGroupDataModel implements Serializable {
     private BookSubGroupDto bookSubGroupDto;
     private List<BookGroupDto> bookGroupDtoListForDropDown;
     private List<String> bookGroupDtoNameList;
     private List<BookSubGroupDto> bookSubGroupDtoList;
     private boolean initEdit;

    public BookSubGroupDto getBookSubGroupDto(){
        return Optional.ofNullable(bookSubGroupDto).orElse(new BookSubGroupDto());
    }

}
