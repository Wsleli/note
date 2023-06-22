package com.wsleli.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsleli.pojo.Brand;
import com.wsleli.pojo.PageBean;
import com.wsleli.pojo.SearchBrand;
import com.wsleli.service.BrandService;
import com.wsleli.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        String jsonString = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        SearchBrand searchBrand = JSON.parseObject(params, SearchBrand.class);
        if ("".equals(searchBrand.getBrandName()) ||
                "".equals(searchBrand.getCompanyName()) ||
                searchBrand.getStatus() == null) {
            resp.getWriter().write("error");
            return;
        }

        List<Brand> brands = brandService.search(searchBrand);

        String jsonString = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);
        if ("".equals(brand.getBrandName()) ||
                "".equals(brand.getCompanyName()) ||
                brand.getOrdered() == null) {
            resp.getWriter().write("error");
            return;
        }

        brandService.add(brand);

        resp.getWriter().write("success");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);
        if ("".equals(brand.getBrandName()) ||
                "".equals(brand.getCompanyName()) ||
                brand.getOrdered() == null) {
            resp.getWriter().write("error");
            return;
        }

        brandService.update(brand);

        resp.getWriter().write("success");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

//        JSONObject jsonObject = JSON.parseObject(params);
//        int id = jsonObject.getIntValue("id");
        int id = JSON.parseObject(params, int.class);
        if (id < 0) {
            resp.getWriter().write("error");
            return;
        }

        brandService.delete(id);

        resp.getWriter().write("success");
    }

    public void deleteS(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        int[] ids = JSON.parseObject(params, int[].class);
        if (ids.length == 0) {
            resp.getWriter().write("error");
            return;
        }

        brandService.deleteS(ids);

        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        JSONObject jsonObject = JSON.parseObject(params);

        int currentPage = jsonObject.getIntValue("currentPage");
        int pageSize = jsonObject.getIntValue("pageSize");

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        String jsonString = JSON.toJSONString(pageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        JSONArray jsonArray = JSON.parseArray(params);

        String brand_params = jsonArray.getJSONObject(0).toString();
        Brand brand = JSON.parseObject(brand_params, Brand.class);

        JSONObject jsonObject = jsonArray.getJSONObject(1);
        int currentPage = jsonObject.getIntValue("currentPage");
        int pageSize = jsonObject.getIntValue("pageSize");

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        String jsonString = JSON.toJSONString(pageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

}
