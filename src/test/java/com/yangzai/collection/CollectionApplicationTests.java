package com.yangzai.collection;

import com.yangzai.collection.entity.Org;
import com.yangzai.collection.entityconfig.BeanConfig;
import com.yangzai.collection.entityconfig.Computer;
import com.yangzai.collection.entity.Person;
import com.yangzai.collection.entity.User;
import com.yangzai.collection.mapper.OrgMapper;
import com.yangzai.collection.mapper.UserMapper;
import com.yangzai.collection.pojo.vo.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User abc = userMapper.selectUser("abc", "123456");
        System.out.println(abc);
    }

    @Test
    public void kk(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AtomicInteger atomicInteger = new AtomicInteger();
            }
        });
    }
    @Test
    public void mm() throws Exception {

        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            File file = new File("C:\\Users\\guozhiyang_vendor\\Documents\\WeChat Files\\All Users\\374d8b8de28c40ef87accbcb373c2ff9.jpg");
            FileInputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[1024];
            int len;
            while ((len=bufferedInputStream.read(bytes))!=-1){
                byteArrayOut.write(bytes,0,len);
            }
            inputStream.close();
            byteArrayOut.close();
            //加载图片
//            bufferImg = ImageIO.read(new File("C:\\Users\\guozhiyang_vendor\\Documents\\WeChat Files\\All Users\\374d8b8de28c40ef87accbcb373c2ff9.jpg"));
//            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("sheet1");
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
            /**
             dx1 - the x coordinate within the first cell.//定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0
             dy1 - the y coordinate within the first cell.//定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0
             dx2 - the x coordinate within the second cell.//定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0
             dy2 - the y coordinate within the second cell.//定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0
             col1 - the column (0 based) of the first cell.//第一个cell所在列，既图片左上角所在列
             row1 - the row (0 based) of the first cell.//图片左上角所在行
             col2 - the column (0 based) of the second cell.//图片右下角所在列
             row2 - the row (0 based) of the second cell.//图片右下角所在行
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 2, 2, (short) 5, 8);
            //插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            File file1 = new File("D:/excel.xls");

            fileOut = new FileOutputStream("D:/excel.xls");
            // 输出文件
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void kj(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        Map<String, Person> bean = annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(bean);
    }

    @Test
    public void ll(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Computer.class);
        Map<String, Person> bean = annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(bean);
    }

    @Test
    public void kl(){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        ReentrantLock lock = new ReentrantLock();
        Condition empty = lock.newCondition();
        Condition full = lock.newCondition();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        Lock writeLock = readWriteLock.writeLock();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        ExecutorService threadPool1 = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

    }



    @Autowired
    private OrgMapper orgMapper;
    @Test
    public void listOrg(){
        List<Org> orgs = orgMapper.selectAll();
        List<Org> orgs1 = toTreeList(orgs, 0);
        System.out.println(orgs1.toString());
    }
    private List<Org> toTreeList(List<Org> childList, Integer parentId) {
        if (childList != null && !childList.isEmpty()) {
            List<Org> treeList = new ArrayList<>();
            Map<Integer, Org> dataMap = new HashMap<>(16);
            for (Org model : childList) {

                if (model.getParentId().equals(parentId)) {
                    treeList.add(model);
                }

                Org parentModel = dataMap.get(model.getParentId());

                if (parentModel != null) {
                    parentModel.getList().add(model);
                }

                dataMap.put(model.getOrgId(), model);
            }

            return treeList;
        }
        return null;
    }
}
