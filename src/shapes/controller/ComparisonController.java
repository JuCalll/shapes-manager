package shapes.controller;

import shapes.interfaces.IDimensionable;

public class ComparisonController {

    public int compare(IDimensionable first, IDimensionable second){
        validateSameType(first,second);
        return Double.compare(first.calculateDimension(),second.calculateDimension());
    }

    private void validateSameType(IDimensionable first, IDimensionable second){
        if(!first.getClass().equals(second.getClass())){
            throw new IllegalArgumentException("Only shapes of the same type can be compared: " + first.getClass().getSimpleName()+ " vs " + second.getClass().getSimpleName());
        }
    }
}
