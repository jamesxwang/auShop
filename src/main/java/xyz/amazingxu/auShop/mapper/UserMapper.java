package xyz.amazingxu.auShop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import xyz.amazingxu.auShop.domain.UserDO;
import xyz.amazingxu.auShop.dto.UserDTO;

import java.util.List;

/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/5/22 21:15
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * DO to DTO
     * @param userDO
     * @return
     */
    @Mappings({@Mapping(source = "id",target = "id")})
    public UserDTO from(UserDO userDO);

    /**
     * DTO to DO
     * @param userDTO
     * @return
     */
    public UserDO to(UserDTO userDTO);

    /**
     * List<DO> to List<DTO>
     * @param userDOS
     * @return
     */
    public List<UserDTO> from(List<UserDO> userDOS);

    /**
     * List<DTO> to List<DO>
     * @param userDTOS
     * @return
     */
    public List<UserDO> to (List<UserDTO> userDTOS);

}
