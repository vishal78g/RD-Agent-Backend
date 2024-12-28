package com.rd.app;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rd.app.entity.Account;

@SpringBootApplication
public class RdAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom Converter for String[] to Account
        Converter<String[], Account> stringArrayToAccountConverter = new Converter<String[], Account>() {
            @Override
            public Account convert(MappingContext<String[], Account> context) {
                String[] source = context.getSource();
                Account account = new Account();

                // Map fields
                account.setAccountNumber(Long.parseLong(source[0])); // Account Number
                account.setName(source[1]); // Name

                // Parse and set Amount (handle "Cr." suffix)
                String amountStr = source[2].replace(" Cr.", "");
                amountStr = amountStr.replace(",", "");
                account.setAmount(Double.parseDouble(amountStr));

                account.setMonthPaid(Integer.parseInt(source[3])); // Month Paid

                // Parse and set Date
                account.setNextInstallment((source[4]));
                
                // Default values
                account.setVillage(null); // Village
                account.setMobileNumber(0L); // Mobile Number

                return account;
            }
        };

        // Add the custom converter
        modelMapper.addConverter(stringArrayToAccountConverter);

        return modelMapper;
    }
}
