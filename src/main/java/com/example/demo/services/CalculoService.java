package com.example.demo.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.InputModel;
import com.example.demo.models.LoggerModel;
import com.example.demo.models.OutputModel;
import com.example.demo.models.ResponseVO;
import com.example.demo.repositories.CalculoRepository;
import com.google.gson.Gson;

@Service
public class CalculoService {
  
    @Autowired
    CalculoRepository calculoRepository;
      
    Gson gson = new Gson();
    
    public ResponseVO calcularplazo(InputModel input){
    	 
    	ResponseVO response = new ResponseVO();
	    List<OutputModel> outList = new ArrayList<OutputModel>();
	    LoggerModel toSave = new LoggerModel();
    	
    	//validar
    	if(null == input.getAmount() || input.getAmount() == 0) {
    		 response.setCode(500);
    	     response.setMessage("El monto debe ser mayor a 0.");
    	     return response;
    	}
		if(null == input.getRate() || input.getRate() < 0) {
			response.setCode(500);
   	     	response.setMessage("La tasa debe ser mayor o igual a 0.");	
   	     	return response;
		}
		if(null == input.getTerms() || input.getTerms() == 0) {
			response.setCode(500);
   	     	response.setMessage("El plazo debe ser mayor a 0.");
   	     	return response;
		}
    		
    	//fechas mamalonas
    	Date now = new Date();
    	Date paymentDate = new Date();
    	LocalDate localDate = now.toInstant().atZone(ZoneId.of("America/Mexico_City")).toLocalDate();
        ZoneId defaultZoneId = ZoneId.of("America/Mexico_City");
        
        Double pago = 0.0d;
        DecimalFormat df = new DecimalFormat("####.##");
  
        if(input.getRate() == 0) {
        	//calculo con tasa 0
        	pago = input.getAmount() / input.getTerms();
        }
        else {
	        //calculo con tasa mayor a 0
	        Double r = input.getRate() / 100;
	        pago = r * input.getAmount() / (1-(Math.pow(1+r,-1*input.getTerms())));
        }
        
        for(int i=1;i<=(input.getTerms()*4);i++) {	
        	OutputModel out = new OutputModel();
        	out.setAmount(Double.parseDouble(df.format(pago/4)));
        	out.setPayment_number(i); 
        	localDate = paymentDate.toInstant().atZone(ZoneId.of("America/Mexico_City")).toLocalDate();
        	paymentDate = Date.from(localDate.plusDays(7).atStartOfDay(defaultZoneId).toInstant());
        	out.setPayment_date(paymentDate);
        	outList.add(out);
        }     	
        
        try {
	        toSave.setInput(gson.toJson(input));
	        toSave.setOutput(gson.toJson(outList));
	        calculoRepository.save(toSave);
        }
        catch(Exception e) {
        	response.setCode(500);
            response.setMessage("Error de escritura.");
            return response;
        }
       
        response.setCode(200);
        response.setMessage("OK");
        response.setOutput(outList);
        
        return response;
    }
    
    public List<LoggerModel> obtenerLogger(){
        return (List<LoggerModel>) calculoRepository.findAll();
    }
}   
