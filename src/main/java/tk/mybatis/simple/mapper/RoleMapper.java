package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {


    /**
     * 查询全部角色对应的权限
     * @return
     */
    List<SysRole> selectAllRoleAndPrivileges();

    /**
     * 根据用户Id获取用户的角色信息
     * @return
     */
    List<SysRole> selectRoleByUserIdChoose(Long userId);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    SysRole selectById(long id);

    /**
     * 更新角色
     * @param role
     */
    void updateById(SysRole role);

    List<SysRole> selectAll();

    List<SysRole> selectAll(RowBounds rowBounds);
    List selectAllMap(RowBounds rowBounds);
}
