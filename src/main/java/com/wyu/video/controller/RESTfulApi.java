package com.wyu.video.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-06-18 15:33
 */
@RestController
public class RESTfulApi {

    private final Map<Integer, Map<String, Object>> dataMap;

    public RESTfulApi() {
        dataMap = new HashMap<>();
        for (int i = 1; i < 3; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name_" + i);
            dataMap.put(i, data);
        }
    }

    @GetMapping("/objects/{id}")
    public Map<String ,Object> getData(@PathVariable("id") Integer id) {
        return dataMap.get(id);
    }

    @DeleteMapping("/objects/{id}")
    public String deleteData(@PathVariable("id") Integer id){
        dataMap.remove(id);
        return "delete success";
    }

    @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data){
        Integer[] ids = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(ids);
        int nextId = ids[ids.length - 1] + 1;
        dataMap.put(nextId, data);
        return "post success";
    }

    @PutMapping("/objects")
    public String putData(@RequestBody Map<String, Object> data){
        Integer id = Integer.valueOf(String.valueOf(data.get("id")));
        if (dataMap.containsKey(id)) {
            dataMap.put(id, data);
        } else {
            Integer[] ids = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(ids);
            int nextId = ids[ids.length - 1] + 1;
            dataMap.put(nextId, data);
        }
        return "put success";
    }
}
