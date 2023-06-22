package com.wsleli.service;

import com.wsleli.pojo.Brand;
import com.wsleli.pojo.PageBean;
import com.wsleli.pojo.SearchBrand;
import com.wsleli.pojo.User;

import java.util.List;

public interface BrandService {
    /**
     * 查询数据
     *
     * @return
     */
    List<Brand> selectAll();

    /**
     * 搜索数据（and）
     *
     * @param searchBrand
     * @return
     */
    List<Brand> search(SearchBrand searchBrand);

    /**
     * 添加数据
     *
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改数据
     *
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除数据
     *
     * @param id
     */
    void delete(int id);

    /**
     * 批量删除数据
     *
     * @param ids
     */
    void deleteS(int[] ids);

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 搜索数据（or）
     *
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    List<User> selectAllUser();
}
