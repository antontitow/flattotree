package ru.tobject.t;

/**
 * @autor : Anton Titov {@literal antontitow@bk.ru}
 * @created : 22.06.2023, 15:09
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class OrganizationWrapper {

    public static class OrganizationEntity {
        public Long id;
        public Long parentId;
        public String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "OrganizationEntity{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public class OrganizationDto {
        public Long id;
        public String name;
        public List<OrganizationDto> childs;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<OrganizationDto> getChilds() {
            return childs;
        }

        public void setChilds(List<OrganizationDto> childs) {
            this.childs = childs;
        }

        @Override
        public String toString() {
            return "OrganizationDto{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", childs=" + childs +
                    '}';
        }
    }

    public List<OrganizationDto> mapper(List<OrganizationEntity> entities) {

        entities.stream().forEach(t -> System.out.println(t));
        List<OrganizationDto> roots = entities
                .stream()
                .filter(t -> t.getParentId() == null)
                .map(this::mapper).
                collect(Collectors.toList());

        return findChildrens(roots, entities);

    }

    private List<OrganizationDto> findChildrens(List<OrganizationDto> dtoList, List<OrganizationEntity> entities) {

        return dtoList.stream().map(dto -> {
            OrganizationDto localDto = dto;
            List<OrganizationDto> childrens = entities.stream()
                    .filter(entity -> entity.getParentId() == dto.getId())
                    .map(this::mapper)
                    .collect(Collectors.toList());

            if (childrens == null || childrens.isEmpty()) {
                return dto;
            } else {
                localDto.setChilds(findChildrens(childrens, entities));

                return localDto;
            }
        }).collect(Collectors.toList());
    }


    public OrganizationDto mapper(OrganizationEntity entity) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(entity.getId());
        organizationDto.setName(entity.getName());
        organizationDto.setChilds(new ArrayList<>());

        return organizationDto;

    }
}

//
//row1: id=1, parentId=null, name="RTK"
//row2: id=2, parentId=1, name="Sales"
//row3: id=3, parentId=2, name="Marketing"
//row3: id=4, parentId=1, name="Finance"
//row5: id=5, parentId=null, name="Sber"
//[
//  {
//    "id": 1,
//    "name": "Rostelecom",
//    "childs": [
//      {
//        "id": 2,
//        "name": "Sales",
//        "childs": [
//          {
//            "id": 3,
//            "name": "Marketing",
//            "childs": [
//            ]
//          }
//        ]
//      },
//      {
//        "id": 4,
//        "name": "Finance",
//        "childs": []
//      }
//    ]
//  },
//  {
//    "id": 5,
//    "name": "SBER",
//    "childs": []
//  }
//]

