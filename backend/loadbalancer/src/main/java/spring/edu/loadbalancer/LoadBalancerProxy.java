package spring.edu.loadbalancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import spring.edu.mongstudent.Student;

@FeignClient(name = "mong-student-service")
@LoadBalancerClient(name = "mong-student-service",configuration=LoadBalancerConfiguration.class)
public interface LoadBalancerProxy {
   
    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody Student student);
}
