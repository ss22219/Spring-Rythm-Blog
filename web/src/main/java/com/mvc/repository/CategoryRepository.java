package com.mvc.repository;

import com.mvc.model.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryRepository extends BaseRepository<Category> {
    public List<Category> getCategories() {
        return getSession().createQuery("from Category where parent=null ").list();
    }

    public Category getCategory(int id) {
        List<Category> list = getSession().createQuery("from Category where categoryId=" + id).list();
        return list.size() > 0 ? list.get(0) : null;
    }

    public Map<String, String> getMonthCategory() {
        List<String> strings = getSession().createSQLQuery("select date_format(create_date,'%Y-%m') from article GROUP BY date_format(create_date,'%Y-%m')").list();
        Map<String, String> map = new HashMap<String, String>();
        for (String str : strings) {
            if (str.length() > 0) {
                String year = str.substring(0, str.indexOf('-'));
                String month = str.substring(str.indexOf('-') + 1, str.length());
                map.put(str, year + "年" + month + "月");
            }
        }
        return map;
    }

    public Category getCategoryByName(String name, int type) {
        List<Category> list = getSession().createQuery("from Category where name=? and type=" + type).setParameter(0, name).setMaxResults(1).list();
        return list.size() > 0 ? list.get(0) : null;
    }
}
