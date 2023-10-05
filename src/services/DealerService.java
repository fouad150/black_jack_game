package services;

import com.sun.jdi.IntegerType;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class DealerService {

    private Random random=new Random();
    public ArrayList<ArrayList<Integer>> createNextCards(int cardSymbol,int cardNumber){
        ArrayList<ArrayList<Integer>> cardsGame=new ArrayList<ArrayList<Integer>>();
        for(int i=cardSymbol;i<=4;i++){
            for(int j=cardNumber;j<=13;j++){
                ArrayList<Integer> card=new ArrayList<Integer>();
                card.add(i);
                card.add(j);
                cardsGame.add(card);
            }
            cardNumber=1;
        }
        return cardsGame;

    }

    public ArrayList<Object> extraire_ieme_carte (ArrayList<ArrayList<Integer>> cardsList,int randomIndex){
        ArrayList<Integer> extractedCard=cardsList.remove(randomIndex);
        ArrayList<Object> result=new ArrayList<Object>();
        result.add(extractedCard);
        result.add(cardsList);
        return result;
    }

    public ArrayList<Object>  tirer_une_carte(ArrayList<ArrayList<Integer>> cardsList){
        int randomIndex= random.nextInt(cardsList.size());
        ArrayList<Object> result =extraire_ieme_carte(cardsList,randomIndex);
        return result;
    }

    public  ArrayList<ArrayList<Integer>>  melanger_jeu_cartes(ArrayList<ArrayList<Integer>> cardsList){
        ArrayList<ArrayList<Integer>> shuffledList=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<52;i++){
            ArrayList<Object> result =tirer_une_carte(cardsList);
            ArrayList<Integer> extractedCard=(ArrayList<Integer>)result.get(0);
            shuffledList.add(0,extractedCard);
            //cardsList=(ArrayList<ArrayList<Integer>>)result.get(1);
        }
        return shuffledList;
    }

    public ArrayList<Object> piocherCartes(ArrayList<ArrayList<Integer>> shuffledList){
        int randomNumber=random.nextInt(13)+20;//min is 20 max is 32
        ArrayList<ArrayList<Integer>> piocheList=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<randomNumber;i++){
            piocheList.add(shuffledList.get(i));
        }
        ArrayList<ArrayList<Integer>> gameList=new ArrayList<ArrayList<Integer>>();
        for (int i=randomNumber;i<52;i++){
            gameList.add(shuffledList.get(i));
        }

        ArrayList<Object> result=new ArrayList<Object>();
        result.add(piocheList);
        result.add(gameList);
        return result;
    }

    public ArrayList<ArrayList<Integer>> defausser_cartes(ArrayList<ArrayList<Integer>> piocheCards, ArrayList<ArrayList<Integer>> playerDealerCards ){
        playerDealerCards.addAll(piocheCards);
        return playerDealerCards;
    }

    public List<Object> giveFirstCards(ArrayList<ArrayList<Integer>> gameList){

        ArrayList<ArrayList<Integer>> playerCards=new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> dealerCards=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<3;i++){
            if(i<2){
                int randomIndex = random.nextInt(gameList.size());
                playerCards.add(gameList.remove(randomIndex));
            }else{
                int randomIndex = random.nextInt(gameList.size());
                dealerCards.add(gameList.remove(randomIndex));
            }

        }

        return List.of(dealerCards,playerCards);
    }

    public Integer cardValue(Integer cardNumber){
        if(cardNumber==11 || cardNumber==12 || cardNumber==13){
            return 10;
        }
        return cardNumber;
    }

    public String cardLetter(Integer cardNumber){
        switch(cardNumber){
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return String.valueOf(cardNumber);
    }

    public String cardSymbol(Integer symbolNumber){
        switch(symbolNumber){
            case 1:
                return "\u2764";//heart
            case 2:
                return "\u2666";//diamond
            case 3:
                return "\u2660";//spade
            case 4:
                return "\u2663";//club
        }
        return "";
    }

    public ArrayList<Integer> giveCard(ArrayList<ArrayList<Integer>> gameList){
        if(!gameList.isEmpty()){
            int cardIndex=(gameList.size())-1;
            ArrayList<Integer> card= gameList.remove(cardIndex);
            return card;
        }
        return null;
    }

}
