package controllers;

import java.util.ArrayList;
import java.util.List;


import services.DealerService;

public class DealerController {
    DealerService dealerService=new DealerService();

    public void compareSums(int playerSum,int dealerSum){
        if(playerSum>dealerSum){
            System.out.println("you won");
        }else if(playerSum<dealerSum){
            System.out.println("dealer won");
        }else{
            System.out.printf("result of the round is a draw");
        }
    }

    public ArrayList<ArrayList<Integer>> createNextCards(int cardSymbol, int cardNumber){
         return dealerService.createNextCards(cardSymbol,cardNumber);
    }

    public int stand(ArrayList<ArrayList<Integer>> playerList){

        int sum=0;
        int AcardsCount=0;
        //showCards(playerList);
        for(int i=0;i<playerList.size();i++){
            Integer cardValue=dealerService.cardValue(playerList.get(i).get(1));
            if(cardValue==1){
                cardValue=11;
                AcardsCount++;
            }
            sum+=cardValue;
        }

        if(AcardsCount==0){
            System.out.println("the sum is: "+sum);
        }else if(AcardsCount>0){
            if(sum-(AcardsCount*10)<11){ // 8+11+11
                sum=sum-((AcardsCount-1)*10);
                System.out.println("the sum is: "+sum);
            }else{
                sum=sum-(AcardsCount*10); // 18+11+11
                System.out.println("the sum is: "+sum);
            }
        }
        return sum;
    }

    public int dealerHit(ArrayList<ArrayList<Integer>> gameList,ArrayList<ArrayList<Integer>> dealerList){

        int sum=0;

        ArrayList<Integer> givenCard=dealerService.giveCard(gameList);
        if(givenCard!=null){

            dealerList.add(givenCard);

            int AcardsCount=0;
            for(int i=0;i<dealerList.size();i++){
                Integer cardValue=dealerService.cardValue(dealerList.get(i).get(1));
                if(cardValue==1){
                    cardValue=11;
                    AcardsCount++;
                }
                sum+=cardValue;
            }

            if(sum<17){
                //return sum
            }else if(sum>=17 && sum<=21){
                //return sum
            }else if(sum>21){
                if(AcardsCount==0){
                   //return sum , player won
                }else{
                    if(sum-(AcardsCount*10)==21 || sum-(AcardsCount*10)==11 ){
                        sum=21;
                    } else if(sum-(AcardsCount*10)>21){ //
                        sum=sum-(AcardsCount*10);
                       //player won
                    }else if(sum-(AcardsCount*10)>=17 && sum-(AcardsCount*10)<21){
                        sum=sum-(AcardsCount);
                    }else if(sum-(AcardsCount*10)>11 && sum-(AcardsCount*10)<17 ){ //12+11+11
                        sum=sum-(AcardsCount*10);
                    }else if(sum-(AcardsCount*10)<11){ //2+11+11=14
                        if(sum-((AcardsCount-1)*10)>=17){ // 6+11+11=18
                            sum=sum-((AcardsCount-1)*10);
                        }else if(sum-((AcardsCount-1)*10)<17){
                            sum=sum-((AcardsCount-1)*10);//2+11+11=14
                        }
                    }
                }


            }
            return sum;
        }else{
            //the sum always will be less than 17
            //the dealer lost
            return sum;
        }
    }

    public  void showCards(ArrayList<ArrayList<Integer>> cardsList){
        for (int i=0;i<cardsList.size();i++){
            String cardSymbol=dealerService.cardSymbol(cardsList.get(i).get(0));
            String cardLetter=dealerService.cardLetter(cardsList.get(i).get(1));
            System.out.println(cardSymbol+" "+cardLetter);
        }
    }
    public  void showPlayerCards(ArrayList<ArrayList<Integer>> playerList){
        System.out.println("your cards:");
        showCards(playerList);
    }
    public  void showDealerCards(ArrayList<ArrayList<Integer>> dealerList){
        System.out.println("dealer:");
        showCards(dealerList);
    }

    public int  hit(ArrayList<ArrayList<Integer>> gameList,ArrayList<ArrayList<Integer>> playerList){
        ArrayList<Integer> givenCard=dealerService.giveCard(gameList);
        int sum=0;
        if(givenCard!=null){
          playerList.add(givenCard);
          Integer AcardsCount=0;
          showPlayerCards(playerList);
          for(int i=0;i<playerList.size();i++){
              Integer cardValue=dealerService.cardValue(playerList.get(i).get(1));
              if(cardValue==1){
                  cardValue=11;
                  AcardsCount++;
              }
              sum+=cardValue;
          }
          if(sum==21){
              //your cards
              System.out.println("your sum is 21");
          }else if(sum<21){
              if(AcardsCount==1){
                  System.out.println("the sum is: "+sum);
                  System.out.println("or: "+(sum-10));
              }else{
                  //your cards
                  System.out.println("the sum is: "+sum);
              }

          }else if(sum>21){
              if(AcardsCount==0){
                  //y c
                  System.out.println("the sum is greater than 21");
              }else if(AcardsCount>0){

                  if(sum-(AcardsCount*10)==21 || sum-((AcardsCount-1)*10)==11){ //19+11+11 or 9+11+11
                      System.out.println("the sum is 21");
                      sum=21;
                  }else if(sum-(AcardsCount*10)<21){ //18+11+11
                      if(sum-(AcardsCount*10)<11){ //8+11+11
                          System.out.println("the sum is: "+(sum-((AcardsCount-1)*10)));
                          System.out.println("or: "+(sum-(AcardsCount*10)));
                          sum=(sum-((AcardsCount-1)*10));
                      }else{
                          System.out.println("the sum is: "+(sum-(AcardsCount*10)));
                          sum=(sum-(AcardsCount*10));
                      }

                  }else{
                      System.out.println("the sum is greater than 21: "+(sum-(AcardsCount*10)));
                      sum=(sum-(AcardsCount*10));
                  }

              }
          }
        }else{
            System.out.println("there is no card");
        }

        return sum;
    }

    public int giveFirstCards(ArrayList<ArrayList<Integer>> playerCards,ArrayList<ArrayList<Integer>> dealerCards){
        Integer playerCard1Value=dealerService.cardValue(playerCards.get(0).get(1));
        Integer playerCard2Value=dealerService.cardValue(playerCards.get(1).get(1));

        showDealerCards(dealerCards);
        showPlayerCards(playerCards);

        int playerSum=0;
        if(playerCard1Value==1 || playerCard2Value==1){
            if(playerCard1Value==playerCard2Value){
                System.out.println("the sum is: 2");
                System.out.println("or: 12");
                playerSum=12;// doesn't matter if 2 or 12
            }else if(playerCard1Value+playerCard2Value==11){
                System.out.println("the sum is black jack");
                playerSum=21;
            }else{
                Integer sum=playerCard1Value+playerCard2Value;
                System.out.println("the sum is: "+sum+" or "+(sum+10));
                playerSum=sum+10;
            }
        }else{
            Integer sum=playerCard1Value+playerCard2Value;
            System.out.println("the sum is: "+sum);
            playerSum=sum;
        }

        return playerSum;

    }



}
