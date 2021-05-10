package edu.ada.micronaut.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.ada.micronaut.service.FinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Singleton
public class YahooFinanceServiceImpl implements FinancialService {

    protected static final Logger logger = LoggerFactory.getLogger(YahooFinanceServiceImpl.class);


    @Override
    public Object getFinancialData(String stock_index) throws IOException {
        Stock stock = null;
        Stock stock1 = null;
        Stock stock2 = null;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal price1 = BigDecimal.ZERO;
        BigDecimal price2 = BigDecimal.ZERO;
        try {
            if (stock_index.equals("GOOGL,MSFT,AAPL") || stock_index.equals("MSFT,GOOGL,AAPL") || stock_index.equals("GOOGL,AAPL,MSFT") || stock_index.equals("AAPL,MSFT,GOOGL") || stock_index.equals("MSFT,AAPL,GOOGL") || stock_index.equals("AAPL,GOOGL,MSFT")) {
                stock = (Stock) YahooFinance.get("GOOGL");
                stock1 = YahooFinance.get("MSFT");
                stock2 = (Stock) YahooFinance.get("AAPL");
                price = stock.getQuote(true).getPrice();
                price1 = stock1.getQuote(true).getPrice();
                price2 = stock2.getQuote(true).getPrice();
                return price + "," + price1 + "," + price2;
            }
           else if (stock_index.equals("GOOGL,MSFT") || stock_index.equals("MSFT,GOOGL") || stock_index.equals("AAPL,GOOGL") || stock_index.equals("GOOGL,AAPL") || stock_index.equals("MSFT,AAPL") || stock_index.equals("AAPL,MSFT")) {
               if (stock_index.equals("GOOGL,MSFT") || stock_index.equals("MSFT,GOOGL")) {
                   stock = (Stock) YahooFinance.get("GOOGL");
                   stock1 = YahooFinance.get("MSFT");
                   price = stock.getQuote(true).getPrice();
                   price1 = stock1.getQuote(true).getPrice();
                   return price + "," + price1;
               }  if (stock_index.equals("AAPL,GOOGL") || stock_index.equals("GOOGL,AAPL")) {
                    stock = (Stock) YahooFinance.get("GOOGL");
                    stock1 = YahooFinance.get("AAPL");
                    price = stock.getQuote(true).getPrice();
                    price1 = stock1.getQuote(true).getPrice();
                    return price + "," + price1;
                }  if (stock_index.equals("AAPL,MSFT") || stock_index.equals("MSFT,AAPL")) {
                    stock = (Stock) YahooFinance.get("MSFT");
                    stock1 = YahooFinance.get("AAPL");
                    price = stock.getQuote(true).getPrice();
                    price1 = stock1.getQuote(true).getPrice();
                    return price + "," + price1;
                }

            }
           else if (stock_index==stock_index) {
                stock = (Stock) YahooFinance.get(stock_index);
                price = stock.getQuote(true).getPrice();
                return price;
            }



        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return price;
    }

}


