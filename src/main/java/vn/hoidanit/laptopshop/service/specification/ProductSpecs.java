package vn.hoidanit.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecs {
        
    //filter logic like statement
    //when we have only a function we can use lambda 




    public static  Specification<Product> nameLike(String name){
        return (root, query, criteriaBuilder) 
      -> criteriaBuilder.like(root.get(Product_.NAME), "%"+name+"%");
    }


    public Specification queryByName(String name){
        return (root,query,criteriaBuilder) -> 
                criteriaBuilder.like(root.get(Product_.NAME), "%"+name+"%");

    }

    // query by min max condition
    public static Specification queryProductWithMinPriceConditions(double minPriceConditions){

        return (root,query,criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE), minPriceConditions);
    }
    public static Specification queryProductWithMaxPriceConditions(double maxPriceConditions){

        return (root,query,criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Product_.PRICE), maxPriceConditions);
    }

    // query by range of price

    public static Specification queryByRangeOfPrice(double startVl,double endVl){
        return (root,query,criteriaBuilder) -> criteriaBuilder.and(
            criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.price), startVl),
            criteriaBuilder.lessThanOrEqualTo(root.get(Product_.price), endVl)
        );
    }
    // public static Specification queryByRangeOfPrice(double startVl,double endVl){
    //     return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), startVl, endVl);
    // }


    // query by some range of price 

    public static Specification queryByRangesOfPrice(double startVl1,double endVl1,double startVl2,double endVl2){

        return (root,query,criteriaBuilder) -> criteriaBuilder.or(
            criteriaBuilder.between(root.get(Product_.PRICE), startVl1, endVl1),
            criteriaBuilder.between(root.get(Product_.PRICE), startVl2, endVl2)
        );

    }
    // query by factory name

    public static Specification queryByFactoryName(String factoryName){
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.FACTORY),factoryName);
    }
    public static Specification queryByFactoryNameList(List<String> factoryNameList){
        return (root,query,criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factoryNameList);
    }
    public static Specification queryByTargetNameList(List<String> targetNameList){
        return (root,query,criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.TARGET)).value(targetNameList);
    }
    // public static Specification queryByFactoryNameList(List<String> factoryNameList){
    //     return (root,query,criteriaBuilder) -> root.get(Product_.FACTORY).in(factoryNameList);

    // }
    
    


}
