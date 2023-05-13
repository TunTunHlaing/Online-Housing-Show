package com.example.onlinehousingshowdemo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.onlinehousingshowdemo.entity.Housing;
import com.example.onlinehousingshowdemo.entity.Owner;
import com.example.onlinehousingshowdemo.repo.HousingRepo;
import com.example.onlinehousingshowdemo.repo.OwnerRepo;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@EnableWebMvc
public class OnlineHousingShowDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHousingShowDemoApplication.class, args);
    }

    private final OwnerRepo ownerRepo;
    private final HousingRepo housingRepo;
    private final PasswordEncoder encoder;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //@PostConstruct
    public void initUsers() {

       Owner owner1 = new Owner(
               "mary",
               "mary@gmail.com",
               encoder.encode("12345"),
               LocalDate.now(),
               LocalDate.now()
       );

       Owner owner2 = new Owner(
               "potter",
               "potter@gmail.com",
               encoder.encode("12345"),
               LocalDate.now(),
               LocalDate.now()
       );

       Housing housing1 = new Housing(
               "Housing1",
               "Yangon",
               4, 5, 6,
               5000.0,
               LocalDate.now(), LocalDate.now()
       );

       Housing housing2 = new Housing(
               "Housing2",
               "Yangon",
               5, 5, 6,
               5300.0,
               LocalDate.now(), LocalDate.now()
       );

       Housing housing3 = new Housing(
               "Housing3",
               "Yangon",
               4, 5, 7,
               5100.0,
               LocalDate.now(), LocalDate.now()
       );

       Housing housing4 = new Housing(
               "Housing4",
               "Yangon",
               4, 4, 6,
               4950.0,
               LocalDate.now(), LocalDate.now()
       );

       Housing housing5 = new Housing(
               "Housing5",
               "Yangon",
               4, 5, 6,
               5000.0,
               LocalDate.now(), LocalDate.now()
       );
       Housing housing6 = new Housing(
               "Housing6",
               "Yangon",
               5, 5, 6,
               5500.0,
               LocalDate.now(), LocalDate.now()
       );
       Housing housing7 = new Housing(
               "Housing7",
               "Yangon",
               4, 5, 6,
               5000.0,
               LocalDate.now(), LocalDate.now()
       );
       Housing housing8 = new Housing(
               "Housing8",
               "Yangon",
               4, 4, 6,
               4900.0,
               LocalDate.now(), LocalDate.now()
       );
       Housing housing9 = new Housing(
               "Housing9",
               "Yangon",
               4, 5, 6,
               5100.0,
               LocalDate.now(), LocalDate.now()
       );
       Housing housing10 = new Housing(
               "Housing10",
               "Yangon",
               4, 5, 6,
               5200.0,
               LocalDate.now(), LocalDate.now()
       );

       List<Housing> housings1 = List.of(housing1, housing2,
               housing3, housing4, housing5, housing7, housing6);

       List<Housing> housings2 = List.of(housing8, housing9, housing10);
       housings1.forEach(System.out::println);
       // 7 housings to owner 1
       housings1.forEach(h -> owner1.addHousing(h));
       //3 housings to owner 2
       housings2.forEach(h -> owner2.addHousing(h));

       ownerRepo.save(owner1);
       ownerRepo.save(owner2);
       housings1.forEach(h -> housingRepo.save(h));
       housings2.forEach(h -> housingRepo.save(h));

   }

}
