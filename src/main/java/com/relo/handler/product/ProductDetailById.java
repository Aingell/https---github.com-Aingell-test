package com.relo.handler.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relo.account.AccountService;
import com.relo.account.AccountVo;
import com.relo.auction.AuctionService;
import com.relo.auction.AuctionVo;
import com.relo.exception.FindException;
import com.relo.handler.Handler;
import com.relo.product.ProductService;
import com.relo.product.ProductVo;

public class ProductDetailById implements Handler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
      response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "http://192.168.0.42:5500");
		response.addHeader("Access-Control-Allow-Credentials", "true");//쿠키허용
      
      ProductService service = new ProductService();
      AuctionService aservice = new AuctionService();
      AccountService acService = new AccountService();
      Map m = new HashMap<>();
      
      HttpSession session = request.getSession(false);
//      session.setAttribute("loginId", "aaa");
      String id = (String) session.getAttribute("loginId");
      //String id = request.getParameter("id");
      m.put("id", id);
      
      int pNum = Integer.parseInt(request.getParameter("pNum"));
      
      int sHopePrice = Integer.parseInt(request.getParameter("sHopePrice"));
      m.put("sHopePrice", sHopePrice);
      ObjectMapper mapper = new ObjectMapper();
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      mapper.setDateFormat(dateFormat);
      try {
         AccountVo avo = acService.getByIdAccount(id);
         JSONArray arr = new JSONArray();
         arr.add(avo);
         AuctionVo auctionVo = aservice.maxPriceByPNum(pNum);
         if(auctionVo == null) {
            m.put("pNum", pNum);   
            int price = sHopePrice;
            m.put("price", price);
            ProductVo vo = service.getByIdProductDetail(m);
            arr.add(vo);
            
            String jsonStr = mapper.writeValueAsString(arr);
            return jsonStr;
         }else {
            int price = auctionVo.getAPrice();
            m.put("price", price);
            ProductVo vo = service.getByIdProductDetail(m);
            arr.add(vo);
//            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(arr);
            return jsonStr;
         }
         
      } catch (FindException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         Map<String, String> map = new HashMap<>();
         map.put("msg", e.getMessage());
//         ObjectMapper mapper = new ObjectMapper();
         String jsonStr = mapper.writeValueAsString(map);
         return jsonStr;
      }
   }

}