package controllers;

import java.util.ArrayList;
import services.DealerService;

public class DealerController {

    public ArrayList<ArrayList<Integer>> createNextCards(int cardSymbol, int cardNumber){
         DealerService dealerService=new DealerService();
         return dealerService.createNextCards(cardSymbol,cardNumber);
    }
}
