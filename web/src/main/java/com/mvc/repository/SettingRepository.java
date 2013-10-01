package com.mvc.repository;

import com.mvc.model.Setting;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SettingRepository extends BaseRepository<Setting> {
    public List<Setting> getSettings() {
        return super.find("from Setting");
    }

    public Setting getSetting(String key) {
        List<Setting> query = super.find("from Setting where key=?", new Object[]{key});
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    public void setSetting(String key, String value) {
        Setting setting = getSetting(key);
        if (setting != null) {
            setting.setValue(value);
            super.update(setting);
        }
    }
}
