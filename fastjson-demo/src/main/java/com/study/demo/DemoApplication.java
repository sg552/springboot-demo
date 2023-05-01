package com.study.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @GetMapping("/hello")
  public String hello(@RequestParam(value="name", defaultValue="lueleulue") String name){
    return String.format("Hello %s! ", name);
  }

  @GetMapping("/user_to_json")
  public String user_to_json(@RequestParam String name, @RequestParam int age){
    User user = new User();
    user.setAge(age);
    user.setName(name);

    String result = JSON.toJSONString(user);

    return result;
  }

  @GetMapping("/json_to_user")
  public String json_to_user(@RequestParam String json){
    String rawString = "{\"@type\":\"com.study.demo.User\",\"name\":\"fromJsonJim\",\"age\":33}";
    System.out.println(JSON.parse(rawString));
    return "ok, check the console";
  }


  @PostMapping("/json_to_user2")
  public String json_to_user2(@RequestParam String json){

    System.out.println(JSON.parse(json));
    User user = (User)JSON.parse(json);

    System.out.println(user.getName());
    return "check the console";
  }
}
