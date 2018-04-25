package entities.ingredients.chemicalIngredients;

import contracts.ChemicalIngredient;
import entities.ingredients.BasicIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient{

    @Column(name="chemical_formula")
    private String chemicalFormula;

    protected BasicChemicalIngredient(){
    }

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula){
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }


    @Override
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
