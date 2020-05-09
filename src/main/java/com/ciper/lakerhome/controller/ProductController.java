package com.ciper.lakerhome.controller;

import com.ciper.lakerhome.entity.Product;
import com.ciper.lakerhome.mapper.ProductMapper;
import com.ciper.lakerhome.mapper.ShoppingcartMapper;
import com.ciper.lakerhome.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {
    final ProductService productService;
    final ProductMapper productMapper;
    final ShoppingcartMapper shoppingcartMapper;

    @Autowired
    public ProductController(ProductMapper productMapper, ShoppingcartMapper shoppingcartMapper ,ProductService productService){
        this.productService = productService;
        this.productMapper = productMapper;
        this.shoppingcartMapper = shoppingcartMapper;
    }

    //首页——查询所有商品
    @GetMapping("show_all_products")
    public String show_all_products(Model model){
        List<Product> product_list = productMapper.selectAll();
        model.addAttribute(product_list);
        return "products";
    }

    //首页——商品名称模糊查询
    @PostMapping("show_select_products")
    public String show_select_products(HttpServletRequest request, Model model){
        String name = request.getParameter("product_name");
        List<Product> product_list = productMapper.selectByName(name);
        model.addAttribute(product_list);
        return "products";
    }

    //账户登录之后
    //作为卖家——查看自己的商品
    @GetMapping("user_sell.html")
    public String user_sell(){
        return "user_sell";
    }

    @GetMapping("owner_show_products")
    public String owner_show_products(HttpSession session, Model model){
        String user_email = session.getAttribute("user_email").toString();
        List<Product> product_list = productMapper.selectByOwner(user_email);
        model.addAttribute(product_list);
        return "user_sell";
    }

    //作为卖家——删除商品
    @GetMapping("owner_delete_products/{id}")
    public String delete_products(@PathVariable("id") Integer id){
        productMapper.deleteByPrimaryKey(id);
        return "user_sell";
    }

    //作为卖家——添加商品
    @GetMapping("owner_add_products.html")
    public String owner_add_products(){
        return "owner_add_products";
    }

    @PostMapping("owner_add_products")
    public String owner_add_products(HttpServletRequest request){
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        Integer price = Integer.valueOf(request.getParameter("price"));
        String owner = session.getAttribute("user_email").toString();
        String place = request.getParameter("place");
        productMapper.insert(name, brand, color, price, owner, place);
        return "user_sell";
    }


    //作为买家——商品显示
    @GetMapping("user_buy.html")
    public String user_buy(){
        return "redirect:/buy_show_products";
    }

    @GetMapping("buy_show_products")
    public String buy_show_products(Model model){
        List<Product> product_list = productMapper.selectAll();
        model.addAttribute(product_list);
        return "user_buy";
    }

    //作为买家——商品名称模糊查询
    @PostMapping("buy_show_select_products")
    public String buy_show_select_products(HttpServletRequest request, Model model){
        String name = request.getParameter("product_name");
        List<Product> product_list = productMapper.selectByName(name);
        model.addAttribute(product_list);
        return "user_buy";
    }

    //作为买家——加入购物车
    @GetMapping("add_to_cart/{id}")
    public String add_to_cart(HttpSession session, @PathVariable("id") Integer id){
        //改变商品交易状态
        productMapper.updateDealStateByProductId(id, 1);
        //插入购物车表
        String user_id = session.getAttribute("user_email").toString();
        shoppingcartMapper.insert(user_id, id);
        return "redirect:/buy_show_products";
    }

    //购物车展示
    @GetMapping("show_shopping_cart")
    public String show_shopping_cart(Model model, HttpSession session){
        String user_id = session.getAttribute("user_email").toString();
        List<Product> product_list = productMapper.selectShoppingCart(user_id);
        model.addAttribute(product_list);
        return "shopping_cart";
    }

    //删除购物车
    @GetMapping("delete_from_cart/{id}")
    public String delete_from_cart(@PathVariable("id") Integer id){
        //改变商品交易状态
        productMapper.updateDealStateByProductId(id, 0);
        //从购物车中删除该商品
        shoppingcartMapper.deleteByProductId(id);
        return "redirect:/show_shopping_cart";
    }

    //结账
    @GetMapping("cart_checkout")
    public String cart_checkout(Model model, HttpSession session){
        int sum= 0;
        String user_email = session.getAttribute("user_email").toString();
        List<Product> price_list = productMapper.cartCheckout(user_email);
        for (Product product : price_list) {
            sum = sum + product.getPrice();
            productMapper.updateDealStateByProductId(product.getId(), 2);
        }
        model.addAttribute("price",sum);
        return "cart_checkout";
    }
}
