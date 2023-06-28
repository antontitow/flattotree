package ru.tobject.t;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TApplication {

    public static void main(String[] args) {
        OrganizationWrapper organizationWrapper = new OrganizationWrapper();

        OrganizationWrapper.OrganizationEntity organizationEntity1 = new OrganizationWrapper.OrganizationEntity();
        organizationEntity1.setId(1L);
        organizationEntity1.setParentId(null);
        organizationEntity1.setName("RTK");
        OrganizationWrapper.OrganizationEntity organizationEntity2 = new OrganizationWrapper.OrganizationEntity();
        organizationEntity2.setId(2L);
        organizationEntity2.setParentId(1L);
        organizationEntity2.setName("Sales");
        OrganizationWrapper.OrganizationEntity organizationEntity3 = new OrganizationWrapper.OrganizationEntity();
        organizationEntity3.setId(3L);
        organizationEntity3.setParentId(2L);
        organizationEntity3.setName("Marketing");
        OrganizationWrapper.OrganizationEntity organizationEntity4 = new OrganizationWrapper.OrganizationEntity();
        organizationEntity4.setId(4L);
        organizationEntity4.setParentId(1L);
        organizationEntity4.setName("Finance");
        OrganizationWrapper.OrganizationEntity organizationEntity5 = new OrganizationWrapper.OrganizationEntity();
        organizationEntity5.setId(5L);
        organizationEntity5.setParentId(null);
        organizationEntity5.setName("Sber");
        List<OrganizationWrapper.OrganizationEntity> list = Arrays.asList(organizationEntity1,organizationEntity2,organizationEntity3,organizationEntity4,organizationEntity5);

       List<OrganizationWrapper.OrganizationDto> organizationDtoList = organizationWrapper.mapper(list);
       System.out.println(organizationDtoList);
//        .stream().forEach(dto->System.out.println(dto));

//        SpringApplication.run(TApplication.class, args);
//        System.out.println("CHERRY PIIIICK");
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
//        System.out.println(dateFormat.format(new Date("Wed Feb 29 00:00:00 UTC 1984")));
//        Integer hash = 118 & 15;
//        System.out.println(hash);
//        HashMap<String, String> mapa = new HashMap<>();
//        mapa.put(null,"1");
//        System.out.println(mapa.get(null));
//        mapa.put(null,"2");
//        mapa.put("0","3");
//        System.out.println(mapa.get(null));
//        System.out.println(mapa.get(0));

    }
}
