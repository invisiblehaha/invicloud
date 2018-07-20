package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.mapper.ProductMapper;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.pojo.vo.SingProductAnalyVO;
import cn.vision.invicloud.support.service.IProductService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.List;
/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;



    //加的
    @Override
    public Integer deleteByProductId(Integer productId) {

        return productMapper.deleteByProductId(productId);
    }

  


    @Override
    public ProductVO getById(Integer productId) {
        return productMapper.getById(productId);
    }

    @Override
    public BasePageDTO<Product> list(PageInfo pageInfo, String search) {
        Page<Product> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Product> products = productMapper.list( pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<Product>(pageInfo, products);
    }
    @Override
    public Integer insertInto (String productName, BigDecimal productPrice, Integer stock)
    {

        return productMapper.insertInto(productName,productPrice,stock);
    }




    public List<ProductVO> getAllProduct(){
        List<ProductVO> allProduct=productMapper.getAllProduct();
        return allProduct;
    }

    public boolean ifIn(SingProductAnalyVO item, List<SingProductAnalyVO> list){
        boolean flag=false;
        for (SingProductAnalyVO s:list) {
            if(item.getCreateTime().equals(s.getCreateTime())){
                s.setBuyAmount(item.getBuyAmount());
                flag=true;
            }
        }
        return flag;
    }
    public void singProduct(Integer productId){
        List<SingProductAnalyVO> rawlist = productMapper.analySing(productId);
        List<SingProductAnalyVO> newlist=new ArrayList<SingProductAnalyVO>();
        Date today=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String curDate=df.format(today)+" 00:00:00";
        SingProductAnalyVO sing=new SingProductAnalyVO(curDate,0);
        newlist.add(sing);
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        for(int i=0;i<30;i++){
            theCa.add(theCa.DATE, -1);
            Date item=theCa.getTime();
            String itemDate=df.format(item)+" 00:00:00";
            SingProductAnalyVO singProductAnalyVO=new SingProductAnalyVO(itemDate,0);
            newlist.add(singProductAnalyVO);
        }
        for (SingProductAnalyVO singProductAnalyVO:rawlist) {
            ifIn(singProductAnalyVO,newlist);
        }
        File file = new File("predict_single_product_data.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        Iterator<SingProductAnalyVO> iter = newlist.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("create_time\tbuy_amount");
            bw.newLine();
            while (iter.hasNext()) {
                bw.write(iter.next().toString());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
