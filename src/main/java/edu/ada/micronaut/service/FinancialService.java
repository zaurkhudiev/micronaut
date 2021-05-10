package edu.ada.micronaut.service;

import java.io.IOException;
import java.util.List;

public interface FinancialService {

    Object getFinancialData(String stock_index) throws IOException;

}
