package com.mou.election;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 沈林强(四笠) on 2021/7/2.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/2
 */
@RestController
@RequestMapping("/")
public class IndexController {


    @RequestMapping("index")
    public String index(){
        return "success";
    }
}
