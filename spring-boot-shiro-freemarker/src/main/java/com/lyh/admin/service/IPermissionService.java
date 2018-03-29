package com.lyh.admin.service;

import com.lyh.admin.service.support.IBaseService;
import com.lyh.admin.vo.ZtreeView;
import com.lyh.admin.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author LYH
 * @since 2016-12-28
 */
public interface IPermissionService extends IBaseService<Permission, Integer> {

    /**
     * 获取角色的权限树
     *
     * @param roleId
     * @return
     */
    List<ZtreeView> tree(int roleId);

    /**
     * 修改或者新增资源
     *
     * @param resource
     */
    void saveOrUpdate(Permission resource);

    /**
     * 关键字分页
     *
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Permission> findAllByLike(String searchText, PageRequest pageRequest);

}
