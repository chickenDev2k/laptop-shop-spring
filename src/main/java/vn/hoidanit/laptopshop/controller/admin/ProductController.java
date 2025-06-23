package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;


@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    
    public ProductController(ProductService productService,UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }
    

    //*** create   ****/

    @GetMapping("/admin/product")
    public String getDashboard(Model model,@RequestParam(name="page",required = false,defaultValue = "1") Optional<String> pageOptional)
    {
        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());

            }
        }catch(Exception e){
                page = 1;
        }
       
        Pageable pageable = PageRequest.of(page-1, 10);   
              // to get all product without filter like %%
        Page<Product> productsPage = this.productService.getAllProduct(pageable); 
        List<Product> products = productsPage.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("products", products);
    
        
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct",new Product());
        return "admin/product/create";
    }


    @PostMapping("/admin/product/create")
    public String createProduct(
         Model model,
        @ModelAttribute("newProduct") @Valid Product product,
        BindingResult newProductBindingResult ,  
        @RequestParam("productImgFile") MultipartFile file)
    {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        //validate no redirect because data will lost
        if(newProductBindingResult.hasErrors()){
            return "admin/product/create";
        }
        //
        String productImage = this.uploadService.handleSaveUploadFile(file,"product");
        model.addAttribute("newProduct", productImage);
        product.setImage(productImage);
        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

    /****      update         *****/
    @GetMapping("/admin/product/update/{id}")
    public String getUpdatePage(Model model,@PathVariable long id){
        model.addAttribute("id", id);
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentProduct);
         model.addAttribute("productImg",("/images/product/"+currentProduct.getImage()));

        return "admin/product/update";
    }
    @PostMapping("/admin/product/update")
    public String updateProduct(Model model,@ModelAttribute("newProduct") Product product,BindingResult newUserBindingResult ,@RequestParam("productImgFile") MultipartFile file){

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        //validate no redirect because data will lost
        if(newUserBindingResult.hasErrors()){
            return "admin/product/update";
        }
        Product checkProduct = this.productService.getProductById(product.getId());
        
       
        System.out.println(product.getId());
        if(checkProduct != null){
            if(!file.isEmpty()){
                String productImg = this.uploadService.handleSaveUploadFile(file, "product");
                checkProduct.setImage(productImg);
            }else{
                checkProduct.setImage(product.getImage());
            }
           
           
            checkProduct.setName(product.getName());
            checkProduct.setPrice(product.getPrice());
            checkProduct.setQuantity(product.getQuantity());
            checkProduct.setShortDesc(product.getShortDesc());
            checkProduct.setDetailDesc(product.getDetailDesc());
            checkProduct.setFactory(product.getFactory());
            checkProduct.setTarget(product.getTarget());
            
    
            productService.handleSaveProduct(checkProduct);
        }
        
        return "redirect:/admin/product";
    }
    

      /****      show detail product         *****/

      @GetMapping("/admin/product/{id}")
      public String showDetailProduct(@PathVariable long id,Model model) {
            model.addAttribute("id", id);
            Product productDetail = this.productService.getProductById(id);
            model.addAttribute("productDetail", productDetail);
            if(productDetail.getImage() != ""){
                model.addAttribute("urlImage", productDetail.getImage());
            }

          return "admin/product/productDetail";
      }

       /****      delete  product         *****/
    @GetMapping("/admin/product/delete/{id}")
    public String getDeletePage(@PathVariable long id,Model model) {
        Product productDelete = this.productService.getProductById(id);
        model.addAttribute("productDelete", productDelete);
        return "admin/product/productDelete";
    }


      
       @PostMapping("/admin/product/delete")
       public String deleteProduct(@ModelAttribute("productDelete") Product product){
        if(this.productService.getProductById(product.getId()) != null){
            this.productService.deleteProduct(product.getId());
        }

        return "redirect:/admin/product";
       }
}
