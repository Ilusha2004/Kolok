package com.example.restservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.exception.NotFoundException;
import com.fibonachi.Fib_1;
import com.fibonachi.Fib_2;

@RestController
@RequestMapping("fib")
public class fibApplication {

    private int counter = 0;
    Fib_1 fib_1;
    Fib_2 fib_2;

    public ArrayList<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("fib", "1"); put("amount: ", "1");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    public Map<String, String> getMessage(@PathVariable String fib){
        return messages.stream()
            .filter(messages -> messages.get("fib").equals(fib))
            .findFirst()
            .orElseThrow(NotFoundException::new);
    }

    // @GetMapping("/factN")
    // public long fact(@RequestParam(value = "number", defaultValue = "0") int n){
    //     return fib_1.FactNaive((int) n);
    // }

    // @GetMapping("/factTillN")
    // public ArrayList<Long> facts(@RequestParam(value = "number", defaultValue = "0") int n){
    //     ArrayList<Long> res = new ArrayList<>();
    //     long result=1;
    //     if(n==0){
    //         res.add(1L);
    //         return res;
    //     }
    //     for(int i=1; i<=n; i++){
    //         result*=i;
    //         res.add(result);
    //     }
    //     return res;
    // }


    @GetMapping("{fib}")
    public Map<String, String> getOne(@PathVariable String fib){
        return getMessage(fib);
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message){
        message.put("fib", String.valueOf(counter++));
        messages.add(message);
        return message;
    }
    
    @PutMapping("{fib}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
        Map<String, String> messageFromDb = getMessage(message.get("fib"));
        messageFromDb.putAll(message);
        messageFromDb.put("fib", id);
        return messageFromDb;
    }

    @DeleteMapping("{fib}")
    public void delete(@PathVariable String fib){
        Map<String, String> message = getMessage(fib);
        messages.remove(message);
    }
  
}
