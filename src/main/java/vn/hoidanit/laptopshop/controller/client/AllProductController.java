package vn.hoidanit.laptopshop.controller.client;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.dto.ProductCriteriaDTO;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
@RequestMapping
public class AllProductController {
    private final ProductService productService;

    public AllProductController( ProductService productService) {
        this.productService = productService;
    }

    // get all product
    @GetMapping("/products")
    public String getAllProduct(Model model,ProductCriteriaDTO productCriteriaDTO,HttpServletRequest request   )
    {
        int page =1;
        
       
        try {
            page = Integer.parseInt(productCriteriaDTO.getPage().get());
        } catch (Exception e) {
            page = 1;
        }

            Pageable pageable =  PageRequest.of(page -1, 3);
            // check sort price
            if(productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()){
                String sort = productCriteriaDTO.getSort().get();
                if(sort.equals("gia-tang-dan")){
                    pageable =  PageRequest.of(page -1, 3,Sort.by(Product_.PRICE).ascending());
                }else if(sort.equals("gia-giam-dan")){
                    pageable =  PageRequest.of(page -1, 3,Sort.by(Product_.PRICE).descending());
                }else{
                    pageable =  PageRequest.of(page -1, 3);
                }
            }
           

            Page<Product> pageProducts = productService.fetchProductsWithSpec(pageable,productCriteriaDTO);
        
            List<Product> products = pageProducts.getContent().size() > 0?pageProducts.getContent():new ArrayList<>();
            String qs = request.getQueryString();
            if (StringUtils.isNotEmpty(qs)) {
                qs = qs.replace("page=" + page, "");
            }
            model.addAttribute("queryString", qs);
            model.addAttribute("products", products);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", pageProducts.getTotalPages());
        
        return "client/product/allProducts";
    }

}
