package xyz.amazingxu.wxblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import xyz.amazingxu.wxblog.domain.UserDO;
import xyz.amazingxu.wxblog.dto.UserDTO;

import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:15
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * DO转DTO
     * @param userDO
     * @return
     */
    @Mappings({@Mapping(source = "id",target = "id")})
    public UserDTO from(UserDO userDO);

    /**
     * DTO转DO
     * @param userDTO
     * @return
     */
    public UserDO to(UserDTO userDTO);

    /**
     * List<DO>转List<DTO>
     * @param userDOS
     * @return
     */
    public List<UserDTO> from(List<UserDO> userDOS);

    /**
     * List<DTO>转List<DO>
     * @param userDTOS
     * @return
     */
    public List<UserDO> to (List<UserDTO> userDTOS);



}