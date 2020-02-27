package onmen;

import java.lang.reflect.Constructor;
import java.util.*;

/***
 *工具类
 */
public class Utils {

    /****
     * 获得摘要
     * @param content
     * @return
     */
    public static String getSummary(String content){
        String outcome;
        //0.剔除空格
        outcome = content.replaceAll(" ","");
        //1.剔除html标签
        outcome =  outcome.replaceAll("<[.[^<]]*>","").replaceAll("[//n|//r]","");
        //2.截取部分内容
        outcome = outcome.substring(0,outcome.length()>50?50:outcome.length());
        //3.如果没有内容则直接使用title作为summary
        if(outcome.length()<=0)
            outcome = "纯图片内容博客，无内容展示";
        return outcome;
    }

    /***
     * 分页参数控制
     *
     */

    /****
     *     确保当前页数符合要求
     */
    public static int currentPageControl(Integer currentPage,int pageCount){
        int current;
        //如果current为空则说明第一次访问置为1
        if (currentPage == null)
            current = 1;
        else {
            current = currentPage;
            //如果页数大于总页数则置为最大值pageCount
            if (current > pageCount)
                current = pageCount;
            //如果小于1则置为1
            if (current < 1)
                current = 1;
        }
        return current;
    }

    /**
     * 确保每页数量符合要求
     * @param pageSize
     * @return
     */
    public static int pageSizeControl(Integer pageSize){
        int size;
        //如果分页参数为空则pagesize置为10
        if (pageSize == null)
            size = 10;
        else {
            size = pageSize;
            //如果参数小于1则置为10
            if (size < 1)
                size = 10;
        }
        return size;
    }
    /***
     * 计算分页数量
     */
    public static int getPageTotal(int total,int size){
        //是否整除
        if(total%size==0)
            return total/size;
        //否则商+1
        return total/size+1;
    }

    /***
     * List<Object[]>转换成List<T> 利用反射实现，非自己编写，作为外部代码引用
     * @param objList
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> transObjectsToBean(List<Object[]> objList, Class<T> clz) {
        if(objList==null || objList.size()==0) {
            return null;
        }

        Class<?>[] cz = null;
        Constructor<?>[] cons = clz.getConstructors();
        for(Constructor<?> ct : cons) {
            Class<?>[] clazz = ct.getParameterTypes();
            if(objList.get(0).length == clazz.length) {
                cz = clazz;
                break;
            }
        }

        List<T> list = new ArrayList<T>();
        for(Object[] obj : objList) {
            Constructor<T> cr = null;
            try {
                cr = clz.getConstructor(cz);
                list.add(cr.newInstance(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    /***
     * 非反射指定类型版
     * @param keys
     * @param objects
     * @return
     */
/* public static List<ArticleMin> transObjectsToBean(List<Object[]> objects){
        List<ArticleMin> lists = new ArrayList<>();
        ArticleMin articleMin;
        for (Object[] object : objects) {
            articleMin = new ArticleMin((Integer)object[0],(String)object[1],(String)object[2],(Date)object[3],(Integer)object[4],(String)object[5],(String)object[6],(Integer)object[7],(Integer)object[8],(Integer)object[9]);
            lists.add(articleMin);
        }
        return lists;
    }*/

    /***
     * 将key[] object[]转换成map<key,value>
     * @param keys
     * @param objects
     * @return
     */
    public static Map<String, String> transObjectsToMap(String[] keys,Object[] objects) {
        Map<String, String> map = new HashMap<>();
        for(int i = 0 ; i<keys.length; i++)
        map.put(keys[i],String.valueOf(objects[i]));
        return map;
    }

    /***
     * 获得指定范围内不重复数量的随机数
     * @param s
     * @param e
     * @param num
     * @return
     */
    public static Integer[] getRandom(int s, int e, Integer num) {
        if(e-s<num){
            try {
                throw new Exception("数量大于生成范围异常");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        Integer [] randomArray =  new Integer[num];
        Map<Integer,Integer> flag = new HashMap();
        Random random = new Random();
        int temp;
        for(int i = 0 ; i<num ; ){
            temp = (random.nextInt(e)+s+1)%e;
            if(flag.get(temp)!=null)
                continue;

            flag.put(temp,1);
            randomArray[i] = temp;
            i++;
        }
        return randomArray;
    }
}
