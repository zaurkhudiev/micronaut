package edu.ada.micronaut.controller;

import java.io.IOException;
import java.util.List;

public interface FinancialController {

    Object getFinancialData(String financial_data_provider, String stock_index) throws IOException;

}
