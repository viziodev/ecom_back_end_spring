package ism.gestionsapprovisionnement.entities;

public enum EtatCommande {
    Encours,Terminer,Facturer,Payer;
    
    public static EtatCommande toEnum(int valOrdinal) {
        for (EtatCommande enumValue:values()){
            if(enumValue.ordinal()==valOrdinal) {
                return enumValue;
            }
        }
        return null;
    }
    
}
