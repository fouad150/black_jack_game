import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import services.DealerService;
import controllers.DealerController;
public class Main {

    public static void main(String[] args){

        System.out.println("\u2764");

        DealerService dealerService=new DealerService();
        DealerController dealerController=new DealerController();

        ArrayList<ArrayList<Integer>> cardsList=dealerController.createNextCards(1,1);

        ArrayList<ArrayList<Integer>> result= dealerService.melanger_jeu_cartes(cardsList);
        //result.forEach((x)->System.out.println(x));

        ArrayList<Object> result2=dealerService.piocherCartes(result);
        System.out.println(result2.get(0));
        System.out.println(result2.get(1));

        System.out.println(dealerService.defausser_cartes((ArrayList<ArrayList<Integer>>) result2.get(0),(ArrayList<ArrayList<Integer>>) result2.get(1)));

    }



}
