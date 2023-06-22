package com.wsleli.mapper;

import com.wsleli.pojo.Brand;
import com.wsleli.pojo.SearchBrand;
import com.wsleli.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    //Brand-------------------------------------------------------------------Brand
    List<Brand> selectAll();

    void add(Brand brand);

    void update(Brand brand);

    void delete(int id);

    void deleteS(@Param("ids") int[] ids);

    // 条件查询(and)
    List<Brand> search(SearchBrand searchBrand);

    // 条件查询(or)
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("brand") Brand brand);

    int selectTotalCountByCondition(Brand brand);

    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    int selectTotalCount();

    //User-------------------------------------------------------------------User

    List<User> selectAllUser();
}
