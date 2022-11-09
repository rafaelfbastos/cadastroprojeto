package app;

import models.AlunoModel;

import java.util.ArrayList;

public class Controller {

    private static Controller singleton;
    private ArrayList<AlunoModel> equipe;

    private Controller(){
        equipe = new ArrayList<>();
    }

    public static Controller getInstance(){
        if(singleton==null){
            singleton = new Controller();
        }
        return singleton;
    }

    public ArrayList<AlunoModel> getEquipe(){
        return equipe;
    }

    public void addEquipe(AlunoModel alunoModel){
        if(!equipe.contains(alunoModel)) equipe.add(alunoModel);
    }

}
