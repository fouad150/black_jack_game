import java.util.ArrayList;
import java.util.List;
import services.DealerService;
import controllers.DealerController;
public class Main {

    public static void main(String[] args){

        DealerService dealerService=new DealerService();
        DealerController dealerController=new DealerController();

        ArrayList<ArrayList<Integer>> cardsList=dealerController.createNextCards(1,1);

        ArrayList<ArrayList<Integer>> result= dealerService.melanger_jeu_cartes(cardsList);
        System.out.println(result.size());
        result.forEach((x)->System.out.println(x));
        ArrayList<Object> result2=dealerService.piocherCartes(cardsList);
        System.out.println(result2);
        //System.out.println(result2.get(0));
        //System.out.println(result2.get(1));

    }



}
