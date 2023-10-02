import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import services.DealerService;
import controllers.DealerController;
public class Main {

    public static void main(String[] args) {

        DealerService dealerService = new DealerService();
        DealerController dealerController = new DealerController();
        Scanner scanner = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> cardsList = dealerController.createNextCards(1, 1);
        //cardsList.forEach(card->System.out.println(card));

        //first while
        int piocher = 1;
        while (piocher == 1) {
            //System.out.println("--------cardsList: "+cardsList);
            //System.out.println(cardsList.size());
            ArrayList<ArrayList<Integer>> result1 = dealerService.melanger_jeu_cartes(cardsList);
            //result1.forEach((x) -> System.out.println(x));
            ArrayList<Object> result2 = dealerService.piocherCartes(result1);
            ArrayList<ArrayList<Integer>> piocheList = (ArrayList<ArrayList<Integer>>) result2.get(0);
            ArrayList<ArrayList<Integer>> gameList = (ArrayList<ArrayList<Integer>>) result2.get(1);
            System.out.println(gameList.size());

            ArrayList<ArrayList<Integer>> allPlayerCards = new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Integer>> allDealerCards = new ArrayList<ArrayList<Integer>>();


            int repeat = 1;
            do {
                if (gameList.size() >= 3) {
                    List<Object> firstCards = dealerService.giveFirstCards(gameList);
                    System.out.println(gameList.size());
                    ArrayList<ArrayList<Integer>> playerCards = (ArrayList<ArrayList<Integer>>) firstCards.get(1);
                    ArrayList<ArrayList<Integer>> dealerCards = (ArrayList<ArrayList<Integer>>) firstCards.get(0);

                    int playerSum = dealerController.giveFirstCards(playerCards, dealerCards);
                    int stand = 0;
                    while (stand == 0 && playerSum < 21) {
                        System.out.println("enter 1 to hit:");
                        System.out.println("enter 2 to stand:");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            int sum = dealerController.hit(gameList, playerCards);
                            if (sum != 0) {
                                playerSum = sum;
                            } else {
                                stand++;
                            }
                        } else if (choice == 2) {
                            playerSum = dealerController.stand(playerCards);
                            stand++;
                        }
                    }

                    if (playerSum <= 21) {
                        int result = 0;
                        while (result == 0) {
                            int dealerSum = dealerController.dealerHit(gameList, dealerCards);
                            if (dealerSum == 0) {
                                System.out.println();
                                System.out.println("no remaining cards for dealer so you won");//player won
                                result++;
                            } else if (dealerSum > 21) {
                                System.out.println();
                                dealerController.showDealerCards(dealerCards);
                                System.out.println("dealer sum: " + dealerSum);
                                System.out.println("you won");
                                result++;
                            } else {
                                if (dealerSum >= 17) {
                                    System.out.println();
                                    dealerController.showDealerCards(dealerCards);
                                    System.out.println("dealer sum: " + dealerSum);
                                    dealerController.compareSums(playerSum, dealerSum);
                                    result++;
                                } else {
                                    //
                                }
                            }
                        }

                    } else {
                        System.out.println("you lost");
                    }

                    allPlayerCards.addAll(playerCards);
                    allDealerCards.addAll(dealerCards);

                    System.out.println("enter 1 to play other round:");
                    System.out.println("enter 0 to stop:");
                    repeat = scanner.nextInt();
                } else {
                    //defausser
                    //repeat=0
                    ArrayList<ArrayList<Integer>> dealerPlayerCards = new ArrayList<ArrayList<Integer>>();
                    dealerPlayerCards.addAll(allPlayerCards);
                    dealerPlayerCards.addAll(allDealerCards);
                    dealerPlayerCards.addAll(gameList);
                    cardsList = dealerService.defausser_cartes(piocheList, dealerPlayerCards);
                    repeat = 0;
                    //System.out.println("--------------------------");
                }


            } while (repeat == 1);


        }
    }


}
