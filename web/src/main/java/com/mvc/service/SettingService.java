package com.mvc.service;

import com.mvc.model.Setting;
import com.mvc.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;
    private Map<String, String> settings;

    public Map<String, String> getSettings() {
        if (settings == null) {
            settings = new HashMap<String, String>();
            List<Setting> settingList = settingRepository.getSettings();
            for (Setting setting : settingList) {
                settings.put(setting.getKey(), setting.getValue());
            }
        }
        return settings;
    }

    public void setSettings(String key, String value) {
        settingRepository.setSetting(key, value);
        settings.put(key, value);
    }

    public String getSetting(String key) {
        if (settings == null) {
            getSettings();
        }
        if (settings.containsKey(key))
            return settings.get(key);
        else
            return null;
    }
}
