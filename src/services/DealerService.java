package services;

import com.sun.jdi.IntegerType;

import java.util.ArrayList;
import java.util.Random;
public class DealerService {

    private Random random=new Random();
    public ArrayList<ArrayList<Integer>> createNextCards(int cardSymbol,int cardNumber){
        ArrayList<ArrayList<Integer>> cardsGame=new ArrayList<ArrayList<Integer>>();
        for(int i=13*(cardSymbol-1)+(cardNumber-1);i<52;i++){
            if(i<13){
                ArrayList<Integer> card=new ArrayList<Integer>();
                card.add(1);
                card.add(i+1);
                cardsGame.add(card);
            }else if(i>=13 && i<26){
                ArrayList<Integer> card=new ArrayList<Integer>();
                card.add(2);
                card.add(i-12);
                cardsGame.add(card);
            }else if(i>=26 && i<39){
                ArrayList<Integer> card=new ArrayList<Integer>();
                card.add(3);
                card.add(i-25);
                cardsGame.add(card);
            }else{
                ArrayList<Integer> card=new ArrayList<Integer>();
                card.add(4);
                card.add(i-38);
                cardsGame.add(card);
            }

        }
        return cardsGame;
    }

    public ArrayList<Object> extraire_ieme_carte (ArrayList<ArrayList<Integer>> cardsList,int randomIndex){
        ArrayList<Integer> extractedCard=cardsList.get(randomIndex);
        cardsList.remove(randomIndex);
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
            cardsList=(ArrayList<ArrayList<Integer>>)result.get(1);
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
}
