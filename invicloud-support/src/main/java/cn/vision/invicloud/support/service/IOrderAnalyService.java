package cn.vision.invicloud.support.service;


import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.pojo.vo.LevelVO;
import cn.vision.invicloud.support.pojo.vo.LikeVO;
import cn.vision.invicloud.support.pojo.vo.OrderAnalyVO;
import com.baomidou.mybatisplus.service.IService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface IOrderAnalyService extends IService<Order> {

    List<OrderAnalyVO> list1();

    void toTxt();

    void toTxt2();

    void buyAmount();
    void payAmount();
    void rfm();
    void catAnaly();
    void vipIncrease();
    List<String> fromTxt(String filename);

    List<LevelVO> getLevels(String filename);
    BasePageDTO<LevelVO> listLevel(String filename,PageInfo pageInfo);
    Integer findInt(String parent);
    List<LikeVO> getLikes(String filename);
    BasePageDTO<LikeVO> listLike(String filename,PageInfo pageInfo);

}
