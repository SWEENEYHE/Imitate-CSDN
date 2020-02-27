package onmen.springboot.service;

import onmen.springboot.dao.SectionMapper;
import onmen.springboot.domain.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 辅助业务实现类
 */
@Component
public class UtilServiceImpl implements UtilService{

    @Autowired
    public SectionMapper sectionMapper;
    /***
     * 获得所有的文章分类名称及对应的值
     * @return
     */
    @Override
    public List<Map<String,String>> getAllSection() {
        //获得未禁用分类
        List<Section> sections = sectionMapper.findAllByState(1);
        List<Map<String,String>> list = new ArrayList<>();
        for (Section section : sections) {
            Map<String, String> map = new HashMap<>();
            map.put("sid",String.valueOf(section.getSid()));
            map.put("sname",String.valueOf(section.getSname()));
            list.add(map);
        }
        return list;
    }
}
