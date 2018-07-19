package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.analysis.RunPython;
import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.analysis.impl.RunPythonImpl;
import cn.vision.invicloud.support.mapper.OrderMapper;
import cn.vision.invicloud.support.pojo.vo.*;
import cn.vision.invicloud.support.service.IOrderAnalyService;
import cn.vision.invicloud.support.utils.MoveFileUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class OrderAnalyServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderAnalyService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderAnalyVO> list1() {
        return orderMapper.analylist();
    }


    @Override
    public void toTxt() throws IOException, InterruptedException {
        List<OrderAnalyVO> list = orderMapper.analylist();
        File file = new File("1.txt");
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
        Iterator<OrderAnalyVO> iter = list.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
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
        RunPython runPython = new RunPythonImpl();
        String pythonPath2 = OrderAnalyServiceImpl.class.getClassLoader().getResource("python/analysis_4.py").getPath().substring(1);
        String pythonPath = OrderAnalyServiceImpl.class.getClassLoader().getResource("python/analysis_3.py").getPath().substring(1);
        String txtPath = System.getProperty("user.dir");
        runPython.getRecommedationByData(pythonPath, txtPath);
        runPython.getRecommedationByData2(pythonPath2, txtPath);
    }
    @Override
    public void toTxt2() throws IOException {
        List<LevelAnalyVO> list = orderMapper.analylist2();
        File file = new File("2.txt");
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
        Iterator<LevelAnalyVO> iter = list.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
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
        RunPython runPython = new RunPythonImpl();
        String pythonPath = OrderAnalyServiceImpl.class.getClassLoader().getResource("python/analysis_2.py").getPath().substring(1);
        String txtPath = System.getProperty("user.dir");
        runPython.getRecommedationByData2(pythonPath, txtPath);
    }
    @Override
    public void buyAmount() throws IOException {
        List<BuyVO> list = orderMapper.analyBuy();
        File file = new File("order_by_buy_amount.txt");
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
        Iterator<BuyVO> iter = list.iterator();
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
        RunPython runPython = new RunPythonImpl();
        String filePath = System.getProperty("user.dir");
        //runPython.test(filePath);
    }

    @Override
    public void payAmount() throws IOException {
        List<PayVO> list = orderMapper.analyPay();
        File file = new File("order_by_pay_amount.txt");
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
        Iterator<PayVO> iter = list.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("create_time\tpay_amount");
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
        RunPython runPython = new RunPythonImpl();
        String filePath = System.getProperty("user.dir");
        //runPython.test(filePath);
    }
    @Override
    public void rfm() throws IOException {
        List<RFMVO> list = orderMapper.rfm();
        File file = new File("RFM_data.txt");
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
        Iterator<RFMVO> iter = list.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("customer_id\trecent\tmonetary\tfrequency");
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
        RunPython runPython = new RunPythonImpl();
        String pythonPath = OrderAnalyServiceImpl.class.getClassLoader().getResource("python/analysis_RFM.py").getPath().substring(1);
        String txtPath = System.getProperty("user.dir");
        runPython.getRecommedationByData2(pythonPath, txtPath);
    }

    @Override
    public void removeAllData() {
        String oldfile = System.getProperty("user.dir")+"\\1.txt";
        String newpath = "src/main/java/cn/vision/invicloud/support/entity/";
        String path = OrderAnalyServiceImpl.class.getResource("").getPath();;
        MoveFileUtils.moveFile(oldfile, newpath);
    }

    @Override
    public List<String> fromTxt(String filename){
        List<String> newList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                newList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newList;
    }
    @Override
    public Integer findInt(String parent){
        String item="";
        parent=parent.trim();
        if(!"".equals(parent)){
            for(int i=0;i<parent.length();i++){
                if(parent.charAt(i)>=48&& parent.charAt(i)<=57){
                    item+=parent.charAt(i);
                }
            }
        }
        return Integer.parseInt(item);
    }
    @Override
    public  List<LevelVO> getLevels(String filename){
        List<LevelVO> levelVOList=new ArrayList<LevelVO>();
        List<String> list=fromTxt(filename);
        list.remove(0);
        for(String str : list) {
            LevelVO levelVO=new LevelVO();
            String[] substrs=str.split("\\,");
            String[] subsubstrs=substrs[2].split("\\:");

            levelVO.setCustomerId(this.findInt(subsubstrs[0]));
            levelVO.setMoney(this.findInt(subsubstrs[1]));
            levelVO.setNum(this.findInt(substrs[3]));


            levelVOList.add(levelVO);
        }

        return levelVOList;
    }
    @Override
    public BasePageDTO<LevelVO> listLevel(String filename,PageInfo pageInfo) {
        List<LevelVO> list=this.getLevels(filename);
        int count=0;
        for (LevelVO o: list) {
            count++;
        }
        pageInfo.setTotal(count);
        return new BasePageDTO<LevelVO>(pageInfo, list);

    }

    @Override
    public List<LikeVO> getLikes(String filename){
        List<LikeVO> likeVOList=new ArrayList<LikeVO>();
        List<String> list=fromTxt(filename);
        list.remove(0);
        for(String str : list) {
            LikeVO likeVO=new LikeVO();
            String[] substrs=str.split("\\,");
               List<Integer> subList=new ArrayList<Integer>();
               likeVO.setCustomerId(this.findInt(substrs[1]));
            if(!substrs[2].equals("[]")){
                for(int i=2;i<substrs.length;i++){
                        subList.add(this.findInt(substrs[i]));
                }
                likeVO.setLikeList(subList);
            }

                likeVOList.add(likeVO);

        }
       return likeVOList;

    }
    @Override
    public BasePageDTO<LikeVO> listLike(String filename, PageInfo pageInfo){
        List<LikeVO> list=this.getLikes(filename);
        int count=0;
        for (LikeVO o: list) {
            count++;
        }
        pageInfo.setTotal(count);
        return new BasePageDTO<LikeVO>(pageInfo, list);
    }

}
